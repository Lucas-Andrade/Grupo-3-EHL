package utils;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import outputformatters.Translatable;
import outputformatters.totranslatableconverters.ToTranslatableConverter;
import outputformatters.translators.ToHtmlTranslator;
import outputformatters.translators.ToJsonTranslator;
import outputformatters.translators.ToPlainTextTranslator;
import outputformatters.translators.Translator;
import parsingtools.CommandParser;
import utils.exceptions.formattersexceptions.UnknownTranslatableException;
import utils.exceptions.formattersexceptions.UnknownTypeException;
import utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import utils.exceptions.parsingexceptions.UnsupportedAcceptValueException;
import utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;
import com.google.gson.Gson;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


/**
 * Class whose instances are responsible for interpreting string-commands and returning its output
 * and the stream where to write the output to.
 * <p>
 * Each instance is bound to a concrete string-command and to a {@link CommandParser} instance. The
 * public methods of this class allow getting the {@link Callable command}, {@link Translator output
 * translator} and {@link PrintStream stream} coded in the string-command. The register of the
 * received {@link CommandParser} should contain the given string-command.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @see CommandParser
 * @see StringCommandsDictionary
 * @see Translator
 */
public class StringCommandsExecutor implements Executor {
    
    /* Implementation notes:
     * 
     * - This class will break the Open-Closed Principle (see SOLID principles); every time a new
     * Translator is created, a new entry has to be added to the static field
     * TRANSLATORS.
     * 
     * -This class makes use of the static fields in CommandStrings_Dictionary. 
     */
    
    
    
    // CLASS FIELD AND CONSTRUCTOR
    
    /**
     * The mapping between strings that may be contained in the string-command and the
     * {@link Translator} instance they correspond to (uses the {@link StringCommandsDictionary}).
     */
    public static final Map< String, Translator > TRANSLATORS = new HashMap< String, Translator >();
    
    /**
     * The list of the methods ({@code args[0]}) of string-commands that support the output
     * customization settings coded in the parameters with names
     * {@link StringCommandsDictionary#ACCEPT} and {@link StringCommandsDictionary#STREAM}.
     */
    public static final List< String > methodsThatSupportOutputCustomization = new ArrayList<>();
    
    /**
     * Initializes the fields {@link #TRANSLATORS} and {@link #methodsThatSupportAcceptAndStream} .
     */
    static {
        
        /*TRANSLATORS*/
        try {
            TRANSLATORS.put( StringCommandsDictionary.HTML, new ToHtmlTranslator( 5 ) );
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN Parser!" );
            // never happens because argument 5 is bigger than 1
        }
        TRANSLATORS.put( StringCommandsDictionary.TEXT, new ToPlainTextTranslator() );
        TRANSLATORS.put( StringCommandsDictionary.JSON, new ToJsonTranslator() );
        
        /*methodsThatSupportOutputCustomization*/
        methodsThatSupportOutputCustomization.add( "GET" );
        methodsThatSupportOutputCustomization.add( "OPTION" );
    }
    
    
    
    // INSTANCE FIELDS
    
    /**
     * The string-command (received in the constructor). This array with 2 or 3 entries contains the
     * string command's
     * <ul>
     * <li>method (in the entry of index 0),</li>
     * <li>path (in the entry of index 1),</li>
     * <li>parameters-list (optionally, in the entry of index 2).</li>
     * </ul>
     */
    private final String[] args;
    
    /**
     * The command parser (received in the constructor) that should recognize {@link #args} and
     * translate it into a {@link Callable command}.
     */
    private final CommandParser cmdParser;
    
    /**
     * A unmodifiable map of parameters created from the parameters-list ({@code args[2]}, if
     * existent). If {@link #args} has only two entries, this map is empty. Otherwise, if the
     * parameters-list was written with the correct syntax, this {@link Map}'s keys are the names of
     * the parameters and its values are the corresponding parameters' values.
     */
    private final Map< String, String > parametersMap;
    
    
    
    // CONSTRUCTOR
    /**
     * Creates a new instance of {@link StringCommandsExecutor} bound to a concrete string-command
     * and to a {@link CommandParser} instance. The method and path received in {@code args} (its
     * first and second entries) should match the method and path of one of the commands registered
     * in {@code cmdParser}.
     * <p>
     * To know more about string-commands correct syntax, see {@link CommandParser}'s documentation.
     * </p>
     * 
     * @param cmdParser
     *            The command parser whose register should contain the given string-command.
     * @param args
     *            The string-command. This array with 2 or 3 entries contains the string command's
     *            <ul>
     *            <li>method (in the entry of index 0),</li>
     *            <li>path (in the entry of index 1),</li>
     *            <li>parameters-list (optionally, in the entry of index 2)</li>
     *            </ul>
     * @throws InvalidArgumentException
     *             If {@code cmdParser==null}.
     * @throws InvalidCommandSyntaxException
     *             If {@code args}' length is smaller than 2 or bigger than 3.
     * @throws InvalidCommandParametersSyntaxException
     *             If the parameters in {@code args[2]} (if existent) are not correctly separated by
     *             '{@code &}' or a certain parameter has not the format {@code name=value}.
     * @throws DuplicateParametersException
     *             If several values for the same parameter have been received in the
     *             parameters-list.
     * @throws UnsupportedAcceptValueException
     *             If the {@code Accept} parameter value given is not suppoted by this application.
     * 
     * @see CommandParser
     */
    public StringCommandsExecutor( CommandParser cmdParser, String... args )
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidArgumentException, InvalidCommandSyntaxException, UnsupportedAcceptValueException {
    
        if( cmdParser == null )
            throw new InvalidArgumentException(
                                                "Cannot instantiate parser with null command parser." );
        if( args == null || args.length < 2 || args.length > 3 )
            throw new InvalidCommandSyntaxException(
                                                     "Commands must have either 1 or 2 space-characters." );
        
        this.args = args;
        this.cmdParser = cmdParser;
        this.parametersMap = getParametersFromParametersList();
    }
    
    
    
    // PUBLIC METHODS
    
    /**
     * Returns the string-ouput of the string-command received in the constructor.
     * 
     * @return The corresponding {@link Callable} instance.
     * @throws Exception
     *             If there was a problem with the data given for producing the command or in the
     *             execution of the command.
     */
    public String getOutput() throws Exception {
    
        Callable< ? > command = getCommand();
        String accept = findValueOf( StringCommandsDictionary.ACCEPT );
        if( accept != null && accept.equals( StringCommandsDictionary.JSON ) )
            return convertToGson( command );
        
        return convertUsingTranslators( command );
    }
    
    /**
     * Returns the stream correspondent to the value of the parameter with name
     * {@link StringCommandsDictionary#STREAM} received in the parameters-list of the
     * string-command; if this parameter wasn't in the parameters-list returns the
     * {@link PrintStream} {@link System#out}.
     * 
     * @return The stream correspondent to the value of the parameter with name
     *         {@link StringCommandsDictionary#STREAM} or<br />
     *         the stream {@link System#out} if no value was received.
     * @throws InvalidParameterValueException
     *             If the value of the parameter accept is unknown.
     */
    public OutputStream getStream() throws InvalidParameterValueException {
    
        String filePath = findValueOf( StringCommandsDictionary.STREAM );
        if( filePath == null || !supportsOutputCustomization() )
            return System.out;
        
        try {
            return new PrintStream( filePath );
        }
        catch( IOException e ) {
            throw new InvalidParameterValueException( StringCommandsDictionary.STREAM, filePath,
                                                      e.getMessage(), e );
        }
    }
    
    /**
     * Writes the string {@link #getOutput()} to the stream {@link #getStream()}.
     * 
     * @throws IOException
     * @throws Exception
     */
    public void writeOutputToStream() throws IOException, Exception {
    
        OutputStream stream = getStream();
        stream.write( getOutput().getBytes() );
        stream.flush();
        if( !stream.equals( System.out ) )
            stream.close();
    }
    
    
    
    // AUXILIARY PRIVATE METHOD
    
    // used in constructor
    /**
     * Interprets the string {@code parameters} that contains a parameters-list written with the
     * syntax
     * 
     * <pre>
     *  {@literal<}parameters-list> -> {@literal<}name>={@literal<}value>[&{@literal<}name>={@literal<}value>]*
     *  {@literal<}name> -> {@literal<}string>
     *  {@literal<}value> -> {@literal<}string>
     * </pre>
     * 
     * and produces a {@link Map} whose keys are the {@code name}s and whose values are the
     * {@code value}s.
     * 
     * @throws InvalidCommandParametersSyntaxException
     *             If the parameters are not correctly separated by '{@code &}' or a certain
     *             parameter has not the format <code>{@literal <}name>={@literal <}value></code>.
     * @throws DuplicateParametersException
     *             If several values for the same parameter have been received in the
     *             parameters-list.
     * @throws UnsupportedAcceptValueException
     *             If the {@code Accept} parameter value received is not supported by this
     *             application.
     */
    private Map< String, String > getParametersFromParametersList()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        UnsupportedAcceptValueException {
    
        Map< String, String > parametersMap = new HashMap< String, String >();
        if( args.length == 2 )
            return parametersMap;
        
        String[] parameters = args[2].split( "&" );
        for( String parameter : parameters ) {
            String[] nameAndValue = parameter.split( "=" );
            if( nameAndValue.length != 2 )
                throw new InvalidCommandParametersSyntaxException(
                                                                   "Invalid syntax in parameters-list!"
                                                                           + nameAndValue );
            if( nameAndValue[0] == "accept"
                && (!nameAndValue[1].equals( "text/html" )
                    || !nameAndValue[1].equals( "application/json" ) || !nameAndValue[1].equals( "text/plain" )) )
                throw new UnsupportedAcceptValueException( "Unsupported Accept" );
            
            String parameterName = nameAndValue[0];
            String parameterValue = nameAndValue[1];
            
            if( parametersMap.containsKey( parameterName ) )
                throw new DuplicateParametersException( parameterName );
            parametersMap.put( parameterName, parameterValue );
        }
        return parametersMap;
    }
    
    // used in getOutput
    /**
     * Returns the {@link Callable command} correspondent to the string-command received in the
     * constructor.
     * 
     * @return The corresponding {@link Callable} instance.
     * @throws InvalidArgumentException
     *             If {@code parameters==null}.
     * @throws InvalidCommandSyntaxException
     *             If {@code args.length} is not 2 or 3.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters-list for a required parameter is invalid.
     * @throws MissingRequiredParameterException
     *             If the received parameters-list does not contain one of the required parameters
     *             for instantiating the command.
     * @throws NoSuchElementInDatabaseException
     *             If there is no user in {@link #postingUsersDatabase} whose username is the login
     *             name receive in the parameters map. The message of this exception is <i>«{login
     *             name} not found in {@code postingUsersDatabase.getDatabaseName()}»</i>.
     * @throws RequiredParameterNotPresentException
     *             If some parameters needed to create the new instance are missing.
     * @throws UnknownCommandException
     *             If the given string-command wasn't registered.
     * @throws WrongLoginPasswordException
     *             If the login password received does not match the login username's password.
     */
    private Callable< ? > getCommand()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException {
    
        return cmdParser.getCommand( parametersMap, args );
    }
    
    // used in getOuput
    /**
     * Returns the result of {@code command.call()} in Json format.
     * 
     * @param command
     *            The command whose output is to be converted.
     * @return The result of {@code command.call()} in Json format.
     * @throws Exception
     *             If there was a problem in the execution of the command or there was received an
     *             exception result.
     */
    private String convertToGson( Callable< ? > command ) throws Exception {
    
        Object result = command.call();
        if( result instanceof Optional )
            result = ((Optional< ? >)result).get();
        return new Gson().toJson( result );
    }
    
    // used in getOutput
    /**
     * Returns the result of {@code command.call()} in the format specified in the parameters-list
     * of the string-command given in the constructor.
     * 
     * @param command
     *            The command whose output is to be converted.
     * @return The result of {@code command.call()} in the format specified in the parameters-list
     *         of the string-command given in the constructor.
     * @throws Exception
     *             If there was a problem in the execution of the command or there was received an
     *             exception result.
     * @throws InvalidParameterValueException
     *             If the value of the parameter accept is unknown.
     */
    private String convertUsingTranslators( Callable< ? > command )
        throws Exception, InvalidParameterValueException {
    
        try {
            Translatable intermediateRepr = ToTranslatableConverter.convert( command.call() );
            return getTranslator().encode( intermediateRepr );
        }
        catch( UnknownTypeException | UnknownTranslatableException e ) {
            throw new InternalErrorException( e.getMessage() );
        }
    }
    
    // used in convertUsingTranslators
    /**
     * Returns the {@link Translator} correspondent to the value of the parameter with name
     * {@link StringCommandsDictionary#ACCEPT} received in the parameters-list of the
     * string-command; if this parameter wasn't in the parameters-list returns the translator
     * correspondent to {@link StringCommandsDictionary#TEXT}.
     * 
     * @return The translator correspondent to the value of the parameter with name
     *         {@link StringCommandsDictionary#ACCEPT} or<br/>
     *         the translator {@link StringCommandsDictionary#TEXT} if no value was received.
     * @throws InvalidParameterValueException
     *             If the value of the parameter accept is unknown.
     */
    private Translator getTranslator() throws InvalidParameterValueException {
    
        String translator = findValueOf( StringCommandsDictionary.ACCEPT );
        if( translator == null || !supportsOutputCustomization() )
            translator = StringCommandsDictionary.TEXT;
        
        Translator t = TRANSLATORS.get( translator );
        if( t == null )
            throw new InvalidParameterValueException( StringCommandsDictionary.ACCEPT, translator );
        return t;
    }
    
    // used in the methods getTranslator and getStream
    /**
     * Checks whether the string-command bound to this instance of {@link StringCommandsExecutor}
     * supports the functionalities coded in the parameters with names
     * {@link StringCommandsDictionary#ACCEPT} and {@link StringCommandsDictionary#STREAM} (these
     * are related with output formatting and printing).
     * 
     * @return {@code true} if the command supports custom output formatting and printing; <br/>
     *         {@code false} if the results can only be printed in plain text to the
     *         {@link System#out}.
     */
    private boolean supportsOutputCustomization() {
    
        return methodsThatSupportOutputCustomization.contains( args[0] );
    }
    
    // used in the methods getOutput, getTranslator and getStream
    /**
     * Returns the value of the parameter with name {@code parameterName} in the string-command's
     * parameters-list. If there is no parameters-list or there is no such parameter in the given
     * list, returns {@code null}.
     * 
     * @param parameterName
     *            The name of the parameter whose value is to be found.
     * @return The value of the parameter with name {@code parameterName} in the string-command's
     *         parameters-list; or <br/>
     *         {@code null} if there is no parameters-list or there is no such parameter in the
     *         given list.
     */
    private String findValueOf( String parameterName ) {
    
        return parametersMap.get( parameterName );
    }
    
}
