package test.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.PostAirshipCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.domain.commands.postcommands.PostCivilAirshipCommand;
import main.java.domain.commands.postcommands.PostMilitaryAirshipCommand;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PostAirshipCommandsFactory}
 * {@link UserAuthenticatingFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommandsFactory_Tests {
    
    private static InMemoryUsersDatabase usersDatabase;
    private static InMemoryAirshipsDatabase airshipsDatabase;
    private Map< String, String > parameters;
    private static User user;
    
    // Before Class
    
    @BeforeClass
    public static void createUsersAndAirshipsDatabaseAndAddAUser()
        throws InvalidRegisterException, InvalidArgumentException {
        
        usersDatabase = new InMemoryUsersDatabase( "Users Database" );
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        user = new User( "Daniel", "pass", "@d" );
        
        usersDatabase.add( user, user );
    }
    
    // Before
    
    @Before
    public void createParametersMap() {
        
        parameters = new HashMap<>();
        
        parameters.put( CLIStringsDictionary.LOGINNAME, "Daniel" );
        parameters.put( CLIStringsDictionary.LOGINPASSWORD, "pass" );
        
        parameters.put( CLIStringsDictionary.LATITUDE, "0" );
        parameters.put( CLIStringsDictionary.LONGITUDE, "0" );
        parameters.put( CLIStringsDictionary.ALTITUDE, "0" );
        parameters.put( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE, "10" );
        parameters.put( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE, "0" );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommands()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException, InternalErrorException, Exception {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Civil" );
        parameters.put( CLIStringsDictionary.NUMBEROFPASSENGERS, "20" );
        
        Callable< ? > postCivilAirshipCommand =
                (new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase )).newCommand( parameters );
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Military" );
        parameters.put( CLIStringsDictionary.HASARMOUR, "yes" );
        
        Callable< ? > postMilitaryAirshipCommand =
                (new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase )).newCommand( parameters );
        
        Assert.assertTrue( postCivilAirshipCommand instanceof PostCivilAirshipCommand );
        Assert.assertTrue( postMilitaryAirshipCommand instanceof PostMilitaryAirshipCommand );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPostAirshipCommandGivingANullUserDatabase()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Civil" );
        parameters.put( CLIStringsDictionary.LOGINNAME, "Daniel" );
        
        new PostAirshipCommandsFactory( null, airshipsDatabase ).newCommand( parameters );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPostAirshipCommandGivingANullAirshipDatabase()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Civil" );
        parameters.put( CLIStringsDictionary.LOGINNAME, "Daniel" );
        
        new PostAirshipCommandsFactory( usersDatabase, null ).newCommand( parameters );
    }
    
    @Test( expected = NoSuchElementInDatabaseException.class )
    public
            void
            shouldThrowNoSuchElementInDatabaseExceptionWhenTryingToCreateAnAirshipCommandGivingAnNonExistingUser()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Civil" );
        parameters.put( CLIStringsDictionary.LOGINNAME, "Pedro" );
        
        new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ).newCommand( parameters );
    }
    
    @Test( expected = WrongLoginPasswordException.class )
    public
            void
            shouldThrowWrongLoginPasswordExceptionWhenTryingToCreateAnAirshipCommandGivingTheWrongUserPassword()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Civil" );
        parameters.put( CLIStringsDictionary.LOGINPASSWORD, "ola" );
        
        new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ).newCommand( parameters );
    }
    
    @Test( expected = MissingRequiredParameterException.class )
    public
            void
            shouldThrowMissingRequiredParameterExceptionWhenTryingToCreateAPostCivilAirshipCommandWithoutAllTheParameters()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Civil" );
        
        new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ).newCommand( parameters );
    }
    
    @Test( expected = MissingRequiredParameterException.class )
    public
            void
            shouldThrowMissingRequiredParameterExceptionWhenTryingToCreateAPostMilitaryAirshipCommandWithoutAllTheParameters()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Military" );
        
        new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ).newCommand( parameters );
    }
    
    @Test( expected = InvalidParameterValueException.class )
    public
            void
            shouldThrowInvalidParameterValueExceptionWhenTryingToCreateAPostAnAirshipCommandWithAnInvalidSpecificParameters()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "Civil" );
        parameters.put( CLIStringsDictionary.NUMBEROFPASSENGERS, "ola" );
        
        new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ).newCommand( parameters );
    }
    
    @Test( expected = InvalidParameterValueException.class )
    public
            void
            shouldThrowInvalidParameterValueExceptionWhenTryingToCreateAPostAnAirshipCommandWithAnInvalidTypeParameters()
                throws InvalidArgumentException, NoSuchElementInDatabaseException,
                MissingRequiredParameterException, InvalidParameterValueException,
                WrongLoginPasswordException, InternalErrorException {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "ola" );
        parameters.put( CLIStringsDictionary.NUMBEROFPASSENGERS, "20" );
        
        new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase ).newCommand( parameters );
    }
}