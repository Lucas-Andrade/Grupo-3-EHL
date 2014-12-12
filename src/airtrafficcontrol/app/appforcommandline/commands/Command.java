package airtrafficcontrol.app.appforcommandline.commands;


import airtrafficcontrol.app.appforcommandline.commands.postcommands.WrongLoginPasswordException;
import airtrafficcontrol.app.appforcommandline.exceptions.commands.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.database.NoSuchElementInDatabaseException;


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
