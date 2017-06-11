package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.RequestManager;

/**
 * Servlet implementation class AddUser
 * URL : {PATH}/usuario/atualizar/area{PARAMS}
 */
@WebServlet("/usuario/atualizar/area")
public class UpdateUserRecommendationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserRecommendationServlet() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	private void doRequest(HttpServletRequest request, HttpServletResponse response) {
		// Executa o comando para cadastrar um usuário
		try {
			RequestManager.getInstance()
			.action(RequestManager.ATUALIZAR_USUARIO_AREA)
			.setResponse(response)
			.setRequest(request)
			.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
