package control.work;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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

public class WorkUpdateCommand extends WorkCommand{

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
	            if(field.equals("id")){
	            	work.setid(Long.valueOf(value));
	            }else if(field.equals("title")){
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
		                    tryUpdate(data, item, work, user);
		                }
	                }else{// Save work into into table
	                	work.setFile(value);
	                	tryUpdate(data, null, work, user);
	                }
	            }
	            //System.out.println("Verificando os campos enviados : " + item.getFieldName() + "," + item.isFormField());
	        }
        }
        
        // Mostra a saida em JSON
     	Print.json(getResponse(), data);
	}
            
    private void tryUpdate(JSONData data, FileItem item, Obra work, String user) {
    	try{
        	update(item, work, user);
        	data.put(JSONOut.CODE, JSONOut.Sucess.COMPLETADA);
        }catch(Exception e){
        	e.printStackTrace();
        	data.put(JSONOut.CODE, JSONOut.Erro.OCORREU_ALGUM_ERRO);
        }
	}

	private void update(FileItem item, Obra work, String usuario) 
    		throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	
    	// Save work into directory and generate full path
    	String path = (item != null)? WorkControl.saveFile(getRequest(), item) : work.getFile();
		
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
		
		// Updated table obra 
		ObraDao.atualizaObra
		(
				work.getid(),
				work.getinstituicao(), 
				areaId.toString(), 
				work.getautor(), 
				work.gettitulo(), 
				work.getdata(), 
				work.getresumo(), 
				path
		);
	}

}

	
