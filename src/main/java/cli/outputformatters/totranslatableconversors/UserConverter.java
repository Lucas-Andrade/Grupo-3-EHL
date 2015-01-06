package main.java.cli.outputformatters.totranslatableconversors;

import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.users.User;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.CommandLineStringsDictionary;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class UserConverter implements Converter
{
	@Override
	public Translatable convert( Object user )
	{
		//TODO cast exceptions
		User u = (User)user;
		
		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( CommandLineStringsDictionary.USERNAME, u.getIdentification() );
		propertiesBag.put( CommandLineStringsDictionary.EMAIL, u.getEmail() );
		propertiesBag.put( CommandLineStringsDictionary.FULLNAME, u.getFullName() );

		return new Translatable( null, "User", null, null, propertiesBag, user.toString() );
	}
}
