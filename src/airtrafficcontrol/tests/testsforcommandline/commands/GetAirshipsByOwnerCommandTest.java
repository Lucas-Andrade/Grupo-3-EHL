package airtrafficcontrol.tests.testsforcommandline.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.*;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipsByOwnerCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.model.airships.*;
import airtrafficcontrol.app.appforcommandline.model.users.User;

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
