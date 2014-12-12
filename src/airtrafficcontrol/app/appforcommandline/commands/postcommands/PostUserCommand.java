package airtrafficcontrol.app.appforcommandline.commands.postcommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;


import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;


public class PostUserCommand extends PostCommand
{
	
	private static final String USERNAME = "username"; 
	
	private static final String PASSWORD = "password";
	
	private static final String EMAIL = "email";
	
	private static final String FULLNAME = "fullName";
	
	private static final String[] REQUIREDPARAMETERS = {USERNAME,PASSWORD,EMAIL};
	
	
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
			
	
	public PostUserCommand( InMemoryUserDatabase postingUsersDatabase,
			InMemoryUserDatabase postedUsersDatabase, Map< String, String > parameters ) {
		
		super( postingUsersDatabase, postedUsersDatabase, parameters );
	

	
	}

	
	@Override
	protected void internalPostExecute() throws CommandException {
		

		String username = parameters.get(USERNAME);
		String password = parameters.get(PASSWORD); 
		String email = parameters.get(EMAIL);
		String fullName = parameters.get(FULLNAME);
		
		User user = (fullName !=null ) ? new User(username,password,email,fullName)	: new User(username,password,email);
		

		User postingUser =usersDatabase.getElementByIdentification(parameters.get("loginName"));
		database.add(user, postingUser);
	}
	
	//@Override 

	protected String[] getSpecificRequiredParameters() {
		return REQUIREDPARAMETERS;
	}
	
	
			
}
