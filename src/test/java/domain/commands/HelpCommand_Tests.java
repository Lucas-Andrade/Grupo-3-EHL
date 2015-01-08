package test.java.domain.commands;


import java.util.Map;
import main.java.cli.HelpCommand;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.HelpCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories.PatchUserPasswordCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.postfactories.PostUserCommandsFactory;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import main.java.utils.exceptions.parsingexceptions.InvalidParameterValueException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import main.java.utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import org.junit.Assert;
import org.junit.Test;

public class HelpCommand_Tests {


	@Test

	public void shouldGetAllCommandsDescription() throws WrongLoginPasswordException, MissingRequiredParameterException, UnknownCommandException, NoSuchElementInDatabaseException, InvalidParameterValueException, InternalErrorException, Exception{
		
		InMemoryAirshipsDatabase airshipsDatabase = new InMemoryAirshipsDatabase("airshipsDatabase");
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("airshipsDatabase");

		CommandParser cmdParser = new CommandParser();
		
		// OPTION

		cmdParser.registerCommand( "OPTION", "/", new HelpCommandsFactory(cmdParser ) );
		
		// GET
		
		cmdParser.registerCommand( "GET", "/airships",new GetAllAirshipsInADatabaseCommandsFactory(airshipsDatabase ) );
		cmdParser.registerCommand( "GET", "/airships/{flightId}",new GetAirshipByFlightIdCommandsFactory(airshipsDatabase ) );		
	
		
		// PATCH
		
		 cmdParser.registerCommand( "PATCH", "/users/{username}", new PatchUserPasswordCommandsFactory( usersDatabase));	
		
		// POST
		
		cmdParser.registerCommand( "POST", "/users",new PostUserCommandsFactory( usersDatabase,	usersDatabase ) );	
		
		Parser parser = new Parser(cmdParser,"OPTION", "/");
		
		
		 @SuppressWarnings("unchecked")
		Map<String,String> result = (Map<String, String>) parser.getCommand().call();
		
		
		Assert.assertTrue(result.containsValue(new GetAllAirshipsInADatabaseCommandsFactory(airshipsDatabase).getCommandsDescription()));
		Assert.assertTrue(result.containsValue(new GetAirshipByFlightIdCommandsFactory(airshipsDatabase).getCommandsDescription()));
		Assert.assertTrue(result.containsValue(new PatchUserPasswordCommandsFactory(usersDatabase).getCommandsDescription()));
		Assert.assertTrue(result.containsValue(new PostUserCommandsFactory(usersDatabase,usersDatabase).getCommandsDescription()));

	}
	
	
	@Test(expected=InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionGivingANullCommandParserInHelpCommand() throws InvalidArgumentException, InvalidCommandParametersSyntaxException, DuplicateParametersException, InvalidCommandSyntaxException, InvalidRegisterException{
		
		 new HelpCommand(null);
	}
	
	

}
