package test.java.commandlineuserinterface.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.java.commandlineuserinterface.CommandParser;
import main.java.commandlineuserinterface.commands.Command;
import main.java.commandlineuserinterface.commands.CommandFactory;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetAirshipsByOwnerCommand;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.commandlineuserinterface.model.airships.Airship;
import main.java.commandlineuserinterface.model.airships.CivilAirship;
import main.java.commandlineuserinterface.model.airships.InMemoryAirshipDatabase;
import main.java.commandlineuserinterface.model.users.User;
import org.junit.Before;
import org.junit.Test;

public class GetAirshipsByOwnerCommandTest
{
	private CommandParser parser;
	private InMemoryAirshipDatabase database;
	private CommandFactory factory = new GetAirshipsByOwnerCommand.Factory( database );


	@Before
	public void before() throws InvalidRegisterException
	{
		// Arrange
		parser = new CommandParser();
		database = new InMemoryAirshipDatabase();
		factory = new GetAirshipsByOwnerCommand.Factory( database );

		//Act
		parser.registerCommand( "GET", "/airships/owner/{username}", factory );
	}
	
	@Test
	public void shouldCreateACommand()
	{
		//Assert
		assertTrue( factory.newInstance( null ) instanceof Command );
	}

	@Test
	public void shouldReturnThe_doNotCreartedAirships_Message() throws Exception
	{
		GetAirshipsByOwnerCommand getAirship = ( GetAirshipsByOwnerCommand )parser
				.getCommand( "GET", "/airships/owner/Daniel" );

		getAirship.execute();
		// Assert
		assertEquals( getAirship.getResult(),
				"The User Daniel still did not create Airships" );
	}
	
	@Test
	public void shouldReturnTheCreatedAirshipsMessage() throws Exception
	{
		//Arrange
		User user = new User( "anonymous G", "semPalavraPass", "G@g.com" );
		Airship airship1 = new CivilAirship( 0, 0, 10, 20, 0, 100 );
		
		//Act
		database.add( airship1, user );
		GetAirshipsByOwnerCommand getAirship = ( GetAirshipsByOwnerCommand )parser
				.getCommand( "GET", "/airships/owner/anonymous G" );
		getAirship.execute();
		
		// Assert
		assertEquals( getAirship.getResult(),
				"Flight ID: 1\n"
				+ "Latitude: 0.0 Longitude: 0.0 Altitude: 10.0\n"
				+ "Maximum Altitude Permited: 20.0 Minimum Altitude Permited: 0.0\n"
				+ "Is Outside The Given Corridor: false\n" );
	}
}
