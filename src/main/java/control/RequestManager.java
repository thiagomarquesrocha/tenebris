package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.factory.DaoFactory;
import control.factory.KeywordsFactory;
import control.factory.LearningFactory;
import control.factory.UserCommandFactory;
import control.factory.WorkCommandFactory;
import control.learning.bayes.FrontEnd;
import control.recomendation.profile.Principal;
import control.recomendation.rate.BackEndRating;
import control.recomendation.rate.BackEndRecommendation;
import model.Learning;
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
	public static final int LISTAR_OBRAS_USUARIO = 15;
	public static final int ALTERAR_OBRA = 16;
	public static final int REMOVER_OBRA = 17;
	public static final int LISTAR_OBRAS_RECENTES = 18;
	public static final int LISTAR_TIPOS_OBRAS = 19;
	public static final int LISTAR_PALAVRASCHAVES = 20;
	public static final int SALVAR_APRENDIZAGEM = 21;
	public static final int PREDIZER_RELEVANCIA_OBRA = 22;
	
	private int op;
	HttpServletResponse response;
	HttpServletRequest request;
	RecommendRate recommend;
	private Learning learning;
	
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
	
	public RequestManager setLearning(Learning learning) {
		this.learning = learning;
		return this;
	}

	public void execute() throws Exception{
		switch (op){
			case RECUPERAR_PERFIL_USUARIO : // Executa o comando de recuperar o perfil do usuario se ele ja estiver logado
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.PROFILE, CommandFacade.INVOKE_USER, DaoFactory.USER);
				break;
			case RECOMENDAR_HIBRIDAMENTE: // Recomenda obras baseado nas duas tecnicas de recomendacao
				new RecommendHybrid(this).execute();
				break;
			case RECOMENDAR_POR_AVALIACAO:	// Recomenda uma obra baseado na avaliacao dos usuarios
				new BackEndRecommendation(response).BackEndR(recommend.getUserID(), recommend.getQtdR());
				break;
			case RECOMENDAR_POR_PERFIL:	// Recomenda uma obra baseado no perfil do usuario
				System.out.println("Recomendando para o usuário : " + recommend.getUserID());
				new Principal(response).getRecommender(recommend.getUserID());
				break;
			case LISTAR_OBRAS_RECENTES: // Listar obras recentes
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.LIST_RECENTS, CommandFacade.FACTORY_WORK, DaoFactory.WORK);
				break;
			case LISTAR_PALAVRASCHAVES: // Listar palavras chaves de uma obra
				execute(CommandFacade.FACTORY_KEYWORDS, KeywordsFactory.LIST_KEYWORDS, CommandFacade.FACTORY_KEYWORDS, DaoFactory.KEYWORDS);
				break;
			case SALVAR_APRENDIZAGEM: // Salva aprendizagem
				execute(CommandFacade.FACTORY_LEARNING, LearningFactory.SAVE_LEARNING, CommandFacade.FACTORY_LEARNING, DaoFactory.LEARNING);
				break;
			case PREDIZER_RELEVANCIA_OBRA : // Prediz se uma obra eh relevante
				FrontEnd.predict(response, learning.getUserId().intValue());
				break;
			case AVALIAR: // Avalia uma obra 
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
			case CADASTRAR_INTERESSE : // Cadastra um interesse { interesse, instituicao ou autores }
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.ADD_INTEREST, CommandFacade.FACTORY_USER, DaoFactory.USER);
				break;
			case ATUALIZAR_USUARIO_AREA : // Atualiza a area de um usuario
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.UPDATE_AREA, CommandFacade.FACTORY_USER, DaoFactory.USER);
				break;
			case LISTAR_INSTITUICOES : // Lista as instituicoes do banco
				execute(CommandFacade.FACTORY_USER, UserCommandFactory.LIST_INSTITUTIONS, CommandFacade.FACTORY_USER, DaoFactory.USER);
				break;
			case LISTAR_TIPOS_OBRAS : // Lista os tipos de obras
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.LIST_WORK_TYPES, CommandFacade.FACTORY_WORK, DaoFactory.WORK);
				break;
			case CADASTRAR_NOVA_OBRA :  // Cadastrar nova obra
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.NEW, CommandFacade.FACTORY_WORK, DaoFactory.WORK);
				break;
			case LISTAR_OBRAS_USUARIO: // Listar obras de um usuario
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.LIST_BY_USER, CommandFacade.FACTORY_WORK, DaoFactory.WORK);
				break;
			case ALTERAR_OBRA: // Alterar uma obra
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.UPDATE_WORK, CommandFacade.FACTORY_WORK, DaoFactory.WORK);
				break;
			case REMOVER_OBRA: // Remover uma obra
				execute(CommandFacade.FACTORY_WORK, WorkCommandFactory.REMOVE_WORK, CommandFacade.FACTORY_WORK, DaoFactory.WORK);
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