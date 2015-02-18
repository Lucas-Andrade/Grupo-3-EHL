package utils;


import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;


/**
 * Class whose static methods provide tools for handling {@link String} values. There are:
 * <ul>
 * <li>methods that convert parameters' values into</li>
 * </ul>
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class StringUtils {
    
    
    /**
     * Unused private constructor
     */
    private StringUtils() {
    
    };
    
    
    // STATIC METHODS
    
    /**
     * Converts the string {@code parameterValue} into a {@code boolean}. The string
     * {@code parameterValue} is the value of a parameter with name {@code parameterName}.
     * 
     * @param parameterName
     *            The name of the parameter, to appear in exceptions' messages if any occurs.
     * @param parameterValue
     *            The value of the parameter, the value to be converted.
     * 
     * @return {@code true} if {@code parameterValue} is equal, ignore case, to {@code "true"},
     *         {@code "yes"}, {@code "y"} or {@code "1"}; <br/>
     *         {@code false} if {@code parameterValue} is equal, ignore case, to {@code "false"},
     *         {@code "no"}, {@code "n"} or {@code "0"}.
     * 
     * @throws InvalidParameterValueException
     *             If {@code parameterValue} is not equal, ignore case, to {@code "true"},
     *             {@code "yes"}, {@code "y"}, {@code "1"}, {@code "false"}, {@code "no"},
     *             {@code "n"} or {@code "0"}. This exception's message is <i>«Required parameter
     *             with name {@code parameterName} has invalid value {@code parameterValue} .»</i>.
     * @throws MissingRequiredParameterException
     *             If {@code parameterValue} is {@code null} or the empty string. This exception's
     *             message is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    public static boolean parameterToBoolean( String parameterName, String parameterValue )
        throws InvalidParameterValueException, MissingRequiredParameterException {
    
        if( parameterValue == null || parameterValue.equals( "" ) )
            throw new MissingRequiredParameterException( parameterName );
        
        if( parameterValue.equalsIgnoreCase( "true" ) || parameterValue.equalsIgnoreCase( "yes" )
            || parameterValue.equalsIgnoreCase( "y" ) || parameterValue.equals( "1" ) )
            return true;
        
        if( parameterValue.equalsIgnoreCase( "false" ) || parameterValue.equalsIgnoreCase( "no" )
            || parameterValue.equalsIgnoreCase( "n" ) || parameterValue.equals( "0" ) )
            return false;
        
        throw new InvalidParameterValueException( parameterName, parameterValue );
    }
    
    
    /**
     * Converts the string {@code parameterValue} into a {@link Double}. The string
     * {@code parameterValue} is the value of a parameter with name {@code parameterName}.
     * 
     * @param parameterName
     *            The name of the parameter, to appear in exceptions' messages if any occurs.
     * @param parameterValue
     *            The value of the parameter, the value to be converted.
     * 
     * @return The {@link Double} in the string {@code parameterValue}.
     * 
     * @throws InvalidParameterValueException
     *             If {@code parameterValue} is not convertible into a {@code Double} (e.g. if it
     *             contains letters). This exception's message is <i>«Required parameter with name
     *             {@code parameterName} has invalid value {@code parameterValue}.»</i>.
     * @throws MissingRequiredParameterException
     *             If {@code parameterValue} is {@code null} or the empty string. This exception's
     *             message is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    public static Double parameterToDouble( String parameterName, String parameterValue )
        throws InvalidParameterValueException, MissingRequiredParameterException {
    
        try {
            if( parameterValue.equals( "" ) )
                throw new MissingRequiredParameterException( parameterName );
            
            return Double.valueOf( parameterValue );
        }
        catch( NullPointerException e ) {
            throw new MissingRequiredParameterException( parameterName, e );
        }
        catch( NumberFormatException e ) {
            throw new InvalidParameterValueException( parameterName, parameterValue, e );
        }
    }
    
    
    /**
     * Converts the string {@code parameterValue} into an {@link Integer}. The string
     * {@code parameterValue} is the value of a parameter with name {@code parameterName}.
     * 
     * @param parameterName
     *            The name of the parameter, to appear in exceptions' messages if any occurs.
     * @param parameterValue
     *            The value of the parameter, the value to be converted.
     * 
     * @return The {@link Integer} in the string {@code parameterValue}.
     * 
     * @throws InvalidParameterValueException
     *             If {@code parameterValue} is not convertible into an {@code Integer} (e.g. if it
     *             contains letters). This exception's message is <i>«Required parameter with name
     *             {@code parameterName} has invalid value {@code parameterValue}.»</i>.
     * @throws MissingRequiredParameterException
     *             If {@code parameterValue} is {@code null} or the empty string. This exception's
     *             message is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    public static Integer parameterToInteger( String parameterName, String parameterValue )
        throws InvalidParameterValueException, MissingRequiredParameterException {
    
        if( parameterValue == null || parameterValue.equals( "" ) )
            throw new MissingRequiredParameterException( parameterName );
        
        try {
            return Integer.valueOf( parameterValue );
        }
        catch( NumberFormatException e ) {
            throw new InvalidParameterValueException( parameterName, parameterValue, e );
        }
    }
    
    
    /**
     * Checks if the string {@code parameterValue} is non-{@code null} and non-empty. The string
     * {@code parameterValue} is the value of a parameter with name {@code parameterName}.
     * 
     * @param parameterName
     *            The name of the parameter, to appear in exceptions' messages if any occurs.
     * @param parameterValue
     *            The value of the parameter, the value to be evaluated.
     * 
     * @return The non-{@code null} and non-empty string {@code parameterValue}.
     * 
     * @throws MissingRequiredParameterException
     *             If {@code parameterValue} is {@code null} or the empty string. This exception's
     *             message is <i>«Required parameter with name {@code parameterName} missing.»</i>).
     */
    public static String parameterToString( String parameterName, String parameterValue )
        throws MissingRequiredParameterException {
    
        if( parameterValue == null || parameterValue.equals( "" ) )
            throw new MissingRequiredParameterException( parameterName );
        
        return parameterValue;
    }
    
    
    /**
     * Insert line breaks to the given {@code string} such that each line have at maximum
     * {@code maxLineSize} characters.
     * 
     * @param string
     *            - The String to parser.
     * @param maxLineSize
     *            - The Maximum number of characters by line.
     * @return the parsed {@code string}
     */
    public static String errorStringParser( String string, int maxSize ) {
  
        
       StringBuilder result = new StringBuilder(); 
       String[] split = string.split( "\n" );
       for(String element:split){         
            
           result.append("\n").append( spliter(element,maxSize));
       }
       result.deleteCharAt( 0 );
    return result.toString();
               
    }  
      
    private static String spliter(String string,int maxSize){
        
        StringBuilder copy = new StringBuilder( string );
        
        int length = string.length();
        int index;
        int splitBeforeIndex = maxSize;
      
        
        while( splitBeforeIndex <= length ) {
            index = copy.lastIndexOf( " ", splitBeforeIndex );
            if( index + maxSize < splitBeforeIndex ) {
                index = splitBeforeIndex - 1;
                copy.replace( index, index, "-\r\n" );
                index += 3;
                length += 3;
            }
            else {
                copy.replace( index, index + 1, "\r\n" );
                index += 2;
                length += 2;
            }
            splitBeforeIndex = index + maxSize;
        }
        return copy.toString();
    }
}
