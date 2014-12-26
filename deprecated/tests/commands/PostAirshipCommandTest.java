package test.java.commandlineuserinterface.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.java.commandlineuserinterface.CommandParser;
import main.java.commandlineuserinterface.commands.Command;
import main.java.commandlineuserinterface.commands.CommandFactory;
import main.java.commandlineuserinterface.commands.postcommands.PostAirshipCommand;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.DuplicateParametersException;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.UnknownCommandException;
import main.java.commandlineuserinterface.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.commandlineuserinterface.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.commandlineuserinterface.exceptions.factoryexceptions.WrongLoginPasswordException;
import main.java.commandlineuserinterface.model.airships.InMemoryAirshipDatabase;
import main.java.commandlineuserinterface.model.users.InMemoryUserDatabase;
import main.java.commandlineuserinterface.model.users.User;
import org.junit.Before;
import org.junit.Test;

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
