package control.recomendation.rate;

import model.RecommendRate;

/**
 * Fabrica de criacao de recomendacoes
 * @author Thiago
 *
 */
public class RecommendFactory {
	public static RecommendRate create(){
		return new RecommendRate();
	}
}
