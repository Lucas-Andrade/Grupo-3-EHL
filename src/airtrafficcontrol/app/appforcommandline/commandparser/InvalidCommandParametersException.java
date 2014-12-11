package airtrafficcontrol.app.appforcommandline.commandparser;

/**
 * Class whose instances represent parser errors that result from trying to  
 * parse a command which has an illegal syntax. 
 * 
 * TODO: elaborate
 */
@SuppressWarnings("serial")
public class InvalidCommandParametersException extends CommandParserException 
{
	public InvalidCommandParametersException() {
	}

	public InvalidCommandParametersException(String message) {
		super(message);
	}


	public InvalidCommandParametersException(String message, Throwable cause) {
		super(message, cause);
	}
 
}
