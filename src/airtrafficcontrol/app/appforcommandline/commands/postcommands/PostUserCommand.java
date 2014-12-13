package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

	/** 
	 * Class that extends {@link PostCommand} to add a new user   
	 * into an User Database.
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */


public class PostUserCommand extends PostCommand<User>
{
	
	private static final String USERNAME = "username"; 
	
	private static final String PASSWORD = "password";
	
	private static final String EMAIL = "email";
	
	private static final String FULLNAME = "fullName";
	
	private static final String[] REQUIREDPARAMETERS = {USERNAME,PASSWORD,EMAIL};
	
	/**
	 * 
	 * Class that implements the {@link CommandFactory} factory
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	
	public static class Factory implements CommandFactory {
		
		
		InMemoryUserDatabase postingUsersDatabase;
		InMemoryUserDatabase postedUsersDatabase;
		Map<String,String> parameters;
		
		
		public Factory(InMemoryUserDatabase postingUsersDatabase,InMemoryUserDatabase postedUsersDatabase, Map<String,String> parameters ){
			
			this.postingUsersDatabase=postingUsersDatabase;
			this.postedUsersDatabase=postedUsersDatabase;
			this.parameters=parameters;
		}
		
		
		public Command newInstance(Map<String, String> parameters) {
	
			return new PostUserCommand( postingUsersDatabase,postedUsersDatabase,parameters);
		}
		
	}
	
	/**
	 * Constructor 
	 * 
	 * store the container parameters
	 * 
	 * @param postingUsersDatabase - Users Data who contains the user responsible to add the new user. 
	 * @param postedUsersDatabase - User Data who will contain the new user.
	 * @param parameters - Container with parameters needed to proceed the search.
	 */
	
	public PostUserCommand( InMemoryUserDatabase postingUsersDatabase,
			InMemoryUserDatabase postedUsersDatabase, Map< String, String > parameters ) {
		
		super( postingUsersDatabase, postedUsersDatabase, parameters );
	

	
	}
	
	/**
	 * Override of {@link AbstractCommand} 
	 * 
	 * execute the main action associated to this command
	 * 
	 */
	
	
	@Override
	protected void internalPostExecute() throws CommandException {
		

		String username = parameters.get(USERNAME);
		String password = parameters.get(PASSWORD); 
		String email = parameters.get(EMAIL);
		String fullName = parameters.get(FULLNAME);
		
		User user = (fullName !=null ) ? new User(username,password,email,fullName)	: new User(username,password,email);
		

		User postingUser =usersDatabase.getElementByIdentification(parameters.get("loginName"));	
		
		
		result = database.add(user, postingUser) ? "User was successfull added": "User was not successfull added";
		
		 
	
	
	}
	
	/**
	 * Override of {@link AbstractCommand} 
	 * 
	 * Method responsible to get the Obligation parameters associated to this Command
	 * 
	 */
	@Override 

	protected String[] getSpecificRequiredParameters() {
		return REQUIREDPARAMETERS;
	}
	
	
			
}