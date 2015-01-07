package test.java.commands;


import java.util.Map;

import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.commandfactories.HelpCommandsFactory;
import main.java.cli.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import main.java.cli.commandfactories.userauthenticatingfactories.patchfactories.PatchUserPasswordCommandsFactory;
import main.java.cli.commandfactories.userauthenticatingfactories.postfactories.PostUserCommandsFactory;
import main.java.cli.commands.HelpCommand;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.DuplicateParametersException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.cli.exceptions.commandparserexceptions.UnknownCommandException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.users.InMemoryUsersDatabase;

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
