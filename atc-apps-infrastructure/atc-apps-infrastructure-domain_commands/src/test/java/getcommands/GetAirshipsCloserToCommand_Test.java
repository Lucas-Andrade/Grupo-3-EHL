package getcommands;


import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import commands.getcommands.GetAirshipsCloserToCommand;
import databases.InMemoryAirshipsDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link GetAirshipsCloserToCommand}
 * {@link GetTheNearestAirshipsToGeographicPositionCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsCloserToCommand_Test {
    
    InMemoryAirshipsDatabase airshipsDatabase;
    User user1;
    
    // Before
    @Before
    public void createAirshipDatabaseUserAndCommandParser() throws InvalidArgumentException {
        
        // Arrange
        airshipsDatabase = new InMemoryAirshipsDatabase( "FirstAirshipsDatabse" );
        user1 = new User( "Pantunes", "pass", "Pantunes@gmail", "Pantunes" );
    }
    
    // Test Normal Dynamic And Prerequisites
    @Test
    public void shouldGiveAllTheAirshipsNearestOfTheGeograficCoordinate() throws Exception {
        
        // Act
        Airship air1 = new CivilAirship( 30, 225, 10000, 20000, 0, 100 );
        Airship air2 = new MilitaryAirship( 0, 315, 15000, 20000, 0, true );
        Airship air3 = new CivilAirship( 45, 180, 12000, 20000, 0, 50 );
        Airship air4 = new MilitaryAirship( -60, 90, 15000, 20000, 0, false );
        Airship air5 = new CivilAirship( -60, 225, 12000, 20000, 0, 50 );
        Airship air6 = new CivilAirship( -90, 360, 12000, 20000, 0, 50 );
        Airship air7 = new CivilAirship( 30, 45, 12000, 20000, 0, 50 );
        
        airshipsDatabase.add( air1, user1 );
        airshipsDatabase.add( air2, user1 );
        airshipsDatabase.add( air3, user1 );
        airshipsDatabase.add( air4, user1 );
        airshipsDatabase.add( air5, user1 );
        airshipsDatabase.add( air6, user1 );
        airshipsDatabase.add( air7, user1 );
        
        String actualResult =
                new GetAirshipsCloserToCommand( airshipsDatabase, 2, 60, 225 ).call()
                                                                                                    .toString();
        
        String expected =
                new StringBuilder( "[" ).append( air1.toString() ).append( ", " )
                                        .append( air3.toString() ).append( "]" ).toString();
        
        // Assert
        assertEquals( expected, actualResult );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullAirshipDatabase()
        throws Exception {
        
        new GetAirshipsCloserToCommand( null, 3, 45, 100 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToGiveANegativeValueToDesiredAirshipsNumber()
                throws Exception {
        
        new GetAirshipsCloserToCommand( airshipsDatabase, -4, 45, 100 ).call();
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveAInvalidValueToLatitude()
        throws Exception {
        
        new GetAirshipsCloserToCommand( airshipsDatabase, 2, -245, 100 ).call();
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveAInvalidValueToLongitude()
        throws Exception {
        
        new GetAirshipsCloserToCommand( airshipsDatabase, 2, 45, 1000 ).call();
    }
}
