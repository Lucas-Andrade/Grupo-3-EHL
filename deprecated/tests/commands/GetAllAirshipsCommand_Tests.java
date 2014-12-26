package test.java.commandlineuserinterface.commands;

import main.java.commandlineuserinterface.CommandParser;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetAllAirshipsCommand;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.commandlineuserinterface.model.airships.Airship;
import main.java.commandlineuserinterface.model.airships.CivilAirship;
import main.java.commandlineuserinterface.model.airships.InMemoryAirshipDatabase;
import main.java.commandlineuserinterface.model.airships.MilitaryAirship;
import main.java.commandlineuserinterface.model.users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetAllAirshipsCommand_Tests {

	private CommandParser parser = new CommandParser();

	private InMemoryAirshipDatabase airshipDatabase;

	private User user1 = new User("daniel", "pass1", "daniel@gmail.com");
	private static Airship airship1 = new MilitaryAirship(10, 10, 1000, 2000, 200, false);
	private static Airship airship2 = new CivilAirship(10, 10, 3000, 2000, 200, 100);

	@Before
	public void createUserAndAirshipDatabaseAndAddElements() throws InvalidRegisterException {

		airshipDatabase = new InMemoryAirshipDatabase();

		parser.registerCommand("GET", "/airships", new GetAllAirshipsCommand.Factory(
				airshipDatabase));
	}

	@Test
	public void shouldSuccessfullyGetAllAirships() throws Exception {

		// Act
		airshipDatabase.add(airship1, user1);
		airshipDatabase.add(airship2, user1);

		GetAllAirshipsCommand getAllAirships = (GetAllAirshipsCommand) parser.getCommand("GET",
				"/airships");

		getAllAirships.execute();

		// Assert
		Assert.assertEquals(getAllAirships.getResult(),

		"Flight ID: 1\nLatitude: 10.0 Longitude: 10.0 Altitude: 1000.0"
				+ "\nMaximum Altitude Permited: 2000.0 Minimum Altitude Permited: 200.0"
				+ "\nIs Outside The Given Corridor: false\nCarries Weapons: false\n"

				+ "Flight ID: 2\nLatitude: 10.0 Longitude: 10.0 Altitude: 3000.0"
				+ "\nMaximum Altitude Permited: 2000.0 Minimum Altitude Permited: 200.0"
				+ "\nIs Outside The Given Corridor: true\nNumber of Passengers: 100\n");
	}

	@Test
	public void shouldGetTheCorrectInformationIfTheDatabaseIsEmpty() throws Exception {

		// Act
		GetAllAirshipsCommand getAllAirships = (GetAllAirshipsCommand) parser.getCommand("GET",
				"/airships");

		getAllAirships.execute();

		// Assert
		Assert.assertEquals(getAllAirships.getResult(), "Airships Database is Empty");
	}
}