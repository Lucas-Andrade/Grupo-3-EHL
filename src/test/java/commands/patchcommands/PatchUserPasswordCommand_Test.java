package test.java.commands.patchcommands;

import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.commandfactories.userauthenticatingfactories.patchfactories.PatchUserPasswordCommandsFactory;
import main.java.cli.commands.patchcommands.PatchUserPasswordCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.InvalidRegisterException;

import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PatchUserPasswordCommand_Test {

	CommandParser cmdparser = new CommandParser();
	InMemoryUsersDatabase userDatabase;
	
	@Before
	public void BeforeTests() throws InvalidArgumentException, InvalidRegisterException {

	userDatabase = new InMemoryUsersDatabase("usersDatabase");
	
	cmdparser.registerCommand("PATCH", "/users/{username}", new PatchUserPasswordCommandsFactory(userDatabase) );
	
	} 

	@Test
	public void shouldChangesTheUserPassword() throws Exception{
		
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		userDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"PATCH", "/users/pantunes", "oldPassword=pass&newPassword=pass2&loginName=pantunes&loginPassword=pass");
		
		String result = parser.getCommand().call().toString();
		
		Assert.assertEquals("The User Password was successfully changed",result);
		 
	} 
	
	@Test
	public void shouldNotChangesTheUserPassword() throws Exception{
		
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		userDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"PATCH", "/users/pantunes", "oldPassword=fakepass&newPassword=pass2&loginName=pantunes&loginPassword=pass");
		String result = parser.getCommand().call().toString();
		
		Assert.assertEquals("The User Password was not changed",result);
		 
	}	 
		
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullOldPassword()  throws Exception{
		
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		userDatabase.add(user1, user1);
		
		new PatchUserPasswordCommand(userDatabase,"pantunes",null,"newPassword").call();
			
			
	}
	
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullNewPassword()  throws Exception{
		
						
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		userDatabase.add(user1, user1);
		
		new PatchUserPasswordCommand(userDatabase,"pantunes","oldPassword",null).call();
			
	}
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullIdentification() throws Exception  {
		
		
		 new PatchUserPasswordCommand(userDatabase,null,"oldPassword","newPassword").call();
			
	}
	
	
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullUserDatabase() throws InvalidArgumentException  {
		
						
		 new PatchUserPasswordCommand(null,"pantunes","oldPassword","newPassword");
			 
	}
	
	
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullUserDatabaseInTheFactory() throws InvalidArgumentException   {
					
		new PatchUserPasswordCommandsFactory(null);
	}	  
}
