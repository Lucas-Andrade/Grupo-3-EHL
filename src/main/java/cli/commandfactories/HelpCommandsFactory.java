package main.java.cli.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CommandParser;
import main.java.cli.commands.HelpCommand;
import main.java.cli.exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link CallablesFactory factories} that produce
 * commands of type {@link HelpCommand}. Commands are {@link Callable}
 * instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class HelpCommandsFactory extends
		StringsToCommandsFactory< Map< String, String > >
{
	
	// INSTANCE FIELD
	/**
	 * The command parser that contains a registry of the commands whose
	 * descriptions are to be returned by the command created by this factory.
	 */
	private CommandParser cmdParser;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link HelpCommandsFactory} that produces commands of type
	 * {@link HelpFactory}.
	 * 
	 * @param cmdParser
	 *            The command parser with the registry of the commands whose
	 *            descriptions are to be returned by the {@link HelpCommand}s
	 *            produced by this factory.
	 * @throws InvalidArgumentException
	 *             If {@code cmdParser==null}.
	 */
	public HelpCommandsFactory( CommandParser cmdParser )
			throws InvalidArgumentException {
		
		super( "Returns the descriptions of known commands." );
		
		if( cmdParser == null )
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null parser." );
		this.cmdParser = cmdParser;
	}
	
	
	
	// PUBLIC METHODS
	
	/**
	 * Returns a command of type {@link HelpCommand}.
	 * 
	 * @return A command of type {@link HelpCommand}.
	 */
	public final Callable< Map< String, String > > internalNewInstance() {
		
		try
		{
			return new HelpCommand( cmdParser );
		}
		catch( InvalidArgumentException e )
		{// never happens cause cmdParser is not null
			return null;
		}
	}
	
	/**
	 * Returns an array of strings with the name of the parameters needed to produce
	 * {@link HelpCommand}s: {@code null} because factories of this type need no
	 * parameters from the string-commands.
	 * 
	 * @return {@code null}.
	 */
	@Override
	protected String[] getRequiredParameters() {
		return null;
	}
	
}