package model;

public class Obra {
	
    private Long id;
    private String instituicao;
    private String area;
    private String autor;
    private String titulo;
    private String resumo;
    private String data;
	private int avaliacao;
	private String file;
	private float media;
	private Integer type;
	
	public static Obra newInstance(){
		return new Obra();
	}
 
	public Long getid() {
        return id;
    }
	
    public void setid(Long id) {
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
    	this.data = data;
    }

	public void setAvaliacao(int i) {
		this.avaliacao = i;
	}
	
	public int getAvaliacao(){
		return avaliacao;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	public String getFile(){
		return file;
	}

	public void setMedia(float media) {
		this.media = media;
	}

	public float getMedia() {
		return media;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
    
}