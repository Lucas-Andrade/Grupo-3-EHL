package test.java.commands.postcommands;

import main.java.cli.commands.postcommands.PostCivilAirshipCommand;
import main.java.cli.commands.postcommands.PostMilitaryAirshipCommand;
import main.java.cli.commands.postcommands.PostUserCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

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

	private InMemoryUsersDatabase usersDatabase;
	private User user1;
	private PostUserCommand postUser;

	// Before

	@Before
	public void createUserAndUserDatabaseWhereToPostTheNewUsers() throws InvalidArgumentException {

		// Arrange
		usersDatabase = new InMemoryUsersDatabase("Users Database");

		user1 = new User("Daniel", "pass", "@daniel");
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldPostAUserWithTheCorrectParametersIncludingFullNameInTheGivenDatabase()
			throws Exception {

		// Act
		postUser = new PostUserCommand("Pedro", "pass2", "@pedro", "Pedro Antunes", usersDatabase,
				user1);
		String testedInformation = postUser.call();

		// Assert
		Assert.assertEquals(testedInformation, "New user successfully added: "
				+ usersDatabase.getElementByIdentification("Pedro").toString());
	}

	@Test
	public void shouldPostAUserWithTheCorrectParametersGivenANullFullNameInTheGivenDatabase()
			throws Exception {

		// Act
		postUser = new PostUserCommand("Pedro", "pass2", "@pedro", null, usersDatabase, user1);
		String testedInformation = postUser.call();

		// Assert
		Assert.assertEquals(testedInformation, "New user successfully added: "
				+ usersDatabase.getElementByIdentification("Pedro").toString());
	}

	@Test
	public void shouldNotPostAUserWithTheSameEmailHasAnotherExistingUserInTheDatabase()
			throws Exception {

		// Act
		postUser = new PostUserCommand("Pedro", "pass2", "@pedro", null, usersDatabase, user1);
		postUser.call();

		postUser = new PostUserCommand("Daniel", "pass", "@pedro", null, usersDatabase, user1);
		String testedInformation = postUser.call();

		// Assert
		Assert.assertEquals(testedInformation,
				"User not added. Either the username «Daniel» or\nthe email «@pedro» already exist in Users Database");
	}

	// Test Exceptions
	
	 @Test (expected = InvalidArgumentException.class)
	 public void
	 shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
	 throws InvalidArgumentException {
	
		 postUser = new PostUserCommand("Pedro", "pass2", "@pedro", "Pedro Antunes", null, user1);
	 }
}