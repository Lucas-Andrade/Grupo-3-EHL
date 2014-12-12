package airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions;

/**
 * Class whose instances represent parser errors that result from finding 
 * argument repetitions in a command. 
 * 
 * TODO: elaborate
 */
@SuppressWarnings("serial")
public class DuplicateParametersException extends CommandParserException 
{
	public DuplicateParametersException() {
	}

	public DuplicateParametersException(String message) {
		super(message);
	}


	public DuplicateParametersException(String message, Throwable cause) {
		super(message, cause);
	}
 
}
