package appForConsole.commands;

import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.exceptions.commandExecptions.WrongLoginPasswordException;
import appForConsole.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Command {

	/**
	 * Execute the {@link Command}
	 * 
	 * @throws CommandException
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 */
	public void execute() throws CommandException, NoSuchElementInDatabaseException,
			WrongLoginPasswordException;

	public String getResult();
}