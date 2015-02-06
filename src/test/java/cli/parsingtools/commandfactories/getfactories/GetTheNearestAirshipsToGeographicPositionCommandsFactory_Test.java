package test.java.cli.parsingtools.commandfactories.getfactories;


import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.GetTheNearestAirshipsToGeographicPositionCommandsFactory;
import main.java.domain.commands.getcommands.GetTheNearestAirshipsToGeographicPositionCommand;
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
import main.java.utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import main.java.utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import org.junit.Assert;
import org.junit.Test;


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
                (new Parser( cmdParser, "GET", "/airships/find",
                             "nbAirships=2&latitude=60&longitude=225" )).getCommand();
        
        Assert.assertTrue( getTheNearestAirshipsToGeographicPositionCommand instanceof GetTheNearestAirshipsToGeographicPositionCommand );
        
    }
    
    
    @Test( expected = InvalidArgumentException.class )
    public void
            shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullAirShipDatabaseInTheFactory()
                throws Exception {
    
        new GetTheNearestAirshipsToGeographicPositionCommandsFactory( null );
    }
}
