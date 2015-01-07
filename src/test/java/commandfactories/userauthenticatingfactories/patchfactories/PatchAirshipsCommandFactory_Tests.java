package test.java.commandfactories.userauthenticatingfactories.patchfactories;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import main.java.cli.CommandLineStringsDictionary;
import main.java.cli.commandfactories.userauthenticatingfactories.patchfactories.PatchAirshipsCommandFactory;
import main.java.cli.commands.patchcommands.PatchCivilAirshipCommand;
import main.java.cli.commands.patchcommands.PatchMilitaryAirshipCommand;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.InMemoryUsersDatabase;
import main.java.cli.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PatchAirshipsCommandFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipsCommandFactory_Tests {

	private static InMemoryUsersDatabase usersDatabase;
	private static InMemoryAirshipsDatabase airshipsDatabase;
	private Map<String, String> parameters;
	private Map<String, String> onlyRequiredParameters;
	private static User user;
	private static Airship airship1, airship2;

	// Before Class

	@BeforeClass
	public static void createUsersAndAirshipsAndTheirDatabases() throws InvalidRegisterException,
			InvalidArgumentException {

		usersDatabase = new InMemoryUsersDatabase("Users Database");
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");

		user = new User("Daniel", "pass", "@d");
		airship1 = new CivilAirship(10, 0, 20, 100, 50, 20);
		airship2 = new MilitaryAirship(10, 0, 20, 100, 50, true);

		usersDatabase.add(user, user);

		airshipsDatabase.add(airship1, user);
		airshipsDatabase.add(airship2, user);
	}

	// Before
	
	@Before
	public void createParametersMap() {

		parameters = new HashMap<String, String>();

		parameters.put(CommandLineStringsDictionary.LOGINNAME, "Daniel");
		parameters.put(CommandLineStringsDictionary.LOGINPASSWORD, "pass");

		parameters.put(CommandLineStringsDictionary.LATITUDE, "0");
		parameters.put(CommandLineStringsDictionary.LONGITUDE, "0");
		parameters.put(CommandLineStringsDictionary.ALTITUDE, "0");
		parameters.put(CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE, "10");
		parameters.put(CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE, "0");
		
		onlyRequiredParameters = new HashMap<String, String>();
		
		onlyRequiredParameters.put(CommandLineStringsDictionary.LOGINNAME, "Daniel");
		onlyRequiredParameters.put(CommandLineStringsDictionary.LOGINPASSWORD, "pass");
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldSuccessfullyCreateTheCorrectCommandsGivenNoneOfTheOptionalParameters()
			throws NoSuchElementInDatabaseException, MissingRequiredParameterException,
			InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException,
			InvalidArgumentException {
		
		onlyRequiredParameters.put(CommandLineStringsDictionary.FLIGHTID, "id1");

		Callable<?> patchCivilAirshipCommand = (new PatchAirshipsCommandFactory(usersDatabase,
				airshipsDatabase)).newInstance(onlyRequiredParameters);

		onlyRequiredParameters.put(CommandLineStringsDictionary.FLIGHTID, "id2");

		Callable<?> patchMilitaryAirshipCommand = (new PatchAirshipsCommandFactory(usersDatabase,
				airshipsDatabase)).newInstance(onlyRequiredParameters);

		Assert.assertTrue(patchCivilAirshipCommand instanceof PatchCivilAirshipCommand);
		Assert.assertTrue(patchMilitaryAirshipCommand instanceof PatchMilitaryAirshipCommand);
	}
	
	@Test
	public void shouldSuccessfullyCreateTheCorrectCommandsGivingAllOptionalParameters()
			throws NoSuchElementInDatabaseException, MissingRequiredParameterException,
			InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException,
			InvalidArgumentException {

		parameters.put(CommandLineStringsDictionary.FLIGHTID, "id1");

		Callable<?> patchCivilAirshipCommand = (new PatchAirshipsCommandFactory(usersDatabase,
				airshipsDatabase)).newInstance(parameters);

		parameters.put(CommandLineStringsDictionary.FLIGHTID, "id2");

		Callable<?> patchMilitaryAirshipCommand = (new PatchAirshipsCommandFactory(usersDatabase,
				airshipsDatabase)).newInstance(parameters);

		Assert.assertTrue(patchCivilAirshipCommand instanceof PatchCivilAirshipCommand);
		Assert.assertTrue(patchMilitaryAirshipCommand instanceof PatchMilitaryAirshipCommand);
	}

	// Test Exceptions

	@Test (expected = NoSuchElementInDatabaseException.class)
	public void shouldThrowNoSuchElementInDatabaseExceptionWhenTryingToCreateAPatchAirshipCommandGivingTheIdentificationOfAnNonExistingID()
			throws InvalidArgumentException, NoSuchElementInDatabaseException,
			MissingRequiredParameterException, InvalidParameterValueException,
			WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.FLIGHTID, "id20");
		
		new PatchAirshipsCommandFactory(usersDatabase, airshipsDatabase).newInstance(parameters);
	}
}