package me.bazhenov.maxent;

public class ObraRB {
	
    private int id;
    private int usuario;
	private int relevancia;
	private String titulo;
	private int palavrachave;
	private String pcString;
	
	public ObraRB(int ID, int Usuario, int Relevancia, String Titulo, int Palavrachave){
		id = ID;
		usuario = Usuario;
		relevancia = Relevancia;
		titulo = Titulo;
		palavrachave = Palavrachave;
	}
	
	public ObraRB(String tit, int keyword){
		titulo = tit;
		palavrachave = keyword;
	}
	
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