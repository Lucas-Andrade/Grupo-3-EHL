package test.java.commandfactories.getfactories;

import java.util.concurrent.Callable;

import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.commandfactories.getfactories.GetAirshipsWithLessPassengersThanCommandsFactory;
import main.java.cli.commands.getcommands.GetAirshipsWithLessPassengersThanCommand;
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
 * {@link GetAirshipsWithLessPassengersThanCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithLessPassengersThanCommandsFactory_Tests {

	private static CommandParser cmdparser = new CommandParser();
	private static InMemoryAirshipsDatabase airshipsDatabase;

	// Before Class

	@BeforeClass
	public static void createTheCommandParserAndRegisterTheCommands()
			throws InvalidRegisterException, InvalidArgumentException {

		cmdparser = new CommandParser();

		airshipsDatabase = new InMemoryAirshipsDatabase("Airships Database");

		cmdparser.registerCommand("GET", "/airships/nbPassengers/{nbP}/bellow",
				new GetAirshipsWithLessPassengersThanCommandsFactory(airshipsDatabase));
	}

	// Test Normal Dinamic And Prerequisites

	@Test
	public void shouldSuccessfullyCreateTheCorrectCommands() throws WrongLoginPasswordException,
			MissingRequiredParameterException, InvalidCommandSyntaxException,
			UnknownCommandException, NoSuchElementInDatabaseException,
			InvalidParameterValueException, InvalidArgumentException, InternalErrorException,
			Exception {

		Callable<?> getAirshipsWithLessPassengersThanCommand = (new Parser(cmdparser, "GET",
				"/airships/nbPassengers/20/bellow")).getCommand();

		Assert.assertTrue(getAirshipsWithLessPassengersThanCommand instanceof GetAirshipsWithLessPassengersThanCommand);
	}

	// Test Exceptions

	@Test (expected = InvalidArgumentException.class)
	public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateAGetAirshipsWithLessPassengersThanCommandsFactoryGivenANullDatabase()
			throws InvalidArgumentException {

		new GetAirshipsWithLessPassengersThanCommandsFactory(null);
	}
}