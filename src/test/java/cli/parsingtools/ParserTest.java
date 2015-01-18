package test.java.cli.parsingtools;


import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories.PatchUserPasswordCommandsFactory;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import main.java.utils.exceptions.parsingexceptions.InvalidParameterValueException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import main.java.utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import main.java.utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;

import org.junit.Assert;
import org.junit.Test;


public class ParserTest
{

	@Test 
	public void shouldGetAllUsersToTestParserWithTwoArgsAndGetCommandMethod() throws Exception{
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		CommandParser cmdparser = new CommandParser();
		
		cmdparser.registerCommand("GET", "/users", new GetAllUsersInADatabaseCommandsFactory(usersDatabase));
		
		Parser parser = new Parser(cmdparser,"GET","/users");
		
		Assert.assertEquals("[username: MASTER, password: master, email: master@master\n]", parser.getCommand().call().toString() );
				
	}
		
	@Test 
	public void shouldPatchUserPasswordToTestParserWithThreeArgsAndGetCommandMethod() throws Exception{
		
		CommandParser cmdparser = new CommandParser();
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		cmdparser.registerCommand("PATCH", "/users/{username}", new PatchUserPasswordCommandsFactory(usersDatabase) );
			
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		usersDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"PATCH", "/users/pantunes", "oldPassword=pass&newPassword=pass2&loginName=pantunes&loginPassword=pass");
		
		Assert.assertEquals("The User Password was successfully changed", parser.getCommand().call().toString() );
				
	} 
	
	@Test
	public void shouldGetAnObjectOfTheTypeToPlainTextTranslatorAfterExecuteGetTranslatorMethod() throws InvalidArgumentException, InvalidRegisterException,
												InvalidCommandParametersSyntaxException, DuplicateParametersException, 
													InvalidCommandSyntaxException, InvalidParameterValueException {
		
		
		CommandParser cmdparser = new CommandParser();
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		cmdparser.registerCommand("PATCH", "/users/{username}", new PatchUserPasswordCommandsFactory(usersDatabase) );
			
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		usersDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"PATCH", "/users/pantunes","accept=text/plain");
	
		Assert.assertEquals("ToPlainTextTranslator",parser.getTranslator().getClass().getSimpleName());
	
	}
	
	@Test
	public void shouldGetAnObjectOfTheTypeToPlainTextIfNoSpeficyTheOutputFormat() throws InvalidArgumentException, InvalidRegisterException,
												InvalidCommandParametersSyntaxException, DuplicateParametersException, 
													InvalidCommandSyntaxException, InvalidParameterValueException {
		
		
		CommandParser cmdparser = new CommandParser();
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		cmdparser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommandsFactory(usersDatabase) );
			
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		usersDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"GET", "/users/pantunes");
	
		Assert.assertEquals("ToPlainTextTranslator",parser.getTranslator().getClass().getSimpleName());
	
	}

	
	@Test
	public void shouldGetAPrintStreamObjectAfterExecuteGetStreamMethod() throws InvalidArgumentException, InvalidCommandParametersSyntaxException, 
											DuplicateParametersException, InvalidCommandSyntaxException, 
											InvalidRegisterException, InvalidParameterValueException{
		
		CommandParser cmdparser = new CommandParser();
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		cmdparser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommandsFactory(usersDatabase) );
			
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		usersDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"GET", "/users/pantunes","output-file=src/main/java/cli/TestFile.txt");
	
		Assert.assertEquals("PrintStream",parser.getStream().getClass().getSimpleName());
		
	}
	
	@Test
	public void shouldGetAPrintStreamObjectAfterExecuteGetStreamMethodButNotGivingAnySpecificParameter() throws InvalidArgumentException, InvalidCommandParametersSyntaxException, 
											DuplicateParametersException, InvalidCommandSyntaxException, 
											InvalidRegisterException, InvalidParameterValueException{
		
		CommandParser cmdparser = new CommandParser();
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		cmdparser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommandsFactory(usersDatabase) );
			
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		usersDatabase.add(user1, user1);
		
		Parser parser = new Parser(cmdparser,"GET", "/users/pantunes");
		
		Assert.assertFalse(parser.getStream().checkError());
				
	}
		
	
	@Test(expected=InvalidParameterValueException.class)
	public void shouldThrowInvalidParameterValueExceptionWhenGiveAnInvalidPathForOutputFile() throws  InvalidParameterValueException, InvalidArgumentException,
																				InvalidRegisterException, InvalidCommandParametersSyntaxException,
																				DuplicateParametersException, InvalidCommandSyntaxException{
		
		CommandParser cmdparser = new CommandParser();
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		cmdparser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommandsFactory(usersDatabase) );
			
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		usersDatabase.add(user1, user1);
		
		Parser parser =	new Parser(cmdparser,"GET", "/users/pantunes","output-file=src|main|java|cli");
			parser.getStream();
		
	}
	
	@Test(expected= InvalidParameterValueException.class)
	public void shouldThrowInvalidParameterValueExceptionWhenTryToGetAnInvalidTranslatorFormatParameter() throws InvalidCommandParametersSyntaxException, DuplicateParametersException, 
								InvalidCommandSyntaxException, InvalidArgumentException, 
								InvalidRegisterException, InvalidParameterValueException{	
		

		CommandParser cmdparser = new CommandParser();
		
		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
		
		cmdparser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommandsFactory(usersDatabase) );
			
		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
		
		usersDatabase.add(user1, user1);
		
		Parser.TRANSLATORS.put("FakeFormat", null);
		
		Parser parser = new Parser(cmdparser,"GET", "/users/pantunes","accept=FakeFormat");
	
		parser.getTranslator();
	}
	
	@Test(expected=InvalidCommandSyntaxException.class)
	
	public void shouldThrowInvalidCommandSyntaxExceptionWhenIsGivingOneArgs() throws InvalidCommandParametersSyntaxException, DuplicateParametersException, InvalidCommandSyntaxException, InvalidArgumentException{
		
		CommandParser cmdparser = new CommandParser();
		new Parser(cmdparser,"GET");
		
	}
	
	@Test(expected=InvalidCommandSyntaxException.class)
	
	public void shouldThrowInvalidCommandSyntaxExceptionWhenIsGivingNullArgs() throws InvalidCommandParametersSyntaxException, DuplicateParametersException, InvalidCommandSyntaxException, InvalidArgumentException{
		
		CommandParser cmdparser = new CommandParser();
		String args = null;
		new Parser(cmdparser , args );
		
	}
	
	@Test(expected=InvalidCommandSyntaxException.class)
	
	public void shouldthrowInvalidCommandSyntaxExceptionWhenIsGivingFourArgs() throws InvalidCommandParametersSyntaxException, DuplicateParametersException, InvalidCommandSyntaxException, InvalidArgumentException{
		
		CommandParser cmdparser = new CommandParser();
		new Parser(cmdparser , "GET", "/users", "/users", "/users");
		
	}
		
	// CONSTRUCTOR
	
	@Test( expected = InvalidArgumentException.class )
	public void constructorShouldThrowInvalidArgumentExceptionIfCmdParserIsNull()
			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidCommandSyntaxException,
			InvalidArgumentException {
		 
		new Parser( null, "", "" );
	}
	
	
	@Test( expected = InvalidCommandSyntaxException.class )
	public void constructorShouldThrowInvalidCommandSyntaxExceptionIfNoArgsGiven()
			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidCommandSyntaxException,
			InvalidArgumentException {
		
		new Parser( new CommandParser() );
	}
	
	
	@Test( expected = InvalidCommandSyntaxException.class )
	public void constructorShouldThrowInvalidCommandSyntaxExceptionIfOnly1ArgGiven()
			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidCommandSyntaxException,
			InvalidArgumentException {
		
		new Parser( new CommandParser(), "arg[0]" );
	}
	
	
	@Test( expected = InvalidCommandSyntaxException.class )
	public void constructorShouldThrowInvalidCommandSyntaxExceptionIfMoreThan3ArgsGiven()

			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidCommandSyntaxException,
			InvalidArgumentException {
		
		new Parser( new CommandParser(), "arg[0]", "arg[1]", "arg[2]", "arg[3]" );
	}
	
	
	@Test( expected = InvalidCommandParametersSyntaxException.class )
	public void constructorShouldThrowInvalidCommandParametersSyntaxException()

			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidCommandSyntaxException,
			InvalidArgumentException {
		
		new Parser( new CommandParser(), "", "", "a" );
	}
	
	
	@Test( expected = InvalidCommandParametersSyntaxException.class )
	public void constructorShouldThrowInvalidCommandParametersSyntaxException2()

			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidCommandSyntaxException,
			InvalidArgumentException {
		
		new Parser( new CommandParser(), "", "", "a&b=c" );
	}
	
	
	@Test( expected = DuplicateParametersException.class )
	public void constructorShouldThrowDuplicateParametersException()
			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidCommandSyntaxException,
			InvalidArgumentException {
		
		new Parser( new CommandParser(), "", "", "a=b&a=c" );
	}
	
}
