package testsAppForConsole.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import appForConsole.CommandParser;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.commands.getAirshipsCommands.GetAirshipsByOwnerCommand;
import appForConsole.exceptions.commandParserExceptions.InvalidRegisterException;
import appForConsole.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import appForConsole.model.Param;
import appForConsole.model.airships.Airship;
import appForConsole.model.airships.CivilAirship;
import appForConsole.model.airships.InMemoryAirshipDatabase;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;

public class GetAirshipsByOwnerCommandTest
{
	private CommandParser parser;
	private InMemoryAirshipDatabase database;
	private InMemoryUserDatabase userDatabase;
	private CommandFactory factory;
	private User user;

	private String Airship1Info;

	@Before
	public void before() throws InvalidRegisterException
	{
		// Arrange
		parser = new CommandParser();
		database = new InMemoryAirshipDatabase();
		userDatabase = new InMemoryUserDatabase();
		user = new User( "anonymous G", "semPalavraPass", "G@g.com" );
		userDatabase.add( user, user );
		factory = new GetAirshipsByOwnerCommand.Factory( database, userDatabase );

		Airship1Info = "Flight ID: 1\n"
				+ "Latitude: 0.0 Longitude: 0.0 Altitude: 10.0\n"
				+ "Maximum Altitude Permited: 20.0 Minimum Altitude Permited: 0.0\n"
				+ "Is Outside The Given Corridor: false\n"
				+ "Number of Passengers: 100\n";

		// Act
		parser.registerCommand( "GET", "/airships/owner/{" + Param.OWNER + "}",
				factory );
	}

	@Test
	public void shouldCreateACommand()
	{
		// Assert
		assertTrue( factory.newInstance( null ) instanceof Command );
	}

	@Test
	public void shouldReturnThe_doNotCreartedAirships_Message()
			throws Exception
	{
		GetAirshipsByOwnerCommand getAirship = ( GetAirshipsByOwnerCommand )parser
				.getCommand( "GET", "/airships/owner/anonymous G" );
		try
		{
			getAirship.execute();
		}
		catch( NoSuchElementInDatabaseException e )
		{
			// Assert
			assertEquals( e.getMessage(),
					"No Airship was added by the given User" );
		}
	}

	@Test
	public void shouldReturnTheCreatedAirshipsMessage() throws Exception
	{
		// Arrange
		Airship airship1 = new CivilAirship( 0, 0, 10, 20, 0, 100 );

		// Act
		database.add( airship1, user );

		GetAirshipsByOwnerCommand getAirship = ( GetAirshipsByOwnerCommand )parser
				.getCommand( "GET", "/airships/owner/anonymous G" );
		getAirship.execute();

		// Assert
		assertEquals( getAirship.getResult(), Airship1Info );
	}
}