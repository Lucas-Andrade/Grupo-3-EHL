package parsingtools.commandfactories.getfactories.getbyidfactories;


import java.util.concurrent.Callable;
import parsingtools.commandfactories.CommandFactory;
import utils.StringCommandsDictionary;
import databases.Database;
import elements.User;
import exceptions.InvalidArgumentException;


/**
 * A {@link CommandFactory factory} that creates commands to get a user with a certain username from
 * a users database. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetElementByIdentificationCommandsFactory} of {@link User Users}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetUserByUsernameCommandsFactory extends
        GetElementByIdentificationCommandsFactory< User > {
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetUserByUsernameCommandFactory} that produces commands to get a user
     * with a certain username from {@code usersDatabase}. That username is the value of the
     * parameter with key {@link StringCommandsDictionary#USERNAME} received in the parameters map.
     * 
     * @param usersDatabase
     *            - The database where to get the user from.
     * 
     * @throws InvalidArgumentException
     *             If {@code usersDatabase} is {@code null}.
     */
    public GetUserByUsernameCommandsFactory( Database< User > usersDatabase )
        throws InvalidArgumentException {
        
        super( StringCommandsDictionary.USERNAME, usersDatabase );
    }
    
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
        
        return "Gets a user with a certain username.";
    }
}
