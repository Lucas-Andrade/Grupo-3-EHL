package test.java.commands;

import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.commandfactories.userauthenticatingfactories.DeleteAirshipCommandFactory;
import main.java.cli.commands.DeleteAirshipCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DeleteAirshipCommand_Tests {
	
	CommandParser cmdParser;
	InMemoryAirshipsDatabase airshipsDatabase;
	InMemoryUsersDatabase userDatabase;
	User user;
	
	@Before
	public void BeforeTests() throws InvalidRegisterException, InvalidArgumentException{
		
		cmdParser = new CommandParser();		
		airshipsDatabase = new InMemoryAirshipsDatabase("airshipsDatabase");
		userDatabase = new InMemoryUsersDatabase("userDatabase");
		user = new User("pantunes", "pantunespassword", "pantunes@gmail.com");
		userDatabase.add(user, user);
		cmdParser.registerCommand("DELETE", "/airships/{flightId}", new DeleteAirshipCommandFactory(userDatabase, airshipsDatabase));
		
	}
	
	@Test
	public void shoulDeleteAnAirhipsMemberOfInMemoryAirshipsDatabase() throws Exception{
		
		Airship air1 = new CivilAirship(30, 230, 10000, 20000, 0, 199);
		
		airshipsDatabase.add(air1, user);
	
		
		Parser parser = new Parser(cmdParser, "DELETE", 
				new StringBuilder("/airships/").append(air1.getIdentification()).toString()
																				,"loginName=pantunes&loginPassword=pantunespassword");
		
		Assert.assertEquals("Airship successfully removed",parser.getCommand().call().toString());
	}
	
	@Test
	public void shouldNotDeleteAnAirshipBecauseAnInvalidLoginPassword() throws Exception{
		
		Airship air1 = new CivilAirship(30, 230, 10000, 20000, 0, 199);
	
		
		Parser parser = new Parser(cmdParser, "DELETE", 
				new StringBuilder("/airships/").append(air1.getIdentification()).toString()
																				,"loginName=pantunes&loginPassword=pantunespassword");
		
		Assert.assertEquals("Airship doesn't exist in the database",parser.getCommand().call().toString());
	}
		
	@Test(expected=InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenGiveAnNullInMemoryAirshipsDatabase() throws Exception{
		
		Airship air1 = new CivilAirship(30, 230, 10000, 20000, 0, 199);
		
		new DeleteAirshipCommand(null,air1.getIdentification() );
	}
	
	@Test(expected=InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenGiveAnNullIdentification() throws Exception{
			
		new DeleteAirshipCommand(airshipsDatabase,null );
	}
	
}
