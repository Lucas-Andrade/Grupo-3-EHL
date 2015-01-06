package test.java.model;

import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	 
	User user1, user2;
 
	@Before
	
	public void BeforeTests() throws InvalidArgumentException{

		user1 = new User("Pedro","pass","pedro@gmail.com","Pedro Antunes");
		user2 = new User("Gonçalo","pass2","Gonçalo@gmail.com");
	 	 
	}  
	
	@Test
	
	public void ShouldReturnUserIdentification() {
	
		Assert.assertTrue(user1.getIdentification().equals("Pedro"));
		Assert.assertTrue(user2.getIdentification().equals("Gonçalo"));

	}  
	  
	@Test 
	
	public void ShouldReturnAStringWithAllTheInformation(){
		
		Assert.assertTrue(user1.toString().equals("username: Pedro, password: pass, email: pedro@gmail.com, fullName: Pedro Antunes\n"));
		
	}
	
	@Test
	public void ShouldReturnAStringWithAllTheInformationExceptFullname() {

		Assert.assertTrue(user2.toString().equals("username: Gonçalo, password: pass2, email: Gonçalo@gmail.com\n"));

	}

	@Test
	
	public void ShouldReturnUsername(){
		
		Assert.assertTrue(user1.getIdentification().equals("Pedro"));
		Assert.assertTrue(user2.getIdentification().equals("Gonçalo"));
	}
	@Test 
	public void ShouldReturnTheUserFullname(){
		
		Assert.assertEquals("Pedro Antunes",user1.getFullName());
		
	}

	
	@Test 
	
	public void ShouldReturnAnUserEmail(){
		
		
		Assert.assertEquals("pedro@gmail.com",user1.getEmail());
		
	}
	
	@Test

	public void ShouldAnUserBeInstanceOfUserClass(){
		
		Assert.assertTrue(user1.getClass().equals(User.class));
		Assert.assertTrue(user2.getClass().equals(User.class));

	}
	
	@Test
	
	public void VerifyIfUsersAreEquals() throws InvalidArgumentException{
		
		Assert.assertTrue(user1.equals(new User("Pedro","pass","pedro@gmail.com","Pedro Antunes")));
		Assert.assertFalse(user1.equals(user2)); 
	
	}
 
	@Test 
	
	public void VerifyIfUsersHasDiferentsUsernames() throws InvalidArgumentException{
		
		Assert.assertFalse(user1.equals(new User("Gonçalo","pass","pedro@gmail.com","Pedro Antunes")));
	
	}
	
	@Test
	
	public void VerifyIfUsersHasDiferentsEmails() throws InvalidArgumentException{
		
		Assert.assertFalse(user1.equals(new User("Pedro","pass","Gonçalo@gmail.com","Pedro Antunes")));

 
	} 
	
	@Test
	
	public void ShouldChangeTheUserPassword() throws InvalidArgumentException{

		Assert.assertTrue(user1.changePassword("newpass", "pass"));
		 
	}
	
	@Test
	
	public void ShouldNotChangeTheUserPassword() throws InvalidArgumentException{

		Assert.assertFalse(user1.changePassword("newpass", "password"));
		 
	} 
  
	@Test 
	
	public void ShouldReturnAStringWithoutPasswordButWithAnFullname(){
		
		Assert.assertEquals("username: Pedro, email: pedro@gmail.com, fullName: Pedro Antunes\n",user1.toStringWithoutPassword());

	}
	
	@Test
	
	public void ShouldReturnAStringWithoutPasswordButWithoutAnFullname(){
		
		
		Assert.assertEquals("username: Gonçalo, email: Gonçalo@gmail.com\n",user2.toStringWithoutPassword());
 
		
	} 
	
	@Test
	
	public void ShouldAuthenticatePassword(){
		
		Assert.assertTrue(user1.authenticatePassword("pass"));
		
	}
	
	@Test
	
	public void ShouldNotAuthenticatePassword(){
		
		Assert.assertFalse(user1.authenticatePassword("password"));
		
	}
		
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenUsernameIsNull() throws InvalidArgumentException{
	
		new User(null,"pass","pantunes@gmail.com","Pantunes da Silva Pantunes");
	} 

	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenPasswordIsNull() throws InvalidArgumentException{
	
		new User("Pantunes",null,"pantunes@gmail.com","Pantunes da Silva Pantunes");
	}
	
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenEmailIsNull() throws InvalidArgumentException{
	
		new User("Pantunes","pass",null,"Pantunes da Silva Pantunes");
	}
			
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenEmailDoesntHaveAt() throws InvalidArgumentException{

		new User("Pantunes","pass","pantunesgmail.com","Pantunes da Silva Pantunes");

	}

	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenEmailDoesntHaveOnlyOneAt() throws InvalidArgumentException{
		
		new User("Pantunes","pass","pant@unes@gmail.com","Pantunes da Silva Pantunes");
	} 
	 
	@Test(expected=InvalidArgumentException.class)
	
	public void ShouldThrowInvalidArgumentExceptionWhenFullnameIsNull() throws InvalidArgumentException{
		
		new User("pantunes","pass","pantunes@gmail.com",null);
	}
	
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenUsernameIsAnEmptyString() throws InvalidArgumentException {
		
		new User("","pass","pantunes@gmail.com","Pantunes da Silva Pantunes");

	} 
 
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenPasswordIsAnEmptyString() throws InvalidArgumentException{
		
		new User("pantunes","","pantunes@gmail.com","Pantunes da Silva Pantunes");

	}
	
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenEmailIsAnEmptyString() throws InvalidArgumentException{
		
		new User("pantunes","password","","Pantunes da Silva Pantunes");

	} 
	
	
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenTryingToChangeThePasswordGivingANull() throws InvalidArgumentException{
		
		user1.changePassword(null, "pass");

	} 
	@Test(expected=InvalidArgumentException.class)

	public void ShouldThrowInvalidArgumentExceptionWhenTryingToChangeThePasswordGivingAStringWithoutCharacters() throws InvalidArgumentException{
		
		user1.changePassword("", "pass");

	} 
	 
	@Test 
	
	public void OnlyBecauseIWant100PercentCoverage() throws InvalidArgumentException{
		
		Airship airship = new MilitaryAirship(0, 0, 0, 10, 0, false);
		Assert.assertFalse(user1.equals(null)); 
		Assert.assertTrue(user1.equals(user1));
		Assert.assertFalse(user1.equals(airship));  
 
	} 

	
	
}
