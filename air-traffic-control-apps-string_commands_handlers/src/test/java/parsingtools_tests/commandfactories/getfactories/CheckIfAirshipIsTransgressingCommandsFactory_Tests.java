package parsingtools_tests.commandfactories.getfactories;


import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parsingtools.CommandParser;
import parsingtools.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import utils.StringCommands_Executor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import commands.getcommands.CheckIfAirshipIsTransgressingCommand;
import databases.InMemoryAirshipsDatabase;
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
 * {@link CheckIfAirshipIsTransgressingCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommandsFactory_Tests {
    
    private static CommandParser cmdparser = new CommandParser();
    private static InMemoryAirshipsDatabase airshipsDatabase;
    
    // Before Class
    
    @BeforeClass
    public static void createTheCommandParserAndRegisterTheCommands()
        throws InvalidRegisterException, InvalidArgumentException {
        
        cmdparser = new CommandParser();
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        cmdparser.registerCommand( "GET",
                                   "/airships/reports/{flightId}",
                                   new CheckIfAirshipIsTransgressingCommandsFactory(
                                                                                     airshipsDatabase ) );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommand()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException, InternalErrorException, Exception {
        
        Callable< ? > checkIfAirshipIsTransgressingCommand =
                (new StringCommands_Executor( cmdparser, "GET", "/airships/reports/id20" )).getCommand();
        
        Assert.assertTrue( checkIfAirshipIsTransgressingCommand instanceof CheckIfAirshipIsTransgressingCommand );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateACheckIfAirshipIsTransgressingCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new CheckIfAirshipIsTransgressingCommandsFactory( null );
    }
}
