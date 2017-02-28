package control.learning;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

public class FrontEnd {
	
	public static void predict(HttpServletResponse response, int user, int item){
		Exemplo ex = new Exemplo(response);
		//System.out.println("Aqui estou");
		try {
			ex.Metodo(user, item);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
