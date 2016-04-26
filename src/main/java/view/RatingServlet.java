package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.RequestManager;
import control.recomendation.rate.RateUtil;
import model.RecommendRate;

/**
 * Servlet implementation class RatingWorkServlet
 * URL : {PATH}/obra/avaliar/{PARAMS}
 */
@WebServlet("/obra/avaliar")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Cria o pedido recomendacao
			RecommendRate recommend = RateUtil.getInstance().create(request);
			// Executa a avaliacaoa
			RequestManager.getInstance()
			.action(RequestManager.AVALIAR)
			.setResponse(response)
			.setRecommend(recommend)
			.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
