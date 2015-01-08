package test.java.cli.parsingtools.commandfactories.getfactories;

import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllAirshipsInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import main.java.utils.exceptions.parsingexceptions.InvalidParameterValueException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.WrongLoginPasswordException;
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