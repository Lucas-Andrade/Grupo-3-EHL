package airtrafficcontrol.tests.testsforcommandline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.postcommands.PostUserCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;

public class PostUserCommand_Tests { 

	CommandParser commandParser = new CommandParser();
	
	InMemoryUserDatabase postingUsersDatabase = new InMemoryUserDatabase();
	InMemoryUserDatabase postedUsersDatabase= new InMemoryUserDatabase();
	
	PostUserCommand postUser;
	
	@Before
	
	public void BeforeTests() throws InvalidRegisterException{

		commandParser.registerCommand("POST", "/users", new PostUserCommand.Factory(postingUsersDatabase, postedUsersDatabase));
	}
	
	
	@Test 
	
	public void VerifyIfUserIsCorrectlyAddedIntoUsersDatabaseGivingOnlyTheRequiredParameters() throws Exception{
				
	postUser =	(PostUserCommand) commandParser.getCommand("POST","/users","username=pedro&password=pass2&email=pedro@gmail.com&loginName=MASTER&loginPassword=master");
	
	postUser.execute();

	Assert.assertEquals(postUser.getResult(),"User was successfull added");
	
	
	} 
	
	@Test 
	
	public void VerifyIfUserIsCorrectlyAddedIntoUsersDatabaseGivingAllParameters() throws Exception{
				
	postUser =	(PostUserCommand) commandParser.getCommand("POST","/users",
						"username=pedro&password=pass2&email=pedro@gmail.com&fullName=Pantunes&loginName=MASTER&loginPassword=master");
	
	postUser.execute();
 
	Assert.assertEquals(postUser.getResult(),"User was successfull added");
	
	
	}
	
	@Test 
	
	public void VerifyThatUserIsNotAddedIntoUsersDatabaseBecauseAlreadyWasAddedOneWithTheSameIdentification() throws Exception{
				
	 postUser =	(PostUserCommand) commandParser.getCommand("POST","/users","username=null&password=pass2&email=pedro@gmail.com&fullName=Pantunes&loginName=MASTER&loginPassword=master");
	
	 postUser.execute();
	
	 postUser =	(PostUserCommand) commandParser.getCommand("POST","/users","username=null&password=pass2&email=pedro@gmail.com&fullName=Pantunes&loginName=MASTER&loginPassword=master");

	 postUser.execute();
	
	Assert.assertEquals(postUser.getResult(),"User was not successfull added");
	
	 
	}
}
