package test.java;


import main.java.cli.CommandParser;
import main.java.cli.Parser;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.DuplicateParametersException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandSyntaxException;
import org.junit.Test;


public class ParserTest
{
	
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
