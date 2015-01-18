package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories;

import java.util.concurrent.Callable;

import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.domain.commands.patchcommands.PatchUserPasswordCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances are {@link CallablesFactory factories} that produce a command of type
 * {@link PatchUserPasswordCommands}. Commands are {@link Callable} instances. This class extends
 * {@link StringsToCommandsFactory}
 *
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link User user}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPasswordCommandsFactory extends StringsToCommandsFactory<String> {
	
	// INSTANCE FIELDS
	
	/**
	 * {@code requiredParametersNames} - The array of strings with the names of the parameters
	 * needed to produce the command.
	 */
	private String[]				requiredParametersNames;
	
	/**
	 * {@code usersDatabase} - The users database that contains the user.
	 */
	private final Database<User>	usersDatabase;
	
	/**
	 * {@code username} - The user's username.
	 */
	private String					username;
	
	/*
	 * {@code oldPassword} - The old password needed to confirm if its the rightfull user who's
	 * changing the password.
	 */
	private String					oldPassword;
	
	/*
	 * {@code newPawwsord} - The new user password that will be atributed to the user if the command
	 * is successful.
	 */
	private String					newPassword;
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new {@link PatchUserPasswordCommandsFactory} that produces commands of type
	 * {@link PatchUserPasswordCommands}.
	 * 
	 * @param usersDatabase
	 *            The database where to get the User from.
	 * @throws InvalidArgumentException
	 *             If the {@code usersDatabase} is null.
	 */
	public PatchUserPasswordCommandsFactory(Database<User> usersDatabase)
		throws InvalidArgumentException {
	
		super("Change An User Password");
		
		if (usersDatabase == null)
			throw new InvalidArgumentException(
				"Cannot instantiate post factory with null databases.");
		
		this.usersDatabase = usersDatabase;
		this.requiredParametersNames = new String[] {CLIStringsDictionary.USERNAME,
			CLIStringsDictionary.OLDPASSWORD, CLIStringsDictionary.NEWPASSWORD};
	}
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Method responsible for returning a command of the type {@link PatchUserPasswordCommand} after
	 * getting the necessary {@code required parameters} using the private auxiliar methods
	 * {@link #setUsername()}, {@link #setOldPassword()}, {@link #setNewPassword()}.
	 * 
	 * @return A {@link PatchUserPasswordCommand}
	 *
	 * @throws InvalidArgumentException
	 *             If the value received in the parameters map for a required parameter is invalid.
	 */
	@Override
	protected Callable<String> internalNewInstance() throws InvalidArgumentException {
	
		setUsername();
		setOldPassword();
		setNewPassword();
		
		return new PatchUserPasswordCommand(usersDatabase, username, oldPassword, newPassword);
	}
	
	/**
	 * Returns an array of strings with name of the parameters needed to produce the command: the
	 * name of the parameters that contain the user's username, old password and new password.
	 * 
	 * @return An array of strings with the name of the required parameters.
	 */
	@Override
	protected String[] getRequiredParameters() {
	
		return requiredParametersNames;
	}
	
	// PRIVATE AUXILIAR METHODS
	
	/**
	 * Method responsible to set the username field needed to {@code PatchUserPasswordCommands}
	 * command.
	 * 
	 * This method calls the {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the username.
	 */
	private void setUsername() {
	
		username = getParameterAsString(requiredParametersNames[0]);
	}
	
	/**
	 * Method responsible to set the OldPassword field needed to {@code PatchUserPasswordCommands}
	 * command.
	 * 
	 * This method calls the {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the OldPassword.
	 */
	private void setOldPassword() {
	
		oldPassword = getParameterAsString(requiredParametersNames[1]);
	}
	
	/**
	 * Method responsible to set the NewPassword field needed to {@code PatchUserPasswordCommands}
	 * command.
	 * 
	 * This method calls the {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the NewPassword.
	 */
	private void setNewPassword() {
	
		newPassword = getParameterAsString(requiredParametersNames[2]);
	}
}