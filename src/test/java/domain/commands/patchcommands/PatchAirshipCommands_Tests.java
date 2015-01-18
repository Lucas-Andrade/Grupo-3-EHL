package test.java.domain.commands.patchcommands;

import main.java.domain.commands.patchcommands.PatchCivilAirshipCommand;
import main.java.domain.commands.patchcommands.PatchMilitaryAirshipCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
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
 * {@link PatchCivilAirshipCommand}
 * {@link PatchMilitaryAirshipCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipCommands_Tests {

	private InMemoryAirshipsDatabase airshipsDatabase;
	private User user1;
	private Airship airship1, airship2;
	private PatchCivilAirshipCommand patchCivilAirship;
	private PatchMilitaryAirshipCommand patchMilitaryAirship;

	// Before

	@Before
	public void createAirshipsAndUserAndAirshipDatabaseWithTheCreatedAirshipsToBePatched()
			throws InvalidArgumentException {

		// Arrange
		user1 = new User("Daniel", "pass", "@daniel");

		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");

		airship1 = new CivilAirship(0, 0, 0, 100, 50, 20);
		airship2 = new MilitaryAirship(0, 0, 30, 100, 50, false);

		airshipsDatabase.add(airship1, user1);
		airshipsDatabase.add(airship2, user1);
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldPatchACivilAirshipWithTheCorrectParametersInTheGivenDatabase()
			throws Exception {

		// Act
		patchCivilAirship = new PatchCivilAirshipCommand(airshipsDatabase,
				airship1.getIdentification(), user1, 2, 4, 13, 100, 50, 100);

		// Assert
		Assert.assertEquals(patchCivilAirship.call(), "Airship successfully patched");
		Assert.assertEquals(
				airshipsDatabase.getElementByIdentification(airship1.getIdentification())
						.toString(), "Flight ID: " + airship1.getIdentification()
						+ "\nLatitude: 2.0 Longitude: 4.0 Altitude: 13.0\n"
						+ "Maximum Altitude Permited: 100.0Minimum Altitude Permited: 50.0\n"
						+ "Is Outside The Given Corridor: true\n" + "Number of Passengers: 100\n");
	}

	@Test
	public void shouldPatchAMilitaryAirshipWithTheCorrectParametersInTheGivenDatabase()
			throws Exception {

		// Act
		patchMilitaryAirship = new PatchMilitaryAirshipCommand(airshipsDatabase,
				airship2.getIdentification(), user1, 20, 10, 30, 100, 50, false);

		// Assert
		Assert.assertEquals(patchMilitaryAirship.call(), "Airship successfully patched");
		Assert.assertEquals(
				airshipsDatabase.getElementByIdentification(airship2.getIdentification())
						.toString(), "Flight ID: " + airship2.getIdentification()
						+ "\nLatitude: 20.0 Longitude: 10.0 Altitude: 30.0\n"
						+ "Maximum Altitude Permited: 100.0Minimum Altitude Permited: 50.0\n"
						+ "Is Outside The Given Corridor: true\n" + "Carries Weapons: false\n");
	}

	@Test
	public void shouldNotPatchAnAirshipThatDoesNotExistInTheDatabase() throws Exception {

		// Act
		patchCivilAirship = new PatchCivilAirshipCommand(airshipsDatabase, "id20", user1, 0, 0, 0,
				100, 50, 100);
		patchMilitaryAirship = new PatchMilitaryAirshipCommand(airshipsDatabase, "id30", user1, 20,
				10, 30, 100, 50, false);

		// Assert
		Assert.assertEquals(patchCivilAirship.call(), "Airship does not exist in the database");
		Assert.assertEquals(patchMilitaryAirship.call(), "Airship does not exist in the database");
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchCivilAirshipCommandGivenANullDatabase()
			throws InvalidArgumentException {

		patchCivilAirship = new PatchCivilAirshipCommand(null, "id20", user1, 0, 0, 0, 100, 50, 100);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchMilitaryAirshipCommandGivenANullDatabase()
			throws InvalidArgumentException {

		patchMilitaryAirship = new PatchMilitaryAirshipCommand(null, "id20", user1, 0, 0, 0, 100,
				50, false);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchCivilAirshipCommandGivenANullIdentification()
			throws InvalidArgumentException {

		patchCivilAirship = new PatchCivilAirshipCommand(airshipsDatabase, null, user1, 0, 0, 0,
				100, 50, 100);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchMilitaryAirshipCommandGivenANullIdentification()
			throws InvalidArgumentException {

		patchMilitaryAirship = new PatchMilitaryAirshipCommand(airshipsDatabase, null, user1, 0, 0,
				0, 100, 50, false);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchCivilAirshipCommandGivenANullUser()
			throws InvalidArgumentException {

		patchCivilAirship = new PatchCivilAirshipCommand(airshipsDatabase, "id2", null, 0, 0, 0,
				100, 50, 100);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchMilitaryAirshipCommandGivenANullUser()
			throws InvalidArgumentException {

		patchMilitaryAirship = new PatchMilitaryAirshipCommand(airshipsDatabase, "id1", null, 0, 0,
				0, 100, 50, false);
	}
}