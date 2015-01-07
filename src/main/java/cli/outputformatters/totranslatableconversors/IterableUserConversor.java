package main.java.cli.outputformatters.totranslatableconversors;


import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.users.User;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of {@code Iterable<User>} into
 * {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class IterableUserConversor extends Conversor
{
	@SuppressWarnings( "unchecked" )
	Translatable convert( Object iterableOfUsers ) throws UnknownTypeException {
		
		Iterable< User > it;
		try
		{
			it = (Iterable< User >)iterableOfUsers;
		}
		catch( ClassCastException e )
		{
			throw new UnknownTypeException( "Could not convert " + iterableOfUsers
					+ " into a translatable." );
		}
		
		Map< String, Object > propertiesBag = new HashMap< String, Object >();		
		for( User user : it )
		{
			propertiesBag.put( user.getIdentification(),
					ToTranslatableConversor.convert( user ) );
		}
		
		return new Translatable( "users", "user", null, null, propertiesBag, null );
	}
}
