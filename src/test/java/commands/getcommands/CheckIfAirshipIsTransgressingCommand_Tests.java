package test.java.commands.getcommands;

import main.java.cli.commands.getcommands.CheckIfAirshipIsTransgressingCommand;
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

/**
 * 
 * Those Tests were created to test the {@link GetValidationOfTransgressionByFlightIdCommand} Class
 * who's a Command of the Air Traffic Project
 *
 */
public class CheckIfAirshipIsTransgressingCommand_Tests {

	private InMemoryAirshipsDatabase airshipsDatabase;
	private InMemoryUsersDatabase userDatabase;
	private User user1, user2;
	private Airship airship1, airship2, airship3;
	private CheckIfAirshipIsTransgressingCommand checkIfAirshipIsTransgressing;

	// Before

	@Before
	public void createAirshipsAndUsersAndTheirDatabases() throws InvalidArgumentException {

		// Arrange
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");
		userDatabase = new InMemoryUsersDatabase("Users Database");

		user1 = new User("Daniel", "pass", "@daniel");
		user2 = new User("Pedro", "pass2", "@pedro", "Pedro Antunes");

		airship1 = new CivilAirship(0, 0, 0, 100, 50, 20);
		airship2 = new CivilAirship(0, 0, 60, 100, 50, 25);
		airship3 = new MilitaryAirship(0, 0, 30, 100, 50, false);

		userDatabase.add(user1, user1);
		userDatabase.add(user2, user1);

		airshipsDatabase.add(airship1, user1);
		airshipsDatabase.add(airship2, user1);
		airshipsDatabase.add(airship3, user2);
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldCheckCorrectlyIfAnAirshipCorrespondingToTheGivenIdentificationIsTransgressing()
			throws Exception {

		// Act
		checkIfAirshipIsTransgressing = new CheckIfAirshipIsTransgressingCommand(airshipsDatabase,
				airship1.getIdentification());

		String testedInformation1 = checkIfAirshipIsTransgressing.call();
		
		checkIfAirshipIsTransgressing = new CheckIfAirshipIsTransgressingCommand(airshipsDatabase,
				airship2.getIdentification());

		String testedInformation2 = checkIfAirshipIsTransgressing.call();
		

		// Assert
		Assert.assertEquals(testedInformation1,
				"The Airship with the Flight ID " + airship1.getIdentification() + " is transgressing its air corridor.");
		
		Assert.assertEquals(testedInformation2,
				"The Airship with the Flight ID " + airship2.getIdentification() + " is not transgressing its air corridor.");
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
			throws InvalidArgumentException {

		checkIfAirshipIsTransgressing = new CheckIfAirshipIsTransgressingCommand(null, "ola");
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullIdentification()
			throws InvalidArgumentException {

		checkIfAirshipIsTransgressing = new CheckIfAirshipIsTransgressingCommand(airshipsDatabase,
				null);
	}
}