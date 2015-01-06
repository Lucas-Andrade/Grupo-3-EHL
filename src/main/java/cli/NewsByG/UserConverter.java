package main.java.cli.NewsByG;

import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.users.User;
import main.java.cli.translations.translatables.Translatable;
import main.java.cli.CommandLineStringsDictionary;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
class UserConverter extends Converter
{
	@Override
	Translatable convert( Object user )
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
