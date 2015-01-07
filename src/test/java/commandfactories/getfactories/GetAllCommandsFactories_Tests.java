package test.java.commandfactories.getfactories;

import java.util.concurrent.Callable;

import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import main.java.cli.commands.getcommands.GetAllElementsInADatabaseCommand;
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
 * {@link GetAllElementsInADatabaseCommandsFactory}
 * {@link GetAllUsersInADatabaseCommandsFactory}
 * {@link GetAllAirshipsInADatabaseCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllCommandsFactories_Tests {

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

		cmdparser.registerCommand("GET", "/airships", new GetAllAirshipsInADatabaseCommandsFactory(
				airshipsDatabase));

		cmdparser.registerCommand("GET", "/users", new GetAllUsersInADatabaseCommandsFactory(
				userDatabase));
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldSuccessfullyCreateTheCorrectCommands() throws WrongLoginPasswordException,
			MissingRequiredParameterException, InvalidCommandSyntaxException,
			UnknownCommandException, NoSuchElementInDatabaseException,
			InvalidParameterValueException, InvalidArgumentException, InternalErrorException,
			Exception {

		Callable<?> getAllUsersCommand = (new Parser(cmdparser, "GET", "/users")).getCommand();

		Callable<?> getAllAirshipsCommand = (new Parser(cmdparser, "GET", "/airships"))
				.getCommand();

		Assert.assertTrue(getAllUsersCommand instanceof GetAllElementsInADatabaseCommand);
		Assert.assertTrue(getAllAirshipsCommand instanceof GetAllElementsInADatabaseCommand);
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllAirshipsInADatabaseCommandsFactoryGivenANullDatabase()
			throws InvalidArgumentException {

		new GetAllAirshipsInADatabaseCommandsFactory(null);
	}

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllUsersInADatabaseCommandsFactoryGivenANullDatabase()
			throws InvalidArgumentException {

		new GetAllUsersInADatabaseCommandsFactory(null);
	}
}
