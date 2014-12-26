package test.java.commandlineuserinterface.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.java.commandlineuserinterface.CommandParser;
import main.java.commandlineuserinterface.commands.Command;
import main.java.commandlineuserinterface.commands.CommandFactory;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetAirshipsByOwnerCommand;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetAirshipsWithMinimumPassengersCommand;
import main.java.commandlineuserinterface.model.airships.Airship;
import main.java.commandlineuserinterface.model.airships.CivilAirship;
import main.java.commandlineuserinterface.model.airships.InMemoryAirshipDatabase;
import main.java.commandlineuserinterface.model.users.User;
import org.junit.Before;
import org.junit.Test;

public class GetAirshipsWithMinimumPassengersCommandTest
{
	private CommandParser parser;
	private InMemoryAirshipDatabase database;
	private CommandFactory factory = new GetAirshipsByOwnerCommand.Factory( database );
	private User user;
	private Airship airship1;
	private String Airship1Info;


	@Before
	public void before() throws Exception
	{
		// Arrange
		parser = new CommandParser();
		database = new InMemoryAirshipDatabase();
		factory = new GetAirshipsWithMinimumPassengersCommand.Factory( database );
		user = new User( "anonymous G", "semPalavraPass", "G@g.com" );
		airship1 = new CivilAirship( 0, 0, 10, 20, 0, 100 );
		Airship1Info = "Flight ID: 1\n"
				+ "Latitude: 0.0 Longitude: 0.0 Altitude: 10.0\n"
				+ "Maximum Altitude Permited: 20.0 Minimum Altitude Permited: 0.0\n"
				+ "Is Outside The Given Corridor: false\n";

		//Act
		parser.registerCommand( "GET", "/airships/nbPassengers/{nbP}/bellow", factory );
		database.add( airship1, user );
	}
	
	@Test
	public void shouldCreateACommand()
	{
		//Assert
		assertTrue( factory.newInstance( null ) instanceof Command );
	}

	@Test
	public void shouldReturnThe_ThereAreNoAirShipsWithLessThanAGivenNumber_Message() throws Exception
	{
		//Act
		GetAirshipsWithMinimumPassengersCommand getAirship = ( GetAirshipsWithMinimumPassengersCommand )parser
				.getCommand( "GET", "/airships/nbPassengers/90/bellow" );
		getAirship.execute();
		
		// Assert
		assertEquals( getAirship.getResult(),
				"There are no Airships with less passengers then 90" );
	}
	
	@Test
	public void shouldReturnThe_AirShipsWithLessThanAGivenNumber_Message() throws Exception
	{
		//Act
		GetAirshipsWithMinimumPassengersCommand getAirship = ( GetAirshipsWithMinimumPassengersCommand )parser
				.getCommand( "GET", "/airships/nbPassengers/200/bellow" );
		getAirship.execute();
		// Assert
		assertEquals( getAirship.getResult(), Airship1Info );
	}
}
