package control.recomendation.profile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import model.Obra;

public class Usuario {
	public List<Obra> Lista(HttpServletResponse response, int userId, ArrayList<String> listaDocumentos, int quantidade) throws SQLException{
		//Essa clase tem apenas um metodo, no qual esse tem finalidade de analisar os documentos e ver qual se repete mais na lista
		//sendo esse, logicamente, o mais similar ao perfil do usuario.
		JSON resultado = new JSON();//Objeto criado para enviar o documento para a classe JSON, que ser� responsavel por deixar
		                            //os documentos de saida no padr�o JSON.
		boolean cond;
		int cont;
		ArrayList<String> aux1 = new ArrayList<String>();
		ArrayList<String> aux2 = new ArrayList<String>();
		ArrayList<String> aux3 = new ArrayList<String>();
		for(int i = 0; i < listaDocumentos.size(); i++){
			cont = 0;
			cond = true;
			for(int j = 0; j < aux1.size(); j++){
				if(listaDocumentos.get(i).equals(aux1.get(j))){
					cond = false;
				}
			}
			if(cond){
				for(int j = 0; j < listaDocumentos.size(); j++){
					if(listaDocumentos.get(i).equals(listaDocumentos.get(j))){
						cont++;
					}
				}
				aux1.add(listaDocumentos.get(i));
				aux2.add("" + cont);
			}
		}
		
		int[] vetor = new int[aux2.size()];
		for(int i = 0; i < aux2.size(); i++){
			vetor[i] = Integer.parseInt(aux2.get(i));
		}
		
		for(int i = 0; i < vetor.length; i++){
			for(int j = 0; j < vetor.length; j++){
				if(vetor[i] > vetor[j]){
					int aux = vetor[i];
					vetor[i] = vetor[j];
					vetor[j] = aux;
				}
			}
		}
		aux3.add("" + vetor[0]);
		for(int i = 0; i < vetor.length; i++){
			cond = true;
			for(int j = 0; j < aux3.size(); j++){
				if(vetor[i] == Integer.parseInt(aux3.get(j))){
					cond = false;
				}
			}
			if(cond){
				aux3.add("" + vetor[i]);
			}
		}
		
		List<Obra> list = new ArrayList<>();
		cont = 0;
		for(int i = 0; i < aux3.size(); i++){
			for(int j = 0; j < aux2.size(); j++){
				if(aux3.get(i).equals(aux2.get(j))){
					System.out.println((String)aux1.get(j));
					Obra obra = resultado.Print((String)aux1.get(j), userId);
					list.add(obra);
					cont++;
				}
				if(cont > (quantidade - 1)){
					break;
				}
			}
			if(cont > (quantidade - 1)){
				break;
			}
		}
		
	    return list;
	}
}
