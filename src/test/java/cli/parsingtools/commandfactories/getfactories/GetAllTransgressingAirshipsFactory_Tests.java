package test.java.cli.parsingtools.commandfactories.getfactories;


import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAllTransgressingAirshipsCommandsFactory;
import main.java.domain.commands.getcommands.GetAllTransgressingAirshipsCommand;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
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
import org.junit.BeforeClass;
import org.junit.Test;


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