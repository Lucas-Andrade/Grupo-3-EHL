package test.java.cli.parsingtools.commandfactories.getfactories;



import static org.junit.Assert.assertTrue;
import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import main.java.domain.commands.getcommands.GetAirshipsOfOwnerCommand;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import org.junit.BeforeClass;
import org.junit.Test;


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
