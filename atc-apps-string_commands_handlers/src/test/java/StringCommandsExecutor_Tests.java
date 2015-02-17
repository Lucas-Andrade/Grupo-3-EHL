

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;
import parsingtools.CommandParser;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllUsersInADatabaseCommandsFactory;
import parsingtools.commandfactories.getfactories.getbyidfactories.GetUserByUsernameCommandsFactory;
import parsingtools.commandfactories.userauthenticatingfactories.PatchUserPasswordCommandsFactory;
import utils.StringCommandsExecutor;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.UnsupportedAcceptValueException;
import utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import databases.InMemoryUsersDatabase;
import elements.User;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * {@link StringCommandsExecutor};
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class StringCommandsExecutor_Tests {
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetAllUsersToTestParserWithTwoArgsAndGetCommandMethod() throws Exception {
    
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        CommandParser cmdparser = new CommandParser();
        
        cmdparser.registerCommand( "GET", "/users",
                                   new GetAllUsersInADatabaseCommandsFactory( usersDatabase ) );
        
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "GET", "/users", "accept=application/json" );
        
        Assert.assertEquals( "[{\"username\":\"MASTER\",\"password\":\"master\",\"email\":\"master@master\",\"fullName\":\"\"}]",
                             parser.getOutput() );
        
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
                                            "oldPassword=pass&newPassword=pass2&loginName=pantunes&loginPassword=pass&accept=application/json" );
        
        
        Assert.assertEquals( "{\"theStatus\":true,\"message\":\"User password successfully changed.\"}",
                             parser.getOutput() );
        
    }
    
    @Test
    public void shouldGetAnObjectOfTheTypeToPlainTextTranslatorAfterExecuteGetTranslatorMethod()
        throws Exception {
    
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "PATCH", "/users/{username}",
                                   new PatchUserPasswordCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "PATCH", "/users/pantunes",
                                            "oldPassword=pass&newPassword=pantunes&accept=text/plain" );
        
        Assert.assertEquals( "User password successfully changed.", parser.getOutput() );
        
    }
    
    @Test
    public void shouldGetAnObjectOfTheTypeToPlainTextIfNoSpeficyTheOutputFormat() throws Exception {
    
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes" );
        
        Assert.assertEquals( "username: pantunes,\r\nemail: Pantunes@gmail.com\r\n",
                             parser.getOutput() );
        
    }
    
    @Test
    public void shouldWriteTheStringWithResultToTheStream() throws Exception {
    
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        usersDatabase.add( user1, user1 );
        
        CommandParser cmdparser = new CommandParser();
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes",
                                            "output-file=src/test/resources/ParserTest_TestFile2.txt" );
        parser.writeOutputToStream();
        
        BufferedReader br =
                new BufferedReader( new FileReader( "src/test/resources/ParserTest_TestFile2.txt" ) );
        String builder = null;
        StringBuilder builder2 = new StringBuilder();
        while( (builder = br.readLine()) != null )
            builder2.append( builder );
        
        Assert.assertEquals( "username: pantunes,email: Pantunes@gmail.com", builder2.toString() );
        br.close();
    }
    
    @Test
    public void shouldWriteTheStringWithResultToThePrintStream()
        throws InvalidArgumentException, InvalidCommandParametersSyntaxException,
        DuplicateParametersException, InvalidCommandSyntaxException, InvalidRegisterException,
        InvalidParameterValueException, IOException, UnsupportedAcceptValueException {
    
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        usersDatabase.add( user1, user1 );
        
        CommandParser cmdparser = new CommandParser();
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        OutputStream ps =
                new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes" ).getStream();
        
        Assert.assertTrue( ps instanceof PrintStream );
    }
    
    @Test
    public void shouldGetAPrintStreamObjectAfterExecuteGetStreamMethod()
        throws InvalidArgumentException, InvalidCommandParametersSyntaxException,
        DuplicateParametersException, InvalidCommandSyntaxException, InvalidRegisterException,
        InvalidParameterValueException, IOException, UnsupportedAcceptValueException {
    
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        usersDatabase.add( user1, user1 );
        
        CommandParser cmdparser = new CommandParser();
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes",
                                            "output-file=src/test/resources/ParserTest_TestFile.txt" );
        
        OutputStream ps = parser.getStream();
        Assert.assertEquals( "PrintStream", ps.getClass().getSimpleName() );
        
        ps.write( "OLA".getBytes() );
        ps.flush();
        BufferedReader br =
                new BufferedReader( new FileReader( "src/test/resources/ParserTest_TestFile.txt" ) );
        String fileContent = br.readLine();
        Assert.assertEquals( "OLA", fileContent );
        ps.close();
        br.close();
        
        File f = new File( "src/test/resources/ParserTest_TestFile.txt" );
        Assert.assertTrue( f.delete() );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void shouldThrowInvalidCommandSynthaxExceptionWhenGivingNullArgs()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        CommandParser cmdparser = new CommandParser();
        String[] test = null;
        @SuppressWarnings( "unused" )
        StringCommandsExecutor parser = new StringCommandsExecutor( cmdparser, test );
        
    }
    
    @Test( expected = UnsupportedAcceptValueException.class )
    public
            void
            shouldThrowUnsupportedAcceptValueExceptionWhenTryToGetAnUnknownAcceptParameterValue()
                throws Exception {
    
        
        CommandParser cmdparser = new CommandParser();
        
        InMemoryUsersDatabase usersDatabase = new InMemoryUsersDatabase( "firstUsersDatabse" );
        
        cmdparser.registerCommand( "GET", "/users/{username}",
                                   new GetUserByUsernameCommandsFactory( usersDatabase ) );
        
        User user1 = new User( "pantunes", "pass", "Pantunes@gmail.com" );
        
        usersDatabase.add( user1, user1 );
        
        StringCommandsExecutor.TRANSLATORS.put( "FakeFormat", null );
        
        StringCommandsExecutor parser =
                new StringCommandsExecutor( cmdparser, "GET", "/users/pantunes",
                                            "accept=FakeFormat" );
        
        parser.getOutput();
    }
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void shouldThrowInvalidCommandSyntaxExceptionWhenIsGivingOneArgs()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        CommandParser cmdparser = new CommandParser();
        new StringCommandsExecutor( cmdparser, "GET" );
        
    }
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void shouldThrowInvalidCommandSyntaxExceptionWhenIsGivingNullArgs()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        CommandParser cmdparser = new CommandParser();
        String args = null;
        new StringCommandsExecutor( cmdparser, args );
        
    }
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void shouldthrowInvalidCommandSyntaxExceptionWhenIsGivingFourArgs()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        CommandParser cmdparser = new CommandParser();
        new StringCommandsExecutor( cmdparser, "GET", "/users", "/users", "/users" );
        
    }
    
    // Test Constructor Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void constructorShouldThrowInvalidArgumentExceptionIfCmdParserIsNull()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        
        new StringCommandsExecutor( null, "", "" );
    }
    
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void constructorShouldThrowInvalidCommandSyntaxExceptionIfNoArgsGiven()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        
        new StringCommandsExecutor( new CommandParser() );
    }
    
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void constructorShouldThrowInvalidCommandSyntaxExceptionIfOnly1ArgGiven()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        
        new StringCommandsExecutor( new CommandParser(), "arg[0]" );
    }
    
    
    @Test( expected = InvalidCommandSyntaxException.class )
    public void constructorShouldThrowInvalidCommandSyntaxExceptionIfMoreThan3ArgsGiven()
        
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        
        new StringCommandsExecutor( new CommandParser(), "arg[0]", "arg[1]", "arg[2]", "arg[3]" );
    }
    
    
    @Test( expected = InvalidCommandParametersSyntaxException.class )
    public void constructorShouldThrowInvalidCommandParametersSyntaxException()
        
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        
        new StringCommandsExecutor( new CommandParser(), "", "", "a" );
    }
    
    
    @Test( expected = InvalidCommandParametersSyntaxException.class )
    public void constructorShouldThrowInvalidCommandParametersSyntaxException2()
        
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        new StringCommandsExecutor( new CommandParser(), "", "", "a&b=c" );
        
    }
    
    
    @Test( expected = DuplicateParametersException.class )
    public void constructorShouldThrowDuplicateParametersException()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidCommandSyntaxException, InvalidArgumentException, UnsupportedAcceptValueException {
    
        
        new StringCommandsExecutor( new CommandParser(), "", "", "a=b&a=c" );
        
    }
    
    
}
