package main.java.cli.parsingtools.commandfactories;

import java.util.concurrent.Callable;
import main.java.cli.HelpCommand;
import main.java.cli.parsingtools.CommandParser;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances are {@link CallablesFactory factories} that produce commands of type
 * {@link HelpCommand}. Commands are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class HelpCommandsFactory extends StringsToCommandsFactory<OptionsList> {

	// INSTANCE FIELD

	/**
	 * {@code cmdParser} - The command parser that contains a registry of the commands whose
	 * descriptions are to be returned by the command created by this factory.
	 */
	private CommandParser cmdParser;

	// CONSTRUCTOR
	/**
	 * Creates a new {@link HelpCommandsFactory} that produces commands of type {@link HelpFactory}.
	 * 
	 * @param cmdParser
	 *            The command parser with the registry of the commands whose descriptions are to be
	 *            returned by the {@link HelpCommand}s produced by this factory.
	 * @throws InvalidArgumentException
	 *             If {@code cmdParser==null}.
	 */
	public HelpCommandsFactory(CommandParser cmdParser) throws InvalidArgumentException {

		super("Returns the descriptions of known commands.");

		if (cmdParser == null)
			throw new InvalidArgumentException("Cannot instantiate factory with null parser.");
		this.cmdParser = cmdParser;
	}

	// IMPLEMENTED METHOD OF StringsToCommandsFactory ABSTRACT CLASS

	/**
	 * Returns a command of type {@link HelpCommand}.
	 * 
	 * @return A command of type {@link HelpCommand}.
	 */
	@Override
	public final Callable<OptionsList> internalNewInstance() {

		try {
			return new HelpCommand(cmdParser);

		} catch (InvalidArgumentException e) {// Never happens cause cmdParser is not null!
			return null;
		}
	}

	/**
	 * Returns an array of strings with the name of the parameters needed to produce
	 * {@link HelpCommand}s: {@code null} because factories of this type need no parameters from the
	 * string-commands.
	 * 
	 * @return {@code null}.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return null;
	}
}