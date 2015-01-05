package test.java.model;

import java.util.ArrayList;
import java.util.List;

import main.java.cli.Optional;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//github.com/Lucas-Andrade/Grupo-3-EHL.git
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.AirshipPredicates;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.MilitaryAirship;

public class InMemoryDatabase_Tests {

	private InMemoryUsersDatabase userDatabase;
	private InMemoryAirshipsDatabase airshipDatabase;
	private Airship airship1, airship2, airship3;
	private User user, user2;

	// Before

	@Before
	public void Before() throws InvalidArgumentException {

		// Arrange
		userDatabase = new InMemoryUsersDatabase("Users Database");
		airshipDatabase = new InMemoryAirshipsDatabase("Airships Database");
		airship1 = new MilitaryAirship(0, 0, 0, 10, 0, false);
		user = new User("X", "P", "T@", "O");
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldCreateAnInMemoryUserDatabaseWithAMasterUser() throws InvalidArgumentException {

		// Arrange
		InMemoryUsersDatabase userDatabase = new InMemoryUsersDatabase("Users Database");
		User masterUser = new User("MASTER", "master", "master@master");

		// Assert
		Assert.assertEquals(userDatabase.getElementByIdentification("MASTER").toString(),
				masterUser.toString());
	}

	@Test
	public void shouldAddAnElementToTheDatabase() throws InvalidArgumentException {

		// Assert
		Assert.assertTrue(airshipDatabase.add(airship1, user));
		Assert.assertTrue(userDatabase.add(user, user));
	}

	@Test
	public void shouldNotAddTheSameElemenToADatabase() throws InvalidArgumentException {

		// Act
		airshipDatabase.add(airship1, user);

		// Assert
		Assert.assertFalse(airshipDatabase.add(airship1, user));
	}

	@Test
	public void shouldNotAddUsersWithTheSameEmailToAUserDataBase() throws InvalidArgumentException {

		// Act
		userDatabase.add(user, user);

		// Assert
		Assert.assertFalse(userDatabase.add(new User("XPTO", "P", "T@", "O"), user));
	}

	@Test
	public void shouldRemoveAnElement() throws DatabaseException, InvalidArgumentException {

		// Act
		airshipDatabase.add(airship1, user);
		userDatabase.add(user, user);

		// Assert
		Assert.assertTrue(airshipDatabase.removeByIdentification(airship1.getIdentification()));
		Assert.assertTrue(userDatabase.removeByIdentification(user.getIdentification()));
	}

	@Test
	public void shouldNotRemoveAnElementThatDoesNotExist() throws DatabaseException,
			InvalidArgumentException {

		// Assert
		Assert.assertFalse(airshipDatabase.removeByIdentification(airship1.getIdentification()));
	}

	@Test
	public void shouldGetAllAirshipsRegistedByTheSameUser()
			throws NoSuchElementInDatabaseException, InvalidArgumentException {

		// Arrange
		user2 = new User("daniel", "d", "@d");
		airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
		airship3 = new CivilAirship(0, 0, 0, 10, 5, 20);
		List<Airship> airships = new ArrayList<>();

		// Act
		airshipDatabase.add(airship1, user);
		airshipDatabase.add(airship2, user);
		airshipDatabase.add(airship3, user2);

		airships.add(airship1);
		airships.add(airship2);

		// Assert
		Assert.assertEquals(airshipDatabase.getAirshipsOfUser("X").toString(), airships.toString());
	}

	@Test
	public void shouldGetAllAirshipsThatAreTransgressing() throws InvalidArgumentException {

		// Arrange
		airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
		List<Airship> airships = new ArrayList<>();

		// Act
		airshipDatabase.add(airship1, user);
		airshipDatabase.add(airship2, user);

		airships.add(airship2);

		// Assert
		Assert.assertEquals(
				airshipDatabase.getAllElementsThat(
						new AirshipPredicates.IsTrangressingItsAirCorridor()).toString(),
				airships.toString());
	}

	@Test
	public void shouldGetAllAirshipsThatVerifyHaveANumberOfPassengersBellowAThreshold() throws InvalidArgumentException {

		// Arrange
		airship2 = new CivilAirship(0, 0, 0, 10, 5, 100);
		Airship airship3 = new CivilAirship(0, 0, 0, 10, 5, 2);
		Airship airship4 = new CivilAirship(0, 0, 0, 10, 5, 200);

		InMemoryAirshipsDatabase airships = new InMemoryAirshipsDatabase("secondUsersDataBase");

		// Act
		airshipDatabase.add(airship1, user);
		airshipDatabase.add(airship2, user);
		airshipDatabase.add(airship3, user);
		airshipDatabase.add(airship4, user);

		airships.add(airship3, user);
		airships.add(airship2, user);

		// Assert
		Assert.assertEquals(
				airshipDatabase.getAllElementsThat(
						new AirshipPredicates.HasPassagersNumberBelowAThreshold(102)).toString(),
				airships.toString());
	}

	@Test
	public void shouldReturnAOptionalWithAndApropriateErrorMessageWhenTryingToGetTheAirshipsOfAUserThatDidNotRegistedAnyAirship()
			throws InvalidArgumentException {

		// Arrange
		airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
		List<Airship> airships = new ArrayList<>();

		// Act
		airshipDatabase.add(airship1, user);
		airshipDatabase.add(airship2, user);

		airships.add(airship1);
		airships.add(airship2);

		Optional<Iterable<Airship>> list = airshipDatabase.getAirshipsOfUser("Daniel");

		// Assert
		Assert.assertEquals(list.toString(), "No airship added by Daniel");
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateADatabaseWithANullName()
			throws InvalidArgumentException {

		// Assert
		new InMemoryAirshipsDatabase(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenTryingToAddNullElementsToADatabase()
			throws InvalidArgumentException {

		// Assert
		airshipDatabase.add(null, user);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenTryingToAddElementsToADatabaseGivenNullUser()
			throws InvalidArgumentException {

		// Assert
		airshipDatabase.add(airship1, null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenTryingToRemoveElementsFromADatabaseGivenANullIdentification()
			throws DatabaseException, InvalidArgumentException {

		// Assert
		airshipDatabase.removeByIdentification(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingGetAnElementFromADatabaseGivenANullIdentification()
			throws InvalidArgumentException {

		// Assert
		userDatabase.getElementByIdentification(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGetAllElementThatVerifyACertainCriteriaWhitoutSpecifyingIt()
			throws InvalidArgumentException {

		// Assert
		airshipDatabase.getAllElementsThat(null);
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

		Assert.assertTrue(airshipDatabase.add(airship1, user));
		Assert.assertFalse(airshipDatabase.add(airship1, user));

	}

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
		airshipDatabase.add(airship1, null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryToAddAnAirshipGivingANullAirship()
			throws InvalidArgumentException {

		// Assert
		airshipDatabase.add(null, user);
	}
}
