package main.java.cli.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;


/**
 * A {@link FactoryOfParametrizedCallables factory} that creates
 * {@link Callable} instances.
 * <p>
 * Implementors must define the method {@link #newInstance(Map)} which returns a
 * {@link Callable} instance. The {@link Map} received contains the parameters
 * needed to create the {@link Callable} instance.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <R>
 *            The type parameter of the {@link Callable} instance returned by
 *            the method {@link #newInstance(Map)} ('R' for result, since the
 *            method {@link Callable#call()} will return an object of type
 *            {@code R}).
 * @param <K>
 *            The type of the keys that are received in the {@link Map} with the
 *            parameters.
 * @param <V>
 *            The type of the values that are received in the {@link Map} with
 *            the parameters.
 * @see Callable
 * @see Map
 */
public interface CallablesFactory< R, K, V >
{
	
	/**
	 * Returns a {@link Callable} instance whose method {@link Callable#call()}
	 * returns either a result of type {@code R} or a {@link Exception checked
	 * exception}. The {@link Map} received contains the parameters needed to
	 * create the {@link Callable} instance.
	 * 
	 * @param parameters
	 *            The parameters needed to create the instance to return.
	 * @return A {@link Callable} instance whose method {@link Callable#call()}
	 *         returns either a result of type {@code R} or a {@link Exception
	 *         checked exception}.
	 * @throws MissingRequiredParameterException
	 *             If {@code parameters} lacks some parameteres needed to create
	 *             the new instance.
	 * @throws InvalidParameterValueException
	 *             If the value received in {@code parameters} for a required
	 *             parameter is not valid.
	 * @throws Exception
	 *             If the user who is posting is not in the
	 *             {@code postingUsersDatabase}. The concrete type of this
	 *             exception is {@link NoSuchElementInDatabaseException} and its
	 *             message is <i>«{login name} not found in {database's
	 *             name}.»</i>.
	 * @throws WrongLoginPasswordException
	 *             If the login password received does not match the login
	 *             username's password.
	 * 
	 */
	public Callable< R > newInstance( Map< K, V > parameters )
			throws MissingRequiredParameterException,
			InvalidParameterValueException, WrongLoginPasswordException,
			Exception;
}
