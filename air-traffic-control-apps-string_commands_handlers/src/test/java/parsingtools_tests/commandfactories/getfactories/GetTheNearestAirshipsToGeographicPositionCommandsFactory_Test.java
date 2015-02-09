package parsingtools_tests.commandfactories.getfactories;


import java.util.concurrent.Callable;
<<<<<<< HEAD
import javax.swing.text.html.parser.Parser;
=======
>>>>>>> branch 'AppForCommandLine3' of https://github.com/Lucas-Andrade/Grupo-3-EHL.git
import org.junit.Assert;
import org.junit.Test;
import parsingtools.CommandParser;
import parsingtools.commandfactories.getfactories.GetTheNearestAirshipsToGeographicPositionCommandsFactory;
import utils.StringCommands_Executor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
<<<<<<< HEAD
import commands.getcommands.GetTheNearestAirshipsToGeographicPositionCommand;
=======
import commands.getcommands.GetAirshipsCloserToCommand;
>>>>>>> branch 'AppForCommandLine3' of https://github.com/Lucas-Andrade/Grupo-3-EHL.git
import databases.InMemoryAirshipsDatabase;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


public class GetTheNearestAirshipsToGeographicPositionCommandsFactory_Test {
    
    @Test
    public void shouldCreateTheCorrectInstanceCommand()
        throws InvalidArgumentException, InvalidRegisterException, UnknownCommandException,
        WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidCommandParametersSyntaxException,
        DuplicateParametersException, InternalErrorException {
        
        CommandParser cmdParser = new CommandParser();
        InMemoryAirshipsDatabase airshipsDatabase;
        airshipsDatabase = new InMemoryAirshipsDatabase( "FirstAirshipsDatabse" );
        cmdParser.registerCommand( "GET",
                                   "/airships/find",
                                   new GetTheNearestAirshipsToGeographicPositionCommandsFactory(
                                                                                                 airshipsDatabase ) );
        
        Callable< ? > getTheNearestAirshipsToGeographicPositionCommand =
                (new StringCommands_Executor( cmdParser, "GET", "/airships/find",
                             "nbAirships=2&latitude=60&longitude=225" )).getCommand();
        
        Assert.assertTrue( getTheNearestAirshipsToGeographicPositionCommand instanceof GetAirshipsCloserToCommand );
        
    }
    
    
    @Test( expected = InvalidArgumentException.class )
    public void
            shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullAirShipDatabaseInTheFactory()
                throws Exception {
        
        new GetTheNearestAirshipsToGeographicPositionCommandsFactory( null );
    }
}
