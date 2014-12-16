package main.java.exceptions.commandParserExceptions;

import java.text.MessageFormat;

@SuppressWarnings ("serial")
public class InvalidRegisterException extends CommandParserException {

	public InvalidRegisterException(String placeholderToAdd, String existingPlaceholder) {

		super(MessageFormat.format("Command registred with a placeholder with name '{0}', "
				+ "at node with an already existant placeholder child with name '{1}'",
				placeholderToAdd, existingPlaceholder));
	}
}