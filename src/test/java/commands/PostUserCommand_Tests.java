package test.java.commands;

import main.java.CommandParser;
import main.java.commands.postCommands.PostUserCommand;
import main.java.exceptions.commandParserExceptions.InvalidRegisterException;
import main.java.model.users.InMemoryUserDatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
				"username=null&password=pass2&email=pedros@gmail.com&"
						+ "fullName=Pantunes&loginName=MASTER&loginPassword=master");

		postUser.execute();

		Assert.assertEquals("User was not successfull added - username already exists", postUser.getResult());
	}
	
	@Test
	public void VerifyThatUserIsNotAddedIntoUsersDatabaseBecauseAlreadyWasAddedOneWithTheSameEmail()
			throws Exception {

		postUser = (PostUserCommand) commandParser.getCommand("POST", "/users",
				"username=nulls&password=pass2&email=pedro@gmail.com&"
						+ "fullName=Pantunes&loginName=MASTER&loginPassword=master");

		postUser.execute();

		Assert.assertEquals("User was successfull added", postUser.getResult());

		postUser = (PostUserCommand) commandParser.getCommand("POST", "/users",
				"username=null&password=pass2&email=pedro@gmail.com&"
						+ "fullName=Pantunes&loginName=MASTER&loginPassword=master");

		postUser.execute();

		Assert.assertEquals("User was not successfull added - email already exists", postUser.getResult());
	}
	
}