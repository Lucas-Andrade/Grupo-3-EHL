package parsingtools_tests.commandfactories.getfactories;



import static org.junit.Assert.assertTrue;
import java.util.concurrent.Callable;
import org.junit.BeforeClass;
import org.junit.Test;
import parsingtools.CommandParser;
import parsingtools.Parser;
import parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import commands.getcommands.GetAirshipsOfOwnerCommand;
import databases.InMemoryAirshipsDatabase;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetAirshipsOfOwnerCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsOfOwnerCommandsFactory_Tests {
    
    private static CommandParser cmdparser = new CommandParser();
    private static InMemoryAirshipsDatabase airshipsDatabase;
    
    // Before Class
    
    @BeforeClass
    public static void createTheCommandParserAndRegisterTheCommands()
        throws InvalidRegisterException, InvalidArgumentException {
        
        cmdparser = new CommandParser();
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        cmdparser.registerCommand( "GET", "/airships/owner/{owner}",
                                   new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase ) );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldSuccessfullyCreateTheCorrectCommand() throws Exception {
        
        
        Callable< ? > getAirshipsOfOwnerCommands =
                (new Parser( cmdparser, "GET", "/airships/owner/Daniel" )).getCommand();
        
        assertTrue( getAirshipsOfOwnerCommands instanceof GetAirshipsOfOwnerCommand );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipsOfOwnerCommandsFactoryGivenANullDatabase()
                throws InvalidArgumentException {
        
        new GetAirshipsOfOwnerCommandsFactory( null );
    }
}
