package testsAppForConsole.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import appForConsole.CommandParser;
import appForConsole.commands.getUsersCommands.GetUserByUsernameCommand;
import appForConsole.exceptions.commandParserExceptions.InvalidRegisterException;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;

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