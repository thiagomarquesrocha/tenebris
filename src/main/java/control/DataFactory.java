package control;

import model.JSONData;

/**
 * Fabrica de objetos do tipo JSON
 * @author ACER
 *
 */
public class DataFactory {
	public static JSONData create(){
		return new JSONData().create();
	}
}
