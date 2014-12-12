package airtrafficcontrol.tests.testsforcommandline.testcommands;

import org.junit.Before;

import airtrafficcontrol.app.appforcommandline.commandparser.CommandParser;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class Command_Tests {

	private CommandParser parser = new CommandParser();
	
	private InMemoryUserDatabase userDatabase;
	private InMemoryAirshipDatabase airshipDatabase;
	
	private User user1, user2, user3;
	private Airship airship1, airship2, airship3, airship4;
	
	@Before
	public void createUserAndAirshipDatabaseAndAddElements() {
		
		userDatabase = new InMemoryUserDatabase();
		
		userDatabase.add(user1, user1);
		userDatabase.add(user2, user1);
		userDatabase.add(user3, user1);
		
		airshipDatabase = new InMemoryAirshipDatabase();
		
		airshipDatabase.add(airship1, user1);
		airshipDatabase.add(airship2, user1);
		airshipDatabase.add(airship3, user2);
		airshipDatabase.add(airship4, user2);
	}
}