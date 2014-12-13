package airtrafficcontrol.tests.testsforcommandline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.getuserscommands.GetAllUsersCommand;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class GetAllUsersCommand_Tests {

	private CommandParser parser = new CommandParser();

	private InMemoryUserDatabase userDatabase;

	private User user1, user2;

	@Before
	public void createUsersAndUsersDatabaseAndAddElements() {

		user1 = new User("daniel", "pass1", "daniel@gmail.com");
		user2 = new User("eva", "pass2", "eva@gmail.com");

		userDatabase = new InMemoryUserDatabase();

		userDatabase.add(user1, user1);
		userDatabase.add(user2, user1);
	}

	@Test
	public void shouldSuccessfullyGetUsers() throws Exception {

		// Act
		parser.registerCommand("GET", "/users", new GetAllUsersCommand.Factory(userDatabase));

		GetAllUsersCommand getAllUsers = (GetAllUsersCommand) parser.getCommand("GET", "/users");

		getAllUsers.execute();

		// Assert
		Assert.assertEquals(getAllUsers.getResult(),

				"username: daniel, password: pass1, email: daniel@gmail.com\n"
				
				+ "username: eva, password: pass2, email: eva@gmail.com\n"

				+ "username: Daniel, password: DanyGs, email: danielacgomes1992@gmail.com, fullName: Daniel Gomes\n");
	}
}