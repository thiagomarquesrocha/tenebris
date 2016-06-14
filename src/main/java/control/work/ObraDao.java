package control.work;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import control.ConnectionSingleton;
import model.Obra;

public class ObraDao {
    
    
    /**
     * Insert a work into "obra" table
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
    public static void insereObra(String instituicao, String area, String autor, String titulo, String data, String resumo, String imagem, String usuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
    	  Connection conn = ConnectionSingleton.getInstance().getConnection();
		  
		  String sql = "INSERT INTO obra (instituicao, area, autor, titulo, data, resumo, imagem, usuario) VALUES("+instituicao+","+area+",'"+autor+"','"+titulo+"','"+data+"','"+resumo+"',?,"+usuario+");";
		  
		  System.out.println("SQL :" + sql);
		  
		  PreparedStatement st = conn.prepareStatement(sql);
		  
		  st.setString(1, imagem);
		  
		  st.execute();
			  
		 /**
		  * @TODO Quando cadastra uma nova obra, 
		  * se algum erro acontecer significa que ocorreu algum problema 
		  * na inserção dos dados na tabela. N existe nenhuma restricao na
		  * tabela obra. O ID que vc estava recebendo por parametro ele eh
		  * gerado automaticamente quando voce inserir uma nova obra 
		  * 
		  * Obs: Verificar o que voce quis fazer
		  * 
		  */
		  
		  //String sql = "UPDATE obra SET intituicao="+instituicao+",area = "+area+",autor = "+autor+",titulo = "+titulo+",data = "+data+",resumo="+resumo+",imagem="+imagem+",usuario="+usuario+" WHERE id = "+id+";";
		  //st.executeUpdate(sql);
		  //conn.close();
	}
    
    public static void atualizaObra(int id, String instituicao, String area, String autor, String titulo, String data, String resumo, String imagem, int usuario) throws SQLException {
    	
    	 try {
    	
    	  Connection conn = ConnectionSingleton.getInstance().getConnection();
   		  Statement st = conn.createStatement();
			  
		String sql = "UPDATE obra SET intituicao="+instituicao+",area = "+area+",autor = "+autor+",titulo = "+titulo+",data = "+data+",resumo="+resumo+",imagem="+imagem+",usuario="+usuario+" WHERE id = "+id+";";
		
		st.executeUpdate(sql);
		conn.close();
		  
	}
    
    catch (SQLException e) {

        throw new RuntimeException(e);

    }
    
  }
    
public static void excluiObra(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	

		     try {

		    	 Connection conn = ConnectionSingleton.getInstance().getConnection();
		   		 Statement st = conn.createStatement();

		 		String sql = "DELETE FROM obra WHERE id = "+id+";";
		 		st.executeUpdate(sql);
		 		conn.close();

		     } catch (SQLException e) {

		         throw new RuntimeException(e);

		     }		
		  
	}

public static List<Obra> listaObra(long usuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	
	List<Obra> list = null;
	
    try {
   	      Connection conn = ConnectionSingleton.getInstance().getConnection();

    	  list = new ArrayList<>();
  		  
		  String sql = String.format("SELECT a.*, b.nome area FROM (SELECT a.id, a.instituicao, a.area as areaId, a.autor, a.titulo, a.data, a.resumo, a.usuario, a.imagem as path, b.avaliacao from obra a LEFT OUTER JOIN avaliacao b ON a.id = b.obra AND a.usuario = b.usuario WHERE a.usuario=?) a, area b WHERE a.areaId = b.id");
		  
		  //System.out.println(sql);
		  
		  PreparedStatement stmt = conn.prepareStatement(sql);
		  
		  // Seta o usuario
		  stmt.setLong(1, usuario);
		  
		  ResultSet rs = stmt.executeQuery();
		  
		  while (rs.next()) {
			  
			  Obra obra = new Obra();
			  int obraId = rs.getInt("id");
			  String instituicao = rs.getString("instituicao");
			  String area = rs.getString("area");
			  String autor = rs.getString("autor");
			  String titulo = rs.getString("titulo");
			  String resumo = rs.getString("resumo");
			  String data = rs.getString("data");
			  Integer avaliacao = rs.getInt("avaliacao");
			  String file = rs.getString("path");
			  
			  obra.setid(obraId);
			  obra.setinstituicao(instituicao);
			  obra.setarea(area);
			  obra.setautor(autor);
			  obra.settitulo(titulo);
			  obra.setresumo(resumo);
			  obra.setdata(data);
			  obra.setAvaliacao(avaliacao);
			  obra.setFile(file);
			  list.add(obra);
		  }
		  
		  stmt.close();
		  
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
    
    	return list;
    }
    
}