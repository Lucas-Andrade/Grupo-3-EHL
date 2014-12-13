package airtrafficcontrol.tests.testsforcommandline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAllAirshipsCommand;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.CivilAirship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.airships.MilitaryAirship;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class GetAllAirshipsCommand_Tests {

	private CommandParser parser = new CommandParser();

	private InMemoryUserDatabase userDatabase;
	private InMemoryAirshipDatabase airshipDatabase;

	private User user1, user2, user3;
	private Airship airship1, airship2, airship3;

	@Before
	public void createUserAndAirshipDatabaseAndAddElements() {

		user1 = new User("daniel", "pass1", "daniel@gmail.com");
		user2 = new User("eva", "pass2", "eva@gmail.com");
		user3 = new User("goncalo", "pass3", "goncalo@gmail.com");

		userDatabase = new InMemoryUserDatabase();

		userDatabase.add(user1, user1);
		userDatabase.add(user2, user1);
		userDatabase.add(user3, user1);

		airship1 = new MilitaryAirship(10, 10, 1000, 2000, 200, false);
		airship2 = new CivilAirship(10, 10, 3000, 2000, 200, 100);
		airship3 = new CivilAirship(10, 10, 1000, 2000, 200, 100);

		airshipDatabase = new InMemoryAirshipDatabase();

		airshipDatabase.add(airship1, user1);
		airshipDatabase.add(airship2, user1);
		// airshipDatabase.add(airship3, user2);
	}

	@Test
	public void shouldSuccessfullyGetAllAirships() throws Exception {

		// Act
		parser.registerCommand("GET", "/airships", new GetAllAirshipsCommand.Factory(
				airshipDatabase));

		GetAllAirshipsCommand getAllAirships = (GetAllAirshipsCommand) parser.getCommand("GET",
				"/airships");

		getAllAirships.execute();

		// Assert
		Assert.assertEquals(getAllAirships.getResult(),

				"Flight ID: 1\nLatitude: 10.0 Longitude: 10.0 Altitude: 1000.0"
				+ "\nMaximum Altitude Permited: 2000.0 Minimum Altitude Permited: 200.0"
				+ "\nIs Outside The Given Corridor: false\n"

				+ "Flight ID: 2\nLatitude: 10.0 Longitude: 10.0 Altitude: 3000.0"
				+ "\nMaximum Altitude Permited: 2000.0 Minimum Altitude Permited: 200.0"
				+ "\nIs Outside The Given Corridor: true\n");
	}
}