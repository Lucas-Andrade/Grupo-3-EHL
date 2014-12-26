package test.java.commandlineuserinterface.model;

import main.java.commandlineuserinterface.model.users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Those Tests were created to test the {@link User} Class 
 * who's part of  the Air Traffic Project
 *
 */

public class UserTest {
	
	User user1, user2;

	@Before
	
	public void BeforeTests(){
		
		user1 = new User("Pedro","pass","pedro@gmail.com","Pedro Antunes");
		user2 = new User("Gonçalo","pass2","Gonçalo@gmail.com");
	
	}
	
	@Test
	
	public void ShouldReturnUserIdentification() {
	
		Assert.assertTrue(user1.getIdentification().equals("Pedro"));
		Assert.assertTrue(user2.getIdentification().equals("Gonçalo"));

	}
	 
	@Test
	
	public void ShouldReturnAString(){
		
		Assert.assertTrue(user1.toString().equals("username: Pedro, password: pass, email: pedro@gmail.com, fullName: Pedro Antunes"));
		Assert.assertTrue(user2.toString().equals("username: Gonçalo, password: pass2, email: Gonçalo@gmail.com"));

	}
	
	@Test
	
	public void ShouldReturnUsername(){
		
		Assert.assertTrue(user1.getIdentification().equals("Pedro"));
		Assert.assertTrue(user2.getIdentification().equals("Gonçalo"));
	}
		
	@Test

	public void ShouldAnUserBeInstanceOfUserClass(){
		
		Assert.assertTrue(user1.getClass().equals(User.class));
		Assert.assertTrue(user2.getClass().equals(User.class));

	}
	
	@Test
	
	public void VerifyIfUsersAreEquals(){
		
		Assert.assertTrue(user1.equals(new User("Pedro","pass","pedro@gmail.com","Pedro Antunes")));
		Assert.assertFalse(user1.equals(user2)); 
	
	}
 
	@Test 
	
	public void VerifyIfUsersHasDiferentsUsernames(){
		
		Assert.assertFalse(user1.equals(new User("Gonçalo","pass","pedro@gmail.com","Pedro Antunes")));
	
	}
	
	@Test
	
	public void VerifyIfUsersHasDiferentsEmails(){
		
		Assert.assertFalse(user1.equals(new User("Pedro","pass","Gonçalo@gmail.com","Pedro Antunes")));

 
	} 
	
	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenUsernameIsNull(){
	
		new User(null,"pass","pantunes@gmail.com","Pantunes da Silva Pantunes");
	} 

	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenPasswordIsNull(){
	
		new User("Pantunes",null,"pantunes@gmail.com","Pantunes da Silva Pantunes");
	}
	
	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenEmailIsNull(){
	
		new User("Pantunes","pass",null,"Pantunes da Silva Pantunes");
	}
			
	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenEmailDoesntHaveAt(){

		new User("Pantunes","pass","pantunesgmail.com","Pantunes da Silva Pantunes");

	}

	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenEmailDoesntHaveOnlyOneAt(){
		
		new User("Pantunes","pass","pant@unes@gmail.com","Pantunes da Silva Pantunes");
	}
	
	@Test(expected=IllegalArgumentException.class)
	
	public void ShouldThrowIllegalArgumentExceptionWhenFullnameIsNull(){
		
		new User("pantunes","pass","pantunes@gmail.com",null);
	}
	
	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenUsernameIsAnEmptyString(){
		
		new User("","pass","pantunes@gmail.com","Pantunes da Silva Pantunes");

	}

	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenPasswordIsAnEmptyString(){
		
		new User("pantunes","","pantunes@gmail.com","Pantunes da Silva Pantunes");

	}
	
	@Test(expected=IllegalArgumentException.class)

	public void ShouldThrowIllegalArgumentExceptionWhenEmailIsAnEmptyString(){
		
		new User("pantunes","password","","Pantunes da Silva Pantunes");

	}
	
}
