package test.java.commands;

import main.java.cli.commands.patchcommands.PatchUserPasswordCommand;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.patchfactories.PatchUserPasswordCommandsFactory;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import main.java.cli.utils.exceptions.commandparserexceptions.InvalidRegisterException;
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
		
		Parser parser = new Parser(cmdparser,"PATCH", "/users/pantunes", "oldPassword=pass&newPassword=pass2");
		String result = parser.getCommand("PATCH", "/users/pantunes", "oldPassword=pass&newPassword=pass2").call().toString();
		
		Assert.assertEquals("The User Password was successfully changed",result);
		 
	} 
	
	@Test
	public void shouldNotChangesTheUserPassword() throws Exception{
		
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		userDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"PATCH", "/users/pantunes", "oldPassword=fakepass&newPassword=pass2");
		String result = parser.getCommand("PATCH", "/users/pantunes", "oldPassword=fakepass&newPassword=pass2").call().toString();
		
		Assert.assertEquals("The User Password was not changed",result);
		 
	}	 
		
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullOldPassword()  throws Exception{
		
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		userDatabase.add(user1, user1);
				
		@SuppressWarnings("unused")
		PatchUserPasswordCommand patchuserPassword = new PatchUserPasswordCommand(userDatabase,"pantunes",null,"newPassword");
			
	}
	
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullNewPassword()  throws Exception{
		
						
		@SuppressWarnings("unused")
		PatchUserPasswordCommand patchuserPassword = new PatchUserPasswordCommand(userDatabase,"pantunes","oldPassword",null);
			
	}
	
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullIdentification()  throws Exception{
		
					
		@SuppressWarnings("unused")
		PatchUserPasswordCommand patchuserPassword = new PatchUserPasswordCommand(userDatabase,null,"oldPassword","newPassword");
			
	}
	
	
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullUserDatabase()  throws Exception{
		
						
		@SuppressWarnings("unused")
		PatchUserPasswordCommand patchuserPassword = new PatchUserPasswordCommand(null,"pantunes","oldPassword","newPassword");
			 
	}
	
	
	@Test(expected=InvalidArgumentException.class)
	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullUserDatabaseInTheFactory()  throws Exception{
					
		@SuppressWarnings("unused")
		PatchUserPasswordCommandsFactory patchuserPassword = new PatchUserPasswordCommandsFactory(null);
			
	}	
	
}
