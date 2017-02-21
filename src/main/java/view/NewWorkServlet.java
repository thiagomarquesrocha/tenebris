package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.RequestManager;

/**
 * Servlet implementation class NewWorkServlet
 * URL : {PATH}/obra/cadastrar{PARAMS}
 */
@WebServlet(name = "AdicionarObra", urlPatterns = { "/obra/cadastrar" })
public class NewWorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewWorkServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Executa o comando para cadastrar uma nova obra
		try {
			RequestManager.getInstance()
			.action(RequestManager.CADASTRAR_NOVA_OBRA)
			.setResponse(response)
			.setRequest(request)
			.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
