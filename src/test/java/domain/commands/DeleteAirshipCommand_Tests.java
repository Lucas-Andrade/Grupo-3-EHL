package test.java.domain.commands;

import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.DeleteAirshipCommandsFactory;
import main.java.domain.commands.DeleteAirshipCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
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
		cmdParser.registerCommand("DELETE", "/airships/{flightId}", new DeleteAirshipCommandsFactory(userDatabase, airshipsDatabase));
		
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
