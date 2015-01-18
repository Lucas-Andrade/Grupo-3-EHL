package main.java.domain.commands.patchcommands;

import java.util.concurrent.Callable;

import main.java.domain.model.Database;
import main.java.domain.model.InMemoryDatabase;
import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;

/**
 * Class whose instances are commands that change the user's password from a user which belongs in a
 * given users database.
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPasswordCommand implements Callable<String> {
	
	// INSTANCE FIELDS
	
	/**
	 * The users database that contains the user we want to patch.
	 */
	private final Database<User>	userDatabase;
	
	/**
	 * The user identification giving by the username.
	 */
	private final String			username;
	
	/**
	 * The new user Password that will be the utilized.
	 */
	private final String			newPassword;
	
	/**
	 * 
	 * The user password that will be replaced.
	 */
	private final String			oldPassword;
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new instance of this command that changes the user password that belongs to
	 * {@link #userDatabase}.
	 * 
	 * @param userDataBase
	 *            - The database of Users where to search.
	 * @param username
	 *            - The user's identification.
	 * @param oldPassword
	 *            - The password that will be replaced.
	 * @param newPassword
	 *            - The password that will replace the previous one.
	 * 
	 * @throws InvalidArgumentException
	 *             If either {@code userDataBase}, {@code username}, {@code oldPassword} or
	 *             {@code newPassword} are {@code null}.
	 */
	public PatchUserPasswordCommand(Database<User> userDataBase, String username,
		String oldPassword, String newPassword) throws InvalidArgumentException {
	
		if (userDataBase == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");
		
		if (username == null)
			throw new InvalidArgumentException("Cannot instantiate command with null username.");
		
		if (oldPassword == null)
			throw new InvalidArgumentException("Cannot instantiate command with null oldPassword.");
		
		if (newPassword == null)
			throw new InvalidArgumentException("Cannot instantiate command with null newPassword.");
		
		this.username = username;
		this.userDatabase = userDataBase;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	
	/**
	 * Returns a String with the information regarding the success of the user's password change.
	 * 
	 * If there is no user in the database with the given username the method
	 * {@link InMemoryDatabase#getElementByIdentification(String)
	 * getElementByIdentification(String)} return and create a new {@link Optional} that will will
	 * throw {@link NoSuchElementInDatabaseException} when it's {@link Optional#get() get()} method
	 * is called. An error message will be returned when this exception is caught by the try-catch.
	 * 
	 * @return A String with the information regarding the success of the operation.
	 * 
	 * @throws InvalidArgumentException
	 *             If the given {@code #username} is null. Will never happen because it is already
	 *             taken cared of in the class constructor.
	 * @throws Exception
	 *             Should never happen because the get method from {@link Optional} can only throw a
	 *             {@link NoSuchElementInDatabaseException} which will be caught by the try-catch.
	 */
	@Override
	public String call() throws InvalidArgumentException, Exception {
	
		try {
			
			User originalUser = userDatabase.getElementByIdentification(username).get();
			
			if (originalUser.authenticatePassword(oldPassword)) {
				
				userDatabase.removeByIdentification(username);
				
				User user = new User(username, newPassword, originalUser.getEmail(),
					originalUser.getFullName());
				
				userDatabase.add(user, user);
				
				return "User password successfully changed";
			}
			
			return "Failed autentication, please insert the correct password";
			
		} catch (DatabaseException e) {
			return "The user does not exist in the database";
		}
	}
}