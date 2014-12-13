package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipsWithMinimumPassengersCommand;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.CivilAirship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class PostAirshipCommandTest
{
	private CommandParser parser;
	private InMemoryAirshipDatabase airshipDatabase;
	private InMemoryUserDatabase userDatabase;
	private CommandFactory factory;

	private User user;
	private Airship airship1;
	private String Airship1Info;


	@Before
	public void before() throws Exception
	{
		// Arrange
		parser = new CommandParser();
		airshipDatabase = new InMemoryAirshipDatabase();
		userDatabase = new InMemoryUserDatabase();
		factory = new PostAirshipCommand.Factory( userDatabase, airshipDatabase );

		user = new User( "anonymous G", "semPalavraPass", "G@g.com" );
		airship1 = new CivilAirship( 0, 0, 10, 20, 0, 100 );
		Airship1Info = "Flight ID: 1\n"
				+ "Latitude: 0.0 Longitude: 0.0 Altitude: 10.0\n"
				+ "Maximum Altitude Permited: 20.0 Minimum Altitude Permited: 0.0\n"
				+ "Is Outside The Given Corridor: false\n";

		// Act
		parser.registerCommand( "POST", "POST /airships/{type} ", factory );
		airshipDatabase.add( airship1, user );
	}

	@Test
	public void shouldCreateACommand()
	{
		// Assert
		assertTrue( factory.newInstance( null ) instanceof Command );
	}

	@Test
	public void shouldCreateAnCivilAirship() throws Exception
	{
		// TYPE = "type";
		// private static final String LATITUDE = "latitude";
		// private static final String LONGITUDE = "longitude";
		// private static final String ALTITUDE = "altitude";
		// private static final String MINALTITUDE = "minAltitude";
		// private static final String MAXALTITUDE = "maxAltitude";
		// private static final String HASARMOUR = "hasArmour";
		// private static final String PASSENGERSNUMBER = "nbPassengers";
		// private static final String LOGINNAME = "loginName";
		//
		// Arrange
		String civilParameters = 
				"latitude=0&longitude=0&altitude=0&minAltitude=0&maxAltitude=10&nbPassengers=100&loginName=Daniel";
		// Act
		PostAirshipCommand postAirship = ( PostAirshipCommand )parser
				.getCommand( "POST", "POST /airships/{Civil}", civilParameters );
		postAirship.execute();

		 assertEquals( postAirship.getResult(),
		 "1" );
	}

//	@Test
//	public void shouldReturnThe_AirShipsWithLessThanAGivenNumber_Message()
//			throws Exception
//	{
//		// Act
//		GetAirshipsWithMinimumPassengersCommand getAirship = ( GetAirshipsWithMinimumPassengersCommand )parser
//				.getCommand( "GET", "/airships/nbPassengers/200/bellow" );
//		getAirship.execute();
//		// Assert
//		assertEquals( getAirship.getResult(), Airship1Info );
//	}
}
