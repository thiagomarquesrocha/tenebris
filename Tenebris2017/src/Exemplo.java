import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

//Classe que reune todas as classes auxiliares para desempenhar a rede bayesiana.

public class Exemplo {
	
	public void Metodo(int UserID, int ItemID) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		MysqlDataSource dataSource = new MysqlDataSource();
		
		dataSource.setUser("root");
    	dataSource.setPassword("");
    	dataSource.setServerName("localhost");
    	dataSource.setDatabaseName("tenebris2016");
    	Connection conn = dataSource.getConnection();
    	Statement stmt = (Statement) conn.createStatement();
    	
		String sql = String.format("SELECT * FROM redebayesiana WHERE idUsuario = %s", UserID); //Conexão com a nova tabela
		ResultSet rs = stmt.executeQuery(sql);
			

        final Classifier<String, String> bayes =
                new BayesClassifier<String, String>();
        
        List<ObraRB> obras = new ArrayList<ObraRB>();//Como a mesma obra tem vários registros na tabela "redebayesiana", crio aqui uma Lista de objetos, da classe ObraRB (Obra Rede Bayesiana) e guardar cada palavra-chave.
        int item;
        int usuario;
        int relevancia;
        String titulo;
        int pchave;
        while (rs.next()) {
        	usuario = rs.getInt("IDUSUARIO");
        	item = rs.getInt("IDOBRA");
        	relevancia = rs.getInt("RELEVANCIA");
            titulo = rs.getString("TITULO");
            pchave = rs.getInt("PCHAVE");
            
            ObraRB obra = new ObraRB(); // Instancio a classe ObraRB e aplico seus métodos. Ela é similar e tem a mesma função que a classe Obra que críamos para o projeto passado.
            obra.setId(item);
            obra.setUsuario(usuario);
            obra.setRelevancia(relevancia);
            obra.setTitulo(titulo);
            obra.setPchave(pchave);
            obras.add(obra);
        }
        
        for(int i = 0; i < obras.size(); i++){ // Este loop for serve, para cada item dentro da lista "obras", array da classe ObraRB, para resgatar o valor numérico int através da conexão com a nova tabela "idpchave", que relaciona o número da palavra-chave com a string. Ou seja, para cada objeto que está dentro da lista "obras", é feito uma conexão com o banco de dados para ser feito esse resgate.
        	sql = String.format("SELECT * FROM idpalavrachave WHERE idpchave = %s", obras.get(i).getPchave());
        	rs = stmt.executeQuery(sql);
        	while(rs.next()){
        		
        		String pcstring = rs.getString("PALAVRACHAVE");
        		obras.get(i).setPcstring(pcstring);
        		
        	}
        }
        
        for(int i = 0; i < obras.size(); i++){ // Agora que já temos todas as palavras-chaves como string, em vez de reunir todas as palavras-chaves do mesmo artigo e criar o registro para o método da rede bayesiana, eu simplesmente registro o "título e a palavra-chave" várias vezes, no lugar de "título e palavras-chaves". Ou seja, se temos dois artigos, cada um com três palavras-chaves, a lista "obras" tem tamanho igual a 2 x 3 = 6. Em vez de reunir todas as palavras-chaves do mesmo artigo e registrar somente 2 artigos, eu registro como se fossem 6 artigos (3 artigos com título A, e mais 3 artigos com o título B), diferenciando somente nas palavras-chave.
        									   // Levei em consideração que teríamos vários regsitros, mas este método de rede bayesiana funciona a base de "quantos mais registros, melhor, mais discrepante". Mas também o outro lado da moeda, que teríamos que gastar mais recursos da máquina para juntar as strings novamente para fazer poucos registros. Logo, concluí que essa seria a saída mais fácil. Registrar cada linha da tabela "redebayesiana" como se fosse um artigo diferente.
        	String palavra = obras.get(i).getTitulo() + " " + obras.get(i).getPcstring();
        	if(obras.get(i).getRelevancia() > 0){ // Se o artigo está registrado como relevante (valor numérico 1), é relevante, do contrário, é falso.
        		final String[] positiveText = (palavra).split("\\s");
            	bayes.learn("Relevante", Arrays.asList(positiveText)); // Método para pegar o registro. Para cada linha, é feito uma registro de relevância.
        	} else{
        		final String[] negativeText = (palavra).split("\\s");
            	bayes.learn("Irrelevante", Arrays.asList(negativeText));
        	}
        }
        
        // Ao fim deste processo, o modelo de aprendizagem para o usuário está pronto.
        
        sql = String.format("SELECT * FROM obra where id=%s", ItemID); // Agora, precisamos resgatar as características do artigo que queremos prever a relevância.
		rs = stmt.executeQuery(sql);
        
		String[] unknownText = " ".split("\\s");
		
		List<ObraRB> artigo = new ArrayList<ObraRB>(); // Lista da classe ObraRB, para guardar cada linha referente ao artigo, que consta na tabela "obra", já que o que diferencia é a nova coluna "palava-chave"; um registro para cada palavra-chave.
		while (rs.next()) {
            titulo = rs.getString("TITULO");
            pchave = rs.getInt("PALAVRACHAVE");
            
            ObraRB obra = new ObraRB();
            obra.setTitulo(titulo);
            obra.setPchave(pchave);
            artigo.add(obra);
        }
		
		String palavraschaves = null;
		for(int i = 0; i < artigo.size(); i++){
        	sql = String.format("SELECT * FROM idpalavrachave WHERE idpchave = %s", obras.get(i).getPchave()); // Resgate da palavra-chave. Similar ao processo de resgate para construção do modelo de aprendizagem.
        	rs = stmt.executeQuery(sql);
        	while(rs.next()){
        		
        		String pcstring = rs.getString("PALAVRACHAVE");
        		artigo.get(i).setPcstring(pcstring);
        		palavraschaves = palavraschaves + " " + pcstring; // Como os vários itens dentro da lista "artigo" fazem parte do mesmo artigo, decidi por simplesmente reunir numa string. Se a abordagem fosse diferente, ou melhor, similar com a que escohi para o modelo de aprendizagem, seria como perguntar para o sistema de vários artigos diferentes e não de unicamente um (redundância proposital).
        		
        	}
        }
		String[] unknownText1 = (artigo.get(0).getTitulo() + " " + palavraschaves).split("\\s"); // Registro a "dúvida" no modelo de aprendizagem.
        System.out.println(bayes.classify(Arrays.asList(unknownText1)).getCategory()); // Chamando o método, e assim printo se é "Relevante" ou "Irrelvante".
		
        ((BayesClassifier<String, String>) bayes).classifyDetailed(
                Arrays.asList(unknownText));

        bayes.setMemoryCapacity(500);
        
        rs.close();
        stmt.close();
        conn.close();
    }

}
