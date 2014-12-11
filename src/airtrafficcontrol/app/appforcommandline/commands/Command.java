package airtrafficcontrol.app.appforcommandline.commands;

import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Command
{
	
	public void execute() throws CommandException;
}
