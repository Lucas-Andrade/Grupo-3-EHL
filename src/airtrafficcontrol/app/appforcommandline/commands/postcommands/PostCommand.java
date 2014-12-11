package airtrafficcontrol.app.appforcommandline.commands.postcommands;


import java.util.Map;
import airtrafficcontrol.app.appforcommandline.Database;
import airtrafficcontrol.app.appforcommandline.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.DemandingParameterNotPresentException;


public abstract class PostCommand extends AbstractCommand
{
	
	// INSTANCE FIELDS
	
	/**
	 * The users' database that stores the user who's posting.
	 */
	protected InMemoryUserDatabase usersDatabase;
	
	/**
	 * The database where to store the posted element.
	 */
	protected Database database;
	
	
	
	// CONSTRUCTOR
	/**
	 * Stores the containers {@code parameters}, {@code usersDatabase} and
	 * {@code database}.
	 * 
	 * @param usersDatabase
	 *            The users' database that stores the user who is posting.
	 * @param database
	 *            The database where to store the posted element.
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public PostCommand( InMemoryUserDatabase usersDatabase, Database database,
			Map< String, String > parameters ) {
		
		super( parameters );
		this.usersDatabase = usersDatabase;
		this.database = database;
	}
	
	
	
	// EXECUTE METHOD
	/**
	 * Performs the specific action associated with this command, inclusively
	 * authenticates the user who is posting.
	 */
	protected void internalExecute() throws CommandException {
		authenticateUser();
		internalPostExecute();
	};
	
	
	// UNIMPLEMENTED METHODS
	
	/**
	 * Performs the specific action associated with this command.
	 */
	protected abstract void internalPostExecute() throws CommandException;
	
	/**
	 * Returns an array of {@link String strings} that has the names of the
	 * parameters without whom the command cannot execute.
	 * 
	 * @return An array of {@link String strings} that has the names of the
	 *         parameters without whom the command cannot execute.
	 */
	protected abstract String[] getRequiredParameters();
	
	
	
	// AUXILIAR PRIVATE METHODS
	
	/**
	 * Checks whether the required parameters (obtained using the method
	 * {@link #getRequiredParameters()}) were received.
	 * 
	 * @param requiredParameters
	 */
	private void authenticateUser( String... parameterNames )
			throws DemandingParameterNotPresentException {
		// TODO: confirm password
	}
	
}
