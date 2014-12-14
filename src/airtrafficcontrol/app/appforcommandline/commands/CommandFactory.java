package airtrafficcontrol.app.appforcommandline.commands;

import java.util.Map;

/**
 * Contract to be supported by all {@link Command} factories.
 */
public interface CommandFactory {

	/**
	 * Creates the corresponding {@link Command} instance.
	 * 
	 * @param parameters
	 *            The command's parameters
	 * @return The new instance
	 */
	public Command newInstance(Map<String, String> parameters);
}
