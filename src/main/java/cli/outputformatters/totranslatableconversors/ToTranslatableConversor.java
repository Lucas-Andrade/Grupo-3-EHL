package main.java.cli.outputformatters.totranslatableconversors;


import java.util.HashMap;
import java.util.Map;
import main.java.cli.outputformatters.Translatable;
import main.java.utils.Optional;
import main.java.utils.OptionsList;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;


/**
 * Class whose method {@link #convert} converts instances of certain types into
 * {@link Translatables}. <b> The argument {@code object} of method
 * {@link #convert(Object)} must be an instance of one of the following types:
 * <ul>
 * <li>{@link User}, {@link CivilAirship} or {@link MilitaryAirship},</li>
 * <li>{@link Iterable} of one of the three previous types,</li>
 * <li>{@link Iterable} of {@link Airship}s,</li>
 * <li>{@link OptionsList},</li>
 * <li>{@link String} or</li>
 * <li>{@link Optional} representing one of the previous types.</li>
 * </ul>
 * </li> </ul> </b>
 * <p>
 * <b>Implementation note:</b>
 * </p>
 * <p>
 * This class will break the Open-Closed Principle (see SOLID principles); every
 * time a new {@link Conversor} is created, a new entry has to be added to the
 * static field {@link #CONVERSORSbyTYPE}.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToTranslatableConversor
{
	
	// CLASS FIELDS
	
	/**
	 * Auxiliary strings that are a string representation of types.
	 * <p>
	 * By convention, if {@code instance}'s type is not generic, these strings
	 * equal {@code instance.getClass().getSimpleName()} for a certain instance
	 * {@code instance}.
	 * </p>
	 */
	private static String civilClass = "CivilAirship"; // CivilAirship
	private static String iterableUserClass = "itUser"; // Iterable<User>
	private static String iterableCivilClass = "itCivilAirship"; // Iterable<CivilAirship>
	private static String iterableMilitaryClass = "itMilitaryAirship"; // Iterable<MilitaryAirship>
	private static String militaryClass = "MilitaryAirship"; // MilitaryAirship
	private static String optionsListClass = "OptionsList"; // OptionsList
	private static String stringClass = "String"; // String
	private static String userClass = "User"; // User
	
	/**
	 * The mapping between strings that represent instance types and the
	 * {@link Conversor} instance needed to convert instances of that type into
	 * {@link Translatables} (uses the {@link ResultTypesStringsDictionary}).
	 */
	private static final Map< String, Conversor > CONVERSORSbyTYPE = new HashMap< String, Conversor >();
	static
	{
		CONVERSORSbyTYPE.put( userClass,
				new SimpleInstancesToTranslatables.UserConversor() );
		CONVERSORSbyTYPE.put( civilClass,
				new SimpleInstancesToTranslatables.CivilAirshipConversor() );
		CONVERSORSbyTYPE.put( militaryClass,
				new SimpleInstancesToTranslatables.MilitaryAirshipConversor() );
		CONVERSORSbyTYPE.put( stringClass, new StringToTranslatableConversor() );
		CONVERSORSbyTYPE.put( iterableUserClass,
				new IterablesToTranslatables.IterableUserConversor() );
		CONVERSORSbyTYPE.put( iterableCivilClass,
				new IterablesToTranslatables.IterableAirshipConversor() );
		CONVERSORSbyTYPE.put( iterableMilitaryClass,
				new IterablesToTranslatables.IterableAirshipConversor() );
		CONVERSORSbyTYPE.put( optionsListClass, new OptionsListToTranslatableConversor() );
		
	}
	
	
	
	// PUBLIC STATIC METHOD
	/**
	 * Converts {@code object} into a {@link Translatable}.
	 * <p>
	 * <b> The argument {@code object} must be an instance of one of the
	 * following types:
	 * <ul>
	 * <li>{@link User}, {@link CivilAirship} or {@link MilitaryAirship},</li>
	 * <li>{@link Iterable} of one of the three previous types,</li>
	 * <li>{@link Iterable} of {@link Airship}s,</li>
	 * <li>{@link OptionsList},</li>
	 * <li>{@link String} or</li>
	 * <li>{@link Optional} representing one of the previous types.</li>
	 * <ul>
	 * </ul></li>
	 * 
	 * </ul> </b>
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
		
		return getTranslatableOfValue( object );
	}
	
	
	/**
	 * Returns the {@link Translatable} that results from converting the value
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
			
			// obter o translatable
			return getTranslatableOfValue( obj );
			
		}
		catch( Exception e )
		{
			// se sai excepçao é porq value é null
			// manda-se a msg da excepçao pro StringConversor
			return CONVERSORSbyTYPE.get( stringClass ).convert( e.getMessage() );
		}
	}
	
	
	/**
	 * Returns the {@link Translatable} that results from converting {@code obj}
	 * .
	 * 
	 * @param obj
	 *            The value to be converted.
	 * @return The {@link Translatable} that returns from converting {@code obj}
	 *         .
	 * @throws UnknownTypeException
	 *             If the value is not one of the mentioned in
	 *             {@link #convert(Object)} documentation.
	 */
	private static Translatable getTranslatableOfValue( Object obj )
			throws UnknownTypeException {
		
		
		if( obj instanceof Iterable )
		{
			// se for um Iterable nao vazio
			if( ((Iterable< ? >)obj).iterator().hasNext() )
			{
				String elementsType = ((Iterable< ? >)obj).iterator().next()
						.getClass().getSimpleName();
				return getConversor( "it" + elementsType ).convert( obj );
			}
			
			// se for um Iterable vazio que nao estava dentro de um Optional
			// (o getTranslatableOfValueContainedInOptional trata este caso à
			// parte)
			else throw new UnknownTypeException(
					"Cannot convert empty iterables to string." );
		}
		
		// se nao for um Iterable
		return getConversor( obj.getClass().getSimpleName() ).convert( obj );
	}
	
	
	/**
	 * Returns the correct {@link Conversor} for an object of type whose string
	 * representation is {@code objectType}.
	 * 
	 * @param objectType
	 *            The string representation of the type of the object to be
	 *            converted.
	 * @return The correct {@link Conversor} for an object of type whose string
	 *         representation is {@code objectType}.
	 * @throws UnknownTypeException
	 *             If {@code objectType} is unknown.
	 */
	private static Conversor getConversor( String objectType )
			throws UnknownTypeException {
		
		Conversor c = CONVERSORSbyTYPE.get( objectType );
		
		if( c == null )
			throw new UnknownTypeException(
					"Cannot convert this type of object! (" + objectType + ")." );
		
		return c;
	}
	
}
