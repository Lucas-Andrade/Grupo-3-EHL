package test.java;

import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories.PatchUserPasswordCommandsFactory;
import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PatchUserPasswordCommand}
 * {@link PatchUserPasswordCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPassword_Test {

	CommandParser cmdparser = new CommandParser();
	InMemoryUsersDatabase userDatabase;

	// Before

	@Before
	public void createUserDatabaseAndCommandParser() throws InvalidArgumentException,
			InvalidRegisterException {

		userDatabase = new InMemoryUsersDatabase("usersDatabase");

		cmdparser.registerCommand("PATCH", "/users/{username}",
				new PatchUserPasswordCommandsFactory(userDatabase));
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldChangesTheUserPassword() throws Exception {

		User user1 = new User("pantunes", "pass", "Pantunes@gmail.com");

		userDatabase.add(user1, user1);

		Parser parser = new Parser(cmdparser, "PATCH", "/users/pantunes",
				"oldPassword=pass&newPassword=pass2&loginName=pantunes&loginPassword=pass");

		String result = parser.getCommand().call().toString();

		Assert.assertEquals("The User Password was successfully changed", result);

	}

	@Test
	public void shouldNotChangesTheUserPassword() throws Exception {

		User user1 = new User("pantunes", "pass", "Pantunes@gmail.com");

		userDatabase.add(user1, user1);

		Parser parser = new Parser(cmdparser, "PATCH", "/users/pantunes",
				"oldPassword=fakepass&newPassword=pass2&loginName=pantunes&loginPassword=pass");
		String result = parser.getCommand().call().toString();

		Assert.assertEquals("The User Password was not changed", result);

	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullOldPassword()
			throws Exception {

		User user1 = new User("pantunes", "pass", "Pantunes@gmail.com");

		userDatabase.add(user1, user1);

		new PatchUserPasswordCommand(userDatabase, "pantunes", null, "newPassword").call();

	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullNewPassword()
			throws Exception {

		User user1 = new User("pantunes", "pass", "Pantunes@gmail.com");

		userDatabase.add(user1, user1);

		new PatchUserPasswordCommand(userDatabase, "pantunes", "oldPassword", null).call();

	}

	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullIdentification()
			throws Exception {

		new PatchUserPasswordCommand(userDatabase, null, "oldPassword", "newPassword").call();

	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullUserDatabase()
			throws InvalidArgumentException {

		new PatchUserPasswordCommand(null, "pantunes", "oldPassword", "newPassword");

	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullUserDatabaseInTheFactory()
			throws InvalidArgumentException {

		new PatchUserPasswordCommandsFactory(null);
	}
}
