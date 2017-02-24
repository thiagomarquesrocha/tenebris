package control.work;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import control.ConnectionSingleton;
import control.GenericJSON;
import model.Obra;

public class ObraDao {
    
    
    /**
     * Insert a work into "obra" table
     * @param type int
     * @param instituicao int
     * @param area int
     * @param autor String
     * @param titulo String
     * @param data String
     * @param resumo String
     * @param imagem String
     * @param usuario int
     * @throws SQLException Could not possible insert this data into table
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public static long insereObra(int type, String instituicao, String area, String autor, String titulo, String data, String resumo, String imagem, String usuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
    	  Connection conn = ConnectionSingleton.getInstance().getConnection();
		  
		  String sql = "INSERT INTO obra (tipo, instituicao, area, autor, titulo, data, resumo, imagem, usuario) VALUES("+type+","+instituicao+","+area+","+autor+",'"+titulo+"','"+data+"','"+resumo+"',?,"+usuario+");";
		  
		  System.out.println("SQL :" + sql);
		  
		  PreparedStatement st = conn.prepareStatement(sql, WorkDao.COLUMMN_ID);
		  
		  st.setString(1, imagem);
		  
		  st.execute();
		  
		  long id = 0;
		  try {
				id = GenericJSON.getField("id", st);
		  } catch (Exception e) {
				e.printStackTrace();
		  }
		  
		  return id;
	}
    
    public static void atualizaObra(long id, int type, String instituicao, String area, String autor, String titulo, String data, String resumo, String imagem) throws SQLException {
    	
    	 try {
    	
    	  Connection conn = ConnectionSingleton.getInstance().getConnection();
   		    
   		  String sql = "UPDATE obra SET tipo="+type+",instituicao="+instituicao+",area="+area+",titulo=?,data=?,resumo=?,imagem=? WHERE id = "+id;
		
   		  System.out.println(sql);
   		  
   		  PreparedStatement st = conn.prepareStatement(sql);
   		  
   		  // Set parameters
   		  st.setString(1, titulo);
   		  st.setString(2, data);
   		  st.setString(3, resumo);
   		  st.setString(4, imagem);
   		  
		  st.executeUpdate();
		  
		  st.close();
		}catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
    
  }
    
public static void excluiObra(long id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
	     try {
	    	 Connection conn = ConnectionSingleton.getInstance().getConnection();
	   		 Statement st = conn.createStatement();

	 		String sql = "DELETE FROM obra WHERE id = "+id+";";
	 		st.executeUpdate(sql);

	     } catch (SQLException e) {

	         throw new RuntimeException(e);

	     }		
		  
	}

public static List<Obra> listaObra(long usuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	
	List<Obra> list = null;
	
    try {
   	      Connection conn = ConnectionSingleton.getInstance().getConnection();

    	  list = new ArrayList<>();
  		  
    	  /** Outra maneira de consultar as obras do usuario
			* SELECT * FROM (SELECT
			        	a.id,
			            a.instituicao,
			            a.area AS areaId,
			            a.autor,
			            a.titulo,
			            a.data,
			            a.resumo,
			            a.usuario,
			            a.imagem AS path,
			            '' as avaliacao,
			            '' as obra,
			            '' as media
			    FROM
			        obra a, area b, autor c
			    WHERE a.usuario = 2 AND a.area = b.id AND a.autor = c.id ORDER BY a.data DESC) q1
			    
			UNION
			
			SELECT 
			    *
			FROM
				(SELECT 
			        	a.id,
			            a.instituicao,
			            a.area AS areaId,
			            a.autor,
			            a.titulo,
			            a.data,
			            a.resumo,
			            a.usuario,
			            a.imagem AS path,
			            b.avaliacao
			    FROM
			        obra a,  avaliacao b,  area c, autor d
			    WHERE a.id = b.obra AND a.usuario = b.usuario AND a.usuario = 2 AND a.area = c.id AND a.autor = d.id ORDER BY a.data DESC) a
			        LEFT OUTER JOIN
			    (SELECT 
			        obra, AVG(avaliacao) AS media
			    FROM
			        avaliacao
			    GROUP BY obra) b ON a.id = b.obra
			GROUP BY a.id
			ORDER BY data DESC
			LIMIT 0, 100
			Abaixo esta sendo usado uma maneira diferente
			*/
		  String sql = String.format("SELECT * FROM (SELECT a.*, b.nome area, c.nome as autorNome FROM (SELECT a.id, a.instituicao, a.area as areaId, a.autor, a.titulo, a.data, a.resumo, a.usuario, a.imagem as path, b.avaliacao from obra a LEFT OUTER JOIN avaliacao b ON a.id = b.obra AND a.usuario = b.usuario WHERE a.usuario=?) a, area b, autor c WHERE a.areaId = b.id AND a.autor = c.id ORDER BY a.data DESC) a LEFT OUTER JOIN (SELECT obra, avg(avaliacao) as media FROM avaliacao GROUP BY obra) b ON a.id = b.obra GROUP BY a.id ORDER BY a.data DESC LIMIT 0, 50");
		  
		  //System.out.println(sql);
		  
		  PreparedStatement stmt = conn.prepareStatement(sql);
		  
		  // Seta o usuario
		  stmt.setLong(1, usuario);
		  
		  ResultSet rs = stmt.executeQuery();
		  
		  while (rs.next()) {
			  
			  Obra obra = new Obra();
			  long obraId = rs.getLong("id");
			  String instituicao = rs.getString("instituicao");
			  String area = rs.getString("area");
			  String autor = rs.getString("autorNome");
			  String titulo = rs.getString("titulo");
			  String resumo = rs.getString("resumo");
			  String data = rs.getString("data");
			  Integer avaliacao = rs.getInt("avaliacao");
			  String file = rs.getString("path");
			  Float media = rs.getFloat("media");
			  
			  obra.setid(obraId);
			  obra.setinstituicao(instituicao);
			  obra.setarea(area);
			  obra.setautor(autor);
			  obra.settitulo(titulo);
			  obra.setresumo(resumo);
			  obra.setdata(data);
			  obra.setAvaliacao(avaliacao);
			  obra.setFile(file);
			  obra.setMedia(media);
			  list.add(obra);
		  }
		  
		  stmt.close();
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
    
    	return list;
    }
    
}