package control.recomendation.rate;

import model.RecommendParameters;

/**
 * Fabrica de criacao de recomendacoes
 * @author Thiago
 *
 */
public class RecommendFactory {
	public static RecommendParameters create(){
		return new RecommendParameters();
	}
}
