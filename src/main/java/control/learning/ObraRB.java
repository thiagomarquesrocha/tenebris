package control.learning;

public class ObraRB {
	
    private int id;
    private int usuario;
	private int relevancia;
	private String titulo;
	private int palavrachave;
	private String pcString;
 
	public int getId() {
        return id;
    }
	
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUsuario() {
        return usuario;
    }
	
    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    
    public int getPchave() {
        return palavrachave;
    }
    public void setPchave(int pchave) {
        this.palavrachave = pchave;
        
    }
    
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getPcstring() {
        return pcString;
    }
    public void setPcstring(String pcstring) {
        this.pcString = pcstring;
    }

	public void setRelevancia(int relevancia) {
		this.relevancia = relevancia;
	}
	
	public int getRelevancia(){
		return relevancia;
	}
    
}