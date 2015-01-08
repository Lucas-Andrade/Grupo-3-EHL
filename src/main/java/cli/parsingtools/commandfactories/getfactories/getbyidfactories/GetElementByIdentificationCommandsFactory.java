package main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories;

import java.util.Map;
import java.util.concurrent.Callable;

import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.domain.commands.getcommands.GetElementFromADatabaseByIdCommand;
import main.java.domain.model.Database;
import main.java.domain.model.Element;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Abstract class whose subclasses' instances are {@link StringsToCommandsFactory factories} that
 * produce commands of type {@link GetElementFromADatabaseByIdCommand}. Commands are
 * {@link Callable} instances.
 * 
 * Extends {@link StringsToCommandsFactory} of {@link Optional Optional<E>}
 * 
 * @param <E>
 *            - The type of elements that a database can contain.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class GetElementByIdentificationCommandsFactory<E extends Element> extends
		StringsToCommandsFactory<Optional<E>> {

	// INSTANCE FIELDS

	/**
	 * {@code requiredParametersNames} - The array of strings with the names of the parameters
	 * needed to produce the command.
	 */
	private final String[] requiredParametersNames;

	/**
	 * {@code database} - The database where to get the elements from.
	 */
	private final Database<E> database;

	/**
	 * {@code identification} - The identification of the element to get from {@link #database}.
	 */
	private String identification;

	// CONSTRUCTOR

	/**
	 * Creates a new {@link GetElementByIdentificationCommandFactory} that produces commands of type
	 * {@link GetElementFromADatabaseByIdCommand}.
	 * 
	 * @param commandsDescription
	 *            - A short description of the command produced by this factory.
	 * @param identificationParameterName
	 *            - The name of the parameter (whose value is the element's identification) to look
	 *            for in the key-set of the {@link Map} of parameters received in the method
	 *            {@link #newInstance(Map)} .
	 * @param database
	 *            - The database where to get the elements from.
	 * 
	 * @throws InvalidArgumentException
	 *             If either {@code commandsDescription}, {@code identificationParameterName} or
	 *             {@code database} are {@code null}.
	 */
	public GetElementByIdentificationCommandsFactory(String commandsDescription,
			String identificationParameterName, Database<E> database)
			throws InvalidArgumentException {

		super(commandsDescription);

		if (identificationParameterName == null)
			throw new InvalidArgumentException(
					"Cannot instantiate factory with null parameter name!");

		if (database == null)
			throw new InvalidArgumentException("Cannot instantiate factory with null database!");

		this.requiredParametersNames = new String[] {identificationParameterName};
		this.database = database;
	}

	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory

	/**
	 * Returns a command of type {@link GetElementFromADatabaseByIdCommand} after getting the
	 * necessary {@code required parameters} using the private auxiliar method
	 * {@link #setIdValueOfTheParametersMap()}.
	 * 
	 * @return A command of type {@link GetElementFromADatabaseByIdCommand}.
	 */
	@Override
	protected Callable<Optional<E>> internalNewInstance() {

		setIdValueOfTheParametersMap();

		try {
			return new GetElementFromADatabaseByIdCommand<E>(database, identification);

		} catch (InvalidArgumentException e) { // never happens because database is not null
			return null;
		}
	}

	/**
	 * Returns an array of strings with name of the parameter needed to produce the command- in this
	 * case the name of the parameter that contains the desired element's identification.
	 * 
	 * @return An array of strings with the name of the required parameters.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return requiredParametersNames;
	}

	// PRIVATE AUXILIAR METHOD

	/**
	 * Sets the value of the field {@link #identification} with the value received in the parameters
	 * map.
	 * <p>
	 * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn, this
	 * last one is called inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed
	 * that the field {@link #identification} is non-{@code null} after this method finishes its
	 * job.
	 * </p>
	 */
	private void setIdValueOfTheParametersMap() {

		identification = getParameterAsString(requiredParametersNames[0]);
	}
}