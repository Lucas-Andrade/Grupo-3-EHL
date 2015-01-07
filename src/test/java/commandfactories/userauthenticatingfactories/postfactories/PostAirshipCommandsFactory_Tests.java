package test.java.commandfactories.userauthenticatingfactories.postfactories;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import main.java.cli.CommandLineStringsDictionary;
import main.java.cli.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.cli.commandfactories.userauthenticatingfactories.postfactories.PostAirshipCommandsFactory;
import main.java.cli.commands.postcommands.PostCivilAirshipCommand;
import main.java.cli.commands.postcommands.PostMilitaryAirshipCommand;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.cli.exceptions.commandparserexceptions.UnknownCommandException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
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
 * {@link PostAirshipCommandsFactory}
 * {@link UserAuthenticatingFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommandsFactory_Tests {

	private static InMemoryUsersDatabase usersDatabase;
	private static InMemoryAirshipsDatabase airshipsDatabase;
	private Map<String, String> parameters;
	private static User user;

	// Before Class

	@BeforeClass
	public static void createTheCommandParserAndRegisterTheCommands()
			throws InvalidRegisterException, InvalidArgumentException {

		usersDatabase = new InMemoryUsersDatabase("Users Database");
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");
		
		user = new User("Daniel", "pass", "@d");
		
		usersDatabase.add(user, user);
	}
	
	@Before
	public void createParametersMap() {
		
		parameters = new HashMap<>();
		
		parameters.put(CommandLineStringsDictionary.LOGINNAME, "Daniel");
		parameters.put(CommandLineStringsDictionary.LOGINPASSWORD, "pass");
		
		parameters.put(CommandLineStringsDictionary.LATITUDE, "0");
		parameters.put(CommandLineStringsDictionary.LONGITUDE, "0");
		parameters.put(CommandLineStringsDictionary.ALTITUDE, "0");
		parameters.put(CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE, "10");
		parameters.put(CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE, "0");
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldSuccessfullyCreateTheCorrectCommands() throws WrongLoginPasswordException,
			MissingRequiredParameterException, InvalidCommandSyntaxException,
			UnknownCommandException, NoSuchElementInDatabaseException,
			InvalidParameterValueException, InvalidArgumentException, InternalErrorException,
			Exception {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Civil");
		parameters.put(CommandLineStringsDictionary.NUMBEROFPASSENGERS, "20");
		
		Callable<?> postCivilAirshipCommand = (new PostAirshipCommandsFactory(usersDatabase,
				airshipsDatabase)).newInstance(parameters);

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Military");
		parameters.put(CommandLineStringsDictionary.HASARMOUR, "yes");
		
		Callable<?> postMilitaryAirshipCommand = (new PostAirshipCommandsFactory(usersDatabase,
				airshipsDatabase)).newInstance(parameters);

		Assert.assertTrue(postCivilAirshipCommand instanceof PostCivilAirshipCommand);
		Assert.assertTrue(postMilitaryAirshipCommand instanceof PostMilitaryAirshipCommand);
	}

	// Test Exceptions
	
	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPostAirshipCommandGivingANullUserDatabase()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Civil");
		parameters.put(CommandLineStringsDictionary.LOGINNAME, "Daniel");
		
		new PostAirshipCommandsFactory(null, airshipsDatabase).newInstance(parameters);
	}
	
	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAPostAirshipCommandGivingANullAirshipDatabase()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Civil");
		parameters.put(CommandLineStringsDictionary.LOGINNAME, "Daniel");
		
		new PostAirshipCommandsFactory(usersDatabase, null).newInstance(parameters);
	}
	
	@Test (expected = NoSuchElementInDatabaseException.class)
	public void shouldThrowNoSuchElementInDatabaseExceptionWhenTryingToCreateAnAirshipCommandGivingAnNonExistingUser()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Civil");
		parameters.put(CommandLineStringsDictionary.LOGINNAME, "Pedro");
		
		new PostAirshipCommandsFactory(usersDatabase, airshipsDatabase).newInstance(parameters);
	}
	
	@Test (expected = WrongLoginPasswordException.class)
	public void shouldThrowWrongLoginPasswordExceptionWhenTryingToCreateAnAirshipCommandGivingTheWrongUserPassword()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Civil");
		parameters.put(CommandLineStringsDictionary.LOGINPASSWORD, "ola");
		
		new PostAirshipCommandsFactory(usersDatabase, airshipsDatabase).newInstance(parameters);
	}

	@Test (expected = MissingRequiredParameterException.class)
	public void shouldThrowMissingRequiredParameterExceptionWhenTryingToCreateAPostCivilAirshipCommandWithoutAllTheParameters()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Civil");
		
		new PostAirshipCommandsFactory(usersDatabase, airshipsDatabase).newInstance(parameters);
	}
	
	@Test (expected = MissingRequiredParameterException.class)
	public void shouldThrowMissingRequiredParameterExceptionWhenTryingToCreateAPostMilitaryAirshipCommandWithoutAllTheParameters()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Military");
		
		new PostAirshipCommandsFactory(usersDatabase, airshipsDatabase).newInstance(parameters);
	}
	
	@Test (expected = InvalidParameterValueException.class)
	public void shouldThrowInvalidParameterValueExceptionWhenTryingToCreateAPostAnAirshipCommandWithAnInvalidSpecificParameters()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "Civil");
		parameters.put(CommandLineStringsDictionary.NUMBEROFPASSENGERS, "ola");
		
		new PostAirshipCommandsFactory(usersDatabase, airshipsDatabase).newInstance(parameters);
	}
	
	@Test (expected = InvalidParameterValueException.class)
	public void shouldThrowInvalidParameterValueExceptionWhenTryingToCreateAPostAnAirshipCommandWithAnInvalidTypeParameters()
			throws InvalidArgumentException, NoSuchElementInDatabaseException, MissingRequiredParameterException, InvalidParameterValueException, WrongLoginPasswordException, InternalErrorException {

		parameters.put(CommandLineStringsDictionary.AIRSHIP_TYPE, "ola");
		parameters.put(CommandLineStringsDictionary.NUMBEROFPASSENGERS, "20");
		
		new PostAirshipCommandsFactory(usersDatabase, airshipsDatabase).newInstance(parameters);
	}
}