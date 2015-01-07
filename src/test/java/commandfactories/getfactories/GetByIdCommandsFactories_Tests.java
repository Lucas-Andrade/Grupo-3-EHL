package test.java.commandfactories.getfactories;

import java.util.concurrent.Callable;

import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.commandfactories.getfactories.getbyidfactories.GetAirshipByFlightIdCommandsFactory;
import main.java.cli.commandfactories.getfactories.getbyidfactories.GetElementByIdentificationCommandsFactory;
import main.java.cli.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import main.java.cli.commands.getcommands.GetElementFromADatabaseByIdCommand;
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

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetElementByIdentificationCommandsFactory}
 * {@link GetUserByUsernameCommandsFactory}
 * {@link GetAirshipByFlightIdCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetByIdCommandsFactories_Tests {

	private static CommandParser cmdparser = new CommandParser();
	private static InMemoryUsersDatabase userDatabase;
	private static InMemoryAirshipsDatabase airshipsDatabase;

	// Before Class

	@BeforeClass
	public static void createTheCommandParserAndRegisterTheCommands()
			throws InvalidRegisterException, InvalidArgumentException {

		cmdparser = new CommandParser();

		userDatabase = new InMemoryUsersDatabase("Users Database");
		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");

		cmdparser.registerCommand("GET", "/airships/{flightId}", new GetAirshipByFlightIdCommandsFactory(
				airshipsDatabase));

		cmdparser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommandsFactory(
				userDatabase));
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldSuccessfullyCreateTheCorrectCommands() throws WrongLoginPasswordException,
			MissingRequiredParameterException, InvalidCommandSyntaxException,
			UnknownCommandException, NoSuchElementInDatabaseException,
			InvalidParameterValueException, InvalidArgumentException, InternalErrorException,
			Exception {

		Callable<?> getUserByUsernameCommand = (new Parser(cmdparser, "GET", "/users/{username}")).getCommand();

		Callable<?> getAirshipByFlightIdCommand = (new Parser(cmdparser, "GET", "/airships/{flightId}"))
				.getCommand();

		Assert.assertTrue(getUserByUsernameCommand instanceof GetElementFromADatabaseByIdCommand);
		Assert.assertTrue(getAirshipByFlightIdCommand instanceof GetElementFromADatabaseByIdCommand);
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipByFlightIdCommandsFactoryGivenANullDatabase()
			throws InvalidArgumentException {

		new GetAirshipByFlightIdCommandsFactory(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetUserByUsernameCommandsFactoryGivenANullDatabase()
			throws InvalidArgumentException {

		new GetUserByUsernameCommandsFactory(null);
	}
}