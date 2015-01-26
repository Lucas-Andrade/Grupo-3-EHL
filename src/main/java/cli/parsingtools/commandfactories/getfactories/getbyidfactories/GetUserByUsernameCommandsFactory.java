package main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories;


import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * A {@link StringsToCommandsFactory factory} that creates commands to get a user with a certain
 * username from a users database. Commands are {@link Callable} instances.
 * 
 * Extends {@link StringsToCommandsFactory} of {@link User Users}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetUserByUsernameCommandsFactory extends
        GetElementByIdentificationCommandsFactory< User > {
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetUserByUsernameCommandFactory} that produces commands to get a user
     * with a certain username from {@code usersDatabase}. That username is the value of the
     * parameter with key {@link CLIStringsDictionary#USERNAME} received in the parameters map.
     * 
     * @param usersDatabase
     *            - The database where to get the user from.
     * 
     * @throws InvalidArgumentException
     *             If {@code usersDatabase} is {@code null}.
     */
    public GetUserByUsernameCommandsFactory( Database< User > usersDatabase )
        throws InvalidArgumentException {
        
        super( "Gets a user with a certain username.", CLIStringsDictionary.USERNAME, usersDatabase );
    }
}