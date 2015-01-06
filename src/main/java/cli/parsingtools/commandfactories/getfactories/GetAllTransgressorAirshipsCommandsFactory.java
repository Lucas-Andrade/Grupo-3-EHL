package main.java.cli.parsingtools.commandfactories.getfactories;


import java.util.concurrent.Callable;
import main.java.cli.commands.getcommands.GetAllTransgressorAirshipsCommand;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.utils.Optional;
import main.java.cli.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that
 * produce commands of type {@link GetAllTransgressorAirshipsCommand}. Commands
 * are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressorAirshipsCommandsFactory extends
		StringsToCommandsFactory< Optional< Iterable< Airship >> >
{
	
	// INSTANCE FIELDS
	/**
	 * The database where to search.
	 */
	private final InMemoryAirshipsDatabase database;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetAllElementsInADatabaseCommandFactory factory}
	 * that produces commands of type {@link GetAllTransgressorAirshipsCommand}.
	 * 
	 * @param database
	 *            The database where to get the elements from.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public GetAllTransgressorAirshipsCommandsFactory(
			InMemoryAirshipsDatabase database ) throws InvalidArgumentException {
		
		super( "Gets all airships that are transgressing their air corridors." );
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null database!" );
		
		this.database = database;
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Returns a command of type {@link GetAllTransgressorAirshipsCommand}.
	 * 
	 * @return A command of type {@link GetAllTransgressorAirshipsCommand}.
	 */
	@Override
	protected Callable< Optional< Iterable< Airship >>> internalNewInstance() {
		
		try
		{
			return new GetAllTransgressorAirshipsCommand( database );
		}
		catch( InvalidArgumentException e )
		{ // never happens because database is not null
			return null;
		}
	}
	
	/**
	 * Returns an array of strings with the name of the parameters needed to
	 * produce the command: {@code null} because factories of this type need no
	 * parameters to create their commands.
	 * 
	 * @return {@code null}.
	 */
	@Override
	protected String[] getRequiredParameters() {
		
		return null;
	}
	
}
