package parsingtools_tests.commandfactories.userauthenticatingfactories;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.userauthenticatingfactories.PostAirshipCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import utils.CLIStringsDictionary;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import commands.postcommands.PostCivilAirshipCommand;
import commands.postcommands.PostMilitaryAirshipCommand;
import databases.InMemoryAirshipsDatabase;
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
    
    // Test Normal Dynamic And Prerequisites
    
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
    public void
            shouldThrowInvalidParameterValueExceptionIfPostingAirshipWithAnInvalidTypeParameter()
                throws Exception {
        
        parameters.put( CLIStringsDictionary.AIRSHIP_TYPE, "ola" );
        parameters.put( CLIStringsDictionary.NUMBEROFPASSENGERS, "20" );
        
        CommandFactory< ? > a = new PostAirshipCommandsFactory( usersDatabase, airshipsDatabase );
        a.newCommand( parameters );
    }
}
