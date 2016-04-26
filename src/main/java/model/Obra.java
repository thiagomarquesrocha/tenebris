package model;

public class Obra {
	
    private int id;
    private String instituicao;
    private String area;
    private String autor;
    private String titulo;
    private String resumo;
    private String data;
	private int avaliacao;
 
	public int getid() {
        return id;
    }
	
    public void setid(int id) {
        this.id = id;
    }
    public String getinstituicao() {
        return instituicao;
    }
    public void setinstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    public String getarea() {
        return area;
    }
    public void setarea(String area) {
        this.area = area;
        
    }    public String getautor() {
        return autor;
    }
    public void setautor(String autor) {
        this.autor = autor;
    
    }
    
    public String gettitulo() {
        return titulo;
    }
    public void settitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getresumo() {
    	return resumo;
    }

    public void setresumo(String resumo) {
    	this.resumo = resumo;
    }

    public String getdata() {
    	return data;
    }
    
    public void setdata(String data) {
    	this.resumo = data;
    }

	public void setAvaliacao(int i) {
		this.avaliacao = i;
	}
	
	public int getAvaliacao(){
		return avaliacao;
	}
    
}