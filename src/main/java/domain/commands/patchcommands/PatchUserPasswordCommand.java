package main.java.domain.commands.patchcommands;

import java.util.concurrent.Callable;

import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;

/**
 * Class whose instances are commands that change the user's password from a user who belongs in a
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
	 *             If either {@code database}, {@code identification}, {@code oldPassword} or
	 *             {@code newPassword} are {@code null}.
	 */
	public PatchUserPasswordCommand(Database<User> userDataBase, String username,
			String oldPassword, String newPassword) throws InvalidArgumentException {

		if (userDataBase == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");

		if (oldPassword == null)
			throw new InvalidArgumentException("Cannot instantiate command with null oldPassword.");

		this.identification = username;
		this.userDatabase = userDataBase;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}

	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE

	/**
	 * Returns a String with the information regarding the success of the user's password change.
	 * 
	 * @return A String with the information regarding the success of the operation.
	 * @throws InvalidArgumentException
	 * 
	 * @throws Exception
	 *             This method will not throw exceptions.
	 */
	@Override
	public String call() throws InvalidArgumentException {

		try {
			userDatabase.removeByIdentification(identification);

			User user = new User(identification, newPassword, email, fullname);

			userDatabase.add(user, user);

			return "User password successfully changed";

		} catch (DatabaseException e) {
			return "The user does not exist in the database";
		}
	}
}