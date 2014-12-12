package airtrafficcontrol.tests.testsforcommandline.testcommands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.commands.getuserscommands.GetAllUsersCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.DuplicateParametersException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidCommandParametersException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.UnknownCommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class Command_Tests {

	private CommandParser parser = new CommandParser();
	
	private InMemoryUserDatabase userDatabase;
	private InMemoryAirshipDatabase airshipDatabase;
	
	private User user1, user2, user3;
	private Airship airship1, airship2, airship3, airship4;
	
	@Before
	public void createUserAndAirshipDatabaseAndAddElements() {
		
		userDatabase = new InMemoryUserDatabase();
		
		userDatabase.add(user1, user1);
		userDatabase.add(user2, user1);
		userDatabase.add(user3, user1);
		
		airshipDatabase = new InMemoryAirshipDatabase();
		
		airshipDatabase.add(airship1, user1);
		airshipDatabase.add(airship2, user1);
		airshipDatabase.add(airship3, user2);
		airshipDatabase.add(airship4, user2);
	}

	@Test
	public void shouldSuccessfullyGetAllUsers() throws InvalidRegisterException,
			UnknownCommandException, DuplicateParametersException,
			InvalidCommandParametersException, CommandException {
		
		// Act
		parser.registerCommand("GET", "/users", new GetAllUsersCommand.Factory(userDatabase));
		
		GetAllUsersCommand getAllUsers = (GetAllUsersCommand) parser.getCommand("GET", "/users");
		getAllUsers.execute();
		
		// Assert
		Assert.assertEquals(getAllUsers.getResult(), "");
		
	}
	
	@Test
	public void shouldSuccessfullyGetAllAirships() {
		
	}
	
	@Test
	public void shouldSuccessfullyGetUserById() {
		
	}

	@Test
	public void shouldSuccessfullyGetAirshipById() {
		
	}
}