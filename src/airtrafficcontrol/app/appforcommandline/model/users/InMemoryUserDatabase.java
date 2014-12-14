package airtrafficcontrol.app.appforcommandline.model.users;

import airtrafficcontrol.app.appforcommandline.model.InMemoryDatabase;

/**
 * 
 * Class who's represent a data base of {@code User}.
 * 
 */
public class InMemoryUserDatabase extends InMemoryDatabase<User> {

	// Constructor

	public InMemoryUserDatabase() {

		User master = new User("MASTER", "master", "master@gmail.com");
		
		this.add(master, master);
	}
}