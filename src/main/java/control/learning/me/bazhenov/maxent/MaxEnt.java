package control.learning.me.bazhenov.maxent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import control.factory.ConnectionFactory;
import control.learning.GSON;
import control.learning.ObraRB;
import model.Obra;

import com.google.gson.Gson;
import opennlp.maxent.GIS;
import opennlp.maxent.GISModel;
import java.sql.Connection;
import java.sql.ResultSet;

public class MaxEnt {

	public static ClasseAuxiliar obrasFormacao(int UserID, Statement stmt) throws SQLException{ 
		
		ArrayList obrasTotais = new ArrayList();
    	
    	String sql = String.format("SELECT obra.id FROM obra");
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
            obrasTotais.add(rs.getInt("ID"));
            
        }
    	
		ArrayList obrasAvaliadas = new ArrayList();
		
		sql = String.format("SELECT * FROM avaliacao WHERE usuario=%s", UserID);
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
            obrasAvaliadas.add(rs.getInt("OBRA"));
            
        }
		
		ArrayList obrasRestantes = new ArrayList();
		boolean aux = true;
		for (Object obrarel : obrasTotais){
			for(Object obraaval : obrasAvaliadas){
				if(obrarel == obraaval){
					aux = false;
				}			
			}
			
			if(aux == true){
				obrasRestantes.add((int)(obrarel));
				//System.out.println(obrarel);
			}
			
			aux = true;
		}

		
		sql = String.format("SELECT * FROM redebayesiana WHERE idUsuario = %s", UserID);
		rs = stmt.executeQuery(sql);
        
		List<ObraRB> obras = new ArrayList<ObraRB>();
		
		int item;
        int usuario;
        int relevancia;
        String titulo = null;
        int pchave;
        
        while (rs.next()) {
        	usuario = rs.getInt("IDUSUARIO");
        	item = rs.getInt("IDOBRA");
        	relevancia = rs.getInt("RELEVANCIA");
            titulo = rs.getString("TITULO");
            pchave = rs.getInt("PCHAVE");
            
            ObraRB obra = new ObraRB(item, usuario, relevancia, titulo, pchave);
            obras.add(obra);
        }
        
        for(ObraRB obra : obras){
        	sql = String.format("SELECT * FROM idpalavrachave WHERE idpchave = %s", obra.getPchave());
        	rs = stmt.executeQuery(sql);
        	while(rs.next()){
        		String pcstring = rs.getString("PALAVRACHAVE");
        		obra.setPcstring(pcstring);
        	}
        }
        
        ClasseAuxiliar ca = new ClasseAuxiliar();
        ca.setObras(obras);
        ca.setObrasRestantes(obrasRestantes);
        
        return ca;
		
	}
	
	public GISModel modeloMaxEnt(List<ObraRB> obras) throws IOException{
		
		Collection<LearningSample> modelo = new ArrayList<LearningSample>();
		
		for(ObraRB obra : obras){
			String palavra = obra.getTitulo() + " " + obra.getPcstring();
			System.out.println(palavra);
        	if(obra.getRelevancia() > 0){
            	modelo.add(new LearningSample("Relevante", palavra));
            	} else{
            	modelo.add(new LearningSample("Irrelevante", palavra));
        	}
		}
		
		PlainTextEventStream stream = new PlainTextEventStream(modelo);
		
		try{
			GISModel model = GIS.trainModel(stream, 100, 1, false, false);
			return model;
		} catch(java.lang.NullPointerException nll){
			return null;
		}
		
		
	}
	
	public List<Obra> modeloParaResposta(GISModel model, List obrasRestantes, Statement stmt, Connection conn) throws SQLException{
		
		List<Obra> list = new ArrayList<>();
		
        String dataJSON =  "{\"data\":[";
        for(Object obrarest : obrasRestantes){
			int ItemID = (int)obrarest;
			//System.out.println(ItemID);
        
			String sql = String.format("SELECT * FROM obra where id=%s", ItemID);
			ResultSet rs = stmt.executeQuery(sql);
			
	        String titulo = null;
	        int pchave;
			
			List<ObraRB> artigo = new ArrayList<ObraRB>(); 
			while (rs.next()) {
				titulo = rs.getString("TITULO");
				//System.out.println(titulo);
			}
		
			sql = String.format("SELECT * FROM obra_palavrachave where obra=%s", ItemID);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				
				pchave = rs.getInt("PALAVRACHAVE");
				//System.out.println(pchave);
				ObraRB obra = new ObraRB(titulo, pchave);
				artigo.add(obra);
			}

			String palavraschaves = "";
			for(ObraRB art : artigo){
				sql = String.format("SELECT * FROM idpalavrachave WHERE idpchave = %s", art.getPchave()); // Resgate da palavra-chave. Similar ao processo de resgate para constru��o do modelo de aprendizagem.
				rs = stmt.executeQuery(sql);
				while(rs.next()){
					String pcstring = rs.getString("PALAVRACHAVE");
					art.setPcstring(pcstring);
					palavraschaves = palavraschaves + " " + pcstring;
					//System.out.println(palavraschaves);
				}
			}
			
			try{
				try{
					String aux = artigo.get(0).getTitulo() + "" + palavraschaves;
					System.out.println(aux);
					String[] input = (aux).split("\\s");
					System.out.println("MAX ENTROP AQUI");
					double[] result = model.eval(input);
					//System.out.println(artigo.get(0).getTitulo() + "" + palavraschaves);
					String bestOutcome = model.getBestOutcome(result);
					if(bestOutcome == "Relevante"){
						GSON auxiliar = new GSON();
						Gson lopes = new Gson();
						model.Obra obra = auxiliar.ConstrucaoJSON(ItemID, conn);
						list.add(obra);
						String json = lopes.toJson(obra);
						dataJSON += json;
					} else{
						//System.out.println(artigo.get(0).getTitulo() + "" + palavraschaves);
					}
					
				} catch (java.lang.NullPointerException nll) {
					//System.out.println("Usuario não consta na tabela redebayesiana");
				}
			}catch (java.lang.IndexOutOfBoundsException i){
				//System.out.println("Não foram registradas palava-chaves para a obra de ID: " + ItemID);
			}
        }
        
        dataJSON += "]}";
        
        System.out.println(dataJSON);
		
		return list;
		
	}

	
	public List<Obra> Metodo(int UserID) throws InstantiationException, 
	IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(ConnectionFactory.USER);
    	dataSource.setPassword(ConnectionFactory.PASSWORD);
    	dataSource.setServerName(ConnectionFactory.HOST_NAME);
    	dataSource.setDatabaseName(ConnectionFactory.BD);
    	
    	Connection conn = dataSource.getConnection();
    	Statement stmt = (Statement) conn.createStatement();
    	
    	ClasseAuxiliar ca = obrasFormacao(UserID, stmt);
    	
    	List<ObraRB> obras = ca.getObras();
    	
    	List obrasRestantes = ca.getObrasR();
    	
    	if (modeloMaxEnt(obras) != null){
    		GISModel modelo = modeloMaxEnt(obras);
    	
    		List<Obra> list = modeloParaResposta(modelo, obrasRestantes, stmt, conn);
    		
    		return list;
    	} else{
    		return null;
    	}
	}

}

