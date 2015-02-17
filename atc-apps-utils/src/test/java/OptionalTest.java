import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;
import utils.Optional;
import exceptions.NullValueInOptionalException;


/**
 * Test class that targets the class {@link Optional}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class OptionalTest {
    
    
    
    // USING THE FIRST CONSTRUCTOR
    
    
    
    @Test
    public void optionalShouldRepresentANonNullValue() throws Exception {
    
        Optional< String > strOpt = new Optional< String >( "OK string", new Exception() );
        Assert.assertFalse( strOpt.isNull() );
    }
    
    @Test
    public void optionalShouldRepresentANullValue() throws Exception {
    
        Optional< String > strOpt = new Optional< String >( null, new Exception() );
        Assert.assertTrue( strOpt.isNull() );
    }
    
    
    
    @Test
    public void shouldGetNonNullValue() throws Exception {
    
        Integer value = Integer.valueOf( 2 );
        Exception exception = null;
        Assert.assertEquals( value, new Optional< Integer >( value, exception ).get() );
    }
    
    @Test
    public void shouldThrowExceptionIfTryingToGetNullValue() throws Exception {
    
        Optional< Boolean > optional = new Optional<>( null, new Exception() );
        Assert.assertTrue( optional.isNull() );
    }
    
    @Test( expected = NullValueInOptionalException.class )
    public void shouldThrowNullValueInOptionalException() throws Exception {
    
        Exception exception = null;
        Assert.assertNull( new Optional< Integer >( null, exception ).get() );
    }
    
    
    
    // USING THE FIRST CONSTRUCTOR
    
    
    
    @Test
    public void nullValuesShouldNotBeConsideredEmpty() {
    
        Assert.assertFalse( new Optional< String >( null, "TEST" ).isEmpty() );
    }
    
    @Test
    public void doublesShouldNotBeConsideredEmpty() {
    
        Assert.assertFalse( new Optional< Double >( Double.valueOf( "6.98" ), "TEST" ).isEmpty() );
    }
    
    @Test
    public void nonEmptyCollectionShouldNotBeConsideredEmpty() {
    
        Collection< String > list = new ArrayList<>();
        list.add( "HAS A ENTRY!" );
        
        Assert.assertFalse( new Optional< Collection< String > >( list, "TEST" ).isEmpty() );
    }
    
    @Test
    public void nonEmptyMapShouldNotBeConsideredEmpty() {
    
        Map< String, Integer > map = new HashMap<>();
        map.put( "UM", 1 );
        
        Assert.assertFalse( new Optional< Map< String, Integer > >( map, "TEST" ).isEmpty() );
    }
    
    @Test
    public void emptyCollectionsAndMapsShouldBeConsideredEmpty() {
    
        Assert.assertTrue( new Optional< TreeSet< String > >( new TreeSet<>(), "TEST" ).isEmpty() );
        Assert.assertTrue( new Optional< TreeMap< String, Boolean > >( new TreeMap<>(), "TEST" ).isEmpty() );
    }
    
    
    
    // EQUALS
    
    @Test
    public void shouldTestEqualityCorrectly() {
    
        Integer value = Integer.valueOf( "2" );
        Optional< Integer > optInt = new Optional< Integer >( Integer.valueOf( "2" ), null, null );
        
        Assert.assertTrue( optInt.equals( optInt ) );
        Assert.assertFalse( optInt.equals( value ) );
        Assert.assertTrue( optInt.equals( new Optional<Integer>( Integer.valueOf( "2" ), new Exception()) ));
        Assert.assertTrue( optInt.equals( new Optional<Integer>( Integer.valueOf( "2" ), "toString" )));
        Assert.assertFalse( optInt.equals( new Optional<Integer>( Integer.valueOf( "3" ), null, null )));
        Assert.assertFalse( optInt.equals( new Optional<String>( "2", null, null )));
    }
    
    @Test
    public void shouldReturnCorrectHashcode() {

        Integer value = Integer.valueOf( "2" );
        Optional< Integer > optInt = new Optional< Integer >( Integer.valueOf( "2" ), null, null );
        
        Assert.assertEquals( 0, new Optional<String>(null, new Exception()).hashCode());        
        Assert.assertEquals( value.hashCode(), optInt.hashCode());  
    }
//    
//    @Test
//    public void test5() throws InvalidArgumentException {
//    
//        new Optional< Object >( new Object(), new InvalidArgumentException(), "TEST" );
//    }
//    
//    @Test
//    public void test6() throws InvalidArgumentException {
//    
//        new Optional< Map< String, String > >( new HashMap<>(), null, "TEST" );
//    }
//    
//    @Test
//    public void test7() throws InvalidArgumentException {
//    
//        Optional< String > optional = new Optional<>( "pantunes", new Exception(), "TEST" );
//        Assert.assertFalse( optional.isNull() );
//    }
//    
//    @Test
//    public void test9() throws InvalidArgumentException {
//    
//        Map< String, String > MapTester = new HashMap< String, String >();
//        
//        MapTester.put( "Tester", "OptionalTeser" );
//        
//        Optional< Map< String, String >> optional =
//                new Optional< Map< String, String >>( MapTester, new InvalidArgumentException(),
//                                                      "TEST" );
//        
//        Assert.assertFalse( optional.isEmpty() );
//    }
//    
//    @Test
//    public void test10() throws Exception {
//    
//        Map< String, String > MapTester = new HashMap< String, String >();
//        
//        Optional< Map< String, String >> optional =
//                new Optional< Map< String, String >>( MapTester, new InvalidArgumentException(),
//                                                      "TEST" );
//        
//        Assert.assertTrue( optional.isEmpty() );
//        
//    }
//    
//    @Test
//    public void test11() throws InvalidArgumentException {
//    
//        Collection< String > MapTester = new ArrayList< String >();
//        
//        MapTester.add( "Tester" );
//        
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( MapTester, new InvalidArgumentException(),
//                                                     "TEST" );
//        
//        Assert.assertFalse( optional.isEmpty() );
//    }
//    
//    @Test
//    public void test12() throws InvalidArgumentException {
//    
//        Collection< String > MapTester = new ArrayList< String >();
//        
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( MapTester, new InvalidArgumentException(),
//                                                     "TEST" );
//        
//        Assert.assertTrue( optional.isEmpty() );
//    }
//    
//    @Test
//    public void test13() throws InvalidArgumentException {
//    
//        Collection< String > MapTester = new ArrayList< String >();
//        
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( MapTester, new InvalidArgumentException(),
//                                                     "TEST" );
//        
//        Assert.assertTrue( optional.hasSpecificStringRepresentationIfEmpty() );
//    }
//    
//    @Test
//    public void test14() throws InvalidArgumentException {
//    
//        Collection< String > MapTester = new ArrayList< String >();
//        
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( MapTester, new InvalidArgumentException(),
//                                                     null );
//        
//        Assert.assertFalse( optional.hasSpecificStringRepresentationIfEmpty() );
//    }
//    
//    @Test
//    public void test15() throws InvalidArgumentException {
//    
//        Collection< String > MapTester = new ArrayList< String >();
//        MapTester.add( "Tester" );
//        
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( MapTester, new InvalidArgumentException(),
//                                                     "TEST" );
//        
//        Assert.assertFalse( optional.hasSpecificStringRepresentationIfEmpty() );
//    }
//    
//    @Test
//    public void test16() throws InvalidArgumentException {
//    
//        Collection< String > MapTester = new ArrayList< String >();
//        MapTester.add( "Tester" );
//        
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( MapTester, new InvalidArgumentException(),
//                                                     null );
//        
//        Assert.assertFalse( optional.hasSpecificStringRepresentationIfEmpty() );
//    }
//    
//    @Test
//    public void test17() throws Exception {
//    
//        String Test = "Tester";
//        Collection< String > MapTester = new ArrayList< String >();
//        MapTester.add( Test );
//        
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( MapTester, new InvalidArgumentException(),
//                                                     null );
//        Assert.assertEquals( Test, optional.get().iterator().next() );
//    }
//    
//    @Test( expected = InvalidArgumentException.class )
//    public void test18() throws Exception {
//    
//        Optional< Collection< String >> optional =
//                new Optional< Collection< String >>( null, new InvalidArgumentException(), null );
//        Assert.assertNull( optional.get().iterator().next() );        
//    }
    
}
