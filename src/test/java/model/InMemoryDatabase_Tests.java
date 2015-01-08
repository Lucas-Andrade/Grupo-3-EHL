package test.java.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.model.InMemoryDatabase;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.AirshipPredicates;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * {@link InMemoryDatabase};
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryDatabase_Tests {

	InMemoryUsersDatabase userDatabase;
	private InMemoryAirshipsDatabase airshipDatabase;
	private Airship airship, airship2;
	private User user;

	// Before

	@Before
	public void createUserAndAirshipAndTheirDatabases() {

		try {

			// Arrange
			userDatabase = new InMemoryUsersDatabase("newUsersDataBase");

			airshipDatabase = new InMemoryAirshipsDatabase("newAirshipsdataBase");

			airship = new MilitaryAirship(0, 0, 0, 10, 0, false);
			user = new User("X", "P", "T@", "O");

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldCreateInMemoryUserDatabaseWithAMasterUser() {

		try {
			// Arrange
			User masterUser = new User("MASTER", "master", "master@master");

			// Assert
			Assert.assertEquals(userDatabase.getElementByIdentification("MASTER").toString(),
					masterUser.toString());

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void shouldGetAllAirshipsThatAreTransgressing() {

		try {
			// Arrange
			airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
			List<Airship> airships = new ArrayList<>();

			// Act
			airshipDatabase.add(airship, user);
			airshipDatabase.add(airship2, user);

			airships.add(airship2);

			// Assert
			Assert.assertEquals(
					airshipDatabase.getAllElementsThat(
							new AirshipPredicates.IsTrangressingItsAirCorridor()).toString(),
					airships.toString());

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldGetAllAirshipsThatVerifyHaveANumberOfPassengersBellowAThreshold() {

		try {
			// Arrange
			airship2 = new CivilAirship(0, 0, 0, 10, 5, 100);
			Airship airship3 = new CivilAirship(0, 0, 0, 10, 5, 2);
			Airship airship4 = new CivilAirship(0, 0, 0, 10, 5, 200);

			InMemoryAirshipsDatabase airships = new InMemoryAirshipsDatabase("secondUsersDataBase");

			// Act
			airshipDatabase.add(airship, user);
			airshipDatabase.add(airship2, user);
			airshipDatabase.add(airship3, user);
			airshipDatabase.add(airship4, user);

			airships.add(airship3, user);
			airships.add(airship2, user);

			// Assert
			Assert.assertEquals(
					airshipDatabase.getAllElementsThat(
							new AirshipPredicates.HasPassagersNumberBelowAThreshold(102))
							.toString(), airships.getAllElements().toString());
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldNotRemoveAnUserBecauseTheUserIsNotRegistedIntoInMemoryUsersDatabase()
			throws DatabaseException, InvalidArgumentException {

		Assert.assertFalse(userDatabase.removeByIdentification("pantunes"));

	}

	@Test
	public void shouldRemoveAnUserInTheInMemoryUsersDatabase() throws DatabaseException,
			InvalidArgumentException {

		User pantunes = new User("pantunes", "pass", "pantunes@gmai.com");
		userDatabase.add(pantunes, user);
		Assert.assertTrue(userDatabase.removeByIdentification("pantunes"));

	}

	@Test
	public void shouldNotAddAnAirshipBecauseThisAirShipIsAlreadyRegistedIntoInMemoryAirshipsDatabase()
			throws DatabaseException, InvalidArgumentException {

		Assert.assertTrue(airshipDatabase.add(airship, user));
		Assert.assertFalse(airshipDatabase.add(airship, user));

	}

	@Test
	public void shoulgetAllTheElementInMemoryDatabase() throws Exception {

		Airship airship1 = new CivilAirship(45, 300, 10000, 20000, 0, 100);
		Airship airship2 = new CivilAirship(-45, 160, 5000, 15000, 0, 300);

		airshipDatabase.add(airship, user);
		airshipDatabase.add(airship1, user);

		Map<String, Airship> mapResult = airshipDatabase.getAll().get();

		Assert.assertTrue(mapResult.containsKey(airship.getIdentification())
				&& mapResult.containsKey(airship1.getIdentification()));

		Assert.assertFalse(mapResult.containsKey(airship2.getIdentification()));

	}

	// Test Exceptions

	@Test (expected = DatabaseException.class)
	public void shouldThrowDatabaseExceptionWhenTryingToRemoveTheMasterUserFromAUserDatabase()
			throws DatabaseException, InvalidArgumentException {

		// Assert

		userDatabase.removeByIdentification("MASTER");

	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryToGetAnElementByIdentificationGivingNullIdenfitication()
			throws InvalidArgumentException {

		// Assert

		userDatabase.getElementByIdentification(null);

	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryToCreateAnInMemoryUsersDatabaseWythNullDatabaseName()
			throws InvalidArgumentException {

		// Assert
		userDatabase = new InMemoryUsersDatabase(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryToGetAllElementsUsingAnNullPredicate()
			throws InvalidArgumentException {

		// Assert
		airshipDatabase.getAllElementsThat(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryToRemoveAnElementGivingANullIdentification()
			throws InvalidArgumentException, DatabaseException {

		// Assert
		airshipDatabase.removeByIdentification(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryToAddAnAirshipGivingANullUser()
			throws InvalidArgumentException {

		// Assert
		airshipDatabase.add(airship, null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryToAddAnAirshipGivingANullAirship()
			throws InvalidArgumentException {

		// Assert
		airshipDatabase.add(null, user);
	}
}