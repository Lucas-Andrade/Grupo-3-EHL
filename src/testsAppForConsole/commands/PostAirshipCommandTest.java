package testsAppForConsole.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import appForConsole.CommandParser;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.commands.postCommands.PostAirshipCommand;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.exceptions.commandExecptions.InvalidParameterValueException;
import appForConsole.exceptions.commandExecptions.MissingRequiredParameterException;
import appForConsole.model.airships.InMemoryAirshipDatabase;
import appForConsole.model.users.InMemoryUserDatabase;

//POST /airships/Civil latitude=0&longitude=0&altitude=2&minAltitude=0&maxAltitude=10&loginName=MASTER&loginPassword=master
public class PostAirshipCommandTest {

	private CommandParser parser;
	private InMemoryAirshipDatabase airshipDatabase;
	private InMemoryUserDatabase userDatabase;
	private CommandFactory factory;

	@Before
	public void before() throws Exception {

		// Arrange
		parser = new CommandParser();
		airshipDatabase = new InMemoryAirshipDatabase();
		userDatabase = new InMemoryUserDatabase();
		factory = new PostAirshipCommand.Factory(userDatabase, airshipDatabase);

		// Act
		parser.registerCommand("POST", "/airships/{type}", factory);
	}

	@Test
	public void shouldCreateACommand() {

		// Assert
		assertTrue(factory.newInstance(null) instanceof Command);
	}

	@Test
	public void shouldCreateAnCivilAirship() throws Exception {

		// Arrange
		String civilParameters = "latitude=0" + "&longitude=0" + "&altitude=0" + "&minAltitude=0"
				+ "&maxAltitude=10" + "&nbPassengers=100" + "&loginName=MASTER"
				+ "&loginPassword=master";
		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/Civil", civilParameters);
		postAirship.execute();

		assertEquals(postAirship.getResult(), "1");
	}

	@Test
	public void shouldCreateAnMilitaryAirship() throws Exception {

		// Arrange
		String militaryParameters = "latitude=0" + "&longitude=0" + "&altitude=0"
				+ "&minAltitude=0" + "&maxAltitude=10" + "&hasArmour=yes" + "&loginName=MASTER"
				+ "&loginPassword=master";
		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/Military", militaryParameters);
		postAirship.execute();

		assertEquals(postAirship.getResult(), "2");
	}

	@Test
	public void shouldNotCreateAnAirshipIfTypeIsIncorrect() throws Exception {

		// Arrange
		String militaryParameters = "latitude=0" + "&longitude=0" + "&altitude=0"
				+ "&minAltitude=0" + "&maxAltitude=10" + "&hasArmour=yes" + "&loginName=MASTER"
				+ "&loginPassword=master";

		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/adrg", militaryParameters);
		try {
			postAirship.execute();
		} catch (CommandException e) {
			// Assert
			assertEquals(e.getMessage(),
					"Required parameter with name type has invalid value adrg.");
		}
	}

	@Test
	public void shouldNotCreateAnCivilAirshipIfCanNotConvertTheMinimumPassagersNumberToInt()
			throws Exception {

		// Arrange
		String civilParameters = "latitude=0" + "&longitude=0" + "&altitude=0" + "&minAltitude=0"
				+ "&maxAltitude=10" + "&nbPassengers=blablablablablablablablabla"
				+ "&loginName=MASTER" + "&loginPassword=master";
		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/Civil", civilParameters);

		try {
			postAirship.execute();
		} catch (InvalidParameterValueException e) {
			assertEquals(e.getMessage(),
					"Required parameter with name nbPassengers has invalid value blablablablablablablablabla.");
		}
	}

	@Test
	public void shouldNotCreateAnMilitaryAirshipIfHasArmourIsNot_yesOrNo() throws Exception {

		// Arrange
		String civilParameters = "latitude=0" + "&longitude=0" + "&altitude=0" + "&minAltitude=0"
				+ "&maxAltitude=10" + "&hasArmour=asdth" + "&loginName=MASTER"
				+ "&loginPassword=master";
		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/Military", civilParameters);

		try {
			postAirship.execute();
		} catch (InvalidParameterValueException e) {
			assertEquals(e.getMessage(),
					"Required parameter with name hasArmour has invalid value asdth.");
		}
	}

	@Test
	public void shouldNotCreateAnCivilAirshipIfPassagersNumberParameterIsMissing() throws Exception {

		// Arrange
		String civilParameters = "latitude=0" + "&longitude=0" + "&altitude=0" + "&minAltitude=0"
				+ "&maxAltitude=10" + "&loginName=MASTER" + "&loginPassword=master";
		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/Civil", civilParameters);

		try {
			postAirship.execute();
		} catch (MissingRequiredParameterException e) {
			assertEquals(e.getMessage(), "Required parameter with name nbPassengers missing.");
		}
	}

	@Test
	public void shouldNotCreateAnMilitaryAirshipIfHasArmourParameterIsMissing() throws Exception {

		// Arrange
		String civilParameters = "latitude=0" + "&longitude=0" + "&altitude=0" + "&minAltitude=0"
				+ "&maxAltitude=10" + "&loginName=MASTER" + "&loginPassword=master";
		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/Military", civilParameters);

		try {
			postAirship.execute();
		} catch (MissingRequiredParameterException e) {
			assertEquals(e.getMessage(), "Required parameter with name hasArmour missing.");
		}
	}

	@Test
	public void shouldNotCreateAnAirshipIfTheThePlaceHolderParameterDoNotExist() throws Exception {

		// Arrange
		String civilParameters = "latitude=0" + "&longitude=0" + "&altitude=0" + "&minAltitude=0"
				+ "&maxAltitude=10" + "&loginName=MASTER" + "&loginPassword=master";
		// Act
		PostAirshipCommand postAirship = (PostAirshipCommand) parser.getCommand("POST",
				"/airships/OVNI", civilParameters);

		try {
			postAirship.execute();
		} catch (InvalidParameterValueException e) {
			assertEquals(e.getMessage(),
					"Required parameter with name type has invalid value OVNI.");
		}
	}
}