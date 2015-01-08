package test.java.cli.outputformatters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import main.java.cli.CLIStringsDictionary;
import main.java.cli.outputformatters.Translatable;
import main.java.cli.outputformatters.totranslatableconversors.ToTranslatableConversor;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.formattersexceptions.UnknownTypeException;

import org.junit.Test;

public class ToTranslatableConversorTest
{
	@Test
	public void shouldGetAMessageTranslatableIfOptionalHaveaNull() throws UnknownTypeException
	{
		// Arrange
		String str = "optional have a null object";
		Optional< Object > optional = new Optional< Object >( null, new Exception( str ) );

		// Act
		Translatable t = ToTranslatableConversor.convert( optional );

		// Assert
		assertEquals( t.getPropertiesBag().get( "message" ), str );
	}

	@Test
	public void shouldGetAMessageTranslatableIfOptionalIsEmpty() throws UnknownTypeException
	{
		//Arrange
		String str = "optional is empty";
		Collection< Object > collection = new ArrayList< Object >();
		Optional< Collection< ? >> optional = new Optional< Collection< ? > >( collection, str );

		// Act
		Translatable t = ToTranslatableConversor.convert( optional );
		
		// Assert
		assertEquals( t.getPropertiesBag().get( "message" ), str );
	}

	@Test
	public void shouldGetAnUnknownTypeExceptionIfIterableIsEmpty()
	{
		// Arrange
		Iterable< Object > iterable = new ArrayList< Object >();

		// Act
		try
		{
			@SuppressWarnings( "unused" )
			Translatable t = ToTranslatableConversor.convert( iterable );
			// should not get this one
			assertTrue( false );
		}
		// Assert
		catch( UnknownTypeException e )
		{
			assertEquals( e.getMessage(), "Cannot convert empty iterables to string." );
		}
	}

	@Test
	public void shouldGetAnIteratorTranslatable() throws InvalidArgumentException, UnknownTypeException
	{
		// Arrange
		User user1 = new User( "G", "password", "G1@email" );
		User user2 = new User( "G2", "password", "G2@email" );
		Collection< User > collection = new ArrayList< User >();
		
		// Act
		collection.add( user1 );
		collection.add( user2 );
		Translatable t = ToTranslatableConversor.convert( collection );
		
		// Assert
		assertEquals( ( ( Translatable )t.getPropertiesBag().get( "G" ) ).getPropertiesBag().get( "email" ),
				"G1@email" );
		assertEquals( ( ( Translatable )t.getPropertiesBag().get( "G2" ) ).getPropertiesBag().get( "email" ),
				"G2@email" );

	}

	@Test
	public void shouldGetUnknownTypeExceptionIfObjetTypeDoesNotBelongToTheTranslatableConvention()
		throws InvalidArgumentException
	{
		// Arrange
		Database< User > ObjectThatCanNotBeATranslatable = new InMemoryUsersDatabase( "database Name" );


		try
		{
			ToTranslatableConversor.convert( ObjectThatCanNotBeATranslatable );
			// should not get this one
			assertTrue( false );
		}
		catch( UnknownTypeException e )
		{
			assertEquals( e.getMessage(), "Cannot convert this type of object! (InMemoryUsersDatabase)." );

		}

	}

	@Test
	public void shouldGetAnStringTranslatable() throws UnknownTypeException
	{
		// Arrange
		String str = "E o Sporting é o nosso grande amor";

		// Act
		Translatable t = ToTranslatableConversor.convert( str );

		// Assert
		assertEquals( t.getPropertiesBag().get( "message" ), str );
	}

	@Test
	public void shouldGetAnUserTranslatable() throws InvalidArgumentException, UnknownTypeException
	{
		// Arrange
		Optional< User > user =
				new Optional< User >( new User( "username", "password", "email@" ), new Exception() );

		// Act
		Translatable t = ToTranslatableConversor.convert( user );

		// Assert
		assertEquals( t.getTag(), "user" );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.USERNAME ), "username" );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.EMAIL ), "email@" );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.FULLNAME ), "" );
	}

	@Test
	public void shouldGetAnCivilTranslatable() throws Exception
	{
		// Arrange
		Optional< Airship > airship =
				new Optional< Airship >( new CivilAirship( 0, 0, 0, 10, 0, 10 ), new Exception() );

		// Act
		Translatable t = ToTranslatableConversor.convert( airship );

		// Assert
		assertEquals( t.getTag(), "civilAirship" );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.FLIGHTID ), airship.get()
																						.getIdentification() );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LATITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LONGITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.ALTITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE ), 10.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.NUMBEROFPASSENGERS ), 10 );

	}

	@Test
	public void shouldGetAnMilitaryTranslatable() throws Exception
	{
		//Arrange
		Optional< Airship > airship =
				new Optional< Airship >( new MilitaryAirship( 0, 0, 0, 10, 0, true ), new Exception() );

		// Act
		Translatable t = ToTranslatableConversor.convert( airship );
		
		// Assert
		assertEquals( t.getTag(), "militaryAirship" );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.FLIGHTID ), airship.get()
																						.getIdentification() );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LATITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.LONGITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.ALTITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE ), 0.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE ), 10.0 );
		assertEquals( t.getPropertiesBag().get( CLIStringsDictionary.HASARMOUR ), true );
	}

}
