package main.java.cli.commandfactories.getfactories.getbyidfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.getcommands.GetElementFromADatabaseByIdCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.Element;


/**
 * Abstract class whose subclasses' instances are {@link StringsToCommandsFactory
 * factories} that produce commands of type
 * {@link GetElementFromADatabaseByIdCommand}. Commands are {@link Callable}
 * instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class GetElementByIdentificationCommandsFactory< E extends Element >
		extends StringsToCommandsFactory< Optional< E > >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The array of strings with the names of the parameters needed to produce
	 * the command.
	 */
	private final String[] requiredParametersNames;
	
	/**
	 * The database where to get the elements from.
	 */
	private final Database< E > database;
	
	/**
	 * The identification of the element to get from {@link #database}.
	 */
	private String identification;
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link GetElementByIdentificationCommandFactory} that
	 * produces commands of type {@link GetElementFromADatabaseByIdCommand}.
	 * 
	 * @param commandsDescription
	 *            A short description of the commands produced by this factory.
	 * @param identificationParameterName
	 *            The name of the parameter (whose value is the element's
	 *            identification) to look for in the key-set of the {@link Map}
	 *            of parameters received in the method {@link #newInstance(Map)}
	 *            .
	 * @param database
	 *            The database where to get the elements from.
	 * @throws InvalidArgumentException
	 *             If either {@code commandsDescription},
	 *             {@code identificationParameterName} or {@code database} are
	 *             {@code null}.
	 */
	public GetElementByIdentificationCommandsFactory(
			String commandsDescription, String identificationParameterName,
			Database< E > database ) throws InvalidArgumentException {
		
		super( commandsDescription );
		
		if( identificationParameterName == null )
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null parameter name!" );
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null database!" );
		
		this.requiredParametersNames = new String[]{ identificationParameterName };
		this.database = database;
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Returns a command of type {@link GetElementFromADatabaseByIdCommand}.
	 * 
	 * @return A command of type {@link GetElementFromADatabaseByIdCommand}.
	 */
	@Override
	protected Callable< Optional< E >> internalNewInstance() {
		
		getIdValueOfTheParametersMap();
		try
		{
			return new GetElementFromADatabaseByIdCommand< E >( database,
					identification );
		}
		catch( InvalidArgumentException e )
		{ // never happens because database is not null
			return null;
		}
	}
	
	/**
	 * Returns an array of strings with name of the parameter needed to produce
	 * the command: the name of the parameter that contains the desired
	 * element's identification.
	 * 
	 * @return An array of strings with the name of the parameter that contains
	 *         the desired element's identification.
	 */
	@Override
	protected String[] getRequiredParameters() {
		
		return requiredParametersNames;
	}
	
	
	
	// AUXILIARY PRIVATE METHODS
	/**
	 * Sets the value of the field {@link #identification} with the value
	 * received in the parameters map.
	 * <p>
	 * If this method is called inside {@link #internalNewInstance(Map)} and
	 * this one is called inside
	 * {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that
	 * the field {@link #identification} is non-{@code null} after this method
	 * finishes its job.
	 * </p>
	 */
	private void getIdValueOfTheParametersMap() {
		
		identification = getParameterAsString( requiredParametersNames[0] );
	}
}
