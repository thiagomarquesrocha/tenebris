package model;

public class Invoke {
	private Command command;

	public void setCommand(Command command) {
		this.command = command;
	}
	
	public void execute(){
		if(command == null) throw new IllegalAccessError("Nao existe nenhum comando para ser executado");
		try {
			((Command) command).execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
