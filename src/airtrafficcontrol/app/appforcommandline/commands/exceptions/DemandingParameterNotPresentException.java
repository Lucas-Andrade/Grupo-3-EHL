package airtrafficcontrol.app.appforcommandline.commands.exceptions;

import java.text.MessageFormat;


@SuppressWarnings( "serial" )
public class DemandingParameterNotPresentException extends CommandException {

	public DemandingParameterNotPresentException(String parameterName) {
		super(MessageFormat.format("Demanding parameter with name {0} not present.", parameterName));
	}



	public DemandingParameterNotPresentException(String message, Throwable cause) {
		super(message, cause);
	}

}
