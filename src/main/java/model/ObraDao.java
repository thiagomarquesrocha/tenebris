package model;
import java.sql.*;
import java.util.List;

import com.google.gson.Gson;

import control.ConnectionPool;

public class ObraDao {

	private int id;
    private String instituicao;
    private String area;
    private String autor;
    private String titulo;
    private String data;
    private String resumo;
    private String imagem;
    private int usuario;
    
    
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
    	
    	  Connection conn = ConnectionPool.getInstance().getConnection();
		  
		  String sql = "INSERT INTO obra (instituicao, area, autor, titulo, data, resumo, imagem, usuario) VALUES("+instituicao+","+area+",'"+autor+"','"+titulo+"','"+data+"','"+resumo+"',?,"+usuario+");";
		  
		  PreparedStatement st = conn.prepareStatement(sql);
		  
		  st.setString(1, imagem);
		  
		  st.execute();
		  conn.close();
			  
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
    	
    	  Connection conn = ConnectionPool.getInstance().getConnection();
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

		    	 Connection conn = ConnectionPool.getInstance().getConnection();
		   		 Statement st = conn.createStatement();

		 		String sql = "DELETE FROM obra WHERE id = "+id+";";
		 		st.executeUpdate(sql);
		 		conn.close();

		     } catch (SQLException e) {

		         throw new RuntimeException(e);

		     }		
		  
	}

public static void ListaObra(int usuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	

    try {

   	    Connection conn = ConnectionPool.getInstance().getConnection();
  		Statement st = conn.createStatement();

  		List<Obra> list = null;
  		Gson gson;
  		

  		  
  		  String sql = String.format("SELECT id, instituicao, area, autor, titulo, data from obra WHERE usuario="+usuario+";");
  		  
  		  //System.out.println(sql);
  		  
  		  PreparedStatement stmt = conn.prepareStatement(sql);
  		  ResultSet rs = stmt.executeQuery();
  		  
  		  Obra obra = new Obra();
  		  
  		  while (rs.next()) {
  			  
  			  int obraId = rs.getInt("id");
  			  String instituicao = rs.getString("instituicao");
  			  String area = rs.getString("area");
  			  String autor = rs.getString("autor");
  			  String titulo = rs.getString("titulo");
  			  String resumo = rs.getString("resumo");
  			  String data = rs.getString("data");
  			  Integer avaliacao = rs.getInt("avaliacao");
  			  
  			  obra.setid(obraId);
  			  obra.setinstituicao(instituicao);
  			  obra.setarea(area);
  			  obra.setautor(autor);
  			  obra.settitulo(titulo);
  			  obra.setresumo(resumo);
  			  obra.setdata(data);
  			  obra.setAvaliacao(avaliacao);
  			  list.add(obra);
  		  }
  		  
  		  stmt.close();
  		  conn.close();
  		  
  		  } catch (Exception e) {
  			  e.printStackTrace();
  		  }
    }
    
}