package control.Commands;

import logic.Game;
import logic.Exceptions.CommandExecuteException;
import logic.Exceptions.CommandParseException;
//import logic.Exceptions.NumberFormatException;


public abstract class Command {
	
	private static final String incArgsMsg = "Incorrect number of arguments";
	protected final String name;
	protected final String shortcut;
	private final String details; 
	private final String help;

	//  protected static final String incorrectNumberOfArgsMsg = "Incorrect number of arguments";
	 // protected static final String incorrectArgsMsg = "Incorrect arguments format";
	  
	public Command(String name,  String shortcut, String details, String help){    
		this.name = name;
	    this.shortcut = shortcut;
	    this.details = details;
	    this.help = help;
	  }
	  
	 public abstract boolean execute(Game game) throws CommandExecuteException;
	  
	 public abstract Command parse(String[] commandWords) throws NumberFormatException, CommandParseException;
	  
	 protected boolean matchCommandName(String name) {
		    return this.shortcut.equalsIgnoreCase(name) || 
		        this.name.equalsIgnoreCase(name);
	  }
	  
	 protected Command parseParamsCommand(String[] words, int args) throws CommandParseException{
			
			if (matchCommandName(words[0])) {
				if (words.length != args) {
					throw new CommandParseException("[ERROR]: Command "+ name + " :" + incArgsMsg);
				}
				return this;
			}
			
			return null;
	  }
	  
	  protected Command parseNoParamsCommand(String[] words) throws CommandParseException{
	
			if (matchCommandName(words[0])) {
				if (words.length != 1) {
					throw new CommandParseException("[ERROR]: Command "+ name + " :" + incArgsMsg);					
				}
				else return this;
			}
			
			return null;
	  }
	  
	  public String helpText(){
	    return details + ": " + help;
	  }
	  
	  public boolean isNumeric(String string) {
	    	boolean resultado;
	        try {
	            Integer.parseInt(string);
	            resultado = true;
	        } catch (NumberFormatException excepcion) {
	            resultado = false;
	        }
	 
	        return resultado;
	    }
	  
	  public String getArgsMsg() {
		  return incArgsMsg;
	  }
}
