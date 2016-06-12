package control.factory;

import java.util.HashMap;

import control.MainCommand;

/**
 * Fabrica de Comandos - estrutura base 
 * @author Thiago
 *
 * @param <T>
 */
public abstract class CommandFactory<T> {
	
	public HashMap<String, T> commands = new HashMap<String, T>();
	
	public final T create(String command){
		if(commands.containsKey(command)){
			System.out.println("=> Objeto reaproveitado : " + command);
			return commands.get(command);
		}
		
		MainCommand c = (MainCommand) getCommand(command);
		
		commands.put(command, (T) c);
		
		return commands.get(command);
	}

	public abstract T getCommand(String command);

}
