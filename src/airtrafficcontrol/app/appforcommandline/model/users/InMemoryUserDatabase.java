package airtrafficcontrol.app.appforcommandline.model.users;

import airtrafficcontrol.app.appforcommandline.model.Database;
import airtrafficcontrol.app.appforcommandline.model.InMemoryDatabase;

/**
 * Class whose instances represent an in-memory database of {@link User users}. </br></br>
 * Implements {@link Database}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryUserDatabase extends InMemoryDatabase<User> {

	// Constructor

	/**
	 * Creates a {@code InMemoryUserDatabase} and automatically generates a default {@link User} that will be added
	 * to it and that will serve as an administrator that will allow the first new {@code User} to be
	 * added.
	 */
	public InMemoryUserDatabase() {

		User master = new User("MASTER", "master", "master@gmail.com");

		this.add(master, master);
	}
}