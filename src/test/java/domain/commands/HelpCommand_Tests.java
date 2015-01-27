package test.java.domain.commands;


import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.PatchUserPasswordCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.PostUserCommandsFactory;
import main.java.domain.commands.HelpCommand;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
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


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link HelpCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class HelpCommand_Tests {
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetAllCommandsDescription()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        UnknownCommandException, NoSuchElementInDatabaseException, InvalidParameterValueException,
        InternalErrorException, Exception {
        
        InMemoryAirshipsDatabase airshipsDatabase =
                new InMemoryAirshipsDatabase( "airshipsDatabase" );
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "airshipsDatabase" );
        
        CommandParser cmdParser = new CommandParser();
        
        // OPTION
        
        cmdParser.registerCommand( "OPTION", "/", new HelpCommandsFactory( cmdParser ) );
        
        // GET
        
        cmdParser.registerCommand( "GET", "/airships",
                                   new GetAllAirshipsInADatabaseCommandsFactory( airshipsDatabase ) );
        cmdParser.registerCommand( "GET", "/airships/{flightId}",
                                   new GetAirshipByFlightIdCommandsFactory( airshipsDatabase ) );
        
        // PATCH
        
        cmdParser.registerCommand( "PATCH", "/users/{username}",
                                   new PatchUserPasswordCommandsFactory( usersDatabase ) );
        
        // POST
        
        cmdParser.registerCommand( "POST", "/users", new PostUserCommandsFactory( usersDatabase,
                                                                                  usersDatabase ) );
        
        Parser parser = new Parser( cmdParser, "OPTION", "/" );
        
        String result = parser.getCommand().call().toString();
        
        Assert.assertTrue( result.contains( new GetAllAirshipsInADatabaseCommandsFactory(
                                                                                          airshipsDatabase ).getCommandsDescription() ) );
        Assert.assertTrue( result.contains( new GetAirshipByFlightIdCommandsFactory(
                                                                                     airshipsDatabase ).getCommandsDescription() ) );
        Assert.assertTrue( result.contains( new PatchUserPasswordCommandsFactory( usersDatabase ).getCommandsDescription() ) );
        Assert.assertTrue( result.contains( new PostUserCommandsFactory( usersDatabase,
                                                                         usersDatabase ).getCommandsDescription() ) );
        
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionGivingANullCommandParserInHelpCommand()
        throws InvalidArgumentException, InvalidCommandParametersSyntaxException,
        DuplicateParametersException, InvalidCommandSyntaxException, InvalidRegisterException {
        
        new HelpCommand( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionGivingANullCommandParserInHelpCommandFactory()
        throws InvalidArgumentException, InvalidCommandParametersSyntaxException,
        DuplicateParametersException, InvalidCommandSyntaxException, InvalidRegisterException {
        
        new HelpCommandsFactory( null );
    }
}