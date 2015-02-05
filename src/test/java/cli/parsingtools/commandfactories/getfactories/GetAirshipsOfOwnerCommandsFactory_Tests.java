package test.java.cli.parsingtools.commandfactories.getfactories;


import static org.junit.Assert.*;
import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.GetAirshipsOfOwnerCommandsFactory;
import main.java.domain.commands.getcommands.GetAirshipsOfOwnerCommand;
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
    
//    @BeforeClass
//    public static void createTheCommandParserAndRegisterTheCommands()
//        throws InvalidRegisterException, InvalidArgumentException {
//        
//        cmdparser = new CommandParser();
//        
//        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
//        
//        cmdparser.registerCommand( "GET", "/airships/owner/{owner}",
//                                   new GetAirshipsOfOwnerCommandsFactory( airshipsDatabase ) );
//    }
    
    // Test Normal Dinamic And Prerequisites
    @Test

    public void a(){
        assertFalse(true);
    }
    
//    @Test
//    public void shouldSuccessfullyCreateTheCorrectCommand()
//        throws WrongLoginPasswordException, MissingRequiredParameterException,
//        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
//        InvalidParameterValueException, InvalidArgumentException, InternalErrorException, Exception {
//        
//        
//        Callable< ? > getAirshipsOfOwnerCommands =
//                (new Parser( cmdparser, "GET", "/airships/owner/Daniel" )).getCommand();
//        
//        Assert.assertTrue( getAirshipsOfOwnerCommands instanceof GetAirshipsOfOwnerCommand );
//    }
//    
//    // Test Exceptions
//    
//    @Test( expected = InvalidArgumentException.class )
//    public
//            void
//            shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipsOfOwnerCommandsFactoryGivenANullDatabase()
//                throws InvalidArgumentException {
//        
//        new GetAirshipsOfOwnerCommandsFactory( null );
//    }
}