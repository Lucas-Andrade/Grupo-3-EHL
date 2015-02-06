package test.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.PatchUserPasswordCommandsFactory;
import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PatchUserPasswordCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPasswordCommandsFactory_Tests {
    
    private InMemoryUsersDatabase usersDatabase;
    private User user1;
    private Map< String, String > parameters;
    
    // Before
    
    @Before
    public void createUsersTheirDatabase() throws InvalidArgumentException {
    
        // Arrange
        user1 = new User( "Daniel", "pass", "@daniel" );
        
        usersDatabase = new InMemoryUsersDatabase( "Users Database" );
        
        usersDatabase.add( user1, user1 );
        
        parameters = new HashMap< String, String >();
        
        parameters.put( CLIStringsDictionary.USERNAME, "Daniel" );
        parameters.put( CLIStringsDictionary.OLDPASSWORD, "pass" );
        parameters.put( CLIStringsDictionary.NEWPASSWORD, "dany" );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommand()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException, InternalErrorException, Exception {
    
        Callable< ? > patchUserPasswordCommand =
                (new PatchUserPasswordCommandsFactory( usersDatabase )).newCommand( parameters );
        
        Assert.assertTrue( patchUserPasswordCommand instanceof PatchUserPasswordCommand );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPatchUserPasswordCommandsFactoryGivenANullUsersDatabase()
                throws InvalidArgumentException {
    
        new PatchUserPasswordCommandsFactory( null );
    }
}
