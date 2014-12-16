package main.java.commands;

import main.java.exceptions.commandExecptions.CommandException;
import main.java.exceptions.commandExecptions.WrongLoginPasswordException;
import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;

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