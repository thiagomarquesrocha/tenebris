package control.recomendation.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import control.JSONUtil;
import control.RecommendControl;
import model.Obra;

public class Principal extends RecommendControl {
	static Connection bd = BancoDados.getConnection();
	String indexDir = "Index";
	Indexer indexer;
	Searcher searcher;
	List<Obra> list;
	// Total de recomendacoes 
	static int TOTAL = 10;
	
	public Principal(){
		super();
	}
	
	public Principal(HttpServletResponse response) {
		super(response);
	}
	
	public Principal getRecommender(int ID) throws SQLException{
		try {
			//long inicio = System.currentTimeMillis();
			search(ID);
			//long fim = System.currentTimeMillis();
			//System.out.println("O tempo de resposta é de: " + (fim - inicio));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	public static void cadastrarUsuario(String Termos, String Autor, String Unidade, String Area) throws SQLException{
		PerfilUsuario usuario = new PerfilUsuario(); //Criando um objeto que ser� usado para criar o perfil do usuario.
		usuario.cadastrarUsuario(Termos, Autor, Unidade, Area);//O metodo que cria o perfil � chamado, sendo necessario enviar como parametro Strings correspondentes ao gosto do usuario.
	}

	private void search(int id) throws IOException, SQLException, ParseException{
		Usuario user = new Usuario();
		searcher = new Searcher(indexDir);
		ArrayList<String> ListaDocumentos = new ArrayList<String>();

		String query = "SELECT area.nome \"area\" FROM tenebris2016.usuario INNER JOIN tenebris2016.area ON usuario.area = area.id AND usuario.id = " + id;
		PreparedStatement statement = bd.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		while(result.next()){
			TopDocs hits = searcher.search(result.getString("area"));
			for(ScoreDoc scoreDoc : hits.scoreDocs){
				Document doc = searcher.getDocument(scoreDoc);
				ListaDocumentos.add(doc.get("id"));
			}
		}		
		result.close();
		
		query = "SELECT autor.nome \"autor\" FROM tenebris2016.usuario_autores INNER JOIN tenebris2016.autor ON usuario_autores.autor = autor.id AND usuario_autores.usuario = " + id;
		statement = bd.prepareStatement(query);
		result = statement.executeQuery();
		while(result.next()){
			TopDocs hits = searcher.search(result.getString("autor"));
			for(ScoreDoc scoreDoc : hits.scoreDocs){
				Document doc = searcher.getDocument(scoreDoc);
				ListaDocumentos.add(doc.get("id"));
			}
		}
		result.close();
		
		query = "SELECT instituicao.nome \"instituicao\" FROM tenebris2016.usuario_instituicoes INNER JOIN tenebris2016.instituicao ON usuario_instituicoes.instituicao = instituicao.id AND usuario_instituicoes.usuario = " + id;
		statement = bd.prepareStatement(query);
		result = statement.executeQuery();
		while(result.next()){
			TopDocs hits = searcher.search(result.getString("instituicao"));
			for(ScoreDoc scoreDoc : hits.scoreDocs){
				Document doc = searcher.getDocument(scoreDoc);
				ListaDocumentos.add(doc.get("id"));
			}
		}
			
		query = "SELECT tag.nome \"tag\" FROM tenebris2016.usuario_tags INNER JOIN tenebris2016.tag ON usuario_tags.tag = tag.id AND usuario_tags.usuario = " + id;
		statement = bd.prepareStatement(query);
		result = statement.executeQuery();
				
		while(result.next()){
			TopDocs hits = searcher.search(result.getString("tag"));
			for(ScoreDoc scoreDoc : hits.scoreDocs){
				Document doc = searcher.getDocument(scoreDoc);
				ListaDocumentos.add(doc.get("id"));
				ListaDocumentos.add(doc.get("id"));
				ListaDocumentos.add(doc.get("id"));
			}
		}
	    
	    if(ListaDocumentos.size() == 0){
	    	System.out.println("O usuário não possui informações sufucientes para o sistema gerar recomendações!");
	    } else{
	    	System.out.println("\nLista de Recomendação da Filtragem baseada em Conteúdo para o usuário " + id + ": ");
	    	list = user.Lista(getResponse(), id, ListaDocumentos, TOTAL);
	    	if(isRequest())
	    		JSONUtil.print(getResponse(), list);
	    }
	    
		searcher.close();
	}
	
	@Override
	public List<Obra> getList() {
		return list;
	}
}
