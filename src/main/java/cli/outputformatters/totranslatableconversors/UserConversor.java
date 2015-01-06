package main.java.cli.outputformatters.totranslatableconversors;


import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.users.User;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;


/**
 * Class whose instances convert instances of {@link User} into
 * {@link Translatables}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class UserConversor extends Converter
{
	
	@Override
	Translatable convert( Object user ) throws UnknownTypeException {
		
		User u;
		try
		{
			u = (User)user;
		}
		catch( ClassCastException e )
		{
			throw new UnknownTypeException( "Could not convert " + user
					+ " into a translatable." );
		}
		
		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( "username", u.getIdentification() );
		propertiesBag.put( "email", u.getEmail() );
		propertiesBag.put( "fullname", u.getFullName() );
		
		return new Translatable( null, "User", null, null, propertiesBag,
				u.toStringWithoutPassword() );
	}
}
