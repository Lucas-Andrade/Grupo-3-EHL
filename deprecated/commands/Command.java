package airtrafficcontrol.deprecated.appforcommandline.commands;


import airtrafficcontrol.app.appforcommandline.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import airtrafficcontrol.app.appforcommandline.exceptions.factoryexceptions.CommandFactoryException;
import airtrafficcontrol.app.appforcommandline.exceptions.factoryexceptions.WrongLoginPasswordException;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Command
{
	
	public void execute() throws CommandFactoryException,
			NoSuchElementInDatabaseException, WrongLoginPasswordException,
			InvalidArgumentException;

	public String getResult();
}
