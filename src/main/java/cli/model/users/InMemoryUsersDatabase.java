package main.java.cli.model.users;

import main.java.cli.model.Database;
import main.java.cli.model.InMemoryDatabase;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;

/**
 * Class whose instances represent in-memory databases of {@link User}s. An in-memory database
 * exists only during the runtime. All {@link InMemoryUsersDatabase} have a default user stored
 * which can be used to add another users to the database: this user's username is "MASTER" and
 * password is "master".</br> Implements {@link Database}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryUsersDatabase extends InMemoryDatabase<User> {

	// Constructor

	/**
	 * Creates a new instance of {@link InMemoryUserDatabase} with the name {@code databaseName} and
	 * automatically generates a default {@link User} that will be added to it and that will serve
	 * as an administrator that will allow the first new {@code User} to be added.
	 * 
	 * @param databaseName
	 *            - This database's name.
	 * 
	 * @throws InvalidArgumentException
	 *             If the given {@code databaseName} is null.
	 */
	public InMemoryUsersDatabase(String databaseName) throws InvalidArgumentException {

		super(databaseName);

		try {
			User master = new User("MASTER", "master", "master@master");
			this.add(master, master);

		} catch (InvalidArgumentException e) {
			// this never happens cause user's constructor wasn't given null nor
			// empty-string arguments and method add of InMemoryDatabase was
			// called with non-null arguments
		}
	}

	// OVERRIDES OF InMemoryDatabase'S METHODS

	/**
	 * Stores an {@link #User} in this database, added by another {@link User}. Override of the
	 * method {@link InMemoryDatabase#add() add()}.
	 * 
	 * If there is another {@code User} with the same {@code userName} or the same {@code emal} in
	 * {@code this} database the {@code User} will not be added.
	 * 
	 * @param userToAdd
	 *            - The {@code User} to be added to this database.
	 * @param userWhoIsPosting
	 *            - The {@code User} who is adding the {@code userToAdd} to the database.
	 * 
	 * @return {@code true} if the airship was successfully added and {@code false} otherwhise.
	 * 
	 * @throws InvalidArgumentException
	 *             If either of the given {@code Users} are {@code null}.
	 */
	@Override
	public boolean add(User userToAdd, User userWhoIsPosting) throws InvalidArgumentException {

		try {
			for (User user : getAllElements().get())
				if (user.getEmail().equals(userToAdd.getEmail()))
					return false;

		} catch (Exception e) { // never happens because getAllElements never returns null optionals
								// so the get() method never throws the exception!
		}

		return super.add(userToAdd, userWhoIsPosting);
	}

	/**
	 * Removes the {@code user} with the given {@code username} from this database. Override of the
	 * method {@link InMemoryDatabase#remove() remove()}.
	 * 
	 * If an {@code user} with the given {@code username} doesn't exist in {@code this} database no
	 * {@code user} will be removed.
	 * 
	 * @param username
	 *            - The username of the user to be removed.
	 * @return {@code true} if the user was successfully removed and {@code false} otherwise.
	 * 
	 * @throws DatabaseException
	 *             When trying to remove the user with username "MASTER" from this database.
	 * @throws InvalidArgumentException
	 *             If the given {@code username} is null.
	 */
	@Override
	public boolean removeByIdentification(String username) throws DatabaseException,
			InvalidArgumentException {

		if (username.equals("MASTER"))
			throw new DatabaseException("Cannot remove the MASTER user.");

		return super.removeByIdentification(username);
	}
}