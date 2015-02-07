package outputformatters_tests;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import outputformatters.totranslatableconverters.ToTranslatableConverter;
import outputformatters.translators.ToPlainTextTranslator;
import utils.Optional;
import utils.OptionsList;
import utils.exceptions.formattersexceptions.UnknownTranslatableException;
import utils.exceptions.formattersexceptions.UnknownTypeException;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


/**
 * Tests to target {@link ToPlainTextTranslator}. These are not unitary-tests, are visual tests
 * meant to be seen on console.
 * 
 * Due the maven building, the class is commented, to see the tests uncomment the class.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "unused" )
public class ToPlainTextTranslatorTest {
//    
//	@Test
//	public void test() throws InvalidArgumentException, UnknownTypeException,
//			UnknownTranslatableException {
//		
//		
//		ToPlainTextTranslator t = new ToPlainTextTranslator();
//		String visualDelimiter = "\n\n\n-----------------------------------------------\n\n\n";
//		
//		
//		// NULL
//		Optional< ? > nulo = new Optional<>( null, new Exception( "nulo" ),
//				"empty" );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( nulo ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// EMPTY ITERABLE
//		Optional< ? > empty = new Optional<>( new ArrayList<>(), "empty" );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( empty ) ) );
//		
//		
//		// OBJECTS INSTANTIATION
//		User user1 = new User( "Pedro", "pass", "pedro@gmail.com",
//				"Pedro Antunes" );
//		User user2 = new User( "Gonçalo", "pass2", "Gonçalo@gmail.com" );
//		Airship airship1 = new CivilAirship( 30, 40, 6000, 3000, 300, 6 );
//		Airship airship2 = new MilitaryAirship( 30, 40, 5000, 4000, 200, false );
//		Airship airship3 = new MilitaryAirship( 30, 40, 2000, 3000, 200, true );
//		CivilAirship airship4 = (CivilAirship)airship1;
//		MilitaryAirship airship5 = (MilitaryAirship)airship2;
//		MilitaryAirship airship6 = (MilitaryAirship)airship3;
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// USER
//		Optional< User > user = new Optional<>( user1, new Exception( "exc1" ) );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( user ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// AIRSHIP Civ
//		Optional< Airship > a1 = new Optional<>( airship1, new Exception(
//				"exc2" ) );
//		System.out.println( t.encode( ToTranslatableConverter.convert( a1 ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// AIRSHIP Mil
//		Optional< Airship > a2 = new Optional<>( airship2, new Exception(
//				"exc2" ) );
//		System.out.println( t.encode( ToTranslatableConverter.convert( a2 ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// CIVIL
//		Optional< CivilAirship > civil = new Optional<>( airship4,
//				new Exception( "exc4" ) );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( civil ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// MILITARY
//		Optional< MilitaryAirship > milit = new Optional<>( airship5,
//				new Exception( "exc5" ) );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( milit ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// ITERABLE COM 2 USERS
//		ArrayList< User > us = new ArrayList<>();
//		us.add( user1 );
//		us.add( user2 );
//		Optional< Iterable< User >> users = new Optional<>( us, new Exception(
//				"null" ), "empty1" );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( users ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// ITERABLE COM 2 CIVIS
//		ArrayList< Airship > cs = new ArrayList<>();
//		cs.add( airship1 );
//		cs.add( airship4 );
//		Optional< Iterable< Airship >> civils = new Optional<>( cs,
//				new Exception( "null" ), "empty2" );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( civils ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// ITERABLE COM 2MILITS
//		ArrayList< MilitaryAirship > ms = new ArrayList<>();
//		ms.add( airship6 );
//		ms.add( airship5 );
//		Optional< Iterable< MilitaryAirship >> milits = new Optional<>( ms,
//				new Exception( "null" ), "empty3" );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( milits ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// ITERABLE COM 4 AIRSHIPS
//		ArrayList< Airship > as = new ArrayList<>();
//		as.add( airship4 );
//		as.add( airship5 );
//		as.add( airship1 );
//		as.add( airship3 );
//		Optional< Iterable< Airship >> airships = new Optional<>( as,
//				new Exception( "null" ), "empty4" );
//		System.out.println( t.encode( ToTranslatableConverter
//				.convert( airships ) ) );
//		
//		
//		
//		System.out.println( visualDelimiter );
//		
//		// OPTIONSLIST
//		Map< String, String > options = new HashMap<>();
//		options.put( "key1", "value1" );
//		options.put( "chave2", "valor2" );
//		options.put( "KEY3", "palavra" );
//		OptionsList list = new OptionsList( options );
//		System.out
//				.println( t.encode( ToTranslatableConverter.convert( list ) ) );
//		
//		
//	}
    
    
}
