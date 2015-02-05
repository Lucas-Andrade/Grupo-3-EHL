package main.java.cli.parsingtools.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.utils.StringUtils;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * TODO
 * 
 * Class whose subclasses' instances interpret string-parameters needed to create commands and
 * create the commands (through method {@link #newCommand(Map)}).
 * 
 * @param <R>
 *            The type of the results returned by the method {@link Callable#call()} of the commands
 *            produced by this {@link CommandFactory}.
 * 
 * @see Callable
 * @see Map
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class ParsingCommand< R > {
    
    
    // INSTANCE FIELD
    /**
     * {@code parametersMap} - {@link Map} containing all the name-value pairs of parameters
     * received to create and execute a specific command.
     */
    protected Map< String, String > parametersMap;
    
    // CONSTRUCTOR
    /**
     * Creates a {@code parsing command}, that interprets string-parameters needed to create
     * commands and creates the commands (through method {@link #newCommand(Map)}).
     * 
     * @param parametersMap
     */
    public ParsingCommand( Map< String, String > parametersMap ) {
        this.parametersMap = parametersMap;
    }
    
    // ABSTRACT METHOD
    /**
     * Returns a {@link Callable}.
     * 
     * @return A {@link Callable}.
     */
    public abstract Callable< R > newCommand();
    
    // PROTECTED METHODS
    
    /**
     * Returns the string-value of the entry in {@link #parametersMap} with key
     * {@code parameterName}.
     * 
     * @param parameterName
     *            The name of the parameter.
     * 
     * @return The string-value of the entry in {@link #parametersMap} with key
     *         {@code parameterName}.
     * @throws MissingRequiredParameterException
     *             If {@code parameterName} is {@code null} or the empty string. This exception's
     *             message is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    protected String getParameterAsString( String parameterName )
        throws MissingRequiredParameterException {
        
        return StringUtils.parameterToString( parameterName, parametersMap.get( parameterName ) );
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
