package control.recomendation.rate;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import control.JSONUtil;
import control.factory.ConnectionFactory;
import model.Obra;

public class SaidaJSON {
	
	List<Obra> list;
	Gson gson;
	
	public SaidaJSON(){
		list = new ArrayList<>();
		gson = new Gson();
	}
	
	public void JSON(int obraId, int usuarioId) {
		
	  String url = ConnectionFactory.HOST;
	  String dbName = ConnectionFactory.BD;
	  String driver = "com.mysql.jdbc.Driver";
	  String userName = ConnectionFactory.USER; 
	  String password = ConnectionFactory.PASSWORD;
	  
	  /*String url = "jdbc:mysql://localhost:3306/";
	  String dbName = "csv_db";
	  String driver = "com.mysql.jdbc.Driver";
	  String userName = "root"; 
	  String password = "";*/
	  
	  System.out.println("ID da obra :" + obraId);

	  
	  try {
		  
		  Class.forName(driver).newInstance();
		  DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		  Connection conn = DriverManager.getConnection(url+dbName,userName,password);
		  
		  String sql = String.format("SELECT a.*, b.avaliacao FROM (SELECT a.id as obraId, a.autor, a.resumo, a.data, LCASE(a.titulo) as titulo, b.nome as nome1, c.nome as nome2 FROM obra a, area b, instituicao c WHERE a.area=b.id AND c.id = a.instituicao AND a.id = %s) a LEFT OUTER JOIN (SELECT * FROM avaliacao WHERE obra = %s AND usuario = %s) b ON a.obraId = b.obra", obraId, obraId, usuarioId);
		  
		  //System.out.println(sql);
		  
		  PreparedStatement stmt = conn.prepareStatement(sql);
		  ResultSet rs = stmt.executeQuery();
		  
		  Obra obra = new Obra();
		  
		  while (rs.next()) {
			  
			  String instituicao = rs.getString("nome2");
			  String area = rs.getString("nome1");
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
	

	public void print(HttpServletResponse response) throws IOException {
		JSONUtil.print(response, list);
	}
	
	public List<Obra> getList(){
		return list;
	}
	
}
