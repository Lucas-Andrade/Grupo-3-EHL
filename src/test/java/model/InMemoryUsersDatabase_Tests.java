package test.java.model;

import static org.junit.Assert.assertTrue;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InMemoryUsersDatabase_Tests { 
	
	 
	InMemoryUsersDatabase userDatabase; 
	private InMemoryAirshipsDatabase airshipDatabase;
	private Airship airship;
	private User user;
	
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
	public void shouldAddAnElementToTheDatabase() {
		try {
			// Assert
			assertTrue(airshipDatabase.add(airship, user));
			assertTrue(userDatabase.add(user, user));

		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

	}
	
	@Test 
	public void shouldNotAddTheSameUserIntoInMemoryUsersDatabase() throws InvalidArgumentException{
		
		Assert.assertTrue(userDatabase.add(user,user));
		Assert.assertFalse(userDatabase.add(user,user));
	}
	
	
	@Test 
	public void shouldRemoveAnUserFromInMemoryUsersDatabase() throws InvalidArgumentException, DatabaseException{
		
		
		userDatabase.add(user,user);
		Assert.assertTrue(userDatabase.removeByIdentification(user.getIdentification()));
		
		
	}
		
	// Test Exceptions
	 
	@Test(expected = DatabaseException.class)
	
	public void shouldThrowDatabaseExceptionWhenTryingToRemoveTheMasterUserFromAUserDatabase()
			throws DatabaseException, InvalidArgumentException {
		
			// Assert 

			userDatabase.removeByIdentification("MASTER");

		

	}
	
	
	
	
	
}
