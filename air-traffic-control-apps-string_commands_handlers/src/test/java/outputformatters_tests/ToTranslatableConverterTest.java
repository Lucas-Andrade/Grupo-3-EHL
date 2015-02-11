package outputformatters_tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import org.junit.Test;
import outputformatters.Translatable;
import outputformatters.totranslatableconverters.ToTranslatableConverter;
import utils.StringCommandsDictionary;
import utils.CompletionStatus;
import utils.Optional;
import utils.OptionsList;
import utils.exceptions.formattersexceptions.UnknownTypeException;
import databases.Database;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


/**
 * Test case which targets the class {@link ToTranslatableConverter}.
 * <p>
 * <b>Implementation notes:</b>
 * </p>
 * <ul>
 * <li>This class makes use of the static fields in {@link StringCommandsDictionary}.</li>
 * </ul>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToTranslatableConverterTest {
    
    /* this test case also tests the Converter's subclasses of the package
     * main.java.cli.outputformatters.totranslatableconversors*/
    
    
    // Conversion of Optionals
    
    @Test
    public void shouldConvertAnOptionalThatRepresentsNullIntoATranslatableOfString()
        throws UnknownTypeException {
        
        // Arrange
        String message = "optional has a null object";
        
        // Act
        Translatable t =
                ToTranslatableConverter.convert( new Optional<>( null, new Exception( message ) ) );
        
        // Assert
        assertNull( t.getTag() );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertEquals( t.getPropertiesBag().get( "message" ), message );
    }
    
    @Test
    public void shouldConvertAnOptionalThatRepresentsAnEmptyCollectionIntoATranslatableOfString()
        throws UnknownTypeException {
        
        // Arrange
        String message = "optional is empty";
        
        // Act
        Translatable t =
                ToTranslatableConverter.convert( new Optional<>( new ArrayList<>(), message ) );
        
        // Assert
        assertNull( t.getTag() );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertEquals( t.getPropertiesBag().get( "message" ), message );
    }
    
    @Test
    public void shouldConvertAnOptionalThatRepresentsASimpleInstanceIntoTheCorrectTranslatable()
        throws InvalidArgumentException, UnknownTypeException {
        
        // Arrange
        Optional< User > user =
                new Optional< User >( new User( "username", "password", "email@" ), new Exception() );
        
        // Act
        Translatable t = ToTranslatableConverter.convert( user );
        
        // Assert
        assertEquals( t.getTag(), "user" );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertEquals( "username", t.getPropertiesBag().get( StringCommandsDictionary.USERNAME ) );
        assertNull( t.getPropertiesBag().get( StringCommandsDictionary.PASSWORD ) );
        assertEquals( "email@", t.getPropertiesBag().get( StringCommandsDictionary.EMAIL ) );
        assertEquals( "", t.getPropertiesBag().get( StringCommandsDictionary.FULLNAME ) );
    }
    
    // Conversion of Iterables and Simple Types
    
    @Test
    public void shouldConvertAnEmptyIterableIntoATranslatableOfString() throws UnknownTypeException {
        
        // Arrange & Act
        Translatable t = ToTranslatableConverter.convert( new ArrayList<>() );
        
        // Assert
        assertNull( t.getTag() );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertEquals( t.getPropertiesBag().get( "message" ), "no result found" );
    }
    
    @Test
    public void shouldConvertAnIterableOfUsersIntoAnTranslatableOfIterableOfUsers()
        throws InvalidArgumentException, UnknownTypeException {
        
        // Arrange & Act
        User user1 = new User( "G", "password1", "G1@email" );
        User user2 = new User( "G2", "password2", "G2@email", "G2 full" );
        Collection< User > collection = new ArrayList< User >();
        collection.add( user1 );
        collection.add( user2 );
        Translatable itUsersTransl = ToTranslatableConverter.convert( collection );
        
        // Assert
        assertEquals( "users", itUsersTransl.getTag() );
        assertEquals( "user", itUsersTransl.getEntryTag() );
        assertNull( itUsersTransl.getKeyTag() );
        assertNull( itUsersTransl.getValueTag() );
        assertTrue( itUsersTransl.getPropertiesBag().get( "G" ) instanceof Translatable );
        assertTrue( itUsersTransl.getPropertiesBag().get( "G2" ) instanceof Translatable );
        
        Translatable g = ((Translatable)itUsersTransl.getPropertiesBag().get( "G" ));
        Translatable g2 = ((Translatable)itUsersTransl.getPropertiesBag().get( "G2" ));
        
        assertEquals( "G", g.getPropertiesBag().get( StringCommandsDictionary.USERNAME ) );
        assertEquals( "G1@email", g.getPropertiesBag().get( StringCommandsDictionary.EMAIL ) );
        assertNull( g2.getPropertiesBag().get( StringCommandsDictionary.PASSWORD ) );
        assertEquals( "", g.getPropertiesBag().get( StringCommandsDictionary.FULLNAME ) );
        assertEquals( "G2 full", g2.getPropertiesBag().get( StringCommandsDictionary.FULLNAME ) );
        
    }
    
    @Test
    public void shouldConvertAnIterableOfAirshipsIntoATranslatableOfIterableOfAirships()
        throws InvalidArgumentException, UnknownTypeException {
        
        // Arrange & Act
        Airship a1 = new CivilAirship( 0, 1, 2, 10, 3, 10 );
        Airship a2 = new MilitaryAirship( 4, 5, 6, 10, 7, true );
        Collection< Airship > iterable = new ArrayList<>();
        iterable.add( a1 );
        iterable.add( a2 );
        Translatable itAirshipsTransl = ToTranslatableConverter.convert( iterable );
        
        // Assert
        assertEquals( "airships", itAirshipsTransl.getTag() );
        assertEquals( "airship", itAirshipsTransl.getEntryTag() );
        assertNull( itAirshipsTransl.getKeyTag() );
        assertNull( itAirshipsTransl.getValueTag() );
        
        Map< String, Object > bag = itAirshipsTransl.getPropertiesBag();
        
        assertTrue( bag.get( a1.getIdentification() ) instanceof Translatable );
        assertTrue( bag.get( a1.getIdentification() ) instanceof Translatable );
        
        Translatable t1 = ((Translatable)bag.get( a1.getIdentification() ));
        
        assertEquals( ((Translatable)bag.get( a1.getIdentification() )).getTag(), "civilAirship" );
        assertEquals( a1.getIdentification(),
                      t1.getPropertiesBag().get( StringCommandsDictionary.FLIGHTID ) );
        
        Translatable t2 = ((Translatable)bag.get( a2.getIdentification() ));
        
        assertEquals( ((Translatable)bag.get( a2.getIdentification() )).getTag(), "militaryAirship" );
        assertEquals( a2.getIdentification(),
                      t2.getPropertiesBag().get( StringCommandsDictionary.FLIGHTID ) );
        
    }
    
    @Test
    public void shouldConvertACivilAirshipIntoATranslatableOfCivilAirship()
        throws InvalidArgumentException, UnknownTypeException {
        
        // Arrange
        CivilAirship a = new CivilAirship( 0, 1, 2, 4, 3, 10 );
        Airship a0 = new CivilAirship( 0, 1, 2, 4, 3, 10 );
        
        // Act
        Translatable t = ToTranslatableConverter.convert( a );
        Map< String, Object > bag = t.getPropertiesBag();
        
        // Assert
        assertEquals( t.getTag(), "civilAirship" );
        assertEquals( ToTranslatableConverter.convert( a0 ).getTag(), "civilAirship" );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertEquals( a.getIdentification(), bag.get( StringCommandsDictionary.FLIGHTID ) );
        assertEquals( 0.0, bag.get( StringCommandsDictionary.LATITUDE ) );
        assertEquals( 1.0, bag.get( StringCommandsDictionary.LONGITUDE ) );
        assertEquals( 2.0, bag.get( StringCommandsDictionary.ALTITUDE ) );
        assertEquals( 4.0, bag.get( StringCommandsDictionary.AIRCORRIDOR_MAXALTITUDE ) );
        assertEquals( 3.0, bag.get( StringCommandsDictionary.AIRCORRIDOR_MINALTITUDE ) );
        assertEquals( 10, bag.get( StringCommandsDictionary.NUMBEROFPASSENGERS ) );
        assertNull( bag.get( StringCommandsDictionary.HASARMOUR ) );
    }
    
    @Test
    public void shouldConvertAnIterableOfAirshipsIntoAnTranslatableOfIterableOfAirships()
        throws InvalidArgumentException, UnknownTypeException {
        
        // Arrange
        Airship a = new MilitaryAirship( 4, 5, 6, 10, 7, true );
        
        // Act
        Translatable t = ToTranslatableConverter.convert( a );
        Map< String, Object > bag = t.getPropertiesBag();
        
        assertEquals( t.getTag(), "militaryAirship" );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertEquals( a.getIdentification(), bag.get( StringCommandsDictionary.FLIGHTID ) );
        assertEquals( 4.0, bag.get( StringCommandsDictionary.LATITUDE ) );
        assertEquals( 5.0, bag.get( StringCommandsDictionary.LONGITUDE ) );
        assertEquals( 6.0, bag.get( StringCommandsDictionary.ALTITUDE ) );
        assertEquals( 10.0, bag.get( StringCommandsDictionary.AIRCORRIDOR_MAXALTITUDE ) );
        assertEquals( 7.0, bag.get( StringCommandsDictionary.AIRCORRIDOR_MINALTITUDE ) );
        assertNull( bag.get( StringCommandsDictionary.NUMBEROFPASSENGERS ) );
        assertEquals( true, bag.get( StringCommandsDictionary.HASARMOUR ) );
        
    }
    
    // Conversion of Maps
    
    @Test
    public void shouldConvertAOptionsListIntoATranslatableOfMap() throws UnknownTypeException {
        
        // Arrange
        Map< String, String > options = new HashMap< String, String >();
        options.put( "command1", "description1" );
        options.put( "command2", "description2" );
        OptionsList list = new OptionsList( options );
        
        // Act
        Translatable t = ToTranslatableConverter.convert( list );
        
        // Assert
        assertEquals( "options", t.getTag() );
        assertEquals( "option", t.getEntryTag() );
        assertEquals( "command", t.getKeyTag() );
        assertEquals( "description", t.getValueTag() );
        assertEquals( t.getPropertiesBag().get( "command1" ), "description1" );
        assertEquals( t.getPropertiesBag().get( "command2" ), "description2" );
    }
    
    // Conversion of Strings
    
    @Test
    public void shouldConvertStringIntoTranslatableOfString() throws UnknownTypeException {
        
        // Arrange
        String str = "E o Sporting é o nosso grande amor!";
        
        // Act
        Translatable t = ToTranslatableConverter.convert( str );
        
        // Assert
        assertNull( t.getTag() );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertFalse( t.getPropertiesBag().isEmpty() );
        assertTrue( t.getPropertiesBag().containsValue( "E o Sporting é o nosso grande amor!" ) );
    }
    
    @Test
    public void shouldConvertAFailureCompletionStatusIntoTranslatableOfString()
        throws UnknownTypeException {
        
        // Arrange
        String str = "«Está tudo mal! (e vai estar sempre.)» by PP";
        
        // Act
        Translatable t = ToTranslatableConverter.convert( str );
        
        // Assert
        assertNull( t.getTag() );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertFalse( t.getPropertiesBag().isEmpty() );
        assertTrue( t.getPropertiesBag()
                     .containsValue( "«Está tudo mal! (e vai estar sempre.)» by PP" ) );
    }
    
    @Test
    public void shouldConvertASuccessCompletionStatusIntoTranslatableOfString()
        throws UnknownTypeException {
        
        // Arrange
        CompletionStatus status = new CompletionStatus( true, "Este teste vai estar green!" );
        
        // Act
        Translatable t = ToTranslatableConverter.convert( status );
        
        // Assert
        assertNull( t.getTag() );
        assertNull( t.getEntryTag() );
        assertNull( t.getKeyTag() );
        assertNull( t.getValueTag() );
        assertFalse( t.getPropertiesBag().isEmpty() );
        assertTrue( t.getPropertiesBag().containsValue( "Este teste vai estar green!" ) );
    }
    
    // Unconvertible Types
    
    @Test
    public void shouldThrowExceptionIfTryingToConvertUnknownType1() throws InvalidArgumentException {
        
        // Arrange
        Database< User > objectThatCantBeConverted = new InMemoryUsersDatabase( "database Name" );
        
        // Act & Assert
        try {
            
            // expected to throw UnknownTypeException:
            ToTranslatableConverter.convert( objectThatCantBeConverted );
            
            assertTrue( false );
        }
        catch( UnknownTypeException e ) {
            assertEquals( "Cannot convert instances of type InMemoryUsersDatabase", e.getMessage() );
        }
        
    }
    
    @Test
    public void shouldThrowExceptionIfTryingToConvertUnknownType2() throws InvalidArgumentException {
        
        // Arrange
        Collection< Integer > objectThatCantBeConverted = new TreeSet<>();
        objectThatCantBeConverted.add( 1 );
        
        // Act & Assert
        try {
            
            // expected to throw UnknownTypeException:
            ToTranslatableConverter.convert( objectThatCantBeConverted );
            
            assertTrue( false );
        }
        catch( UnknownTypeException e ) {
            assertEquals( "Cannot convert instances of type itInteger", e.getMessage() );
        }
        
    }
    
    @Test
    public void shouldThrowExceptionIfTryingToConvertUnknownType3() throws InvalidArgumentException {
        
        // Arrange
        Map< String, User > objectThatCantBeConverted = new HashMap<>();
        
        // Act & Assert
        try {
            
            // expected to throw UnknownTypeException:
            ToTranslatableConverter.convert( objectThatCantBeConverted );
            
            assertTrue( false );
        }
        catch( UnknownTypeException e ) {
            assertEquals( "Cannot convert instances of type HashMap", e.getMessage() );
        }
        
    }
    
    
}
