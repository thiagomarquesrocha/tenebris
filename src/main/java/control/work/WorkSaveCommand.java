package control.work;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import control.ConnectionSingleton;
import control.JSONData;
import control.Print;
import control.area.AreaControl;
import model.JSONOut;
import model.Obra;

public class WorkSaveCommand extends WorkCommand{

	@Override
	public void execute() throws Exception {
		@SuppressWarnings("deprecation")
		boolean isMultiPart = FileUpload.isMultipartContent(getRequest());
		
		String user = null;
		
        JSONData data = new JSONData().create();

        if (isMultiPart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            @SuppressWarnings("unused")
			String formulario = "";
	        @SuppressWarnings("rawtypes")
			List items = upload.parseRequest(getRequest());
	        @SuppressWarnings("rawtypes")
			Iterator iter = items.iterator();
	        
	        Obra work = new Obra();
	
	        while (iter.hasNext()) {
	            FileItem item = (FileItem) iter.next();
	            String field = item.getFieldName();
	            String value = item.getString("UTF-8");
	            // Others inputs from form
	            if(field.equals("title")){
	            	work.settitulo(value);
	            }else if(field.equals("institution")){
	            	work.setinstituicao(value);
	            }else if(field.equals("auth")){
	            	work.setautor(value);
	            }else if(field.equals("area")){
	            	work.setarea(value);
	            }else if(field.equals("date")){
	            	work.setdata(value);
	            }else if(field.equals("resume")){
	            	work.setresumo(value);
	            }else if(field.equals("user")){
	            	user = value;
	            }else if (item.getFieldName().equals("file")) { // Input file 
	                formulario = item.getString();
	                
	                if(!item.isFormField()){
	                	if (item.getName().length() > 0) {
	                		// Save work into directory e save into table
		                    try{
		                    	save(item, work, user);
		                    	data.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA);
		                    }catch(Exception e){
		                    	e.printStackTrace();
		                    	data.put(JSONOut.CODE, JSONOut.Erro.OCORREU_ALGUM_ERRO);
		                    }
		                }
	                }
	            }
	            //System.out.println("Verificando os campos enviados : " + item.getFieldName() + "," + item.isFormField());
	        }
        }
        
        // Mostra a saida em JSON
     	Print.json(getResponse(), data);
	}
            
    private void save(FileItem item, Obra work, String usuario) 
    		throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

    	System.out.println("Subindo a obra para o servidor...");
    	
		//Pega o diretório /obras dentro do diretório atual de onde a aplicação está rodando
		String caminho = getRequest().getServletContext().getRealPath("/obras") + "/";
		
		// Cria o diretório caso ele não exista
		File diretorio = new File(caminho);
		
		System.out.println("Path : " + caminho);
		
		if (!diretorio.exists()){
			diretorio.mkdir();
		}
		
		// Mandar o arquivo para o diretório informado
		Calendar cal = Calendar.getInstance();
		
		String nome =  cal.get(Calendar.DAY_OF_MONTH)+ "_" + cal.get(Calendar.MONTH) + "_" + cal.get(Calendar.YEAR) + "_" + cal.get(Calendar.HOUR_OF_DAY) + "_" + cal.get(Calendar.MINUTE) + "_" + cal.getTimeInMillis() + ".pdf";
		
		System.out.println("Nome : " + nome);
		
		File file = new File(diretorio, nome);
		FileOutputStream output = new FileOutputStream(file);
		
		InputStream is = item.getInputStream();
		
		byte[] buffer = new byte[2048];
		
		int nLidos;
		
		System.out.println("Absolute Path : " + file.getAbsolutePath());
		
		while ((nLidos = is.read(buffer)) >= 0) {
			output.write(buffer, 0, nLidos);
		}
		
		output.flush();
		output.close();
		
		// Insere a area no banco, verificando se ela já existe
		Connection conn = ConnectionSingleton.getInstance().getConnection();
		String area = work.getarea();
		Long areaId = null;
		
		System.out.println("Area : " + area);
		
		try {
			areaId = AreaControl.add(conn, area);
		} catch (Exception e) {
			// A obra ja existe 
			try {
				areaId = AreaControl.findByName(conn, area);
			} catch (Exception e1) {
				// Nao foi possivel recuperar o ID
				e1.printStackTrace();
			}
		}
		
		System.out.println("ID area : " + areaId.toString());

		//Guarda no banco de dados o endereço para recuperação da imagem
		ObraDao.insereObra
		(
				work.getinstituicao(), 
				areaId.toString(), 
				work.getautor(), 
				work.gettitulo(), 
				work.getdata(), 
				work.getresumo(), 
				file.getAbsolutePath(), 
				usuario
		);
	}

}

	
