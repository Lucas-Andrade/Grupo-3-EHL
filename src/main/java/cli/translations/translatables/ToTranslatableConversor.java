package main.java.cli.translations.translatables;


import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import main.java.cli.StringsDictionary;
import main.java.cli.model.Element;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToTranslatableConversor
{
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public static SimpleTypeTranslatable convert( Element element ) {
		try
		{
			return (SimpleTypeTranslatable)ToTranslatableConversor.class
					.getDeclaredMethod( "convert", element.getClass() ).invoke(
							ToTranslatableConversor.class, element );
		}
		catch( NoSuchMethodException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( SecurityException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( IllegalAccessException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( IllegalArgumentException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch( InvocationTargetException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param collection
	 * @param tag
	 * @return
	 */
	public static ComposedTypeTranslatable convert(
			Collection< Element > collection, String tag ) {
		Translatable< ? >[] propertyBag = new Translatable< ? >[collection
				.size()];
		
		int i = 0;
		for( Element element : collection )
		{
			propertyBag[i++ ] = convert( element );
		}
		
		return new ComposedTypeTranslatable( tag, propertyBag );
	}
	
	/**
	 * 
	 * @param map
	 * @param entryTag
	 * @param keyTag
	 * @param valueTag
	 * @return
	 */
	public static MapTypeTranslatable convert( Map< String, String > map,
			String entryTag, String keyTag, String valueTag ) {
		return new MapTypeTranslatable( entryTag + "s", entryTag, keyTag,
				valueTag, map );
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings( "unused" )
	private static SimpleTypeTranslatable convert( User user ) {
		System.out.println( "user" );
		Map< String, String > propertyBag = new HashMap< String, String >();
		propertyBag.put( StringsDictionary.USERNAME, user.getIdentification() );
		propertyBag.put( StringsDictionary.EMAIL, user.getEmail() );
		propertyBag.put( StringsDictionary.FULLNAME, user.getFullName() );
		
		return new SimpleTypeTranslatable( "User", propertyBag,
				user.toStringWithoutPassword() );
	}
	
	/**
	 * 
	 * @param civilAirship
	 * @return
	 */
	@SuppressWarnings( "unused" )
	private static SimpleTypeTranslatable convert( CivilAirship civilAirship ) {
		System.out.println( "Civil" );
		Map< String, String > propertyBag = createAirshipPropertyBag( civilAirship );
		
		propertyBag.put( "Number of Passengers",
				String.valueOf( civilAirship.getPassengers() ) );
		return new SimpleTypeTranslatable( "Civil Airship", propertyBag,
				civilAirship.toString() );
	}
	
	/**
	 *
	 * @param militaryAirship
	 * @return
	 */
	@SuppressWarnings( "unused" )
	private static SimpleTypeTranslatable convert(
			MilitaryAirship militaryAirship ) {
		Map< String, String > propertyBag = createAirshipPropertyBag( militaryAirship );
		
		propertyBag.put( "Carries Weapons",
				String.valueOf( militaryAirship.hasWeapons() ) );
		return new SimpleTypeTranslatable( "Military Airship", propertyBag,
				militaryAirship.toString() );
	}
	
	/**
	 * 
	 * @param airship
	 * @return
	 */
	private static Map< String, String > createAirshipPropertyBag(
			Airship airship ) {
		Map< String, String > propertyBag = new HashMap< String, String >();
		propertyBag.put( StringsDictionary.FLIGHTID,
				airship.getIdentification() );
		propertyBag.put(
				StringsDictionary.LATITUDE,
				String.valueOf( airship.getCoordinates().getLatitude()
						.getValue() ) );
		propertyBag.put(
				StringsDictionary.LONGITUDE,
				String.valueOf( airship.getCoordinates().getLongitude()
						.getValue() ) );
		propertyBag.put(
				StringsDictionary.ALTITUDE,
				String.valueOf( airship.getCoordinates().getAltitude()
						.getValue() ) );
		propertyBag.put( StringsDictionary.AIRCORRIDOR_MINALTITUDE,
				String.valueOf( airship.getAirCorridor().getMinAltitude() ) );
		propertyBag.put( StringsDictionary.AIRCORRIDOR_MAXALTITUDE,
				String.valueOf( airship.getAirCorridor().getMaxAltitude() ) );
		propertyBag.put( "Is Outside The Given Corridor",
				String.valueOf( airship.isTransgressing() ) );
		
		return propertyBag;
	}
}
