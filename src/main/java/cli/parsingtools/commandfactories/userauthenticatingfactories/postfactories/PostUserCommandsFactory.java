package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.postfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.domain.commands.postcommands.PostUserCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands of
 * type {@link PostUserPasswordCommands}. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link User user}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserCommandsFactory extends UserAuthenticatingFactory< User, String > {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * The properties of the user to be added.
     */
    private String username;
    private String password;
    private String email;
    private String fullName;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link PostUserCommandsFactory} that produces commands of type
     * {@link PostUserCommand}.
     * 
     * @param postingUsersDatabase
     *            The database with the user who is posting.
     * @param postedUsersDatabase
     *            The database where to post the new user.
     * @throws InvalidArgumentException
     *             If the {@code database} is null.
     */
    public PostUserCommandsFactory( Database< User > postingUsersDatabase,
                                    Database< User > postedUsersDatabase )
        throws InvalidArgumentException {
        
        super( "Adds a new user.", postingUsersDatabase, postedUsersDatabase );
        
        this.requiredParametersNames =
                new String[]{ CLIStringsDictionary.USERNAME, CLIStringsDictionary.PASSWORD,
                             CLIStringsDictionary.EMAIL };
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM PostCommandsFactory
    
    /**
     * Returns a command of type {@link PostUserCommand} after getting the necessary
     * {@code required parameters} using the private auxiliar method
     * {@link #setValuesOfTheParametersMap()}.
     *
     * @param userWhoIsPosting
     *            - The user who is posting the other user.
     *
     * @return A {@link PostUserCommand}
     */
    @Override
    protected Callable< String > internalInternalNewInstance( User userWhoIsPosting ) {
        
        setValuesOfTheParametersMap();
        
        try {
            return new PostUserCommand( username, password, email, fullName, databaseToChange,
                                        userWhoIsPosting );
            
        }
        catch( InvalidArgumentException e ) {// never happens cause databaseWhereToPost is not null
            return null;
        }
        
    }
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command: the
     * name of the parameters that contain the user's properties.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getSpecificRequiredParameters() {
        
        return requiredParametersNames;
    }
    
    // PRIVATE AUXILIAR METHOD - used in the method postsInternalNewInstance()
    
    /**
     * Sets the value of the user's properties fields with the values received in the parameters
     * map.
     * <p>
     * If this method is called inside {@link #internalNewInstance(Map)} and this one is called
     * inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that the fields
     * {@link #username}, {@link #password} and {@link #email} are non-{@code null} after this
     * method finishes its job.
     * </p>
     */
    private void setValuesOfTheParametersMap() {
        
        username = getParameterAsString( CLIStringsDictionary.USERNAME );
        password = getParameterAsString( CLIStringsDictionary.PASSWORD );
        email = getParameterAsString( CLIStringsDictionary.EMAIL );
        fullName = getParameterAsString( CLIStringsDictionary.FULLNAME );
    }
}