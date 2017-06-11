package control.learning.me.bazhenov.maxent;

import java.util.List;

import control.learning.ObraRB;

public class ClasseAuxiliar {

	private List<ObraRB> obras;
	private List obrasRestantes;
	
	public List<ObraRB> getObras() {
        return obras;
    }
	
    public void setObras(List<ObraRB> obras2) {
        this.obras = obras2;
    }
    
    public List getObrasR() {
        return obrasRestantes;
    }
    
    public void setObrasRestantes(List obrasRestantes) {
    	this.obrasRestantes = obrasRestantes;
    }
	
}
