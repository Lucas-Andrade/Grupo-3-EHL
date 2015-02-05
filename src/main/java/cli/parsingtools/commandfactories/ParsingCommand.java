package main.java.cli.parsingtools.commandfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.utils.StringUtils;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;


/**
 * Abstract class whose subclasses' instances interpret string-parameters needed to create commands
 * and produce commands. Commands are instances of {@link Callable}.
 * <p>
 * Method {@link #newCommand()} uses the {@code Map<String,String>} received in the constructor,
 * which is supposed to contain the parameters needed to create the command: each map-entry
 * represents a parameter, the key is the parameter's name and the value is the parameter's value.
 * This method starts by converting the string-parameters needed to create commands into
 * strongly-typed parameters and creates a command with those parameters.
 * </p>
 * 
 * @param <R>
 *            The type of the results returned by the method {@link Callable#call()} of the commands
 *            produced by this {@link ParsingCommand}.
 * 
 * @see Callable
 * @see Map
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class ParsingCommand< R > {
    
    
    // INSTANCE FIELD
    /**
     * The container of the parameters required to create the command.
     */
    protected Map< String, String > parametersMap;
    
    // CONSTRUCTOR
    /**
     * Creates a new {@link ParsingCommand}.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command.
     */
    public ParsingCommand( Map< String, String > parametersMap ) {
    
        this.parametersMap = parametersMap;
    }
    
    // ABSTRACT METHOD
    /**
     * Produces a command.
     * 
     * @return A command.
     * 
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid
     *             (e.g. is not convertible to the expected type).
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     */
    protected abstract Callable< R > newCommand()
        throws MissingRequiredParameterException, InvalidParameterValueException;
    
    
    
    // PROTECTED METHODS - tools for getting parameters from the parametersMap
    
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
