package main.java.cli.parsingtools.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Abstract class whose subclasses' instances are factories that produce commands. Commands are
 * instances of {@link Callable}.
 * <p>
 * Method {@link #newCommand(Map)} receives a {@code Map<String,String>}, which is supposed to
 * contain the parameters needed to create the command: each map-entry represents a parameter, the
 * key is the parameter's name and the value is the parameter's value. This method confirms if the
 * Map contains all the parameters whose names are returned by the method
 * {@link #getRequiredParametersNames()} and then calls the method {@link #internalNewCommand(Map)}.
 * </p>
 * <p>
 * Each concrete subclass must implement:
 * <ul>
 * <li>the method {@link #getCommandsDescription()} which provides a short description of the
 * commands produced by factories of its type;</li>
 * <li>the method {@link #internalNewCommand()} which returns a command;</li>
 * <li>the method {@link #getRequiredParametersNames()} which returns a {@code String[]} whose
 * entries are the names of the parameters without whom the {@link #internalNewCommand()} method
 * cannot create a specific {@link Callable} command instance.</li>
 * </ul>
 * </p>
 * 
 * @param <T>
 *            The type of the results returned by the method {@link Callable#call()} of the commands
 *            produced by this {@link CommandFactory}.
 * 
 * @see Callable
 * @see Map
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class CommandFactory< T > {
    
    
    // PUBLIC METHOD
    
    /**
     * Produces a command.
     * <p>
     * It starts by validating the received parameters (seeing if there are required parameters
     * missing in {@link parametersMap}, interpreting the parameters into their correct type,
     * authenticating users passwords, etc) and returns a {@link Callable} instance ready to be
     * executed (called).
     * </p>
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * @return A command.
     * 
     * @throws InvalidArgumentException
     *             If {@code parametersMap} is null.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid
     *             (e.g. is not convertible to the expected type).
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws NoSuchElementInDatabaseException
     *             For the commands that need a user to be executed if there is no user in
     *             {@link #usersDatabase} whose username is the login name receive in the parameters
     *             map. The message of this exception is <i>«{login name} not found in
     *             {@code usersDatabase.getDatabaseName()}»</i>.
     * @throws WrongLoginPasswordException
     *             If the login password received does not match the login username's password.
     */
    public final Callable< T > newCommand( Map< String, String > parametersMap )
        throws NoSuchElementInDatabaseException, InvalidArgumentException,
        MissingRequiredParameterException, InvalidParameterValueException,
        WrongLoginPasswordException {
    
        /* Uses TEMPLATE METHOD design pattern */
        
        if( parametersMap == null )
            throw new InvalidArgumentException( "parametersMap CANNOT BE NULL!" );
        checkIfAllRequiredParametersAreInTheParametersMap( parametersMap );
        return internalNewCommand( parametersMap );
    }
    
    
    
    // ABSTRACT METHODS - to be implemented by concrete factories
    
    /**
     * Returns a short description of the commands produced by this factory.
     * 
     * @return a short description of the commands produced by this factory.
     */
    public abstract String getCommandsDescription();
    
    /**
     * Produces a command.
     * <p>
     * It starts by validating the received parameters (interpreting the parameters received in the
     * {@link parametersMap} into their correct type, authenticating users passwords, etc) and
     * returns a {@link Callable} instance ready to be executed (called).
     * </p>
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command. *
     * @return A command.
     * 
     * @throws InvalidArgumentException
     *             If {@code parametersMap} is null.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid
     *             (e.g. is not convertible to the expected type).
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws NoSuchElementInDatabaseException
     *             For the commands that need a user to be executed if there is no user in
     *             {@link #usersDatabase} whose username is the login name receive in the parameters
     *             map. The message of this exception is <i>«{login name} not found in
     *             {@code usersDatabase.getDatabaseName()}»</i>.
     * @throws WrongLoginPasswordException
     *             If the login password received does not match the login username's password.
     */
    protected abstract Callable< T > internalNewCommand( Map< String, String > parametersMap )
        throws MissingRequiredParameterException, InvalidParameterValueException,
        NoSuchElementInDatabaseException, WrongLoginPasswordException, InvalidArgumentException;
    
    /**
     * Returns an array of {@link String}s with the names of the parameters without whom the
     * {@link #newCommand(Map)} method cannot create a command. If no parameter is required to
     * create the command, it is returned {@code null}.
     * 
     * @return An array with the names of the parameters without whose the {@link #newInstance()}
     *         method cannot create a command.
     */
    protected abstract String[] getRequiredParametersNames();
    
    
    
    // AUXILIARY PRIVATE METHOD - used in the newCommand method!
    
    /**
     * Checks whether the required parameters for performing the {@link #newInstance()} method
     * (known through the method {@link #getRequiredParametersNames()}) are contained in the
     * {@link Map} received in the method {@link #newCommand(Map)}. If all required parameters were
     * found or there are no required parameters, this method returns nothing. If any of the
     * required parameters is missing, an exception is thrown indicating in its message the name of
     * the first required parameter not found.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     */
    private void
            checkIfAllRequiredParametersAreInTheParametersMap( Map< String, String > parametersMap )
                throws MissingRequiredParameterException {
    
        String[] requiredParameterNames = getRequiredParametersNames();
        if( requiredParameterNames == null )
            return;
        
        for( String name : requiredParameterNames )
            if( !parametersMap.containsKey( name ) )
                throw new MissingRequiredParameterException( name );
    }
}
