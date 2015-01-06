package main.java.cli.commands.patchcommands;

import java.util.concurrent.Callable;

import main.java.cli.Optional;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.users.User;
	/**
	 * Class whose instance represent commands to change an User Password  
	 * who belongs in one users database.
	 * This Class implements {@code Callable<Optional<String>>}
	 * 
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public class PatchUserPasswordCommand implements Callable< String >  {

	
 
	// INSTANCE FIELDS
		
	/**
	 * The database of users where is needed to find the user.	
	 */
	private final Database<User> userDatabase;
	
	
	/** 
	 * The user identification giving by the username. 
	 */
	
	private final String identification;
	
	/**
	 * The new user Password that will be the utilized.  
	 */
		
	private final String newPassword; 

	/** 
	 * 
	 * The user password that will be replaced. 
	 */

	private final String oldPassword;
	
	
	// CONSTRUCTOR	
	/**
	 * Creates a new instance of this command that changes the user password that
	 * belongs to {@code database}.
	 * 
	 * @param userDataBase
	 *            The database of Users where to search.
	 * @param identification
	 *            The identification of the user giving his username.
	 * @param oldPassword
	 *             	The user password that will be replaced. 
	 * @param newPassword
	 * 				The new user Password that will be the utilized.        
	 * @throws InvalidArgumentException
	 *             If either {@code database}, {@code identification},
	 *              {@code oldPassword} or {@code newPassword} are {@code null}.     
	 */
	
	public PatchUserPasswordCommand(Database<User> userDataBase,String identification,
			String oldPassword, String newPassword) throws InvalidArgumentException {
	 
		
		if(userDataBase == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");

		if(oldPassword == null)
			throw new InvalidArgumentException("Cannot instantiate command with null oldPassword.");
		
		this.identification = identification;
		this.userDatabase = userDataBase;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Returns a String  with the information about the succefully or not of 
	 * Change password operation.
	 * 
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method
	 * {@link Optional#get()} throws {@link NullPointerException} 
	 * if the result is {@code null}.
	 * </p>
	 * 
	 * @return A String 
	 * 					with the information about the successfully of the operation.
	 * @throws Exception
	 *             This method will not throw exceptions.
	 */ 

	@Override
	public String call() throws Exception {
		
		if(userDatabase.getElementByIdentification(identification).get().changePassword(newPassword, oldPassword))
			return "The User Password was successfully changed";
		
		return "The User Password was not changed";
	
	}

}
