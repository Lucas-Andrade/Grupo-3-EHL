package outputformatters.totranslatableconverters;


import java.util.HashMap;
import java.util.Map;
import outputformatters.Translatable;
import utils.CompletionStatus;
import utils.Optional;
import utils.OptionsList;
import utils.exceptions.formattersexceptions.UnknownTypeException;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;


/**
 * Class whose method {@link #convert} converts instances of certain types into
 * {@link Translatables}.
 * <p>
 * <b>The following conventions are obligatory:</b>
 * </p>
 * <p>
 * The argument {@code object} of method {@link #convert(Object)} <b>must be</b> an instance of one
 * of the following types:
 * <ul>
 * <li>{@link User}, {@link CivilAirship} or {@link MilitaryAirship},</li>
 * <li>{@link Iterable} of one of the three previous types,</li>
 * <li>{@link Iterable} of {@link Airship}s,</li>
 * <li>{@link OptionsList},</li>
 * <li>{@link String} or</li>
 * <li>{@link Optional} representing one of the previous types.</li>
 * </ul>
 * </li> </ul> </b>
 * </p>
 * <p>
 * <b>Implementation note:</b>
 * </p>
 * <p>
 * This class will break the Open-Closed Principle (see SOLID principles); every time a new
 * {@link Converter} is created, a new entry has to be added to the static field
 * {@link #CONVERSORS_BY_TYPE}.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToTranslatableConverter {
    
    
    
    // CLASS FIELDS
    
    /**
     * Auxiliary strings that are string representations of types. By convention,
     * <ul>
     * <li>if the type is not generic, the string representation equals
     * {@code Type.class.getSimpleName()};</li>
     * <li>if the type is {@link Iterable}{@code <ParameterType>}, the string representation equals
     * {@code "it"+ParameterType.class.getSimpleName()}.</li>
     * </ul>
     */
    /* non-generic types */
    private static final String CIVIL_TYPE = CivilAirship.class.getSimpleName(); // CivilAirship
    private static final String COMPLETIONSTATUS_TYPE = CompletionStatus.class.getSimpleName(); // CompletionStatus
    private static final String MILITARY_TYPE = MilitaryAirship.class.getSimpleName(); // MilitaryAirship
    private static final String OPTIONSLIST_TYPE = OptionsList.class.getSimpleName(); // OptionsList
    private static final String STRING_TYPE = String.class.getSimpleName(); // String
    private static final String USER_TYPE = User.class.getSimpleName(); // User
    /* iterables */
    private static final String ITERABLE_CIVIL_TYPE = "it" + CIVIL_TYPE; // Iterable<CivilAirship>
    private static final String ITERABLE_MILITARY_TYPE = "it" + MILITARY_TYPE; // Iterable<MilitaryAirship>
    private static final String ITERABLE_USER_TYPE = "it" + USER_TYPE; // Iterable<User>
    
    /**
     * The mapping between string representations of some types and the {@link Converter} instances
     * capable of converting instances of those types into {@link Translatables}.
     */
    private static final Map< String, Converter > CONVERSORS_BY_TYPE =
            new HashMap< String, Converter >();
    
    // CLASS CONSTRUCTOR
    
    /**
     * Unused private constructor
     */
    private ToTranslatableConverter() {
        
    }
    
    /**
     * Initializes the {@link #CONVERSORSbyTYPE} field.
     */
    static {
        
        CONVERSORS_BY_TYPE.put( CIVIL_TYPE,
                                new SimpleInstancesToTranslatables.CivilAirshipConverter() );
        CONVERSORS_BY_TYPE.put( COMPLETIONSTATUS_TYPE,
                                new StringsToTranslatables.CompletionStatusConverter() );
        CONVERSORS_BY_TYPE.put( MILITARY_TYPE,
                                new SimpleInstancesToTranslatables.MilitaryAirshipConverter() );
        CONVERSORS_BY_TYPE.put( OPTIONSLIST_TYPE, new MapsToTranslatables.OptionsListConverter() );
        CONVERSORS_BY_TYPE.put( STRING_TYPE, new StringsToTranslatables.StringConverter() );
        CONVERSORS_BY_TYPE.put( USER_TYPE, new SimpleInstancesToTranslatables.UserConversor() );
        CONVERSORS_BY_TYPE.put( ITERABLE_USER_TYPE,
                                new IterablesToTranslatables.IterableUserConverter() );
        CONVERSORS_BY_TYPE.put( ITERABLE_CIVIL_TYPE,
                                new IterablesToTranslatables.IterableAirshipConverter() );
        CONVERSORS_BY_TYPE.put( ITERABLE_MILITARY_TYPE,
                                new IterablesToTranslatables.IterableAirshipConverter() );
    }
    
    
    
    // PUBLIC STATIC METHOD
    /**
     * Converts {@code object} into a {@link Translatable}.
     * <p>
     * The argument {@code object} <b> must be </b> an instance of one of the following types:
     * <ul>
     * <li>{@link User}, {@link CivilAirship} or {@link MilitaryAirship},</li>
     * <li>{@link Iterable} of one of the three previous types,</li>
     * <li>{@link Iterable} of {@link Airship}s,</li>
     * <li>{@link OptionsList},</li>
     * <li>{@link String} or</li>
     * <li>{@link Optional} representing one of the previous types.</li>
     * </ul>
     * </p>
     * 
     * @param object
     *            The object to be converted.
     * @return The {@link Translatable} instance referent to {@code object}.
     * @throws UnknownTypeException
     *             If {@code object} is not an instance of the types enumerated above.
     */
    public static Translatable convert( Object object ) throws UnknownTypeException {
        
        if( object instanceof Optional )
            return getTranslatableOfValueContainedInOptional( (Optional< ? >)object );
        
        return getTranslatableOfValue( object );
    }
    
    
    
    // PRIVATE METHODS
    
    // used in the method convert
    /**
     * Returns the {@link Translatable} that results from converting the value represented by the
     * {@link Optional} {@code optional}.
     * 
     * @param optional
     *            The {@link Optional} representing the value to be converted.
     * @return The {@link Translatable} that returns from converting the value represented by the
     *         {@link Optional} {@code optional}.
     * @throws UnknownTypeException
     *             If the value represented by {@code optional} is not one of the mentioned in
     *             {@link #convert(Object)} documentation.
     * @see Optional
     */
    private static Translatable getTranslatableOfValueContainedInOptional( Optional< ? > optional )
        throws UnknownTypeException {
        
        Object obj;
        try {
            
            obj = optional.get();
            
            // empty collection or empty map
            if( optional.isEmpty() )
                return getConversor( STRING_TYPE ).convert( optional.toString() );
            return getTranslatableOfValue( obj );
            
        }
        catch( Exception e ) {
            // this is caught if value is null
            return getConversor( STRING_TYPE ).convert( e.getMessage() );
        }
    }
    
    // used in the methods convert and getTranslatableOfValueContainedInOptional
    /**
     * Returns the {@link Translatable} that results from converting {@code obj} .
     * 
     * @param obj
     *            The value to be converted.
     * @return The {@link Translatable} that returns from converting {@code obj}.
     * 
     * @throws UnknownTypeException
     *             If the value is not one of the mentioned in {@link #convert(Object)}
     *             documentation.
     */
    private static Translatable getTranslatableOfValue( Object obj ) throws UnknownTypeException {
        
        
        if( obj instanceof Iterable ) {
            
            // if non-empty iterable
            if( ((Iterable< ? >)obj).iterator().hasNext() ) {
                String elementsType =
                        ((Iterable< ? >)obj).iterator().next().getClass().getSimpleName();
                return getConversor( "it" + elementsType ).convert( obj );
            }
            
            // if empty iterable
            else return getConversor( STRING_TYPE ).convert( "no result found" );
        }
        
        else return getConversor( obj.getClass().getSimpleName() ).convert( obj );
    }
    
    // used in the methods getTranslatableOfValueContainedInOptional and getTranslatableOfValue
    /**
     * Returns the correct {@link Converter} for an instance of type whose string representation is
     * {@code type}.
     * 
     * @param type
     *            The string representation of the type of the instance to be converted.
     * @return The correct {@link Converter} for an instance of type whose string representation is
     *         {@code type}.
     * @throws UnknownTypeException
     *             If the value represented by {@code optional} is not one of the mentioned in
     *             {@link #convert(Object)} documentation.
     */
    private static Converter getConversor( String type ) throws UnknownTypeException {
        
        Converter c = CONVERSORS_BY_TYPE.get( type );
        
        if( c == null )
            throw new UnknownTypeException( "Cannot convert instances of type " + type );
        else return c;
    }
    
}
