package main.java.cli.commandfactories.getfactories.getbyidfactories;


import java.util.concurrent.Callable;
import main.java.cli.CommandLineDictionary;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.users.User;


/**
 * A {@link StringsToCommandsFactory factory} that creates commands to get a user with a
 * certain username from a users database. Commands are {@link Callable}
 * instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetUserByUsernameCommandsFactory extends
		GetElementByIdentificationCommandsFactory< User >
{
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetUserByUsernameCommandFactory} that produces
	 * commands to get a user with a certain username from {@code database}.
	 * That username is the value of the parameter with key
	 * {@link CommandLineDictionary#USERNAME} received in the
	 * parameters map.
	 * 
	 * @param database
	 *            The database where to get the user from.
	 * @throws InvalidArgumentException
	 *             If {@code database} is {@code null}.
	 */
	public GetUserByUsernameCommandsFactory( Database< User > database )
			throws InvalidArgumentException {
		
		super( "Gets a user with a certain username.",
				CommandLineDictionary.USERNAME, database );
	}
}