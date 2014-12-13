package airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions;

@SuppressWarnings ("serial")
public class UnknownCommandException extends CommandParserException {

	public UnknownCommandException(String message) {

		super(message);
	}
}
