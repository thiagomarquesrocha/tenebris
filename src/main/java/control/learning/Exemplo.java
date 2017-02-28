package control.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
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
    	
		String sql = String.format("SELECT * FROM redebayesiana WHERE idUsuario = %s", UserID); //Conex�o com a nova tabela
		ResultSet rs = stmt.executeQuery(sql);
			

        final Classifier<String, String> bayes =
                new BayesClassifier<String, String>();
        
        List<ObraRB> obras = new ArrayList<ObraRB>();//Como a mesma obra tem v�rios registros na tabela "redebayesiana", crio aqui uma Lista de objetos, da classe ObraRB (Obra Rede Bayesiana) e guardar cada palavra-chave.
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
            
            ObraRB obra = new ObraRB(); // Instancio a classe ObraRB e aplico seus m�todos. Ela � similar e tem a mesma fun��o que a classe Obra que cr�amos para o projeto passado.
            obra.setId(item);
            obra.setUsuario(usuario);
            obra.setRelevancia(relevancia);
            obra.setTitulo(titulo);
            obra.setPchave(pchave);
            obras.add(obra);
        }
        
        for(int i = 0; i < obras.size(); i++){ // Este loop for serve, para cada item dentro da lista "obras", array da classe ObraRB, para resgatar o valor num�rico int atrav�s da conex�o com a nova tabela "idpchave", que relaciona o n�mero da palavra-chave com a string. Ou seja, para cada objeto que est� dentro da lista "obras", � feito uma conex�o com o banco de dados para ser feito esse resgate.
        	sql = String.format("SELECT * FROM idpalavrachave WHERE idpchave = %s", obras.get(i).getPchave());
        	rs = stmt.executeQuery(sql);
        	while(rs.next()){
        		
        		String pcstring = rs.getString("PALAVRACHAVE");
        		obras.get(i).setPcstring(pcstring);
        		
        	}
        }
        
        for(int i = 0; i < obras.size(); i++){ // Agora que j� temos todas as palavras-chaves como string, em vez de reunir todas as palavras-chaves do mesmo artigo e criar o registro para o m�todo da rede bayesiana, eu simplesmente registro o "t�tulo e a palavra-chave" v�rias vezes, no lugar de "t�tulo e palavras-chaves". Ou seja, se temos dois artigos, cada um com tr�s palavras-chaves, a lista "obras" tem tamanho igual a 2 x 3 = 6. Em vez de reunir todas as palavras-chaves do mesmo artigo e registrar somente 2 artigos, eu registro como se fossem 6 artigos (3 artigos com t�tulo A, e mais 3 artigos com o t�tulo B), diferenciando somente nas palavras-chave.
        									   // Levei em considera��o que ter�amos v�rios regsitros, mas este m�todo de rede bayesiana funciona a base de "quantos mais registros, melhor, mais discrepante". Mas tamb�m o outro lado da moeda, que ter�amos que gastar mais recursos da m�quina para juntar as strings novamente para fazer poucos registros. Logo, conclu� que essa seria a sa�da mais f�cil. Registrar cada linha da tabela "redebayesiana" como se fosse um artigo diferente.
        	String palavra = obras.get(i).getTitulo() + " " + obras.get(i).getPcstring();
        	if(obras.get(i).getRelevancia() > 0){ // Se o artigo est� registrado como relevante (valor num�rico 1), � relevante, do contr�rio, � falso.
        		final String[] positiveText = (palavra).split("\\s");
            	bayes.learn("Relevante", Arrays.asList(positiveText)); // M�todo para pegar o registro. Para cada linha, � feito uma registro de relev�ncia.
        	} else{
        		final String[] negativeText = (palavra).split("\\s");
            	bayes.learn("Irrelevante", Arrays.asList(negativeText));
        	}
        }
        
        // Ao fim deste processo, o modelo de aprendizagem para o usu�rio est� pronto.
        
        sql = String.format("SELECT * FROM obra where id=%s", ItemID); // Agora, precisamos resgatar as caracter�sticas do artigo que queremos prever a relev�ncia.
		rs = stmt.executeQuery(sql);
        
		String[] unknownText = " ".split("\\s");
		
		List<ObraRB> artigo = new ArrayList<ObraRB>(); // Lista da classe ObraRB, para guardar cada linha referente ao artigo, que consta na tabela "obra", j� que o que diferencia � a nova coluna "palava-chave"; um registro para cada palavra-chave.
		while (rs.next()) {
            titulo = rs.getString("TITULO");
            pchave = rs.getInt("PALAVRACHAVE");
            
            // pchave = 2;
            
            ObraRB obra = new ObraRB();
            obra.setTitulo(titulo);
            obra.setPchave(pchave);
            artigo.add(obra);
        }
		
		String palavraschaves = null;
		for(int i = 0; i < artigo.size(); i++){
        	sql = String.format("SELECT * FROM idpalavrachave WHERE idpchave = %s", obras.get(i).getPchave()); // Resgate da palavra-chave. Similar ao processo de resgate para constru��o do modelo de aprendizagem.
        	rs = stmt.executeQuery(sql);
        	while(rs.next()){
        		
        		String pcstring = rs.getString("PALAVRACHAVE");
        		artigo.get(i).setPcstring(pcstring);
        		palavraschaves = palavraschaves + " " + pcstring; // Como os v�rios itens dentro da lista "artigo" fazem parte do mesmo artigo, decidi por simplesmente reunir numa string. Se a abordagem fosse diferente, ou melhor, similar com a que escohi para o modelo de aprendizagem, seria como perguntar para o sistema de v�rios artigos diferentes e n�o de unicamente um (redund�ncia proposital).
        		
        	}
        }
		String[] unknownText1 = (artigo.get(0).getTitulo() + " " + palavraschaves).split("\\s"); // Registro a "d�vida" no modelo de aprendizagem.
        System.out.println(bayes.classify(Arrays.asList(unknownText1)).getCategory()); // Chamando o m�todo, e assim printo se � "Relevante" ou "Irrelvante".
		
        ((BayesClassifier<String, String>) bayes).classifyDetailed(
                Arrays.asList(unknownText));

        bayes.setMemoryCapacity(500);
        
        rs.close();
        stmt.close();
        conn.close();
    }

}
