package main.java.cli.outputformatters.totranslatableconversors;

import static org.junit.Assert.assertEquals;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.utils.CommandLineStringsDictionary;
import main.java.cli.utils.Optional;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;

import org.junit.Test;

public class ToTranslatableConversorTest
{

	@Test
	public void shouldNotGetAnTranslatable()
	{
		Object object = null;
		try
		{
			ToTranslatableConversor.convert( object );
		}
		catch( UnknownTypeException e )
		{
			assertEquals(e.getMessage(), "Cannot convert empty iterables to string.");
		}		
	}
	
	@Test
	public void shouldGetAnStringTranslatable()
	{
		String str = "E o Sporting é o nosso grande amor";
		try
		{
			Translatable t = ToTranslatableConversor.convert( str );
			assertEquals(t.getPropertiesBag().get( "Message" ), str);
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void shouldGetAnUserTranslatable() throws InvalidArgumentException
	{
		Optional<User> user = new Optional<User>( new User( "username", "password", "email@" ), new Exception());
		try
		{
			Translatable t = ToTranslatableConversor.convert( user );
			assertEquals(t.getTag(), "User");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.USERNAME ), "username");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.EMAIL ), "email@");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.FULLNAME ), "");
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldGetAnCivilTranslatable() throws Exception
	{
		Optional<Airship> airship = new Optional<Airship>( new CivilAirship( 0, 0, 0, 10, 0, 10 ), new Exception());
		try
		{
			Translatable t = ToTranslatableConversor.convert( airship );
			assertEquals(t.getTag(), "CivilAirship");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.FLIGHTID ), airship.get().getIdentification());
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.LATITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.LONGITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.ALTITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE ), "10.0");
			assertEquals(t.getPropertiesBag().get( "Is Outside The Given Corridor" ), "false");
			assertEquals(t.getPropertiesBag().get( "Number of Passengers" ), "10");
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldGetAnMilitaryTranslatable() throws Exception
	{
		Optional<Airship> airship = new Optional<Airship>( new MilitaryAirship( 0, 0, 0, 10, 0, true ), new Exception());
		try
		{
			Translatable t = ToTranslatableConversor.convert( airship );
			assertEquals(t.getTag(), "MilitaryAirship");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.FLIGHTID ), airship.get().getIdentification());
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.LATITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.LONGITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.ALTITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE ), "0.0");
			assertEquals(t.getPropertiesBag().get( CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE ), "10.0");
			assertEquals(t.getPropertiesBag().get( "Is Outside The Given Corridor" ), "false");
			assertEquals(t.getPropertiesBag().get( "Carries Weapons" ), "true");
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}
	
}
