package airtrafficcontrol.app.appforcommandline.commands;


import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.WrongLoginPasswordException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Command
{
	
	public void execute() throws CommandException,
			NoSuchElementInDatabaseException, WrongLoginPasswordException;
}
