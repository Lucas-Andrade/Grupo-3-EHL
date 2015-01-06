package main.java.cli.commands;
import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.utils.OptionsList;
import main.java.cli.utils.exceptions.InvalidArgumentException;

/**
 *TODO:document
 */
public class HelpCommand implements Callable< OptionsList >
{
	
	// INSTANCE FIELD
	/**
	 * The command parser that contains a registry of the commands whose
	 * descriptions are to be returned by the command created by this factory.
	 */
	private CommandParser cmdParser;
	
	public HelpCommand( CommandParser cmdParser ) throws InvalidArgumentException {
		
		if( cmdParser == null )
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null parser." );
		this.cmdParser = cmdParser;
	}
	
	public OptionsList call() {
		
		return new OptionsList(cmdParser.getRegisteredCommands());		
	}
	
}
