package me.bazhenov.maxent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;

public class GSON {
	
		public Obra ConstrucaoJSON(int ItemID, Connection conn) throws SQLException {
			
	 		//System.out.println("ID da obra classe GSON: " + ItemID);
	 		
	 		Obra obra = new Obra();
		  
		  try {
			  
			  String sql = String.format("SELECT * FROM obra WHERE obra.id=%s", ItemID);
				  
			  	  Statement stmt = (Statement) conn.prepareStatement(sql);
				  ResultSet rs = stmt.executeQuery(sql);
				  
				  while (rs.next()) {
					  
					  String titulo = rs.getString("TITULO");
					  String resumo = rs.getString("resumo");
					  String data = rs.getString("cadastradoem");
					  String file = rs.getString("IMAGEM");
					  
					  obra.setid(Long.valueOf(ItemID));
					  obra.settitulo(titulo);
					  obra.setresumo(resumo);
					  obra.setdata(data);
					  obra.setFile(file);
				  }
				  
				  sql = String.format("SELECT instituicao.nome "
					  		+ "FROM obra INNER JOIN instituicao "
					  		+ "ON obra.instituicao=instituicao.id", ItemID);
					  
				  stmt = (Statement) conn.prepareStatement(sql);
				  rs = stmt.executeQuery(sql);
					  
				  while (rs.next()) {
						  
						  String instituicao = rs.getString("NOME");
						  
						  obra.setinstituicao(instituicao);
				  }
				  
				  sql = String.format("SELECT area.nome "
					  		+ "FROM obra INNER JOIN area "
					  		+ "ON obra.area=area.id", ItemID);

				  stmt = (Statement) conn.prepareStatement(sql);
				  rs = stmt.executeQuery(sql);
					  
				  while (rs.next()) {
						  
						  String area = rs.getString("nome");
						  
						  obra.setarea(area);

				  }
				  
				  sql = String.format("SELECT autor.nome "
					  		+ "FROM obra INNER JOIN autor "
					  		+ "ON obra.autor=autor.id", ItemID);
					  
				  stmt = (Statement) conn.prepareStatement(sql);
				  rs = stmt.executeQuery(sql);
					  
				  while (rs.next()) {
						  
						  String autor = rs.getString("nome");
						  
						  obra.setautor(autor);

				  }
			  
			  } catch (Exception e) {
				  e.printStackTrace();
			  }
		  return obra;
		}
}
