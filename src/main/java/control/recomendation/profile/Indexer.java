package control.recomendation.profile;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	
	IndexWriter writer;
	
	@SuppressWarnings("deprecation")
	public Indexer(String indexDirectoryPath) throws IOException{
		Directory indexDirectory = FSDirectory.open(new File(indexDirectoryPath));
		writer = new IndexWriter(indexDirectory, new StandardAnalyzer(Version.LUCENE_36),true, IndexWriter.MaxFieldLength.UNLIMITED);
		
		/*StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36, analyzer);
		writer = new IndexWriter(indexDirectory, conf);*/
	}
		
	public void createIndex() throws SQLException, IOException{
	    String query = "SELECT obra.id, instituicao.nome \"instituicao\", area.nome \"area\", obra.autor, obra.titulo, obra.data, obra.resumo FROM tenebris2016.obra INNER JOIN tenebris2016.instituicao ON obra.instituicao = instituicao.id INNER JOIN tenebris2016.area ON obra.area = area.id";
	    PreparedStatement statement = Principal.bd.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		  
		while (result.next()) {
			Document document = new Document();
			String conteudo;
		    
		    conteudo = result.getString("instituicao");
		    conteudo += " ";
		    conteudo += result.getString("area");
		    conteudo += " ";
		    conteudo += result.getString("autor");
		    conteudo += " ";
		    conteudo += result.getString("titulo");
		    conteudo += " ";
		    conteudo += result.getString("resumo");
		    
		    StringReader aux = new StringReader(conteudo);
		    Field contentField = new Field("conteudo", aux);
		    Field idField = new Field("id", result.getString("id"), Field.Store.YES, Field.Index.NOT_ANALYZED);
		    
		    document.add(contentField);
		    document.add(idField);
		    
		    writer.addDocument(document);
		}
		writer.close();
	}
}
