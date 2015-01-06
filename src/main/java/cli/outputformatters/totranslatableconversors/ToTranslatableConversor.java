package main.java.cli.outputformatters.totranslatableconversors;


import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.Optional;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownResultTypeException;


public class ToTranslatableConversor
{
	
	/**
	 * Auxiliary objects, created with the intent of applying getClass() and
	 * obtaining their Class<?> type.
	 */
	private static User u;
	private static CivilAirship ca;
	private static MilitaryAirship ma;
	
	/**
	 * Auxiliary strings, obtained by applying getSimpleName() to needed
	 * instances of Class<?>.
	 */
	private static String userClass;
	private static String civilClass;
	private static String militaryClass;
	private static String stringClass;
	private static String iterableUserClass;
	private static String iterableCivilClass;
	private static String iterableMilitaryClass;
	
	/**
	 * The mapping between strings that represent instance types and the
	 * {@link Converter} instance needed to convert instances of that type into
	 * {@link Translatables} (uses the {@link ResultTypesStringsDictionary}).
	 */
	private static final Map< String, Converter > RESULTS_TYPES_MAP = new HashMap< String, Converter >();
	
	/**
	 * Fields initialization.
	 */
	static
	{
		// OBJECTOS
		try
		{
			u = new User( "user", "pass", "em@il" );
			ca = new CivilAirship( 30, 40, 6000, 3000, 300, 6 );
			ma = new MilitaryAirship( 30, 40, 5000, 4000, 200, false );
		}
		catch( InvalidArgumentException e )
		{
			System.out
					.println( "ERRO INESPERADO NOS OBJS AUXILIARES DO TRANSLATABLE CONVERSOR" );
		}
		
		// STRINGS DAS CLASSES
		
		userClass = u.getClass().getSimpleName();
		civilClass = ca.getClass().getSimpleName();
		militaryClass = ma.getClass().getSimpleName();
		stringClass = "string".getClass().getSimpleName();
		iterableUserClass = "it" + u.getClass().getSimpleName();
		iterableCivilClass = u.getClass().getSimpleName();
		iterableMilitaryClass = u.getClass().getSimpleName();
		
		// MAP
		
		RESULTS_TYPES_MAP.put( userClass, new UserConverter() );
		RESULTS_TYPES_MAP.put( civilClass, new CivilAirshipConverter() );
		RESULTS_TYPES_MAP.put( militaryClass, new MilitaryAirshipConverter() );
		RESULTS_TYPES_MAP.put( stringClass, new StringConversor() );
		RESULTS_TYPES_MAP.put( iterableUserClass, new IterableUserConversor() );
		RESULTS_TYPES_MAP.put( iterableCivilClass,
				new IterableAirshipConversor() );
		RESULTS_TYPES_MAP.put( iterableMilitaryClass,
				new IterableAirshipConversor() );
		
	}
	
	
	
	/**
	 * Converts {@code object} into a {@link Translatable}.
	 * 
	 * @param object
	 *            The object to be converted.
	 * @param objectConcreteType
	 *            The concrete type of {@code object}.
	 * @return The {@link Translatable} instance referent to {@code object}.
	 * @throws UnknownResultTypeException
	 */
	public static Translatable convert( Object object )
			throws UnknownResultTypeException {
		
		
		if( object instanceof Optional )
			return getTranslatableOfValueContainedInOptional( (Optional< ? >)object );
		
		if( object instanceof String )
			return RESULTS_TYPES_MAP.get( stringClass ).convert(
					((String)object).toString() );
		
		// caso nao seja Optional nem String
		throw new UnknownResultTypeException(
				"Cannot convert empty iterables to string." );
		
		
	}
	
	/**
	 * TODO: document this method
	 * 
	 * @param optional
	 * @return
	 */
	private static Translatable getTranslatableOfValueContainedInOptional(
			Optional<?> optional ) {
		
		Object obj;
		try
		{
			// tiramos o value representado pelo Optional
			obj = optional.get();
			
			
			// se o value for um Iterable vazio
			if( optional.isEmpty() )
				return RESULTS_TYPES_MAP.get( stringClass ).convert(
						optional.toString() );
			
			// se for um Iterable nao vazio
			if( obj instanceof Iterable )
			{				
				// pegamos no 1o elemento e extraímos o nome do seu tipo
				String elementsType = ((Iterable<?>)obj).iterator().next()
						.getClass().getSimpleName();
				
				// vemos se ha conversor para iteraveis desse tipo
				return getConversor( "it" + elementsType ).convert( obj );				
			}
			
			
			// se nao for um Iterable
			return getConversor( obj.getClass().getSimpleName() ).convert( obj );
			
			
			
		}
		catch( Exception e )
		{// se sai excepçao é porq value é null, manda-se a msg da excepçao
			// pro StringConversor
			return RESULTS_TYPES_MAP.get( stringClass )
					.convert( e.getMessage() );
		}
	}
	
	/**
	 * Returns the correct {@link Converter} for an object of type whose string
	 * representation is {@code objectType}.
	 * 
	 * @param objectType
	 *            The string representation of the type of the object to be
	 *            converted.
	 * @return The correct {@link Converter} for an object of type whose string
	 *         representation is {@code objectType}.
	 * @throws UnknownResultTypeException
	 *             If {@code objectType} is unknown.
	 */
	
	private static Converter getConversor( String objectType )
			throws UnknownResultTypeException {
		
		Converter c = RESULTS_TYPES_MAP.get( objectType );
		
		if( c == null )
			throw new UnknownResultTypeException(
					"Cannot convert this type of object! (" + objectType + ")." );
		
		return c;
	}
	
}
