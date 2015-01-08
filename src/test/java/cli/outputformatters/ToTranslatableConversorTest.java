package test.java.cli.outputformatters;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import main.java.cli.CLIStringsDictionary;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.outputformatters.totranslatableconversors.ToTranslatableConversor;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;

import org.junit.Test;

public class ToTranslatableConversorTest
{
	//Nulls and emptys
	@Test
	public void shouldGetAMessageTranslatableIfOptionalHaveaNull() throws UnknownTypeException
	{
		//Arrange
		String str = "optional have a null object";
		Optional<Object> optional = new Optional< Object >( null, new Exception( str ) );
		
		//Act
		Translatable t =
				ToTranslatableConversor.convert( optional );
		assertEquals( t.getPropertiesBag().get( "message" ), str );
	}

	@Test
	public void shouldGetAMessageTranslatableIfOptionalIsEmpty() throws UnknownTypeException
	{
		String str = "optional is empty";
		Collection< Object > collection = new ArrayList< Object >();
		Optional<Collection<?>> optional = new Optional< Collection<?> >( collection, str );


		Translatable t =
				ToTranslatableConversor.convert( optional );
		assertEquals( t.getPropertiesBag().get( "message" ), str );
	}
	
	@Test
	public void shouldGetAnUnknownTypeExceptionIfIterableIsEmpty()
	{
		//Arrange
		Iterable<Object> iterable = new ArrayList<Object>();

		//Act
		try
		{
			@SuppressWarnings( "unused" )
			Translatable t = ToTranslatableConversor.convert( iterable );
			//should not get this one
			assertTrue(false);
		}
		//Assert
		catch( UnknownTypeException e )
		{
			assertEquals( e.getMessage(), "Cannot convert empty iterables to string." );
		}
	}

	@Test
	public void shouldGetAnStringTranslatable()
	{
		String str = "E o Sporting é o nosso grande amor";
		try
		{
			Translatable t = ToTranslatableConversor.convert( str );
			assertEquals( t.getPropertiesBag().get( "message" ), str );
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void shouldGetAnUserTranslatable() throws InvalidArgumentException
	{
		Optional< User > user =
				new Optional< User >( new User( "username", "password", "email@" ), new Exception() );
		try
		{
			Translatable t = ToTranslatableConversor.convert( user );
			assertEquals( t.getTag(), "user" );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.USERNAME ), "username" );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.EMAIL ), "email@" );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.FULLNAME ), "" );
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void shouldGetAnCivilTranslatable() throws Exception
	{
		Optional< Airship > airship =
				new Optional< Airship >( new CivilAirship( 0, 0, 0, 10, 0, 10 ), new Exception() );
		try
		{
			Translatable t = ToTranslatableConversor.convert( airship );
			assertEquals( t.getTag(), "civilAirship" );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.FLIGHTID ),
					airship.get().getIdentification() );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LATITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LONGITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.ALTITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE ), 10.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.NUMBEROFPASSENGERS ), 10 );
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}

	@Test
	public void shouldGetAnMilitaryTranslatable() throws Exception
	{
		Optional< Airship > airship =
				new Optional< Airship >( new MilitaryAirship( 0, 0, 0, 10, 0, true ), new Exception() );
		try
		{
			Translatable t = ToTranslatableConversor.convert( airship );
			assertEquals( t.getTag(), "militaryAirship" );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.FLIGHTID ),
					airship.get().getIdentification() );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LATITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LONGITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.ALTITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE ), 0.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE ), 10.0 );
			assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.HASARMOUR ), true );
		}
		catch( UnknownTypeException e )
		{
			e.printStackTrace();
		}
	}

}
