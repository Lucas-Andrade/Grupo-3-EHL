package test.java.commandfactories.getfactories;

import java.util.concurrent.Callable;

import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.commandfactories.getfactories.GetAllTransgressorAirshipsCommandsFactory;
import main.java.cli.commands.getcommands.GetAllTransgressorAirshipsCommand;
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

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetAllTransgressingAirshipsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressingAirshipsFactory_Tests {

	private static CommandParser cmdparser = new CommandParser();
	private static InMemoryAirshipsDatabase airshipsDatabase;

	// Before Class

	@BeforeClass
	public static void createTheCommandParserAndRegisterTheCommands()
			throws InvalidRegisterException, InvalidArgumentException {

		cmdparser = new CommandParser();

		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");

		cmdparser.registerCommand("GET", "/airships/reports", new GetAllTransgressorAirshipsCommandsFactory(
				airshipsDatabase));
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldSuccessfullyCreateTheCorrectCommands() throws WrongLoginPasswordException,
			MissingRequiredParameterException, InvalidCommandSyntaxException,
			UnknownCommandException, NoSuchElementInDatabaseException,
			InvalidParameterValueException, InvalidArgumentException, InternalErrorException,
			Exception {

		Callable<?> getAllTransgressinAirshipsCommand = (new Parser(cmdparser, "GET", "/airships/reports"))
				.getCommand();

		Assert.assertTrue(getAllTransgressinAirshipsCommand instanceof GetAllTransgressorAirshipsCommand);
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAllTransgressorAirshipsCommandsFactoryGivenANullDatabase()
			throws InvalidArgumentException {

		new GetAllTransgressorAirshipsCommandsFactory(null);
	}
}