package main.java.cli.outputformatters.totranslatableconversors;


import java.util.LinkedHashMap;
import java.util.Map;

import main.java.cli.CLIStringsDictionary;
import main.java.cli.outputformatters.Translatable;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;

/**
 * Class that contains all classes whose instances convert instances of
 * {@code simple} into {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class SimpleInstancesToTranslatables
{
	/**
	 * Class whose instances convert instances of {@link User} into
	 * {@link Translatables}.
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	static class UserConversor
		extends Conversor
	{
		@Override
		Translatable convert( Object user ) throws UnknownTypeException
		{
			User u;
			try
			{
				u = ( User )user;
			}
			catch( ClassCastException e )
			{
				throw new UnknownTypeException( "Could not convert " + user + " into a translatable." );
			}

			Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
			propertiesBag.put( "username", u.getIdentification() );
			propertiesBag.put( "email", u.getEmail() );
			propertiesBag.put( "fullname", u.getFullName() );

			return new Translatable( "user", null, null, null, propertiesBag, u.toStringWithoutPassword() );
		}
	}


	/**
	 * Abstract Class to convert whose subclasses instances convert instances of
	 * {@link Airship}'s subclasses into {@link Translatables}.
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	static abstract class AirshipConversor
		extends Conversor
	{
		/**
		 * Create the {@code propertiesBag} for all Airships
		 * 
		 * @param airship
		 * @return the {@code propertiesBag}
		 */
		Map< String, Object > createAirshipPropertiesBag( Airship airship )
		{
			Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
			propertiesBag.put( CLIStringsDictionary.FLIGHTID, airship.getIdentification() );
			propertiesBag.put( CLIStringsDictionary.LATITUDE, airship.getCoordinates()
																		.getLatitude()
																		.getValue() );
			propertiesBag.put( CLIStringsDictionary.LONGITUDE, airship.getCoordinates()
																		.getLongitude()
																		.getValue() );
			propertiesBag.put( CLIStringsDictionary.ALTITUDE, airship.getCoordinates()
																		.getAltitude()
																		.getValue() );
			propertiesBag.put( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE, airship.getAirCorridor()
																					.getMinAltitude() );
			propertiesBag.put( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE, airship.getAirCorridor()
																					.getMaxAltitude() );

			return propertiesBag;
		}
	}



	/**
	 * Class whose instances convert instances of {@link CivilAirship} into
	 * {@link Translatables}.
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	static class CivilAirshipConversor
		extends AirshipConversor
	{
		@Override
		Translatable convert( Object civilAirship ) throws UnknownTypeException
		{
			CivilAirship ca;
			try
			{
				ca = ( CivilAirship )civilAirship;
			}
			catch( ClassCastException e )
			{
				throw new UnknownTypeException( "Could not convert " + civilAirship + " into a translatable." );
			}

			Map< String, Object > propertiesBag = createAirshipPropertiesBag( ca );
			propertiesBag.put( CLIStringsDictionary.NUMBEROFPASSENGERS, ca.getPassengers() );

			return new Translatable( "civilAirship", null, null, null, propertiesBag, ca.toString() );

		}
	}



	/**
	 * Class whose instances convert instances of {@link MilitaryAirships} into
	 * {@link Translatables}.
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	static class MilitaryAirshipConversor
		extends AirshipConversor
	{
		@Override
		Translatable convert( Object militaryAirship ) throws UnknownTypeException
		{

			MilitaryAirship ma;
			try
			{
				ma = ( MilitaryAirship )militaryAirship;
			}
			catch( ClassCastException e )
			{
				throw new UnknownTypeException( "Could not convert " + militaryAirship
						+ " into a translatable." );
			}

			Map< String, Object > propertiesBag = createAirshipPropertiesBag( ma );
			propertiesBag.put( CLIStringsDictionary.HASARMOUR, ma.hasWeapons() );

			return new Translatable( "militaryAirship", null, null, null, propertiesBag, ma.toString() );

		}
	}
}
