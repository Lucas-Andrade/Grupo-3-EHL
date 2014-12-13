package airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions;

import java.text.MessageFormat;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings ("serial")
public class MissingRequiredParameterException extends CommandException {

	public MissingRequiredParameterException(String parameterName) {

		super(MessageFormat.format("Required parameter with name {0} missing.", parameterName));
	}
}
