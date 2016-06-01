package model;
import java.sql.*;
import java.util.List;

import com.google.gson.Gson;

import control.ConnectionPool;
import control.factory.ConnectionFactory;

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
    

    public static void insereObra(int id, String instituicao, String area, String autor, String titulo, String data, String resumo, String imagem, int usuario) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    	
    	  Connection conn = ConnectionPool.getInstance().getConnection();
		  Statement st = conn.createStatement();
		  
		  try {
		  
		  String sql = "INSERT INTO obra VALUES("+id+","+instituicao+","+area+","+autor+","+titulo+","+data+","+resumo+","+imagem+","+usuario+");";
		  
		  st.executeUpdate(sql);
		  conn.close();
		  
		  } catch (Exception e) {
			  
			  String sql = "UPDATE obra SET intituicao="+instituicao+",area = "+area+",autor = "+autor+",titulo = "+titulo+",data = "+data+",resumo="+resumo+",imagem="+imagem+",usuario="+usuario+" WHERE id = "+id+";";
				
			  st.executeUpdate(sql);
			  conn.close();
		  
		  }
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