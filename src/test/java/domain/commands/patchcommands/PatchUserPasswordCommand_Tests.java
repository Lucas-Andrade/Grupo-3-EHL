package test.java.domain.commands.patchcommands;

import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PatchUserPasswordCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPasswordCommand_Tests {
	
	private InMemoryUsersDatabase	usersDatabase;
	private User					user1, user2;
	private PatchUserPasswordCommand	patchUserPassword, patchUserPassword2;
	
	// Before
	
	@Before
	public void createUsersTheirDatabase() throws InvalidArgumentException {
	
		// Arrange
		user1 = new User("Daniel", "pass", "@daniel");
		user2 = new User("Pedro", "pass2", "@pedro");
		
		usersDatabase = new InMemoryUsersDatabase("Users Database");
		
		usersDatabase.add(user1, user1);
		usersDatabase.add(user2, user1);
	}
	
	// Test Normal Dinamic And Prerequisites
	
	@Test
	public void shouldPatchAUserWithTheCorrectPasswordInTheGivenDatabase() throws Exception {
	
		// Act
		patchUserPassword = new PatchUserPasswordCommand(usersDatabase, "Daniel", "pass", "dany");
		
		// Assert
		Assert.assertEquals(patchUserPassword.call(), "User password successfully changed");
	}
	
	@Test
	public void shouldNotPatchAnUserThatDoesNotExistInTheDatabaseOrTheMasterUser() throws Exception {
	
		// Act
		patchUserPassword = new PatchUserPasswordCommand(usersDatabase, "Gomes", "pass", "dany");
		patchUserPassword2 = new PatchUserPasswordCommand(usersDatabase, "MASTER", "master", "dany");
		
		// Assert
		Assert.assertEquals(patchUserPassword.call(), "The user does not exist in the database");
		Assert.assertEquals(patchUserPassword2.call(), "The user does not exist in the database");
	}
	
	@Test
	public void shouldNotPatchAnExistingUserIfTheWrongAuthenticationPassordIsGiven()
		throws Exception {
	
		// Act
		patchUserPassword = new PatchUserPasswordCommand(usersDatabase, "Daniel", "dany", "dany");
		
		// Assert
		Assert.assertEquals(patchUserPassword.call(),
			"Failed autentication, please insert the correct password");
	}
	
	// Test Exceptions
	
	@Test (
		expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPatchUserPasswordCommandGivenANullDatabase()
		throws InvalidArgumentException {
	
		patchUserPassword = new PatchUserPasswordCommand(null, "Daniel", "pass", "dany");
	}
	
	@Test (
		expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPatchUserPasswordCommandGivenANullUsername()
		throws InvalidArgumentException {
	
		patchUserPassword = new PatchUserPasswordCommand(usersDatabase, null, "pass", "dany");
	}
	
	@Test (
		expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPatchUserPasswordCommandGivenANullOldPassword()
		throws InvalidArgumentException {
	
		patchUserPassword = new PatchUserPasswordCommand(usersDatabase, "Daniel", null, "dany");
	}
	
	@Test (
		expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPatchUserPasswordCommandGivenANullNewPassword()
		throws InvalidArgumentException {
	
		patchUserPassword = new PatchUserPasswordCommand(usersDatabase, "Daniel", "pass", null);
	}
}