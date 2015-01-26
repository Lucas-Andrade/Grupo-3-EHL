package main.java.cli.parsingtools.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.utils.StringUtils;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose subclasses' instances are commands factories. A commands factory interprets
 * string-parameters needed to create commands and creates the commands (through method
 * {@link #newInstance(Map)}). It also provides a short description of the commands produced
 * (through method {@link #getCommandsDescription()}).
 * <p>
 * Subclasses must implement:
 * <ul>
 * <li>a constructor which provides a string description of the commands that the factory produces;</li>
 * <li>the method {@link #internalNewInstance()} which returns a command;</li>
 * <li>the method {@link #getRequiredParametersNames()} which returns a {@code String[]} whose
 * entries are the names of the parameters without whom the {@link #internalNewInstance()} method
 * cannot create a specific {@link Callable} command instance.</li>
 * </ul>
 * Method {@link #newInstance(Map)} receives a {@code Map<String,String>}, which is supposed to
 * contain the parameters needed to create the command: each map-entry represents a parameter, the
 * key is the parameter's name and the value is the parameter's value. This method confirms if the
 * Map contains the parameters returned by {@link #getRequiredParametersNames()}
 * </p>
 * 
 * @param <R>
 *            The {@link Callable} instance type of the command returned by the
 *            {@link #newInstance(Map)} method.
 * 
 * @see Callable
 * @see Map
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class StringsToCommandsFactory< R > {
    
    
    
    // INSTANCE FIELDS
    
    /**
     * {@code parametersMap} - {@link Map} containing all the name-value pairs of parameters
     * received to create and execute a specific command.
     */
    protected Map< String, String > parametersMap;
    
    /**
     * {@code commandsDescription} - A short description of the command produced by this factory.
     */
    private final String commandsDescription;
    
    
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link StringsToCommandsFactory factory} that produces a command specific
     * command whose action is described in {@code commandDescription}.
     * 
     * @param commandDescription
     *            - A short description of the command produced by this factory.
     * 
     * @throws InvalidArgumentException
     *             If the {@code commandDescription} is {@code null}.
     */
    public StringsToCommandsFactory( String commandDescription ) throws InvalidArgumentException {
        
        if( commandDescription == null )
            throw new InvalidArgumentException( "Cannot instantiate factory without description!" );
        
        this.commandsDescription = commandDescription;
    }
    
    
    
    // PUBLIC METHODS
    
    /**
     * @return The description of the commands produced by this factory.
     */
    public String getCommandsDescription() {
        
        return commandsDescription;
    }
    
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
    public final Callable< R > newInstance( Map< String, String > parameters )
        throws NoSuchElementInDatabaseException, InternalErrorException, InvalidArgumentException,
        MissingRequiredParameterException, InvalidParameterValueException,
        WrongLoginPasswordException {
        
        this.parametersMap = parameters;
        
        checkIfAllRequiredParametersAreInTheParametersMap( getRequiredParametersNames() );
        
        return internalNewInstance();
    }
    
    
    
    // AUXILIARY PRIVATE METHOD - used in the newInstance method!
    
    /**
     * Checks whether the required parameters for performing the {@link #newInstance()} method
     * (known through the method {@link #getRequiredParametersNames()}) are contained in the
     * received {@link Map}. If all required parameters were found in the map or there are no
     * required parameters, this method returns nothing. If a required parameter is missing, an
     * exception is thrown indicating in its message the name of the first required parameter not
     * found.
     * 
     * @param requiredParameterNames
     *            - The names(that correspond to the {@code parametersMap} keys) of the required
     *            parameters.
     * @throws MissingRequiredParameterException
     *             If a required parameter is missing.
     */
    private void
            checkIfAllRequiredParametersAreInTheParametersMap( String... requiredParameterNames )
                throws MissingRequiredParameterException {
        
        if( requiredParameterNames == null ) {
            return;
        }
        
        for( String name : requiredParameterNames )
            if( !parametersMap.containsKey( name ) )
                throw new MissingRequiredParameterException( name );
    }
    
    
    
    // UNIMPLEMENTED AUXILIARY METHODS - to be implemented by the factories!
    
    /**
     * Method responsible for produces a command and returns it to the {@link #newInstance()}
     * method.
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
    protected abstract Callable< R > internalNewInstance()
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
    
    
    
    // PROTECTED AUXILIARY METHODS - tools for the factories!
    
    /**
     * Returns the string-value of the entry in {@link #parametersMap} with key
     * {@code parameterName}.
     * 
     * @param parameterName
     *            The name of the parameter.
     * 
     * @return The string-value of the entry in {@link #parametersMap} with key
     *         {@code parameterName}.
     */
    protected String getParameterAsString( String parameterName ) {
        
        return parametersMap.get( parameterName );
    }
    
    /**
     * Converts the string-value of the entry in {@link #parametersMap} with key
     * {@code parameterName} into an {@link Integer}. (Through this documentation, the string-value
     * will be referred as "{@code theValue}")
     * 
     * @param parameterName
     *            The name of the parameter.
     * 
     * @return The {@link Integer} representation of the string {@code theValue}.
     * 
     * @throws InvalidParameterValueException
     *             If the parameter's value is not convertible into an {@code Integer} (e.g. if it
     *             contains letters). This exception's message is <i>«Required parameter with name
     *             {@code parameterName} has invalid value {@code theValue}.»</i>.
     * @throws MissingRequiredParameterException
     *             If the parameter's value is {@code null} or the empty string. This exception's
     *             message is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    protected int getParameterAsInt( String parameterName )
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        return StringUtils.parameterToInteger( parameterName, parametersMap.get( parameterName ) );
    }
    
    /**
     * Converts the string-value of the entry in {@link #parametersMap} with key
     * {@code parameterName} into a {@link Double}. (Through this documentation, the string-value
     * will be referred as "{@code theValue}")
     * 
     * @param parameterName
     *            The name of the parameter.
     * 
     * @return The {@link Double} representation of the string {@code theValue}.
     * 
     * @throws InvalidParameterValueException
     *             If the parameter's value is not convertible into a {@code Double} (e.g. if it
     *             contains letters). This exception's message is <i>«Required parameter with name
     *             {@code parameterName} has invalid value {@code theValue}.»</i>.
     * @throws MissingRequiredParameterException
     *             If the parameter's value is {@code null} or the empty string. This exception's
     *             message is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    protected Double getParameterAsDouble( String parameterName )
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        return StringUtils.parameterToDouble( parameterName, parametersMap.get( parameterName ) );
    }
    
    /**
     * Converts the string-value of the entry in {@link #parametersMap} with key
     * {@code parameterName} into a {@code boolean}. (Through this documentation, the string-value
     * will be referred as "{@code theValue}")
     * 
     * @param parameterName
     *            The name of the parameter.
     * 
     * @return {@code true} if {@code theValue} is equal, ignore case, to {@code "true"},
     *         {@code "yes"}, {@code "y"} or {@code "1"}; <br/>
     *         {@code false} if {@code theValue} is equal, ignore case, to {@code "false"},
     *         {@code "no"}, {@code "n"} or {@code "0"}.
     * 
     * @throws InvalidParameterValueException
     *             If {@code theValue} is not equal, ignore case, to {@code "true"}, {@code "yes"},
     *             {@code "y"}, {@code "1"}, {@code "false"}, {@code "no"}, {@code "n"} or
     *             {@code "0"}. This exception's message is <i>«Required parameter with name
     *             {@code parameterName} has invalid value {@code theValue}.»</i>.
     * @throws MissingRequiredParameterException
     *             If {@code theValue} is {@code null} or the empty string. This exception's message
     *             is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    protected boolean getParameterAsBoolean( String parameterName )
        throws InvalidParameterValueException, MissingRequiredParameterException {
        
        return StringUtils.parameterToBoolean( parameterName, parametersMap.get( parameterName ) );
    }
    
    
}