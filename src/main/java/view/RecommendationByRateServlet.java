package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.RequestManager;
import control.recomendation.rate.RecommendRequest;
import model.RecommendRate;

/**
 * Servlet implementation class RecommendationServlet
 * URL : {PATH}/obra/recomendacao/avaliacao/{PARAMS}
 */
@WebServlet("/obra/recomendacao/avaliacao")
public class RecommendationByRateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendationByRateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Recomendar por avaliacao ");
		try {
			// Cria o pedido recomendacao
			RecommendRate recommend = RecommendRequest.getInstance().create(request);
			// Executa a recomendacao
			RequestManager.getInstance()
			.action(RequestManager.RECOMENDAR_POR_AVALIACAO)
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
