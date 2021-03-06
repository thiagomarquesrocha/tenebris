package control.recomendation.profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Obra;

public class JSON {

	
	public Obra Print(String obraId, int userId) throws SQLException{
		String query = String.format("SELECT a.*, b.avaliacao, c.nome as autorNome, d.* FROM (SELECT obra.id, instituicao.nome instituicao, area.nome area, obra.autor, obra.titulo, obra.data, obra.resumo, obra.imagem FROM tenebris2016.obra INNER JOIN tenebris2016.instituicao ON obra.instituicao = instituicao.id INNER JOIN tenebris2016.area ON obra.area = area.id AND obra.id = %s) a LEFT OUTER JOIN (SELECT * FROM avaliacao WHERE obra = %s AND usuario = %s) b ON a.Id = b.obra, autor c, (SELECT avg(avaliacao) as media FROM avaliacao WHERE obra = %s) d WHERE c.id = a.autor ORDER BY a.data DESC", obraId, obraId, userId, obraId);		
		
		//System.out.println(query);
		
		PreparedStatement statement = Principal.bd.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		
	    while(result.next()){
	    	
	    	String aux = result.getString("ID");
	    	
	    	if(obraId.equals(aux)){
	    		Obra obra = new Obra();
	    		obra.setid(Long.valueOf(result.getString("id")));
	    		obra.settitulo(result.getString("titulo"));
	    		obra.setresumo(result.getString("resumo"));
	    		obra.setarea(result.getString("Area"));
	    		obra.setautor(result.getString("autorNome"));
	    		obra.setAvaliacao(result.getInt("avaliacao"));
	    		obra.setinstituicao(result.getString("instituicao"));
	    		obra.setFile(result.getString("imagem"));
	    		obra.setMedia(result.getFloat("media"));
	    		obra.setdata(result.getString("data"));
			    return obra;
	    	}
	    }
	    
	    return null;
	}
}
