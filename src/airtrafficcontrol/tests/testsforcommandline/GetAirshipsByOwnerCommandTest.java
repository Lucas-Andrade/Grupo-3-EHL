package airtrafficcontrol.tests.testsforcommandline;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipByIdCommand;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipsByOwnerCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.WrongLoginPasswordException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.DuplicateParametersException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.UnknownCommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import airtrafficcontrol.app.appforcommandline.model.Database;
import airtrafficcontrol.app.appforcommandline.model.Element;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.CivilAirship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.airships.MilitaryAirship;
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
		assertTrue( getAirship.getResult().equals(
				"The User Daniel still did not create Airships" ) );
	}
	
	@Test
	public void shouldReturnTheCreatedAirshipsMessage() throws Exception
	{
		//Arrange
		User user = new User( "anonymous G", "semPalavraPass", "G@g.com" );
		Airship airship1 = new CivilAirship( 0, 0, 10, 20, 0, 100 );
//		Airship airship2 = new MilitaryAirship( 0, 0, 10, 0, 20, false );
		
		//Act
		database.add( airship1, user );
//		database.add( airship2, user );
		GetAirshipsByOwnerCommand getAirship = ( GetAirshipsByOwnerCommand )parser
				.getCommand( "GET", "/airships/owner/anonymous G" );
		getAirship.execute();
		
		System.out.println( getAirship.getResult() );
		// Assert
		assertTrue( getAirship.getResult().equals(
				"The User Daniel still did not create Airships" ) );
	}
}
