package control.recomendation.rate;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import control.ConnectionSingleton;
import control.JSONUtil;
import model.Obra;

public class SaidaJSON {
	
	List<Obra> list;
	Gson gson;
	
	public SaidaJSON(){
		list = new ArrayList<>();
		gson = new Gson();
	}
	
	public void JSON(int obraId, int usuarioId) throws SQLException {
		
		Connection conn = ConnectionSingleton.getInstance().getConnection();
 		System.out.println("ID da obra :" + obraId);

	  
	  try {
		  
		  String sql = String.format("SELECT a.*, b.avaliacao, c.nome as autorNome, d.* FROM (SELECT a.id as obraId, a.autor, a.resumo, a.data, LCASE(a.titulo) as titulo, b.nome as nome1, c.nome as nome2, a.imagem FROM obra a, area b, instituicao c WHERE a.area=b.id AND c.id = a.instituicao AND a.id = %s) a LEFT OUTER JOIN (SELECT * FROM avaliacao WHERE obra = %s AND usuario = %s) b ON a.obraId = b.obra, autor c, (SELECT avg(avaliacao) as media FROM avaliacao WHERE obra = %s) d WHERE c.id = a.autor ORDER BY a.data DESC", obraId, obraId, usuarioId, obraId);
		  
		  //System.out.println(sql);
		  
		  PreparedStatement stmt = conn.prepareStatement(sql);
		  ResultSet rs = stmt.executeQuery();
		  
		  Obra obra = new Obra();
		  
		  while (rs.next()) {
			  
			  String instituicao = rs.getString("nome2");
			  String area = rs.getString("nome1");
			  String autor = rs.getString("autorNome");
			  String titulo = rs.getString("titulo");
			  String resumo = rs.getString("resumo");
			  String data = rs.getString("data");
			  Integer avaliacao = rs.getInt("avaliacao");
			  String file = rs.getString("imagem");
			  Float media = rs.getFloat("media");
			  
			  obra.setid(Long.valueOf(obraId));
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
	}
	

	public void print(HttpServletResponse response) throws IOException {
		JSONUtil.print(response, list);
	}
	
	public List<Obra> getList(){
		return list;
	}
	
}
