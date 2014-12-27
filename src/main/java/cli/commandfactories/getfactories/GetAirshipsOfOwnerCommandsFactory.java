package main.java.cli.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.StringsDictionary;
import main.java.cli.commandfactories.CallablesFactory;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.getcommands.GetAirshipsOfOwnerCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;


/**
 * Class whose instances are {@link CallablesFactory factories} that produce
 * commands of type {@link GetAirshipsByOwnerCommand}. Commands are
 * {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsOfOwnerCommandsFactory extends
		StringsToCommandsFactory< Optional< Iterable< Airship > > >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The array of strings with the names of the parameters needed to produce
	 * the command.
	 */
	private final String[] requiredParametersNames;
	
	/**
	 * The database where to get the airships from.
	 */
	private final InMemoryAirshipsDatabase database;
	
	/**
	 * The username of the user whose airships are to get from {@link #database}
	 */
	private String ownerUsername;
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetAirshipsByOwnerCommandFactory} that produces
	 * commands of type {@link GetAirshipsByOwnerCommand}.
	 * 
	 * @param database
	 *            The database where to get the airships from.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public GetAirshipsOfOwnerCommandsFactory( InMemoryAirshipsDatabase database )
			throws InvalidArgumentException {
		
		super( "Gets all airships added by a certain user." );
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null database!" );
		
		this.requiredParametersNames = new String[]{ StringsDictionary.OWNER };
		this.database = database;
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Returns a command of type {@link GetAirshipsByOwnerCommand}.
	 * 
	 * @return A command of type {@link GetAirshipsByOwnerCommand}.
	 */
	@Override
	protected Callable< Optional< Iterable< Airship > >> internalNewInstance() {
		
		getOwnersUsernameValueOfTheParametersMap();
		try
		{
			return new GetAirshipsOfOwnerCommand( database, ownerUsername );
		}
		catch( InvalidArgumentException e )
		{ // never happens because database is not null
			return null;
		}
	}
	
	/**
	 * Returns an array of strings with name of the parameter needed to produce
	 * the command: the name of the parameter that contains the owner's
	 * username.
	 * 
	 * @return An array of strings with the name of the parameter that contains
	 *         the the owner's username.
	 */
	@Override
	protected String[] getRequiredParameters() {
		
		return requiredParametersNames;
	}
	
	
	
	// AUXILIARY PRIVATE METHODS
	/**
	 * Sets the value of the field {@link #ownerUsername} with the value
	 * received in the parameters map.
	 * <p>
	 * If this method is called inside {@link #internalNewInstance(Map)} and
	 * this one is called inside
	 * {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that
	 * the field {@link #ownerUsername} is non-{@code null} after this method
	 * finishes its job.
	 * </p>
	 */
	private void getOwnersUsernameValueOfTheParametersMap() {
		
		ownerUsername = getParameterAsString( requiredParametersNames[0] );
	}
}
