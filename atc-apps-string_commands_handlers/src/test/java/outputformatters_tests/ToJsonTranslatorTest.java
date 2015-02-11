package outputformatters_tests;


import java.util.Map;
import outputformatters.translators.ToJsonTranslator;


/**
 * Tests to target {@link ToJsonTranslator}.
 * 
 * THIS IS NOT A UNITARY CLASS TEST, call it a "lock and behold" class test.
 * 
 * Due the maven building, the class is commented, to see the tests uncomment the class.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "unused" )
public class ToJsonTranslatorTest {
    
    private static Map< String, Object > propertiesBag;
    private static ToJsonTranslator json;
    
//	@Before
//	public void before()
//	{
//		propertiesBag = new HashMap< String, Object >();
//		json = new ToJsonTranslator();
//	}
//
//	@Test
//	public void printAStringTranslatable() throws UnknownTranslatableException
//	{
//		propertiesBag.put( "message", "ola mundo!" );
//		Translatable stringTranslatable =
//				new Translatable( null, null, null, null, propertiesBag, "ahhuuuuuiiiiii!" );
//		ToJsonTranslator json = new ToJsonTranslator();
//		System.out.println( json.encode( stringTranslatable ) );
//		System.out.println( "\n\n______________//______________\n\n" );
//	}
//
//
//	@Test
//	public void printASimpleTranslatable() throws UnknownTranslatableException, InvalidArgumentException
//	{
//		propertiesBag.put( "username", "G" );
//		propertiesBag.put( "email", "G@email" );
//		propertiesBag.put( "fullname", "anonymous G" );
//
//		Translatable userTranslatable =
//				new Translatable( "user", null, null, null, propertiesBag, "ahhuuuuuiiiiii!" );
//		System.out.println( json.encode( userTranslatable ) );
//		System.out.println( "\n\n______________//______________\n\n" );
//	}
//
//	@Test
//	public void printAIterableTranslatable() throws UnknownTranslatableException, InvalidArgumentException
//	{
//		Map< String, Object > propertiesBag1 = new HashMap< String, Object >();
//		propertiesBag1.put( "username", "G1" );
//		propertiesBag1.put( "email", "G1@email" );
//		propertiesBag1.put( "fullname", "anonymous G1" );
//		Translatable userTranslatable1 =
//				new Translatable( "user", null, null, null, propertiesBag1, "ahhuuuuuiiiiii!" );
//
//		Map< String, Object > propertiesBag2 = new HashMap< String, Object >();
//		propertiesBag2.put( "username", "G2" );
//		propertiesBag2.put( "email", "G2@email" );
//		propertiesBag2.put( "fullname", "anonymous G2" );
//		Translatable userTranslatable2 =
//				new Translatable( "user", null, null, null, propertiesBag2, "ahhuuuuuiiiiii!" );
//
//		Map< String, Object > propertiesBag3 = new HashMap< String, Object >();
//		propertiesBag3.put( "username", "G3" );
//		propertiesBag3.put( "email", "G3@email" );
//		propertiesBag3.put( "fullname", "anonymous G3" );
//		Translatable userTranslatable3 =
//				new Translatable( "user", null, null, null, propertiesBag3, "ahhuuuuuiiiiii!" );
//
//		propertiesBag.put( "1", userTranslatable1 );
//		propertiesBag.put( "2", userTranslatable2 );
//		propertiesBag.put( "3", userTranslatable3 );
//		Translatable usersTranslatable = new Translatable( "users", "user", null, null, propertiesBag, null );
//
//		System.out.println( json.encode( usersTranslatable ) );
//		System.out.println( "\n\n______________//______________\n\n" );
//	}
//
//
//	@Test
//	public void printAMapTranslatable() throws UnknownTranslatableException, InvalidArgumentException
//	{
//		propertiesBag.put( "GET /users", "get the users" );
//		propertiesBag.put( "GET /user/id", "get a user by id" );
//		propertiesBag.put( "GET /airships", "get the airships" );
//		
//		Translatable mapTranslatable =
//				new Translatable( "options", "option", "command", "description", propertiesBag,
//						"ahhuuuuuiiiiii!" );
//
//		System.out.println( json.encode( mapTranslatable ) );
//		System.out.println( "\n\n______________//______________\n\n" );
//	}
}
