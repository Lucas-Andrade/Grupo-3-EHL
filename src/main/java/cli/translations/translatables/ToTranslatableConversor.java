package main.java.cli.translations.translatables;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import main.java.cli.CommandLineStringsDictionary;
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
	public static Translatable convert( Object element )
	{
		try
		{
			return ( Translatable )ToTranslatableConversor.class.getDeclaredMethod( "convert",
					element.getClass() ).invoke( ToTranslatableConversor.class, element );
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
	public static Translatable convert( Iterable< Element > collection, String tag )
	{
		Map< String, Object > propertiesBag =  new HashMap< String, Object >();

		for( Element element : collection )
		{
			propertiesBag.put( element.getIdentification(), convert( element ) );
		}

		return new Translatable( tag, null, null, null, propertiesBag, null );
	}

	/**
	 * 
	 * @param map
	 * @param entryTag
	 * @param keyTag
	 * @param valueTag
	 * @return
	 */
	public static Translatable convert( Map< String, Object > propertiesBag, String tag, String entryTag, String keyTag,
			String valueTag )
	{
		return new Translatable( tag, entryTag, keyTag, valueTag, propertiesBag, null );
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings( "unused" )
	private static Translatable convert( User user )
	{
		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( CommandLineStringsDictionary.USERNAME, user.getIdentification() );
		propertiesBag.put( CommandLineStringsDictionary.EMAIL, user.getEmail() );
		propertiesBag.put( CommandLineStringsDictionary.FULLNAME, user.getFullName() );

		return new Translatable( null, "User", null, null, propertiesBag, user.toString() );
	}

	/**
	 * 
	 * @param civilAirship
	 * @return
	 */
	@SuppressWarnings( "unused" )
	private static Translatable convert( CivilAirship civilAirship )
	{
//		System.out.println( "Civil" );
		Map< String, Object > propertiesBag = createAirshipPropertyBag( civilAirship );

		propertiesBag.put( "Number of Passengers", String.valueOf( civilAirship.getPassengers() ) );
		return new Translatable( null, "Civil Airship", null, null, propertiesBag, civilAirship.toString() );
	}

	/**
	 *
	 * @param militaryAirship
	 * @return
	 */
	@SuppressWarnings( "unused" )
	private static Translatable convert( MilitaryAirship militaryAirship )
	{
		Map< String, Object > propertiesBag = createAirshipPropertyBag( militaryAirship );

		propertiesBag.put( "Carries Weapons", String.valueOf( militaryAirship.hasWeapons() ) );
		return new Translatable( null, "Military Airship", null, null, propertiesBag, militaryAirship.toString() );
	}

	/**
	 * 
	 * @param airship
	 * @return
	 */
	private static Map< String, Object > createAirshipPropertyBag( Airship airship )
	{
		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( CommandLineStringsDictionary.FLIGHTID, airship.getIdentification() );
		propertiesBag.put( CommandLineStringsDictionary.LATITUDE,
				String.valueOf( airship.getCoordinates().getLatitude().getValue() ) );
		propertiesBag.put( CommandLineStringsDictionary.LONGITUDE,
				String.valueOf( airship.getCoordinates().getLongitude().getValue() ) );
		propertiesBag.put( CommandLineStringsDictionary.ALTITUDE,
				String.valueOf( airship.getCoordinates().getAltitude().getValue() ) );
		propertiesBag.put( CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE,
				String.valueOf( airship.getAirCorridor().getMinAltitude() ) );
		propertiesBag.put( CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE,
				String.valueOf( airship.getAirCorridor().getMaxAltitude() ) );
		propertiesBag.put( "Is Outside The Given Corridor", String.valueOf( airship.isTransgressing() ) );

		return propertiesBag;
	}
}
