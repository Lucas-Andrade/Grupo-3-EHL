package parsingtools_tests.commandfactories.userauthenticatingfactories;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.userauthenticatingfactories.PatchUserPasswordCommandsFactory;
import utils.CommandStrings_Dictionary;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import commands.patchcommands.PatchUserPasswordCommand;
import databases.InMemoryUsersDatabase;
import elements.User;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


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
        
        parameters.put( CommandStrings_Dictionary.USERNAME, "Daniel" );
        parameters.put( CommandStrings_Dictionary.OLDPASSWORD, "pass" );
        parameters.put( CommandStrings_Dictionary.NEWPASSWORD, "dany" );
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
