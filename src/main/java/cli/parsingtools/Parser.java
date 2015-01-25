package main.java.cli.parsingtools;


import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.outputformatters.translators.ToHtmlTranslator;
import main.java.cli.outputformatters.translators.ToJsonTranslator;
import main.java.cli.outputformatters.translators.ToPlainTextTranslator;
import main.java.cli.outputformatters.translators.Translator;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.InvalidCommandSyntaxException;
import main.java.utils.exceptions.parsingexceptions.InvalidParameterValueException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.UnknownCommandException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.parsingexceptions.parserexceptions.DuplicateParametersException;
import main.java.utils.exceptions.parsingexceptions.parserexceptions.InvalidCommandParametersSyntaxException;


/**
 * Class whose instances are responsible for interpreting string-commands.
 * <p>
 * Each instance is bound to a concrete string-command and to a {@link CommandParser} instance. The
 * public methods of this class allow getting the objects {@link Callable command},
 * {@link Translator output translator} and {@link PrintStream stream} coded in the string-command.
 * The register of the received command parser should contain the given string-command.
 * </p>
 * <p>
 * <b>Implementation notes:</b>
 * </p>
 * <ul>
 * <li>This class will break the Open-Closed Principle (see SOLID principles); every time a new
 * {@link Translator} is created, a new entry has to be added to the static field
 * {@link #TRANSLATORS}.</li>
 * <li>This class makes use of the static fields in {@link CLIStringsDictionary}.
 * </ul>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @see CommandParser
 * @see CLIStringsDictionary
 * @see Translator
 */
public class Parser {
    
    
    
    // CLASS FIELD AND CONSTRUCTOR
    /**
     * The mapping between strings that may be contained in the string-command and the
     * {@link Translator} instance they correspond to (uses the {@link CLIStringsDictionary}).
     */
    public static final Map< String, Translator > TRANSLATORS = new HashMap< String, Translator >();
    static {
        try {
            TRANSLATORS.put( CLIStringsDictionary.HTML, new ToHtmlTranslator( 5 ) );
        }
        catch( InvalidArgumentException e ) {} // never happens because argument 5 is bigger than 1
        TRANSLATORS.put( CLIStringsDictionary.TEXT, new ToPlainTextTranslator() );
        TRANSLATORS.put( CLIStringsDictionary.JSON, new ToJsonTranslator() );
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
     * Creates a new instance of {@link Parser} bound to a concrete string-command and to a
     * {@link CommandParser} instance. The method and path received in {@code args} (its first and
     * second entries) should match the method and path of one of the commands registered in
     * {@code cmdParser}.
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
     *             '{@code &}' or a certain parameter has not the format
     *             <code>{@literal <}name>={@literal <}value>
     *             </code>.
     * @throws DuplicateParametersException
     *             If several values for the same parameter have been received in the
     *             parameters-list.
     * @see CommandParser
     */
    public Parser( CommandParser cmdParser, String... args )
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException,
        InvalidArgumentException, InvalidCommandSyntaxException {
        
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
     * Returns the {@link Callable command} correspondent to the string-command received in the
     * constructor.
     * 
     * @return The corresponding {@link Callable} instance.
     * @throws InvalidArgumentException
     *             If {@code parameters==null}.
     * @throws InvalidCommandSyntaxException
     *             If {@code args.length} is not 2 or 3.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid.
     * @throws InternalErrorException
     *             If an internal error occurred (not supposed to happen).
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
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
    public Callable< ? > getCommand()
        throws WrongLoginPasswordException, MissingRequiredParameterException,
        InvalidCommandSyntaxException, UnknownCommandException, NoSuchElementInDatabaseException,
        InvalidParameterValueException, InvalidArgumentException, InternalErrorException {
        
        return cmdParser.getCommand( parametersMap, args );
    }
    
    /**
     * Returns the {@link Translator} correspondent to the value of the parameter with name
     * {@link CLIStringsDictionary#ACCEPT} received in the parameters-list of the string-command; if
     * this parameter wasn't in the parameters-list returns the translator correspondent to
     * {@link CLIStringsDictionary#TEXT}.
     * 
     * @return The translator correspondent to the value of the parameter with name
     *         {@link CLIStringsDictionary#ACCEPT} or<br/>
     *         the translator {@link CLIStringsDictionary#TEXT} if no value was received.
     * @throws InvalidParameterValueException
     *             If the value of the parameter accept is unknown.
     */
    public Translator getTranslator() throws InvalidParameterValueException {
        
        String translator = findValueOf( CLIStringsDictionary.ACCEPT );
        if( translator == null )
            translator = CLIStringsDictionary.TEXT;
        
        Translator t = TRANSLATORS.get( translator );
        if( t == null )
            throw new InvalidParameterValueException( CLIStringsDictionary.ACCEPT, translator );
        return t;
    }
    
    /**
     * Returns the stream correspondent to the value of the parameter with name
     * {@link CLIStringsDictionary#STREAM} received in the parameters-list of the string-command; if
     * this parameter wasn't in the parameters-list returns the {@link PrintStream}
     * {@link System#out}.
     * 
     * @return The stream correspondent to the value of the parameter with name
     *         {@link CLIStringsDictionary#STREAM} or<br />
     *         the stream {@link System#out} if no value was received.
     * @throws InvalidParameterValueException
     *             If the value of the parameter accept is unknown.
     */
    public PrintStream getStream() throws InvalidParameterValueException {
        
        String filePath = findValueOf( CLIStringsDictionary.STREAM );
        if( filePath == null )
            return System.out;
        try {
            return new PrintStream( filePath );
        }
        catch( FileNotFoundException e ) {
            throw new InvalidParameterValueException( CLIStringsDictionary.STREAM, filePath,
                                                      e.getMessage() );
        }
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
     */
    private Map< String, String > getParametersFromParametersList()
        throws InvalidCommandParametersSyntaxException, DuplicateParametersException {
        
        Map< String, String > parametersMap = new HashMap< String, String >();
        if( args.length == 2 )
            return parametersMap;
        
        String[] parameters = args[2].split( "&" );
        for( String parameter : parameters ) {
            String[] nameAndValue = parameter.split( "=" );
            if( nameAndValue.length != 2 )
                throw new InvalidCommandParametersSyntaxException(
                                                                   "Invalid syntax in parameters-list!" );
            String parameterName = nameAndValue[0];
            String parameterValue = nameAndValue[1];
            
            if( parametersMap.containsKey( parameterName ) )
                throw new DuplicateParametersException( parameterName );
            parametersMap.put( parameterName, parameterValue );
        }
        return parametersMap;
    }
    
    // used in the methods getTranslator and getStream
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
