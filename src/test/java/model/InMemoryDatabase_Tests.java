package test.java.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import main.java.model.airships.Airship;
import main.java.model.airships.AirshipPredicates;
import main.java.model.airships.CivilAirship;
import main.java.model.airships.InMemoryAirshipDatabase;
import main.java.model.airships.MilitaryAirship;
import main.java.model.users.InMemoryUserDatabase;
import main.java.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryDatabase_Tests {

	private InMemoryAirshipDatabase airshipDatabase;
	private Airship airship, airship2;
	private User user;

	@Before
	public void Before() {

		// Arrange
		airshipDatabase = new InMemoryAirshipDatabase();
		airship = new MilitaryAirship(0, 0, 0, 10, 0, false);
		user = new User("X", "P", "T@", "O");
	}

	@Test
	public void shouldCreateInMemoryUserDatabaseWithAMasterUser() {

		// Arrange
		InMemoryUserDatabase userDatabase = new InMemoryUserDatabase();
		User masterUser = new User("MASTER", "master", "master@gmail.com");

		// Assert
		Assert.assertEquals(userDatabase.getElementByIdentification("MASTER").toString(),
				masterUser.toString());
	}

	@Test (expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenTryingToAddNullElementsToADatabase() {

		// Assert
		airshipDatabase.add(null, user);
	}

	@Test
	public void shouldAddMilitaryAirship() {

		// Assert
		assertTrue(airshipDatabase.add(airship, user));
	}

	@Test
	public void shouldAddSecondMilitaryAirshipByTheSameUser() {

		// Arrange
		airship2 = new MilitaryAirship(0, 0, 0, 10, 0, false);

		// Assert
		assertTrue(airshipDatabase.add(airship, user));
		assertTrue(airshipDatabase.add(airship2, user));
	}

	@Test
	public void shouldNotAddTheSameMilitaryAirship() {

		// Act
		airshipDatabase.add(airship, user);

		// Assert
		assertFalse(airshipDatabase.add(airship, user));
	}

	@Test
	public void shouldGetAllAirshipsRegistedByTheSameUser() throws NoSuchElementInDatabaseException {

		// Arrange
		airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
		List<Airship> airships = new ArrayList<>();

		// Act
		airshipDatabase.add(airship, user);
		airshipDatabase.add(airship2, user);

		airships.add(airship);
		airships.add(airship2);

		// Assert
		Assert.assertEquals(airshipDatabase.getAirshipsOfUser("X").toString(), airships.toString());
	}

	@Test (expected = NoSuchElementInDatabaseException.class)
	public void shouldThrowNoSuchElementInDatabaseExceptionWhenTryingToGetTheAirshipsOfAUserThatDidNotRegistedAnyAirship()
			throws NoSuchElementInDatabaseException {

		// Arrange
		airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
		List<Airship> airships = new ArrayList<>();

		// Act
		airshipDatabase.add(airship, user);
		airshipDatabase.add(airship2, user);

		airships.add(airship);
		airships.add(airship2);

		// Assert
		airshipDatabase.getAirshipsOfUser("Daniel");
	}

	@Test
	public void shouldGetAllAirshipsThatAreTransgressing() {

		// Arrange
		airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
		List<Airship> airships = new ArrayList<>();

		// Act
		airshipDatabase.add(airship, user);
		airshipDatabase.add(airship2, user);

		airships.add(airship2);

		// Assert
		Assert.assertEquals(airshipDatabase.getAirshipsThat(new AirshipPredicates.IsTransgressing()).toString(), airships.toString());
	}

	@Test
	public void shouldGetAllAirshipsThatVerifyACertainCriteria() {

		// Arrange
		airship2 = new CivilAirship(0, 0, 0, 10, 5, 100);
		Airship airship3 = new CivilAirship(0, 0, 0, 10, 5, 2);
		Airship airship4 = new CivilAirship(0, 0, 0, 10, 5, 200);

		List<Airship> airships = new ArrayList<>();

		// Act
		airshipDatabase.add(airship, user);
		airshipDatabase.add(airship2, user);
		airshipDatabase.add(airship3, user);
		airshipDatabase.add(airship4, user);

		airships.add(airship2);
		airships.add(airship3);

		// Assert
		Assert.assertEquals(
				airshipDatabase.getAirshipsThat(new AirshipPredicates.HasPassagersNumberBelowAThreshold(102)).toString(), airships.toString());
	}
}