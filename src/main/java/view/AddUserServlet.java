package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.ConnectionPool;
import control.RequestManager;

/**
 * Servlet implementation class AddUser
 * URL : {PATH}/usuario/adicionar/{PARAMS}
 */
@WebServlet(name = "AdicionarUsuario", urlPatterns = { "/usuario/adicionar" })
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
		// Recurepa a conexao
		con = ConnectionPool.getInstance().getConnection();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		createSavepoint();
		
		boolean back = false;
		
		try {
			doRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			back = true;
		} finally {
			executeRollback(back);
		}
	}

	private void createSavepoint() {
		try {
			con.setSavepoint();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void executeRollback(boolean back){
		try {
			if(back)
				con.rollback();
			else
				con.commit();
			
			con.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		createSavepoint();
		
		boolean back = false;
		
		try {
			doRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			back = true;
		} finally {
			executeRollback(back);
		}
	}
	
	private void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		// Executa o comando para cadastrar um usuário
		try {
			RequestManager.getInstance()
			.action(RequestManager.CADASTRAR_USUARIO)
			.setResponse(response)
			.setRequest(request)
			.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
