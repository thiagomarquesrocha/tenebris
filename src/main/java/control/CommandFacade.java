package control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.factory.KeywordsFactory;
import control.factory.LearningFactory;
import control.factory.UserCommandFactory;
import control.factory.WorkCommandFactory;
import control.keywords.KeywordsInvoke;
import control.learning.LearningInvoke;
import control.user.UserInvoke;
import control.work.WorkInvoke;
import model.Dao;
import model.Servlet;

public class CommandFacade {
	
	// Invocador de comandos
	public static final int INVOKE_USER = 1;
	public static final int INVOKE_WORK = 2;
	public static final int INVOKE_KEYWORDS = 3;
	public static final int INVOKE_LEARNING = 4;

	// Fabricas de comandos
	public static final int FACTORY_USER = 1;
	public static final int FACTORY_WORK = 2;
	public static final int FACTORY_KEYWORDS = 3;
	public static final int FACTORY_LEARNING = 4;
	
	private static CommandFacade instance = new CommandFacade();
	private Invoke invoke; // Invocador do comando
	private MainCommand command; // Comando que sera executado
	private int factory; // Fabrica de objetos

	public synchronized static CommandFacade getInstance() {
		return instance;
	}
	
	public CommandFacade request(HttpServletRequest request, HttpServletResponse response){
		Servlet.getInstance().setRequest(request).setResponse(response);
		return this;
	}
	
	public CommandFacade factory(int factory){
		this.factory = factory;
		return this;
	}
	
	public CommandFacade command(String command){
		switch (factory) {
			case FACTORY_USER:
				this.command = UserCommandFactory.getInstance().create(command);
				break;
			case FACTORY_WORK :
				this.command = WorkCommandFactory.getInstance().create(command);
				break;
			case FACTORY_KEYWORDS :
				this.command = KeywordsFactory.getInstance().create(command);
				break;
			case FACTORY_LEARNING :
				this.command = LearningFactory.getInstance().create(command);
			default:
				break;
		}
	
		return this;
	}
	
	public CommandFacade dao(Dao dao){
		command.setDao(dao);
		return this;
	}
	
	public CommandFacade invoke(int invoke){
		switch (invoke) {
			case INVOKE_USER:
				this.invoke = UserInvoke.getInstance();
				break;
			case INVOKE_WORK:
				this.invoke = WorkInvoke.getInstance();
				break;
			case INVOKE_KEYWORDS:
				this.invoke = KeywordsInvoke.getInstance();
				break;
			case INVOKE_LEARNING :
				this.invoke = LearningInvoke.getInstance();
				break;
			default:
				break;
		}
		return this;
	}
	
	public MainCommand execute(){
		invoke.setCommand(command);
		invoke.execute();
		return command;
	}
}
