package parsingtools_tests.commandfactories.getfactories;


import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parsingtools.CommandParser;
import parsingtools.Parser;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetElementByIdentificationCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import commands.getcommands.GetElementFromADatabaseByIdCommand;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
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
 * {@link GetElementByIdentificationCommandsFactory}
 * {@link GetUserByUsernameCommandsFactory}
 * {@link GetAirshipByFlightIdCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetByIdCommandsFactories_Tests {
    
    private static CommandParser cmdparser = new CommandParser();
    private static InMemoryUsersDatabase userDatabase;
    private static InMemoryAirshipsDatabase airshipsDatabase;
    
    // Before Class
    
    @BeforeClass
    public static void createTheCommandParserAndRegisterTheCommands()
        throws InvalidRegisterException, InvalidArgumentException {
        
        cmdparser = new CommandParser();
        
        userDatabase = new InMemoryUsersDatabase( "Users Database" );
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        cmdparser.registerCommand( "GET", "/airships/{flightId}",
                                   new GetAirshipByFlightIdCommandsFactory( airshipsDatabase ) );
        
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( userDatabase ) );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommands()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException, InternalErrorException, Exception {
        
        Callable< ? > getUserByUsernameCommand =
                (new Parser( cmdparser, "GET", "/users/{username}" )).getCommand();
        
        Callable< ? > getAirshipByFlightIdCommand =
                (new Parser( cmdparser, "GET", "/airships/{flightId}" )).getCommand();
        
        Assert.assertTrue( getUserByUsernameCommand instanceof GetElementFromADatabaseByIdCommand );
        Assert.assertTrue( getAirshipByFlightIdCommand instanceof GetElementFromADatabaseByIdCommand );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipByFlightIdCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAirshipByFlightIdCommandsFactory( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetUserByUsernameCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetUserByUsernameCommandsFactory( null );
    }
}
