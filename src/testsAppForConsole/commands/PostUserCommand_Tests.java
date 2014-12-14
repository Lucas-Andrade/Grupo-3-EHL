package testsAppForConsole.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import appForConsole.CommandParser;
import appForConsole.commands.postCommands.PostUserCommand;
import appForConsole.exceptions.commandParserExceptions.InvalidRegisterException;
import appForConsole.model.users.InMemoryUserDatabase;

/**
 * 
 * Those Tests were created to test the {@link PostUserCommand} Class who's part of the Air Traffic
 * Project
 *
 */
public class PostUserCommand_Tests {

	CommandParser commandParser = new CommandParser();

	InMemoryUserDatabase postingUsersDatabase = new InMemoryUserDatabase();
	InMemoryUserDatabase postedUsersDatabase = new InMemoryUserDatabase();

	PostUserCommand postUser;

	@Before
	public void BeforeTests() throws InvalidRegisterException {

		commandParser.registerCommand("POST", "/users", new PostUserCommand.Factory(
				postingUsersDatabase, postedUsersDatabase));
	}

	@Test
	public void VerifyIfUserIsCorrectlyAddedIntoUsersDatabaseGivingOnlyTheRequiredParameters()
			throws Exception {

		postUser = (PostUserCommand) commandParser.getCommand("POST", "/users", "username=pedro&"
				+ "password=pass2&email=pedro@gmail.com&loginName=MASTER&loginPassword=master");

		postUser.execute();

		Assert.assertEquals("User was successfull added", postUser.getResult());
	}

	@Test
	public void VerifyIfUserIsCorrectlyAddedIntoUsersDatabaseGivingAllParameters() throws Exception {

		postUser = (PostUserCommand) commandParser.getCommand("POST", "/users",
				"username=pedro&password=pass2&email=pedro@gmail.com&"
						+ "fullName=Pantunes&loginName=MASTER&loginPassword=master");

		postUser.execute();

		Assert.assertEquals("User was successfull added", postUser.getResult());
	}

	@Test
	public void VerifyThatUserIsNotAddedIntoUsersDatabaseBecauseAlreadyWasAddedOneWithTheSameIdentification()
			throws Exception {

		postUser = (PostUserCommand) commandParser.getCommand("POST", "/users",
				"username=null&password=pass2&email=pedro@gmail.com&"
						+ "fullName=Pantunes&loginName=MASTER&loginPassword=master");

		postUser.execute();

		Assert.assertEquals("User was successfull added", postUser.getResult());

		postUser = (PostUserCommand) commandParser.getCommand("POST", "/users",
				"username=null&password=pass2&email=pedro@gmail.com&"
						+ "fullName=Pantunes&loginName=MASTER&loginPassword=master");

		postUser.execute();

		Assert.assertEquals("User was not successfull added", postUser.getResult());
	}
}