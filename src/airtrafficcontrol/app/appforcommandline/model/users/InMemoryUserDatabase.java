package airtrafficcontrol.app.appforcommandline.model.users;



import airtrafficcontrol.app.appforcommandline.model.InMemoryDatabase;

/**
 * 
 * Class who's represent a data base of {@code User}.
 * 
 */
public class InMemoryUserDatabase extends InMemoryDatabase<User> {
	
	public InMemoryUserDatabase() {
		
		User master = new User("Daniel", "DanyGs", "danielacgomes1992@gmail.com", "Daniel Gomes");

		this.add(master, master);
		}
	}
