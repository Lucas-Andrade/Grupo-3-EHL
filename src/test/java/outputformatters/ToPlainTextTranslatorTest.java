package test.java.outputformatters;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;
import main.java.cli.outputformatters.totranslatableconversors.ToTranslatableConversor;
import main.java.cli.outputformatters.translators.ToPlainTextTranslator;
import main.java.cli.utils.Optional;
import main.java.cli.utils.OptionsList;
import main.java.cli.utils.exceptions.InvalidArgumentException;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTranslatableException;
import main.java.cli.utils.exceptions.conversorsexceptions.UnknownTypeException;
import org.junit.Test;


/**
 * Tests to target {@link ToPlainTextTranslator}. These are not unitary-tests.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToPlainTextTranslatorTest
{
	
	@Test
	public void test() {
		fail( "Not yet implemented" );
	}
	
	public static void main( String[] args ) throws InvalidArgumentException,
			UnknownTypeException, UnknownTranslatableException {
		
		
		ToPlainTextTranslator t = new ToPlainTextTranslator();
		
		
		
		// NULL
		Optional< ? > nulo = new Optional<>( null, new Exception( "nulo" ),
				"empty" );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( nulo ) ) );
		
		// EMPTY ITERABLE
		Optional< ? > empty = new Optional<>( new ArrayList<>(), "empty" );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( empty ) ) );
		
		
		
		User user1 = new User( "Pedro", "pass", "pedro@gmail.com",
				"Pedro Antunes" );
		User user2 = new User( "Gonçalo", "pass2", "Gonçalo@gmail.com" );
		
		Airship airship1 = new CivilAirship( 30, 40, 6000, 3000, 300, 6 );
		Airship airship2 = new MilitaryAirship( 30, 40, 5000, 4000, 200, false );
		Airship airship3 = new MilitaryAirship( 30, 40, 2000, 3000, 200, true );
		CivilAirship airship4 = (CivilAirship)airship1;
		MilitaryAirship airship5 = (MilitaryAirship)airship2;
		MilitaryAirship airship6 = (MilitaryAirship)airship3;
		
		
		
		// USER
		Optional< User > user = new Optional<>( user1, new Exception( "exc1" ) );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( user ) ) );
		
		// AIRSHIP Civ
		Optional< Airship > a1 = new Optional<>( airship1, new Exception(
				"exc2" ) );
		System.out.println( t.encode( ToTranslatableConversor.convert( a1 ) ) );
		
		// AIRSHIP Mil
		Optional< Airship > a2 = new Optional<>( airship2, new Exception(
				"exc2" ) );
		System.out.println( t.encode( ToTranslatableConversor.convert( a2 ) ) );
		
		// CIVIL
		Optional< CivilAirship > civil = new Optional<>( airship4,
				new Exception( "exc4" ) );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( civil ) ) );
		
		// MILITARY
		Optional< MilitaryAirship > milit = new Optional<>( airship5,
				new Exception( "exc5" ) );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( milit ) ) );
		
		
		
		// ITERABLE COM 2 USERS
		ArrayList< User > us = new ArrayList<>();
		us.add( user1 );
		us.add( user2 );
		Optional< Iterable< User >> users = new Optional<>( us, new Exception(
				"null" ), "empty1" );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( users ) ) );
		
		// ITERABLE COM 2 CIVIS
		ArrayList< Airship > cs = new ArrayList<>();
		cs.add( airship1 );
		cs.add( airship4 );
		Optional< Iterable< Airship >> civils = new Optional<>( cs,
				new Exception( "null" ), "empty2" );
		System.out.println( t.encode( ToTranslatableConversor
				.convert( civils ) ) );
		
		// ITERABLE COM 2MILITS
		ArrayList< MilitaryAirship > ms = new ArrayList<>();
		ms.add( airship6 );
		ms.add( airship5 );
		Optional< Iterable< MilitaryAirship >> milits = new Optional<>( ms,
				new Exception( "null" ), "empty3" );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( milits ) ) );
		
		// ITERABLE COM 4 AIRSHIPS
		ArrayList< Airship > as = new ArrayList<>();
		as.add( airship4 );
		as.add( airship5 );
		as.add( airship1 );
		as.add( airship3 );
		Optional< Iterable< Airship >> airships = new Optional<>( as,
				new Exception( "null" ), "empty4" );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( airships ) ) );
		
		
		
		// OPTIONSLIST
		Map< String, String > options = new HashMap<>();
		options.put( "key1", "value1" );
		options.put( "chave2", "valor2" );
		options.put( "KEY3", "palavra" );
		OptionsList list = new OptionsList( options );
		System.out
				.println( t.encode( ToTranslatableConversor.convert( list ) ) );
		
		
	}
}
