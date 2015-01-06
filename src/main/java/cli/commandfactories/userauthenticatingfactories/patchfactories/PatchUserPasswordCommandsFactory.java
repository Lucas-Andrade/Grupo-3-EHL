package main.java.cli.commandfactories.userauthenticatingfactories.patchfactories;


import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.CommandLineDictionary;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.patchcommands.PatchUserPasswordCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.users.User;



/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce a
 * command of type {@link PatchUserPasswordCommands}. Commands are
 * {@link Callable} instances. This class extends
 * {@link StringsToCommandsFactory}
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public class PatchUserPasswordCommandsFactory extends
		StringsToCommandsFactory< Optional< String >>
{
	
	// INSTANCE FIELDS
	
	/**
	 * The array of strings whose stores all the required parameters.
	 */
	private String[] requiredParameters;
	
	/**
	 * The users database.
	 */
	private final Database< User > userDatabase;
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new {@link PatchUserPasswordCommandsFactory} that produces
	 * commands of type {@link PatchUserPasswordCommands}.
	 * 
	 * @param userDatabase
	 *            The database where to get the User from.
	 * @throws InvalidArgumentException
	 *             If {@code userDatabase==null}.
	 */
	public PatchUserPasswordCommandsFactory( Database< User > userDatabase )
			throws InvalidArgumentException {
		super( "Change An User Password" );
		
		if( userDatabase == null )
			throw new InvalidArgumentException(
					"It's not allow instantiate a factory with null Users database" );
		
		this.userDatabase = userDatabase;
		this.requiredParameters = new String[]{ CommandLineDictionary.USERNAME,
				CommandLineDictionary.OLDPASSWORD, CommandLineDictionary.NEWPASSWORD };
		
	}
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Method responsible to return a command of the type
	 * {@code PatchUserPasswordCommands}.
	 * 
	 * @return a command of the type {@code PatchUserPasswordCommands}
	 * 
	 */
	@Override
	protected Callable< Optional< String >> internalNewInstance()
			throws InvalidArgumentException {
		
		return new PatchUserPasswordCommand( userDatabase, getUsername(),
				getOldPassword(), getNewPassword() );
		
	}
	
	/**
	 * Method responsible to return a array who contains all the required
	 * parameters Needed to create a {@code PatchUserPasswordCommands} command.
	 * 
	 * @return a array with required parameters.
	 * 
	 */
	@Override
	protected String[] getRequiredParameters() {
		
		return requiredParameters;
	}
	
	// AUXILIARY PRIVATE METHODS
	
	/**
	 * 
	 * Method responsible to set the username field needed to
	 * {@code PatchUserPasswordCommands} command.
	 * 
	 * This method calls the
	 * {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the username.
	 * 
	 * @return a String with the username value.
	 */
	private String getUsername() {
		
		return getParameterAsString( requiredParameters[0] );
		
	}
	
	/**
	 * 
	 * Method responsible to set the OldPassword field needed to
	 * {@code PatchUserPasswordCommands} command.
	 * 
	 * This method calls the
	 * {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the
	 * OldPassword.
	 * 
	 * @return a String with the OldPassword value.
	 */
	private String getOldPassword() {
		
		return getParameterAsString( requiredParameters[1] );
		
	}
	
	/**
	 * 
	 * Method responsible to set the NewPassword field needed to
	 * {@code PatchUserPasswordCommands} command.
	 * 
	 * This method calls the
	 * {@link StringsToCommandsFactory#getParameterAsString(String)} where
	 * searches on the Map, with all the parameters, the value of the
	 * NewPassword.
	 * 
	 * @return a String with the NewPassword value.
	 */
	private String getNewPassword() {
		
		return getParameterAsString( requiredParameters[2] );
		
	}
	
}
