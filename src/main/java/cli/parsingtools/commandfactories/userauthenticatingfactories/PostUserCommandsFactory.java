package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.commands.postcommands.PostUserCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link ParsingCommand factories} that produce commands of type
 * {@link PostUserPasswordCommands}. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link User user}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserCommandsFactory extends UserAuthenticatingFactory< User, CompletionStatus > {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    
    
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
    
        super( postingUsersDatabase, postedUsersDatabase );
        
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
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * @param userWhoIsPosting
     *            - The user who is posting the other user.
     *
     * @return A {@link PostUserCommand}
     * @throws MissingRequiredParameterException
     *             If one parameter is null or the empty string.
     */
    @Override
    protected Callable< CompletionStatus >
            internalInternalNewCommand( Map< String, String > parametersMap, User userWhoIsPosting )
                throws MissingRequiredParameterException {
    
        return new PostU_ParsingCommand( parametersMap, userWhoIsPosting ).newCommand();
    }
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command: the
     * name of the parameters that contain the user's properties.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getSpecificRequiredParametersNames() {
    
        return requiredParametersNames;
    }
    
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
    
        return "Adds a new user.";
    }
    
    
    // INNER CLASS
    /**
     * Class that extends {@link ParsingCommand}, whose instances will parse the
     * {@code required parameters} and will create a {@link PostUserCommand}
     */
    private class PostU_ParsingCommand extends ParsingCommand< CompletionStatus > {
        
        /**
         * The properties of the user to be added.
         */
        private String username;
        private String password;
        private String email;
        private String fullName;
        private User userWhoIsPosting;
        
        /**
         * Create the {@code ParsingCommand}
         * 
         * @param parametersMap
         *            The container of the parameters required to create the command.
         * @param userWhoIsPosting
         *            - The user who is posting the airship.
         * @throws MissingRequiredParameterException
         *             If one parameter is null or the empty string.
         */
        public PostU_ParsingCommand( Map< String, String > parametersMap, User userWhoIsPosting )
            throws MissingRequiredParameterException {
        
            super( parametersMap );
            this.userWhoIsPosting = userWhoIsPosting;
            
            setParametersFields();
        }
        
        /**
         * @return A command of type {@link PostUserCommand}
         */
        @Override
        public Callable< CompletionStatus > newCommand() {
        
            try {
                return new PostUserCommand( username, password, email, fullName, databaseToChange,
                                            userWhoIsPosting );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN PostUserCommandsFactory!" );
                // never happens cause databaseWhereToPost is not null
            }
        }
        
        /**
         * Set the username, password and email, and fullname fields needed for
         * {@code PostUserCommand} command.
         * 
         * @throws MissingRequiredParameterException
         *             If one parameter is null or the empty string.
         */
        private void setParametersFields() throws MissingRequiredParameterException {
        
            username = getParameterAsString( CLIStringsDictionary.USERNAME );
            password = getParameterAsString( CLIStringsDictionary.PASSWORD );
            email = getParameterAsString( CLIStringsDictionary.EMAIL );
            fullName = getParameterAsString( CLIStringsDictionary.FULLNAME );
        }
        
    }
}
