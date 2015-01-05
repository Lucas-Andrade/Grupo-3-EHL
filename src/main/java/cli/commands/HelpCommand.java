package main.java.cli.commands;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CommandParser;
import main.java.cli.exceptions.InvalidArgumentException;

/**
 *TODO:document
 */
public class HelpCommand implements Callable< Map< String, String > >
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
	
	public Map< String, String > call() {
		
		return cmdParser.getRegisteredCommands();
		//
		// String delimiter = "---------------------------------------------\n";
		// StringBuilder result = new StringBuilder( delimiter );
		// for( Map.Entry< String, String > entry : register.entrySet() )
		// {
		// result.append( " :)  " ).append( entry.getKey() ).append( "\n" )
		// .append( entry.getValue() ).append( "\n" )
		// .append( delimiter );
		// }
		// return result.toString();
		
	}
	
}
