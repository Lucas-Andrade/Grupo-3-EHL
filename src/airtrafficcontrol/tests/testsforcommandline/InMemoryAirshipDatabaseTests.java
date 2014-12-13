package airtrafficcontrol.tests.testsforcommandline;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import airtrafficcontrol.app.appforcommandline.model.Database;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.airships.MilitaryAirship;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class InMemoryAirshipDatabaseTests {

	private Database<Airship> airshipDatabase;
	private Airship airship;
	private User user;

	@Before
	public void Before() {

		// Arrange
		airshipDatabase = new InMemoryAirshipDatabase();
		airship = new MilitaryAirship(0, 0, 0, 10, 0, false);
		user = new User("X", "P", "T@", "O");
	}

	@Test
	public void shouldAddMilitaryAirship() {

		// Assert
		assertTrue(airshipDatabase.add(airship, user));
	}

	@Test
	public void shouldAddSecondMilitaryAirshipByTheSameUser() {

		// Arrange
		Airship airship2 = new MilitaryAirship(0, 0, 0, 10, 0, false);

		// Assert
		assertTrue(airshipDatabase.add(airship, user));
		assertTrue(airshipDatabase.add(airship2, user));
	}

	@Test
	public void shouldNotAddTheSameMilitaryAirship() {

		airshipDatabase.add(airship, user);
		// Assert
		assertFalse(airshipDatabase.add(airship, user));
	}

	@Test
	public void shouldReturnFalseCheckingIfThisAirshipIsInCorridor() {

		airshipDatabase.add(airship, user);
		// Assert
		assertFalse(((InMemoryAirshipDatabase) airshipDatabase)
				.checkIfThisAirshipIsInCorridor(airship.getIdentification()));
	}

	@Test
	public void shouldReturnTrueCheckingIfThisAirshipIsInCorridor() {

		// Arrange
		Airship airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);

		// Act
		airshipDatabase.add(airship2, user);

		// Assert
		assertTrue(((InMemoryAirshipDatabase) airshipDatabase)
				.checkIfThisAirshipIsInCorridor(airship2.getIdentification()));
	}
}
