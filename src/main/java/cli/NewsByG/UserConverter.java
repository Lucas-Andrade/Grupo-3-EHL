package main.java.cli.NewsByG;

import java.util.HashMap;
import java.util.Map;

import main.java.cli.CommandLineStringsDictionary;
import main.java.cli.model.users.User;
import main.java.cli.translations.translatables.Translatable;

/**TODO
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class UserConverter implements Converter< User >
{
	@Override
	public Translatable convert( User user )
	{
		Map< String, Object > propertiesBag = new HashMap< String, Object >();
		propertiesBag.put( CommandLineStringsDictionary.USERNAME, user.getIdentification() );
		propertiesBag.put( CommandLineStringsDictionary.EMAIL, user.getEmail() );
		propertiesBag.put( CommandLineStringsDictionary.FULLNAME, user.getFullName() );

		return new Translatable( null, "User", null, null, propertiesBag, user.toString() );
	}
}
