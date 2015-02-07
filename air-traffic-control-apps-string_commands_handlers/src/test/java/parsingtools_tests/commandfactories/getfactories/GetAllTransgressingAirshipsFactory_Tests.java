package parsingtools_tests.commandfactories.getfactories;


import java.util.concurrent.Callable;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import parsingtools.CommandParser;
import parsingtools.Parser;
import parsingtools.commandfactories.getfactories.GetAllTransgressingAirshipsCommandsFactory;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import commands.getcommands.GetAllTransgressingAirshipsCommand;
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
 * {@link GetAllTransgressingAirshipsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressingAirshipsFactory_Tests {
    
    private static CommandParser cmdparser = new CommandParser();
    private static InMemoryAirshipsDatabase airshipsDatabase;
    
    // Before Class
    
    @BeforeClass
    public static void createTheCommandParserAndRegisterTheCommands()
        throws InvalidRegisterException, InvalidArgumentException {
        
        cmdparser = new CommandParser();
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        cmdparser.registerCommand( "GET",
                                   "/airships/reports",
                                   new GetAllTransgressingAirshipsCommandsFactory( airshipsDatabase ) );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommands()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException, InternalErrorException, Exception {
        
        Callable< ? > getAllTransgressinAirshipsCommand =
                (new Parser( cmdparser, "GET", "/airships/reports" )).getCommand();
        
        Assert.assertTrue( getAllTransgressinAirshipsCommand instanceof GetAllTransgressingAirshipsCommand );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllTransgressorAirshipsCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAllTransgressingAirshipsCommandsFactory( null );
    }
}
