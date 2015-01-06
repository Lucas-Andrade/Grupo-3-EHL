package main.java.cli.commandfactories.userauthenticatingfactories.patchfactories;

import java.util.concurrent.Callable;

import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.cli.commands.patchcommands.PatchUserPasswordCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.users.User;
import main.java.cli.CommandLineStringsDictionary;


	/**
	 	* Class whose instances are {@link CallablesFactory factories} that produce
	 *  a command of type  {@link PatchUserPasswordCommands}. Commands are
	 * {@link Callable} instances. This class extends {@link StringsToCommandsFactory}
	 *	 
	 *	 @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */

		// public class PatchUserPasswordCommandsFactory extends StringsToCommandsFactory<Optional<String>> {
	public class PatchUserPasswordCommandsFactory extends UserAuthenticatingFactory< User , String > {
		// INSTANCE FIELDS
		
		/**
		 * The array of strings whose stores all the required parameters. 
		 */	
		private String[] requiredParameters;
	
		/**
		 * The users database.  
		 */
		private final Database<User> userDatabase;
	
		/**
		 * The User username.
		 */
		private String username;
	
		/*
		 * The old password needed to check if is the true User who's is changing the password.
		 */
		
		private String oldPassword;
		
		/*
		 * The new User password.
		 */		
		private String newPassword;
		
		// CONSTRUCTOR
		
		/**
		 * Creates a new {@link PatchUserPasswordCommandsFactory} that produces
		 * commands of type {@link PatchUserPasswordCommands}.
		 * 
		 * @param userDatabase
		 *            The database where to get the User from.
		 * @throws InvalidArgumentException
		 *             If {@code userDatabase==null}.
		 */
		public PatchUserPasswordCommandsFactory(Database<User> userDatabase)
			throws InvalidArgumentException {

			super("Change An User Password", userDatabase, userDatabase);
		
		 
		this.userDatabase = userDatabase;
		this.requiredParameters = new String[] {CommandLineStringsDictionary.USERNAME,
				CommandLineStringsDictionary.OLDPASSWORD, CommandLineStringsDictionary.NEWPASSWORD };

	} 
			
		// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory

		/**
		 *  Method  responsible to return a command of the type {@code PatchUserPasswordCommands}. 
		 * 
		 * @return a command of the type {@code PatchUserPasswordCommands}
		 * @throws InvalidArgumentException 
		 * 
		 */
		@Override
		protected Callable<String> internalInternalNewInstance(User user) throws InvalidArgumentException {

			
			getUsername();
			getOldPassword();
			getNewPassword();
		
			
			return new PatchUserPasswordCommand(userDatabase,username, oldPassword, newPassword);

		}
		
		/**
		 * Method responsible to return a array who contains all the required parameters 
		 * Needed to create a {@code PatchUserPasswordCommands} command.
		 * 
		 * @return a array with required parameters.
		 * 
		 */
		@Override
		protected String[] getSpecificRequiredParameters() {
			
		return requiredParameters;
		
		}
	
		// AUXILIARY PRIVATE METHODS
		
		/**
		 * 
		 * Method responsible to set the username field needed
		 *  to {@code PatchUserPasswordCommands} command.
		 *  
		 * This method calls the 
		 * 		{@link StringsToCommandsFactory#getParameterAsString(String)} 
		 * where searches on the Map, with all the parameters, 
		 * the value of the username.
		 * 
		 */		
		private void getUsername(){
		
		username = getParameterAsString(requiredParameters[0]);
		
		}

		/**
		 * 
		 * Method responsible to set the OldPassword field needed
		 *  to {@code PatchUserPasswordCommands} command.
		 *  
		 * This method calls the 
		 * 		{@link StringsToCommandsFactory#getParameterAsString(String)} 
		 * where searches on the Map, with all the parameters, 
		 * the value of the OldPassword.
		 * 
		 */
		private void getOldPassword(){
		
		oldPassword = getParameterAsString(requiredParameters[1]);
		
	}
		
		/**
		 * 
		 * Method responsible to set the NewPassword field needed
		 *  to {@code PatchUserPasswordCommands} command.
		 *  
		 * This method calls the 
		 * 		{@link StringsToCommandsFactory#getParameterAsString(String)} 
		 * where searches on the Map, with all the parameters, 
		 * the value of the NewPassword.
		 * 
		 */
		private void getNewPassword(){
		
		newPassword = getParameterAsString(requiredParameters[2]);
		
	}
		
				
	}
