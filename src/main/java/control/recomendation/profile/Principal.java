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
	
	/**
	 * O main � usado somente para chamar  os metodos esticos da classe Principal.
       Nesse caso pede-se uma recomenda��o para o usuario com ID = 3.
	 * @param ID
	 * @throws SQLException
	 */
	public Principal getRecommender(int ID) throws SQLException{
		try {
			createIndex(); //Chamando esse metodo criamos um indice com todos os arquivos que est�o disponiveis na tabela de TTC's
			search(ID); //Esse metodo � chamado para fazer a recomen��o para o usuario cujo ID � enviado como paramentro.
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

	private void createIndex() throws SQLException, IOException {
		indexer = new Indexer(indexDir);
		indexer.createIndex();//Chamando o metodo que cria os indices.
	}

	private void search(int id) throws IOException, SQLException, ParseException{
		
		Usuario user = new Usuario();//Objeto que ser� usuado para fazer a sele��o dos documentos a serem recomendados para o usuario.
		searcher = new Searcher(indexDir);//Objeto que ser� usado para fazer a busca nos indices a procura de documentos similares ao perfil do usuario.
		ArrayList<String> ListaDocumentos = new ArrayList<>();//Esse ArrayList ser� usuado para armazenar os documentos que s�o retornados quando o sistema faz a busca nos indices.

		String query = "SELECT usuario.id \"id\", area.nome \"area\" FROM tenebris2016.usuario INNER JOIN tenebris2016.area ON usuario.area = area.id"; //conexão
		PreparedStatement statement = bd.prepareStatement(query);// com banco de dados
		ResultSet result = statement.executeQuery(); 
		
		String aux;
		
		while(result.next()){
			aux = result.getString("id");
			if(aux.equals("" + id)){
		    	TopDocs hits = searcher.search(result.getString("area"));
				for(ScoreDoc scoreDoc : hits.scoreDocs){
					Document doc = searcher.getDocument(scoreDoc);
					ListaDocumentos.add(doc.get("id"));
				}
		    }
		}
		
		result.close();
		
		query = "SELECT usuario_autores.usuario \"id\", autor.nome \"autor\" FROM tenebris2016.usuario_autores INNER JOIN tenebris2016.autor ON usuario_autores.autor = autor.id";
		statement = bd.prepareStatement(query);
		result = statement.executeQuery();
		
		while(result.next()){
			aux = result.getString("id");
			if(aux.equals("" + id)){
				TopDocs hits = searcher.search(result.getString("autor"));
				for(ScoreDoc scoreDoc : hits.scoreDocs){
					Document doc = searcher.getDocument(scoreDoc);
					ListaDocumentos.add(doc.get("id"));
				}
			}
		}
		
		query = "SELECT usuario_instituicoes.usuario \"id\", instituicao.nome \"instituicao\" FROM tenebris2016.usuario_instituicoes INNER JOIN tenebris2016.instituicao ON usuario_instituicoes.instituicao = instituicao.id";
		statement = bd.prepareStatement(query);
		result = statement.executeQuery();
			
		while(result.next()){
			aux = result.getString("id");
			if(aux.equals("" + id)){
				TopDocs hits = searcher.search(result.getString("instituicao"));
				for(ScoreDoc scoreDoc : hits.scoreDocs){
					Document doc = searcher.getDocument(scoreDoc);
					ListaDocumentos.add(doc.get("id"));
					System.out.println(doc.get("id"));
				}
			}
		}
			
		query = "SELECT usuario_tags.usuario \"id\", tag.nome \"tag\" FROM tenebris2016.usuario_tags INNER JOIN tenebris2016.tag ON usuario_tags.tag = tag.id";
		statement = bd.prepareStatement(query);
		result = statement.executeQuery();
				
		while(result.next()){
			aux = result.getString("id");
			if(aux.equals("" + id)){
				TopDocs hits = searcher.search(result.getString("tag"));
				for(ScoreDoc scoreDoc : hits.scoreDocs){
					Document doc = searcher.getDocument(scoreDoc);
					ListaDocumentos.add(doc.get("id"));
				}
			}
		}
	    
	    if(ListaDocumentos.size() == 0){//Se a Lista de documentos estiver vazia:...
	    	System.out.println("O usuário não possui informações sufucientes para o sistema gerar recomendações!");
	    } else{//senão:...
	    	// Recupera a lista de recomendacoes
	    	list = user.Lista(getResponse(), id, ListaDocumentos, TOTAL);
	    	// Imprime na tela se for um pedido individual de recomendacao
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
