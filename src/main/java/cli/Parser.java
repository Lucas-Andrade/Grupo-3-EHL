package main.java.cli;


import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.commandparserexceptions.DuplicateParametersException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandParametersSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.InvalidCommandSyntaxException;
import main.java.cli.exceptions.commandparserexceptions.UnknownCommandException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;
import main.java.cli.translations.translators.ToHtmlTranslator;
import main.java.cli.translations.translators.ToJsonTranslator;
import main.java.cli.translations.translators.ToPlainTextTranslator;
import main.java.cli.translations.translators.Translator;


/**
 * TODO
 * 
 * violates OCP
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Parser
{
	
	// CLASS FIELD
	
	public static final Map< String, Translator > TRANSLATORS = new HashMap< String, Translator >();
	static
	{
		TRANSLATORS.put( StringsDictionary.HTML, new ToHtmlTranslator() );
		TRANSLATORS.put( StringsDictionary.TEXT, new ToPlainTextTranslator() );
		TRANSLATORS.put( StringsDictionary.JSON, new ToJsonTranslator() );
	}
	
	
	
	// INSTANCE FIELDS
	
	private final String[] args;
	private final CommandParser cmdParser;
	private final Map< String, String > parametersMap;
	
	
	
	// CONSTRUCTOR
	
	public Parser( CommandParser cmdParser, String... args )
			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException, InvalidArgumentException,
			InvalidCommandSyntaxException {
		
		if( cmdParser == null )
			throw new InvalidArgumentException(
					"Cannot instantiate parser with null command parser." );
		if( args == null || args.length < 2 || args.length > 3 )
			throw new InvalidCommandSyntaxException(
					"Commands must have either 1 or 2 space-characters." );
		
		
		this.args = args;
		this.cmdParser = cmdParser;
		this.parametersMap = (args.length == 2)
				? new HashMap< String, String >()
				: getParametersFromParametersList();
	}
	
	
	
	// PUBLIC METHODS
	
	public Callable< ? > getCommand( String... args )
			throws WrongLoginPasswordException,
			MissingRequiredParameterException, InvalidCommandSyntaxException,
			UnknownCommandException, NoSuchElementInDatabaseException,
			InvalidParameterValueException, InvalidArgumentException,
			InternalErrorException {
		
		return cmdParser.getCommand( parametersMap, args );
	}
	
	public Optional< Translator > getTranslator( String... args ) {
		
		String translator = findValueOf( StringsDictionary.ACCEPT );
		if( translator == null )
			translator = StringsDictionary.TEXT;
		return new Optional< Translator >( TRANSLATORS.get( translator ),
				new InvalidParameterValueException( StringsDictionary.ACCEPT,
						translator ) );
	}
	
	public OutputStream getStream( String... args )
			throws InvalidParameterValueException {
		
		String stream = findValueOf( StringsDictionary.STREAM );
		if( stream == null )
			return System.out;
		try
		{
			return new PrintStream( stream );
		}
		catch( FileNotFoundException e )
		{
			throw new InvalidParameterValueException( StringsDictionary.STREAM, stream );
		}
	}
	
	
	// AUXILIARY PRIVATE METHOD
	
	// used in constructor
	/**
	 * Interprets the string {@code parameters} that contains a parameters-list
	 * written with the syntax
	 * 
	 * <pre>
	 *  {@literal<}parameters-list> -> {@literal<}name>={@literal<}value>[&{@literal<}name>={@literal<}value>]*
	 *  {@literal<}name> -> {@literal<}string>
	 *  {@literal<}value> -> {@literal<}string>
	 * </pre>
	 * 
	 * and produces a {@link Map} whose keys are the "{@code names}" and whose
	 * values are the "{@code values}".
	 * 
	 * @throws InvalidCommandParametersSyntaxException
	 *             If the parameters are not correctly separated by '{@code &}'
	 *             or a certain parameter has not the format
	 *             <code>{@literal <}name>={@literal <}value></code>.
	 * @throws DuplicateParametersException
	 *             If several values for the same parameter have been received
	 *             in the parameters-list.
	 */
	private Map< String, String > getParametersFromParametersList()
			throws InvalidCommandParametersSyntaxException,
			DuplicateParametersException {
		
		Map< String, String > parametersMap = new HashMap<>();
		
		if( args[2] != null )
		{
			String[] parametersElements = args[2].split( "&" );
			for( String parameterElement : parametersElements )
			{
				String[] keyAndValue = parameterElement.split( "=" );
				if( keyAndValue.length != 2 )
					throw new InvalidCommandParametersSyntaxException(
							"Invalid syntax in parameteres list!" );
				if( parametersMap.containsKey( keyAndValue[0] ) )
					throw new DuplicateParametersException( keyAndValue[0] );
				parametersMap.put( keyAndValue[0], keyAndValue[1] );
			}
		}
		return parametersMap;
	}
	
	
	private String findValueOf( String parameterName ) {
		
		return parametersMap.get( parameterName );
	}
	
}
