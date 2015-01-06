package test.java.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.GeographicPosition;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import main.java.cli.utils.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryAirshipsDatabase_Tests {
     
	InMemoryUsersDatabase userDatabase;
	private InMemoryAirshipsDatabase airshipDatabase;
	private Airship airship, airship2, airship3;
	private User user, user2;
	 
	@Before
	public void Before() { 
		try {

			// Arrange
			userDatabase = new InMemoryUsersDatabase("newUsersDataBase");

			airshipDatabase = new InMemoryAirshipsDatabase(
					"newAirshipsdataBase");

			airship = new MilitaryAirship(0, 0, 0, 10, 0, false);
			user = new User("X", "P", "T@", "O");

		} catch (InvalidArgumentException e) { 
			e.printStackTrace();
		} 
  
	}

	@Test
	public void shouldAddAnAirshipToTheInMemoryAirshipsDatabase() {
		try {
			// Assert
			assertTrue(airshipDatabase.add(airship, user));

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void shouldNotAddTheSameaAirshipToTheInMemoryAirshipsDatabase() {
		try {
			// Act
			assertTrue(airshipDatabase.add(airship, user));

			// Assert
			assertFalse(airshipDatabase.add(airship, user));

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void shouldRemoveAnAirship() throws DatabaseException {
		try {
			// Act
			
			airshipDatabase.add(airship,user);
			airshipDatabase.add(airship, user);

			// Assert
			assertTrue(airshipDatabase.removeByIdentification(airship
					.getIdentification()));
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
	} 
	 
	@Test
	public void shouldNotRemoveAnAirshipThatDoesNotExist()
			throws DatabaseException {
		try {
			// Assert
			assertFalse(airshipDatabase.removeByIdentification(airship
					.getIdentification()));
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldGetAllAirshipsRegistedByTheSameUser()
			throws NoSuchElementInDatabaseException {

		try {
			// Arrange 
			user2 = new User("daniel", "d", "@d");
			airship2 = new MilitaryAirship(0, 0, 0, 10, 5, false);
			airship3 = new CivilAirship(0, 0, 0, 10, 5, 20);
			List<Airship> airships = new ArrayList<>();

			// Act 
			airshipDatabase.add(airship, user);
			airshipDatabase.add(airship2, user);
			airshipDatabase.add(airship3, user2);

			airships.add(airship);
			airships.add(airship2);

			// Assert
			Assert.assertEquals(airshipDatabase.getAirshipsOfUser("X")
					.toString(), airships.toString());
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void shouldReceiveAMessageConfirmingThatAnyAirshipWasAddedByANullUser () throws InvalidArgumentException{
		
		Assert.assertEquals("No airship added by null",airshipDatabase.getAirshipsOfUser(null).toString());
		
	}
	 
	@Test
	public void shouldRemoveAnAirshipButInMemoryAirshipsDatabaseKeepsNotEmpty() throws InvalidArgumentException, DatabaseException{
		
		 Airship airship2 =  new CivilAirship(0, 0, 0, 10, 0, 100);

		airshipDatabase.add(airship,user);
		airshipDatabase.add(airship2,user); 
			 
		assertTrue(airshipDatabase.removeByIdentification(airship.getIdentification()));
		
}
	
	
	@Test
	public void find() throws InvalidArgumentException{
		
		
		Airship air1 = new CivilAirship(30, 225, 10000, 20000, 0, 100);
		Airship air2 = new MilitaryAirship(0, 315, 15000, 20000, 0, true);
		Airship air3 = new CivilAirship(45, 180, 12000, 20000, 0, 50);
		Airship air4 = new MilitaryAirship(-60, 90, 15000, 20000, 0, false);
		Airship air5 = new CivilAirship(-60, 225, 12000, 20000, 0, 50);
		Airship air6 = new CivilAirship(-90, 360, 12000, 20000, 0, 50);
		Airship air7 = new CivilAirship(30, 45, 12000, 20000, 0, 50);

		airshipDatabase.add(air1, user);
		airshipDatabase.add(air2, user);
		airshipDatabase.add(air3, user);
		airshipDatabase.add(air4, user);
		airshipDatabase.add(air5, user);
		airshipDatabase.add(air6, user);
		airshipDatabase.add(air7, user); 
		 
		
		 
		
	 String result = airshipDatabase.getAirshipsCloserTo(new GeographicPosition(60, 225, 10000), 2).toString() ;

	 Assert.assertEquals("["+air1.toString()+", "+air3.toString()+"]",result);
	}
	
	
	
	
	// Test Exceptions

	@Test(expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToAddNullElementsToADatabase()
			throws InvalidArgumentException {

		// Assert

		airshipDatabase.add(null, user);

	}
	
	@Test(expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToAddElementsToADatabaseGivenNullUser()

			throws InvalidArgumentException {

		// Assert
		airshipDatabase.add(airship, null);

	} 
	
	@Test(expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToRemoveNullElementsFromADatabase() 
			throws  InvalidArgumentException, DatabaseException {

	
		airshipDatabase.removeByIdentification(null);
	
	}

 

}
