package main.java.cli.outputformatters.totranslatableconversors;


import java.util.LinkedHashMap;
import java.util.Map;

import main.java.cli.outputformatters.Translatable;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;

/**
 * Class that contains all classes whose instances convert instances of
 * {@code Iterable}s into {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class IterablesToTranslatables
{
	/**
	 * Class whose instances convert instances of {@code Iterable<User>} into
	 * {@link Translatables}.
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	static class IterableUserConversor
		extends Conversor
	{
		@SuppressWarnings( "unchecked" )
		@Override
		Translatable convert( Object iterableOfUsers ) throws UnknownTypeException
		{
			Iterable< User > it;
			try
			{
				it = ( Iterable< User > )iterableOfUsers;
			}
			catch( ClassCastException e )
			{
				throw new UnknownTypeException( "Could not convert " + iterableOfUsers
						+ " into a translatable." );
			}

			Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
			for( User user : it )
			{
				propertiesBag.put( user.getIdentification(), ToTranslatableConversor.convert( user ) );
			}

			return new Translatable( "users", "user", null, null, propertiesBag, null );
		}
	}



	/**
	 * Class whose instances convert instances of {@code Iterable<Airship>} into
	 * {@link Translatables}.
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	static class IterableAirshipConversor
		extends Conversor
	{
		@SuppressWarnings( "unchecked" )
		@Override
		Translatable convert( Object iterableOfAirships ) throws UnknownTypeException
		{

			Iterable< Airship > it;
			try
			{
				it = ( Iterable< Airship > )iterableOfAirships;
			}
			catch( ClassCastException e )
			{
				throw new UnknownTypeException( "Could not convert " + iterableOfAirships
						+ " into a translatable." );
			}

			Map< String, Object > propertiesBag = new LinkedHashMap< String, Object >();
			for( Airship user : it )
			{
				propertiesBag.put( user.getIdentification(), ToTranslatableConversor.convert( user ) );
			}

			return new Translatable( "airships", "airship", null, null, propertiesBag, null );
		}
	}
}
