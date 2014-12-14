package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetAirshipsWithMinimumPassengersCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.MissingRequiredParameterException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.WrongLoginPasswordException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.DuplicateParametersException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.UnknownCommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
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



	@Before
	public void before() throws Exception
	{
		// Arrange
		parser = new CommandParser();
		airshipDatabase = new InMemoryAirshipDatabase();
		userDatabase = new InMemoryUserDatabase();
		factory = new PostAirshipCommand.Factory( userDatabase, airshipDatabase );

		user = new User( "anonymous G", "semPalavraPass", "G@g.com" );

		// Act
		parser.registerCommand( "POST", "/airships/{type}", factory );
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
		// Arrange
		String civilParameters = "type=Civil"
				+ "&latitude=0"
				+ "&longitude=0"
				+ "&altitude=0"
				+ "&minAltitude=0"
				+ "&maxAltitude=10"
				+ "&nbPassengers=100"
				+ "&loginName=Daniel"
				+ "&loginPassword=DanyGs";
		// Act
		PostAirshipCommand postAirship = ( PostAirshipCommand )parser
				.getCommand( "POST", "/airships/Civil", civilParameters );
		postAirship.execute();

		assertEquals( postAirship.getResult(), "1" );
	}
	
	@Test
	public void shouldCreateAnMilitaryAirship() throws Exception
	{
		// Arrange
		String militaryParameters = "type=Military"
				+ "&latitude=0"
				+ "&longitude=0"
				+ "&altitude=0"
				+ "&minAltitude=0"
				+ "&maxAltitude=10"
				+ "&hasArmour=asd"
				+ "&loginName=Daniel"
				+ "&loginPassword=DanyGs";
		// Act
		PostAirshipCommand postAirship = ( PostAirshipCommand )parser
				.getCommand( "POST", "/airships/Military", militaryParameters );
		postAirship.execute();

		assertEquals( postAirship.getResult(), "2" );
	}
	
	@Test
	public void shouldNotCreateAnAirshipIfOneParameterIsMissing() throws Exception
	{
		// Arrange
		String militaryParameters = "type=Military"
				
				+ "&longitude=0"
				+ "&altitude=0"
				+ "&minAltitude=0"
				+ "&maxAltitude=10"
				+ "&hasArmour=asd"
				+ "&loginName=Daniel"
				+ "&loginPassword=DanyGs";
		// Act
		PostAirshipCommand postAirship = ( PostAirshipCommand )parser
				.getCommand( "POST", "/airships/Military", militaryParameters );
		try
		{
			postAirship.execute();
			assert false;
		}
		catch( CommandException e )
		{
			assert true;
		}
	}
	
	@Test
	public void shouldNotCreateAnAirshipIfTheLoginPasswordIsWrong() throws Exception
	{
		// Arrange
		String militaryParameters = "type=Military"
				+ "&latitude=0"
				+ "&longitude=0"
				+ "&altitude=0"
				+ "&minAltitude=0"
				+ "&maxAltitude=10"
				+ "&hasArmour=asd"
				+ "&loginName=Daniel"
				+ "&loginPassword=E O SPORTING É O NOSSO GRANDE AMOR";
		// Act
		PostAirshipCommand postAirship = ( PostAirshipCommand )parser
				.getCommand( "POST", "/airships/Military", militaryParameters );
		try
		{
			postAirship.execute();
			assert false;
		}
		catch( WrongLoginPasswordException  e )
		{
			assert true;
		}
	}
	
	
	@Test
	public void shouldNotCreateAnAirshipIfItHasGivenAWrongParameter() throws Exception
	{
		// Arrange
		String militaryParameters = "type=Military"
				+ "&latitude=0"
				+ "&longitude=0"
				+ "&altitude=0"
				+ "&minAltitude=0"
				+ "&maxAltitude=10"
				+ "&hasArmour=asd"
				+ "&parametroFalso=blablabla"
				+ "&loginName=Daniel"
				+ "&loginPassword=DanyGs";
		// Act
		PostAirshipCommand postAirship = ( PostAirshipCommand )parser
				.getCommand( "POST", "/airships/Military", militaryParameters );
		try
		{
			postAirship.execute();
			assert false;
		}
		catch( NoSuchElementInDatabaseException e )
		{
			assert true;
		}
	}

	
	@Test(expected=MissingRequiredParameterException.class)
	public void shouldNotCreateAnAirshipIfCanNotConvertTheMinimumPassagersNumberToInt() throws UnknownCommandException, DuplicateParametersException, InvalidCommandParametersSyntaxException, NoSuchElementInDatabaseException, WrongLoginPasswordException, CommandException
	{
		// Arrange
		String civilParameters = "type=Civil"
				+ "&latitude=0"
				+ "&longitude=0"
				+ "&altitude=0"
				+ "&minAltitude=0"
				+ "&maxAltitude=10"
				+ "&nbPassengers=100"
				+ "&loginName=Daniel"
				+ "&loginPassword=DanyGs";
		// Act
		PostAirshipCommand postAirship = ( PostAirshipCommand )parser
				.getCommand( "POST", "/airships/Civil", civilParameters );

			postAirship.execute();
	}
		
}
