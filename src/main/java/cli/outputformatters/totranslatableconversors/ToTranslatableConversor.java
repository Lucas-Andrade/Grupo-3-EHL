package main.java.cli.outputformatters.totranslatableconversors;


import java.util.HashMap;
import java.util.Map;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.Optional;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Class whose method {@link #convert} converts instances of certain types into
 * {@link Translatables}. <b> The argument {@code object} of method
 * {@link #convert(Object)} must be
 * <ul>
 * <li>an instance of {@link String} or
 * <li>an instance of {@link Optional} representing an instance of one of the
 * following types:
 * <ul>
 * <li>{@link User}, {@link CivilAirship} or {@link MilitaryAirship},</li>
 * <li>{@link Iterable} of instances of one of the three previous types,</li>
 * <li>{@link Iterable} of {@link Airship}s or</li>
 * </ul>
 * </li>
 * </ul>
 * </b>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToTranslatableConversor
{
	
	/**
	 * Auxiliary strings that are a string representation of types.
	 * <p>
	 * By convention, if {@code instance}'s type is not generic, these strings
	 * equal {@code instance.getClass().getSimpleName()} for a certain instance
	 * {@code instance}.
	 * </p>
	 */
	private static String userClass = "User"; // User
	private static String civilClass = "CivilAirship"; // CivilAirship
	private static String militaryClass = "MilitaryAirship"; // MilitaryAirship
	private static String stringClass = "String"; // String
	private static String iterableUserClass = "itUser"; // Iterable<User>
	private static String iterableCivilClass = "itCivilAirship"; // Iterable<CivilAirship>
	private static String iterableMilitaryClass = "itMilitaryAirship"; // Iterable<MilitaryAirship>
	
	/**
	 * The mapping between strings that represent instance types and the
	 * {@link Converter} instance needed to convert instances of that type into
	 * {@link Translatables} (uses the {@link ResultTypesStringsDictionary}).
	 */
	private static final Map< String, Converter > CONVERSORSbyTYPE = new HashMap< String, Converter >();
	static
	{
		CONVERSORSbyTYPE.put( userClass, new UserConverter() );
		CONVERSORSbyTYPE.put( civilClass, new CivilAirshipConverter() );
		CONVERSORSbyTYPE.put( militaryClass, new MilitaryAirshipConverter() );
		CONVERSORSbyTYPE.put( stringClass, new StringConversor() );
		CONVERSORSbyTYPE.put( iterableUserClass, new IterableUserConversor() );
		CONVERSORSbyTYPE.put( iterableCivilClass,
				new IterableAirshipConversor() );
		CONVERSORSbyTYPE.put( iterableMilitaryClass,
				new IterableAirshipConversor() );
		
	}
	
	/**
	 * Converts {@code object} into a {@link Translatable}.
	 * <p>
	 * <b> The argument {@code object} must be
	 * <ul>
	 * <li>an instance of {@link String} or
	 * <li>an instance of {@link Optional} representing an instance of one of
	 * the following types:
	 * <ul>
	 * <li>{@link User}, {@link CivilAirship} or {@link MilitaryAirship},</li>
	 * <li>{@link Iterable} of instances of one of the three previous types,</li>
	 * <li>{@link Iterable} of {@link Airship}s or</li>
	 * </ul>
	 * </li>
	 * 
	 * </ul>
	 * </b>
	 * </p>
	 * 
	 * @param object
	 *            The object to be converted.
	 * @param objectConcreteType
	 *            The concrete type of {@code object}.
	 * @return The {@link Translatable} instance referent to {@code object}.
	 * @throws UnknownTypeException
	 *             If {@code object} is not an instance of the types enumerated
	 *             above.
	 */
	public static Translatable convert( Object object )
			throws UnknownTypeException {
		
		
		if( object instanceof Optional )
			return getTranslatableOfValueContainedInOptional( (Optional< ? >)object );
		
		if( object instanceof String )
			return CONVERSORSbyTYPE.get( stringClass ).convert( (String)object );
		
		// caso nao seja Optional nem String
		throw new UnknownTypeException(
				"Cannot convert empty iterables to string." );
		
		
	}
	
	/**
	 * Returns the {@link Translatable} that returns from converting the value
	 * represented by the {@link Optional} {@code optional}.
	 * 
	 * @param optional
	 *            The {@link Optional} representing the value to be converted.
	 * @return The {@link Translatable} that returns from converting the value
	 *         represented by the {@link Optional} {@code optional}.
	 * @throws UnknownTypeException
	 *             If the value represented by {@code optional} is not one of
	 *             the mentioned in {@link #convert(Object)} documentation.
	 */
	private static Translatable getTranslatableOfValueContainedInOptional(
			Optional< ? > optional ) throws UnknownTypeException {
		
		Object obj;
		try
		{
			// tiramos o value representado pelo Optional
			obj = optional.get();
			
			
			// se o value for um Iterable vazio
			if( optional.isEmpty() )
				return CONVERSORSbyTYPE.get( stringClass ).convert(
						optional.toString() );
			
			// se for um Iterable nao vazio
			if( obj instanceof Iterable )
			{
				// pegamos no 1o elemento e extraímos o nome do seu tipo
				String elementsType = ((Iterable< ? >)obj).iterator().next()
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
			return CONVERSORSbyTYPE.get( stringClass ).convert( e.getMessage() );
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
	 * @throws UnknownTypeException
	 *             If {@code objectType} is unknown.
	 */
	
	private static Converter getConversor( String objectType )
			throws UnknownTypeException {
		
		Converter c = CONVERSORSbyTYPE.get( objectType );
		
		if( c == null )
			throw new UnknownTypeException(
					"Cannot convert this type of object! (" + objectType + ")." );
		
		return c;
	}
	
}
