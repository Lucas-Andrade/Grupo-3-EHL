package test.java.domain.commands.getcommands;

import java.util.ArrayList;
import java.util.List;
import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetAllElementsInADatabaseCommand_Tests {

	private InMemoryAirshipsDatabase airshipsDatabase;
	private InMemoryUsersDatabase userDatabase;
	private User user1, user2;
	private Airship airship1, airship2, airship3;
	private GetAllElementsInADatabaseCommand<?> getAllElementsInADatabase;

	// Before

	@Before
	public void createAirshipsAndUsersAndTheirDatabases() throws InvalidArgumentException {

		// Arrange
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");
		userDatabase = new InMemoryUsersDatabase("Users Database");

		user1 = new User("Daniel", "pass", "@daniel");
		user2 = new User("Pedro", "pass2", "@pedro", "Pedro Antunes");

		airship1 = new CivilAirship(0, 0, 0, 100, 50, 20);
		airship2 = new CivilAirship(0, 0, 0, 100, 50, 25);
		airship3 = new MilitaryAirship(0, 0, 0, 100, 50, false);

		userDatabase.add(user1, user1);
		userDatabase.add(user2, user1);

		airshipsDatabase.add(airship1, user1);
		airshipsDatabase.add(airship2, user1);
		airshipsDatabase.add(airship3, user2);
	}

	// Test Normal Dinamic And Prerequisites

	@SuppressWarnings ({"rawtypes", "unchecked"})
	@Test
	public void shouldSuccessfullyGetAllTheAirshipFromAnAirshipsDatabase() throws Exception {

		// Act
		getAllElementsInADatabase = new GetAllElementsInADatabaseCommand(airshipsDatabase);
		Iterable<Airship> testedAirships = (Iterable<Airship>) getAllElementsInADatabase.call()
				.get();

		List<Airship> airships = new ArrayList<Airship>();

		// The Addition Order Matters??
		airships.add(airship2);
		airships.add(airship1);
		airships.add(airship3);

		// Assert
		Assert.assertEquals(testedAirships.toString(), airships.toString());
	}

	@SuppressWarnings ({"rawtypes", "unchecked"})
	@Test
	public void shouldSuccessfullyGetTheCorrectUserGivenItsIdentification() throws Exception {

		// Act
		getAllElementsInADatabase = new GetAllElementsInADatabaseCommand(userDatabase);
		Iterable<User> testedUsers = (Iterable<User>) getAllElementsInADatabase.call().get();

		List<User> users = new ArrayList<User>();

		// The Addition Order Matters
		users.add(new User( "MASTER", "master", "master@master" ));
		users.add(user2);
		users.add(user1);

		// Assert
		Assert.assertEquals(testedUsers.toString(), users.toString());
	}

	// Test Exceptions

	@SuppressWarnings ({"rawtypes", "unchecked"})
	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
			throws InvalidArgumentException {

		getAllElementsInADatabase = new GetAllElementsInADatabaseCommand(null);
	}
}