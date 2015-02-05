package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link CallablesFactory factories} that produce a command of type
 * {@link PatchUserPasswordCommands}. Commands are {@link Callable} instances. This class extends
 * {@link ParsingCommand}
 *
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link User user}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPasswordCommandsFactory extends CommandFactory< String > {
    
    // INSTANCE FIELDS
    
    /**
     * {@code usersDatabase} - The users database that contains the user.
     */
    private final Database< User > usersDatabase;
    
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link PatchUserPasswordCommandsFactory} that produces commands of type
     * {@link PatchUserPasswordCommands}.
     * 
     * @param usersDatabase
     *            The database where to get the User from.
     * @throws InvalidArgumentException
     *             If the {@code usersDatabase} is null.
     */
    public PatchUserPasswordCommandsFactory( Database< User > usersDatabase )
        throws InvalidArgumentException {
        
        if( usersDatabase == null )
            throw new InvalidArgumentException(
                                                "Cannot instantiate post factory with null databases." );
        
        this.usersDatabase = usersDatabase;
    }
    
    /**
     * Method responsible for returning a command of the type {@link PatchUserPasswordCommand} after
     * getting the {@link PatchUP_ParsingCommand}.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * 
     * @return A {@link PatchUserPasswordCommand}
     * 
     * @throws MissingRequiredParameterException
     *             If one parameter is null or the empty string.
     */
    @Override
    protected Callable< String > internalNewCommand( Map< String, String > parametersMap )
        throws MissingRequiredParameterException {
        
        return new PatchUP_ParsingCommand( parametersMap ).newCommand();
    }
    
    
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command: the
     * name of the parameters that contain the user's username, old password and new password.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return new String[]{ CLIStringsDictionary.USERNAME, CLIStringsDictionary.OLDPASSWORD,
                            CLIStringsDictionary.NEWPASSWORD };
    }
    
    
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
        
        return "Change An User Password";
    }
    
    // INNER CLASS
    /**
     * Class that extends {@link ParsingCommand}, whose instances will parse the
     * {@code required parameters} and will create a {@link PatchUserPasswordCommand}
     */
    private class PatchUP_ParsingCommand extends ParsingCommand< String > {
        
        /**
         * The user's username.
         */
        private String username;
        
        /**
         * The old password (needed confirmation - {@code see PatchUserPasswordCommand} )
         */
        private String oldPassword;
        
        /**
         * The new user password that will be attributed to the user if the command is successful.
         */
        private String newPassword;
        
        /**
         * Create the {@code ParsingCommand}
         * 
         * @param parametersMap
         *            The container of the parameters required to create the command.
         * 
         * @throws MissingRequiredParameterException
         *             If one required parameter is null or the empty string.
         */
        public PatchUP_ParsingCommand( Map< String, String > parametersMap )
            throws MissingRequiredParameterException {
            
            super( parametersMap );
            
            setParametersFields();
        }
        
        
        /**
         * @return A command of type {@link PatchUserPasswordCommand}
         */
        @Override
        public Callable< String > newCommand() {
            
            try {
                return new PatchUserPasswordCommand( usersDatabase, username, oldPassword,
                                                     newPassword );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN PostUserCommandsFactory!" );
                // never happens cause databaseWhereToPost is not null
            }
        }
        
        
        /**
         * Set the username, oldPassword and newPassword fields needed to
         * {@code PatchUserPasswordCommands} command.
         * 
         * @throws MissingRequiredParameterException
         *             If one parameter is null or the empty string.
         */
        private void setParametersFields() throws MissingRequiredParameterException {
            
            username = getParameterAsString( CLIStringsDictionary.USERNAME );
            oldPassword = getParameterAsString( CLIStringsDictionary.OLDPASSWORD );
            newPassword = getParameterAsString( CLIStringsDictionary.NEWPASSWORD );
        }
    }
}
