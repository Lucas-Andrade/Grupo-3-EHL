package main.java.cli.parsingtools.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * TODO
 * 
 * Class whose subclasses' instances factories that produce commands. It also provides a short
 * description of the commands produced (through method {@link #getCommandsDescription()}).
 * <p>
 * Subclasses must implement:
 * <ul>
 * <li>a constructor which provides a string description of the commands that the factory produces;</li>
 * <li>the method {@link #internalNewCommand()} which returns a command;</li>
 * <li>the method {@link #getRequiredParametersNames()} which returns a {@code String[]} whose
 * entries are the names of the parameters without whom the {@link #internalNewCommand()} method
 * cannot create a specific {@link Callable} command instance.</li>
 * </ul>
 * Method {@link #newCommand(Map)} receives a {@code Map<String,String>}, which is supposed to
 * contain the parameters needed to create the command: each map-entry represents a parameter, the
 * key is the parameter's name and the value is the parameter's value. This method confirms if the
 * Map contains the parameters returned by {@link #getRequiredParametersNames()}
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
     * Method responsible for producing a command. It starts by performing validating the received
     * parameters (seeing if there are required parameters missing in the map, interpreting the
     * parameters into their correct type, authenticating users passwords, etc) and returns a
     * {@link Callable} instance of the command ready to be executed (called).
     * 
     * @param parameters
     *            - The {@link Map} containing all the name-value pairs of parameters needed to
     *            create the {@link Callable} command instance.
     * 
     * @return An instance of {@code Callable<R>}.
     * 
     * @throws InvalidArgumentException
     *             If the {@code parameters} Map is null or if one or more of the parameter received
     *             is invalid. (e.g. is not convertible to the expected type).
     * @throws NoSuchElementInDatabaseException
     *             For the commands that need a user to be executed if there is no user in
     *             {@link #usersDatabase} whose username is the login name receive in the parameters
     *             map. The message of this exception is <i>«{login name} not found in
     *             {@code usersDatabase.getDatabaseName()}»</i>.
     * @throws InternalErrorException
     *             If an internal error occurred (not supposed to happen).
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws WrongLoginPasswordException
     *             For the commands that need a user to be executed if the login password received
     *             does not match the user's password corresponding to the login username received.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid.
     */
    public final Callable< T > newCommand( Map< String, String > parametersMap )
        throws NoSuchElementInDatabaseException, InternalErrorException, InvalidArgumentException,
        MissingRequiredParameterException, InvalidParameterValueException,
        WrongLoginPasswordException {
        /* Uses TEMPLATE METHOD design pattern */
        
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
     * Produces a command and returns it to the {@link #newInstance()} method.
     * 
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter invalid
     *             (e.g. is not convertible to the expected type).
     * @throws WrongLoginPasswordException
     *             If the login password received does not match the login username's password.
     * @throws NoSuchElementInDatabaseException
     *             If there is no user in {@link #usersDatabase} whose username is the login name
     *             receive in the parameters map. The message of this exception is <i>«{login name}
     *             not found in {@code usersDatabase.getDatabaseName()}»</i>.
     * @throws InternalErrorException
     *             If an internal error that wasn't supposed to happen happened.
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws InvalidArgumentException
     *             If the value received in the parameters map for a required parameter is invalid.
     */
    protected abstract Callable< T > internalNewCommand( Map< String, String > parametersMap )
        throws InvalidParameterValueException, WrongLoginPasswordException,
        NoSuchElementInDatabaseException, InternalErrorException,
        MissingRequiredParameterException, InvalidArgumentException;
    
    /**
     * Returns an array of {@link String}s with the names of the parameters without whom the
     * {@link #newInstance()} method cannot create a specific {@link Callable} command instance.
     * 
     * @return An array with the names of the parameters without whose the {@link #newInstance()}
     *         method cannot create a specific {@link Callable} command instance.
     */
    protected abstract String[] getRequiredParametersNames();
    
    
    
    // AUXILIARY PRIVATE METHOD - used in the newCommand method!
    
    /**
     * Checks whether the required parameters for performing the {@link #newInstance()} method
     * (known through the method {@link #getRequiredParametersNames()}) are contained in the
     * {@link Map} received in the method {@link #newCommand(Map)}. If all required parameters were
     * found in the map or there are no required parameters, this method returns nothing. If a
     * required parameter is missing, an exception is thrown indicating in its message the name of
     * the first required parameter not found.
     * 
     * @param requiredParameterNames
     *            - The names(that correspond to the {@code parametersMap} keys) of the required
     *            parameters.
     * @throws MissingRequiredParameterException
     *             If a required parameter is missing.
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
