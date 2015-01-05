package test.java.model;

import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User user1, user2;

	// Before

	@Before
	public void CreateUsers() throws InvalidArgumentException {

		user1 = new User("Pedro", "pass", "pedro@gmail.com", "Pedro Antunes");
		user2 = new User("Gonçalo", "pass2", "Gonçalo@gmail.com");
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void ShouldReturnUserIdentification() {

		Assert.assertTrue(user1.getIdentification().equals("Pedro"));
		Assert.assertTrue(user2.getIdentification().equals("Gonçalo"));
	}

	@Test
	public void ShouldReturnAString() {

		Assert.assertEquals(user1.toString(),
				"username: Pedro, password: pass, email: pedro@gmail.com, fullName: Pedro Antunes\n");
		Assert.assertEquals(user2.toString(),
				"username: Gonçalo, password: pass2, email: Gonçalo@gmail.com\n");

	}

	@Test
	public void ShouldReturnUsername() {

		Assert.assertTrue(user1.getIdentification().equals("Pedro"));
		Assert.assertTrue(user2.getIdentification().equals("Gonçalo"));
	}

	@Test
	public void ShouldAnUserBeInstanceOfUserClass() {

		Assert.assertTrue(user1.getClass().equals(User.class));
		Assert.assertTrue(user2.getClass().equals(User.class));
	}

	@Test
	public void VerifyIfUsersAreEquals() throws InvalidArgumentException {

		Assert.assertTrue(user1
				.equals(new User("Pedro", "pass", "pedro@gmail.com", "Pedro Antunes")));
		Assert.assertFalse(user1.equals(user2));
	}

	@Test
	public void VerifyIfUsersHaveDiferentsUsernames() throws InvalidArgumentException {

		Assert.assertFalse(user1.equals(new User("Gonçalo", "pass", "pedro@gmail.com",
				"Pedro Antunes")));
	}

	@Test
	public void VerifyIfUsersHaveDiferentsEmails() throws InvalidArgumentException {

		Assert.assertFalse(user1.equals(new User("Pedro", "pass", "Gonçalo@gmail.com",
				"Pedro Antunes")));
	}

	@Test
	public void AutenticatePasswordCorrectly() throws InvalidArgumentException {

		Assert.assertTrue(user1.authenticatePassword("pass"));
		Assert.assertFalse(user2.authenticatePassword("pass"));
	}
	
	
	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowInvalidArgumentExceptionWhenUsernameIsNull()
			throws InvalidArgumentException {

		new User(null, "pass", "pantunes@gmail.com", "Pantunes da Silva Pantunes");
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowIllegalArgumentExceptionWhenPasswordIsNull()
			throws InvalidArgumentException {

		new User("Pantunes", null, "pantunes@gmail.com", "Pantunes da Silva Pantunes");
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowInvalidArgumentExceptionWhenEmailIsNull()
			throws InvalidArgumentException {

		new User("Pantunes", "pass", null, "Pantunes da Silva Pantunes");
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowInvalidArgumentExceptionWhenEmailDoesntHaveAt()
			throws InvalidArgumentException {

		new User("Pantunes", "pass", "pantunesgmail.com", "Pantunes da Silva Pantunes");
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowInvalidArgumentExceptionWhenEmailDoesntHaveOnlyOneAt()
			throws InvalidArgumentException {

		new User("Pantunes", "pass", "pant@unes@gmail.com", "Pantunes da Silva Pantunes");
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowIllegalArgumentExceptionWhenFullnameIsNull()
			throws InvalidArgumentException {

		new User("pantunes", "pass", "pantunes@gmail.com", null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowInvalidArgumentExceptionWhenUsernameIsAnEmptyString()
			throws InvalidArgumentException {

		new User("", "pass", "pantunes@gmail.com", "Pantunes da Silva Pantunes");
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowInvalidArgumentExceptionWhenPasswordIsAnEmptyString()
			throws InvalidArgumentException {

		new User("pantunes", "", "pantunes@gmail.com", "Pantunes da Silva Pantunes");
	}

	@Test (expected = InvalidArgumentException.class)
	public void ShouldThrowInvalidArgumentExceptionWhenEmailIsAnEmptyString()
			throws InvalidArgumentException {

		new User("pantunes", "password", "", "Pantunes da Silva Pantunes");
	}
}