package parsingtools_tests;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Test;
import parsingtools.CommandParser;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.PatchUserPasswordCommandsFactory;
import utils.CommandStrings_Dictionary;
import utils.CompletionStatus;
import utils.StringCommandsExecutor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import databases.InMemoryUsersDatabase;
import elements.User;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;


public class ParserTest {
    
    @Test
    public void shouldGetAllUsersToTestParserWithTwoArgsAndGetCommandMethod() throws Exception {
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        CommandParser cmdparser = new CommandParser();
        
        cmdparser.registerCommand( "GET", "/users",
                                   new GetAllUsersInADatabaseCommandsFactory( usersDatabase ) );
        
        StringCommandsExecutor parser = new StringCommandsExecutor( cmdparser, "GET", "/users" );
        
        Assert.assertEquals( "[username: MASTER,\r\npassword: master,\r\nemail: master@master\r\n]",
                             parser.getCommand().call().toString() );
        
    }
    
    @Test
    public void shouldPatchUserPasswordToTestParserWithThreeArgsAndGetCommandMethod()
        throws Exception {
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "PATCH", "/users/{username}",
                                   new PatchUserPasswordCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "PATCH", "/users/pantunes",
                            "oldPassword=pass&newPassword=pass2&loginName=pantunes&loginPassword=pass" );
        
        Assert.assertEquals( "User password successfully changed.",
                             ((CompletionStatus)parser.getCommand().call()).getMessage() );
        
    }
    
    @Test
    public void shouldGetAnObjectOfTheTypeToPlainTextTranslatorAfterExecuteGetTranslatorMethod()
        throws InvalidArgumentException, InvalidRegisterException,
        InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidParameterValueException {
        
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "PATCH", "/users/{username}",
                                   new PatchUserPasswordCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        StringCommandsExecutor parser = new StringCommandsExecutor( cmdparser, "PATCH", "/users/pantunes", "accept=text/plain" );
        
        Assert.assertEquals( "ToPlainTextTranslator", parser.getTranslator().getClass()
                                                            .getSimpleName() );
        
    }
    
    @Test
    public void shouldGetAnObjectOfTheTypeToPlainTextIfNoSpeficyTheOutputFormat()
        throws InvalidArgumentException, InvalidRegisterException,
        InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidParameterValueException {
        
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        StringCommandsExecutor parser = new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes" );
        
        Assert.assertEquals( "ToPlainTextTranslator", parser.getTranslator().getClass()
                                                            .getSimpleName() );
        
    }
    
    
    @Test
    public void shouldGetAPrintStreamObjectAfterExecuteGetStreamMethod()
        throws InvalidArgumentException, InvalidCommandParametersSyntaxException,
        DuplicateParametersException, InvalidCommandSyntaxException, InvalidRegisterException,
        InvalidParameterValueException, IOException {
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );        
        usersDatabase.add( user1, user1 );
        
        CommandParser cmdparser = new CommandParser(); 
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes",
                            CommandStrings_Dictionary.STREAM + "=src/test/resources/ParserTest_TestFile.txt" );
        
        OutputStream ps = parser.getStream();
        Assert.assertEquals( "PrintStream", ps.getClass().getSimpleName() );
        
        ps.write( "OLA".getBytes() );
        ps.flush();
        BufferedReader br = new BufferedReader(new FileReader( "src/test/resources/ParserTest_TestFile.txt" ));
        String fileContent = br.readLine();
        Assert.assertEquals( "OLA", fileContent);
        ps.close(); br.close();
        
        File f = new File("src/test/resources/ParserTest_TestFile.txt");
        Assert.assertTrue( f.delete() );
    } 
    
    @Test
    public
            void
            shouldGetAPrintStreamObjectAfterExecuteGetStreamMethodButNotGivingAnySpecificParameter()
                throws InvalidArgumentException, InvalidCommandParametersSyntaxException,
                DuplicateParametersException, InvalidCommandSyntaxException,
                InvalidRegisterException, InvalidParameterValueException {
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        StringCommandsExecutor parser = new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes" );
        
        Assert.assertFalse( parser.getStream().checkError() );
        
    }
    
    
//	@Test(expected=InvalidParameterValueException.class)
//	public void shouldThrowInvalidParameterValueExceptionWhenGiveAnInvalidPathForOutputFile() throws  InvalidParameterValueException, InvalidArgumentException,
//																				InvalidRegisterException, InvalidCommandParametersSyntaxException,
//																				DuplicateParametersException, InvalidCommandSyntaxException{
//		
//		CommandParser cmdparser = new CommandParser();
//		
//		InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase("firstUsersDatabse");
//		
//		cmdparser.registerCommand("GET", "/users/{username}", new GetUserByUsernameCommandsFactory(usersDatabase) );
//			
//		User user1 = new User("pantunes", "pass","Pantunes@gmail.com");
//		
//		usersDatabase.add(user1, user1);
//		
//		Parser parser =	new Parser(cmdparser,"GET", "/users/pantunes","output-file=src|main|java|cli.txt");
//			parser.getStream();
//	}
    
    @Test( expected = InvalidParameterValueException.class )
    public
            void
            shouldThrowInvalidParameterValueExceptionWhenTryToGetAnInvalidTranslatorFormatParameter()
                throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
                InvalidCommandSyntaxException, InvalidArgumentException, InvalidRegisterException,
                InvalidParameterValueException {
        
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        StringCommandsExecutor.TRANSLATORS.put( "FakeFormat", null );
        
        StringCommandsExecutor parser = new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes", "accept=FakeFormat" );
        
        parser.getTranslator();
    }
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void shouldThrowInvalidCommandSyntaxExceptionWhenIsGivingOneArgs()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        CommandParser cmdparser = new CommandParser();
        new StringCommandsExecutor( cmdparser, "GET" );
        
    }
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void shouldThrowInvalidCommandSyntaxExceptionWhenIsGivingNullArgs()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        CommandParser cmdparser = new CommandParser();
        String args = null;
        new StringCommandsExecutor( cmdparser, args );
        
    }
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void shouldthrowInvalidCommandSyntaxExceptionWhenIsGivingFourArgs()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        CommandParser cmdparser = new CommandParser();
        new StringCommandsExecutor( cmdparser, "GET", "/users", "/users", "/users" );
        
    }
    
    // CONSTRUCTOR
    
    @Test( expected = InvalidArgumentException.class )
    public void constructorShouldThrowInvalidArgumentExceptionIfCmdParserIsNull()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        new StringCommandsExecutor( null, "", "" );
    }
    
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void constructorShouldThrowInvalidCommandSyntaxExceptionIfNoArgsGiven()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        new StringCommandsExecutor( new CommandParser() );
    }
    
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void constructorShouldThrowInvalidCommandSyntaxExceptionIfOnly1ArgGiven()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        new StringCommandsExecutor( new CommandParser(), "arg[0]" );
    }
    
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void constructorShouldThrowInvalidCommandSyntaxExceptionIfMoreThan3ArgsGiven()
        
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        new StringCommandsExecutor( new CommandParser(), "arg[0]", "arg[1]", "arg[2]", "arg[3]" );
    }
    
    
    @Test( expected = InvalidCommandParametersSyntaxException.class )
    public void constructorShouldThrowInvalidCommandParametersSyntaxException()
        
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        new StringCommandsExecutor( new CommandParser(), "", "", "a" );
    }
    
    
    @Test( expected = InvalidCommandParametersSyntaxException.class )
    public void constructorShouldThrowInvalidCommandParametersSyntaxException2()
        
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        new StringCommandsExecutor( new CommandParser(), "", "", "a&b=c" );
    }
    
    
    @Test( expected = DuplicateParametersException.class )
    public void constructorShouldThrowDuplicateParametersException()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException {
        
        new StringCommandsExecutor( new CommandParser(), "", "", "a=b&a=c" );
    }
    
}
