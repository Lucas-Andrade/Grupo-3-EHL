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

public class InMemoryAirshipDatabaseTests
{
	private Database<Airship> airShipDatabase;
	private Airship airship;
	private User user;

	@Before
	public void Before()
	{
		// Arrange
		airShipDatabase = new InMemoryAirshipDatabase();
		airship = new MilitaryAirship(0, 0, 0, 10, 0, false);
		user = new User("X", "P", "T@", "O");
	}

	@Test
	public void shouldAddMilitaryAirship()
	{
		// Assert
		assertTrue( airShipDatabase.add( airship, user ) );
	}
	
	@Test
	public void shouldAddSecondMilitaryAirshipByTheSameUser()
	{
		//Arrange
		Airship airship2 = new MilitaryAirship( 0, 0, 0, 10, 0, false );
		
		// Assert
		assertTrue( airShipDatabase.add( airship, user ) );
		assertTrue( airShipDatabase.add( airship2, user ) );
	}

	@Test
	public void shouldNotAddTheSameMilitaryAirship()
	{
		airShipDatabase.add( airship, user );
		// Assert
		assertFalse( airShipDatabase.add( airship, user ) );
	}


	@Test
	public void shouldReturnFalseCheckingIfThisAirshipIsInCorridor()
	{
		airShipDatabase.add( airship, user );
		// Assert
		assertFalse( ( ( InMemoryAirshipDatabase )airShipDatabase )
				.checkIfThisAirshipIsInCorridor( airship.getIdentification() ) );
	}
	
	@Test
	public void shouldReturnTrueCheckingIfThisAirshipIsInCorridor()
	{
		//Arrange
		Airship airship2 = new MilitaryAirship( 0, 0, 0, 10, 5, false );
		
		//Act
		airShipDatabase.add( airship2, user );
		
		// Assert
		assertTrue( ( ( InMemoryAirshipDatabase )airShipDatabase )
				.checkIfThisAirshipIsInCorridor( airship2.getIdentification() ) );
	}






}
