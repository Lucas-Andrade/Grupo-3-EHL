package main.java.exceptions.commandParserExceptions;

@SuppressWarnings ("serial")
public class UnknownCommandException extends CommandParserException {

	public UnknownCommandException(String message) {

		super(message);
	}
}