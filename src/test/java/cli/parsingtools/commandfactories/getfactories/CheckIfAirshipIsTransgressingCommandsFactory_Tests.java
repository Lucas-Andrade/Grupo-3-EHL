package test.java.cli.parsingtools.commandfactories.getfactories;

import java.util.concurrent.Callable;
import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import main.java.domain.commands.getcommands.CheckIfAirshipIsTransgressingCommand;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
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
 * {@link CheckIfAirshipIsTransgressingCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommandsFactory_Tests {

	private static CommandParser cmdparser = new CommandParser();
	private static InMemoryAirshipsDatabase airshipsDatabase;

	// Before Class

	@BeforeClass
	public static void createTheCommandParserAndRegisterTheCommands()
			throws InvalidRegisterException, InvalidArgumentException {

		cmdparser = new CommandParser();

		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");

		cmdparser.registerCommand("GET", "/airships/reports/{flightId}",
				new CheckIfAirshipIsTransgressingCommandsFactory(airshipsDatabase));
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldSuccessfullyCreateTheCorrectCommand() throws WrongLoginPasswordException,
			MissingRequiredParameterException, InvalidCommandSyntaxException,
			UnknownCommandException, NoSuchElementInDatabaseException,
			InvalidParameterValueException, InvalidArgumentException, InternalErrorException,
			Exception {

		Callable<?> checkIfAirshipIsTransgressingCommand = (new Parser(cmdparser, "GET",
				"/airships/reports/id20")).getCommand();

		Assert.assertTrue(checkIfAirshipIsTransgressingCommand instanceof CheckIfAirshipIsTransgressingCommand);
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateACheckIfAirshipIsTransgressingCommandsFactoryGivenANullDatabase()
			throws InvalidArgumentException {

		new CheckIfAirshipIsTransgressingCommandsFactory(null);
	}
}