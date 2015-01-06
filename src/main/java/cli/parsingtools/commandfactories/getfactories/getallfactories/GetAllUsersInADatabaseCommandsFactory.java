package main.java.cli.parsingtools.commandfactories.getfactories.getallfactories;


import java.util.concurrent.Callable;
import main.java.cli.model.Database;
import main.java.cli.model.users.User;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.utils.exceptions.InvalidArgumentException;


/**
 * A {@link StringsToCommandsFactory factory} that creates commands to get all the users
 * in an users database. Commands are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllUsersInADatabaseCommandsFactory extends
		GetAllElementsInADatabaseCommandsFactory< User >
{
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetAllUsersFromADatabaseCommandFactory factory} that
	 * produces commands to get all the users in {@code database}.
	 * 
	 * @param database
	 *            The database where to get the users from.
	 * @throws InvalidArgumentException
	 *             If {@code database} is {@code null}.
	 */
	public GetAllUsersInADatabaseCommandsFactory( Database< User > database )
			throws InvalidArgumentException {
		
		super( "Gets the list of all users.", database );
	}
}