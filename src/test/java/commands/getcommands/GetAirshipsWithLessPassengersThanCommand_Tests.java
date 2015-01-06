package test.java.commands.getcommands;

import java.util.ArrayList;
import java.util.List;

import main.java.cli.commands.getcommands.GetAirshipsWithLessPassengersThanCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetAirshipsWithLessPassengersThanCommand_Tests {

	private InMemoryAirshipsDatabase airshipsDatabase;
	private InMemoryUsersDatabase userDatabase;
	private User user1, user2;
	private Airship airship1, airship2, airship3;
	private GetAirshipsWithLessPassengersThanCommand getAirshipsWithLessPassengersThan;

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

	@Test
	public void shouldGetAllTheAirshipsRegisteredByTheUserCorrespondeingToTheGivenIdentification()
			throws Exception {

		// Act
		getAirshipsWithLessPassengersThan = new GetAirshipsWithLessPassengersThanCommand(airshipsDatabase, 25);
		Iterable<Airship> testedAirships = getAirshipsWithLessPassengersThan.call().get();

		List<Airship> airships = new ArrayList<Airship>();

		airships.add(airship1);

		// Assert
		Assert.assertEquals(testedAirships.toString(), airships.toString());
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
			throws InvalidArgumentException {

		getAirshipsWithLessPassengersThan = new GetAirshipsWithLessPassengersThanCommand(null, 35);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANegativeNumberOfPassengers()
			throws InvalidArgumentException {

		getAirshipsWithLessPassengersThan = new GetAirshipsWithLessPassengersThanCommand(airshipsDatabase, -2);
	}
}