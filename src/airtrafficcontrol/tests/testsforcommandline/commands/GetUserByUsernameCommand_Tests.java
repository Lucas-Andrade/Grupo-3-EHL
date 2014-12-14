package airtrafficcontrol.tests.testsforcommandline.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.getuserscommands.GetUserByUsernameCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class GetUserByUsernameCommand_Tests {

	private CommandParser parser = new CommandParser();

	private InMemoryUserDatabase userDatabase;

	private User user1, user2;

	@Before
	public void createUsersAndUsersDatabaseAndAddElements() throws InvalidRegisterException {

		user1 = new User("daniel", "pass1", "daniel@gmail.com");
		user2 = new User("eva", "pass2", "eva@gmail.com");

		userDatabase = new InMemoryUserDatabase();

		parser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommand.Factory(
				userDatabase));
	}

	@Test
	public void shouldSuccessfullyGetUserByUsername() throws Exception {

		// Act
		userDatabase.add(user1, user1);
		userDatabase.add(user2, user1);

		GetUserByUsernameCommand getUser = (GetUserByUsernameCommand) parser.getCommand("GET", "/users/daniel");

		getUser.execute();

		// Assert
		Assert.assertEquals(getUser.getResult(),
				"username: daniel, password: pass1, email: daniel@gmail.com");
	}

	@Test
	public void shouldGetTheCorrectInformationIfTheAirshipDoesNotExist() throws Exception {

		// Act
		GetUserByUsernameCommand getUser = (GetUserByUsernameCommand) parser.getCommand("GET", "/users/daniel");

		getUser.execute();

		// Assert
		Assert.assertEquals(getUser.getResult(), "User Not Found\n");
	}
}