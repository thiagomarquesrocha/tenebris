package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.factory.DaoFactory;
import control.factory.UserCommandFactory;
import control.factory.WorkCommandFactory;
import control.recomendation.profile.Principal;
import control.recomendation.rate.BackEndRating;
import control.recomendation.rate.BackEndRecommendation;
import model.MainCommand;
import model.RecommendRate;
/**
 * Gerenciador de Requisicoes 
 * @author Thiago
 *
 */
public class RequestManager {
	
	private static RequestManager instance = new RequestManager();
	public static final int RECOMENDAR_HIBRIDAMENTE = 1;
	public static final int RECOMENDAR_POR_AVALIACAO = 2;
	public static final int RECOMENDAR_POR_PERFIL = 3;
	public static final int AVALIAR = 4;
	public static final int OBRA = 5;
	public static final int SAIR = 6;
	public static final int LOGIN = 7;
	public static final int SESSAO_ABERTA = 8;
	public static final int CADASTRAR_USUARIO = 9;
	public static final int RECUPERAR_PERFIL_USUARIO = 10;
	public static final int CADASTRAR_INTERESSE = 11;
	public static final int ATUALIZAR_USUARIO_AREA = 12;
	public static final int LISTAR_INSTITUICOES = 13;
	public static final int CADASTRAR_NOVA_OBRA = 14;
	
	private int op;
	HttpServletResponse response;
	HttpServletRequest request;
	RecommendRate recommend;
	
	public synchronized static RequestManager getInstance() {
		return instance;
	}
	
	private RequestManager(){}
	
	public RequestManager action(int op) {
		this.op = op;
		return this;
	}

	public RequestManager setRecommend(RecommendRate recommendRate){
		this.recommend = recommendRate;
		return this;
	}
	
	public RequestManager setRequest(HttpServletRequest request) {
		this.request = request;
		return this;
	}

	public RequestManager setResponse(HttpServletResponse response) {
		this.response = response;
		return this;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}

	public RecommendRate getRecommend() {
		return recommend;
	}

	public void execute() throws Exception{
		switch (op){
			case RECOMENDAR_HIBRIDAMENTE:	
				new RecommendHybrid(this).execute();
				break;
			case RECOMENDAR_POR_AVALIACAO:	
				new BackEndRecommendation(response).BackEndR(recommend.getUserID(), recommend.getQtdR());
				break;
			case RECOMENDAR_POR_PERFIL:	
				System.out.println("Recomendando para o usuário : " + recommend.getUserID());
				new Principal(response).getRecommender(recommend.getUserID());
				break;
			case AVALIAR:
				BackEndRating.BackEndRat(recommend.getUserID(), recommend.getItemID(), recommend.getVarRating());
				break;
			case OBRA : // Visualiza uma obra
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.VIEW, CommandFacade.INVOKE_WORK, DaoFactory.WORK);
				break;
			case SAIR : // Executa o comando de desligar a autenticacao do usuario no sistema
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.LOGOUT, CommandFacade.INVOKE_USER, DaoFactory.USER);
				break;
			case LOGIN : // Executa o comando de autenticar o usuario
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.LOGIN, CommandFacade.INVOKE_USER, DaoFactory.USER);
				break;
			case SESSAO_ABERTA : // Executa o comando para verificar se existe uma sessao para o usuario
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.SESSION, CommandFacade.INVOKE_USER, DaoFactory.USER);
				break;
			case CADASTRAR_USUARIO : // Executa o comando de adicionar o usuario
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.ADD, CommandFacade.INVOKE_USER, DaoFactory.USER);
				break;
			case RECUPERAR_PERFIL_USUARIO : // Executa o comando de recuperar o perfil do usuario se ele ja estiver logado
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.PROFILE, CommandFacade.INVOKE_USER, DaoFactory.USER);
				break;
			case CADASTRAR_INTERESSE : // Cadastra um interesse { interesse, instituicao ou autores }
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.ADD_INTEREST, CommandFacade.FACTORY_USER, DaoFactory.USER);
				break;
			case ATUALIZAR_USUARIO_AREA : // Atualiza a area de um usuario
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.UPDATE_AREA, CommandFacade.FACTORY_USER, DaoFactory.USER);
				break;
			case LISTAR_INSTITUICOES : // Lista as instituicoes do banco
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.LIST_INSTITUTIONS, CommandFacade.FACTORY_USER, DaoFactory.USER);
				break;
			case CADASTRAR_NOVA_OBRA :  // Cadastrar nova obra
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.NEW, CommandFacade.FACTORY_WORK, DaoFactory.WORK);
				break;
		}
	}
	
	/**
	 * Executa um comando 
	 * @param factory
	 * @param command
	 * @param invoke
	 * @param user
	 */
	private MainCommand execute(int factory, String command, int invoke, int user){
		return CommandFacade.getInstance()
		.request(request, response)
		.factory(factory)
		.command(command)
		.invoke(invoke)
		.dao(DaoFactory.create(user))
		.execute();
	}
}