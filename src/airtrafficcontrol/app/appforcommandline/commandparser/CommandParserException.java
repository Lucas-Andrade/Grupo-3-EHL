package airtrafficcontrol.app.appforcommandline.commandparser;

@SuppressWarnings("serial")
public class CommandParserException extends Exception {

	public CommandParserException() {
	}

	public CommandParserException(String message) {
		super(message);
	}


	public CommandParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
