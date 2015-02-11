

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import elements.Airship;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <p>
 * {@link Airship}, {@link CivilAirship} and {@link MilitaryAirship}; {@link GeographicalPosition},
 * {@link GeographicalCoordinate} and {@link AirCorridor}.
 * </p>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Airships_Test {
    
    private Airship airship1, airship2, airship3, airship4;
    
    // Before
    
    @Before
    public void createAirships() throws InvalidArgumentException {
        
        airship1 = new CivilAirship( 30, 40, 600, 3000, 300, 6 );
        airship2 = new MilitaryAirship( 30, 40, 5000, 4000, 200, false );
        airship3 = new MilitaryAirship( 30, 40, 20, 3000, 200, true );
    }
    
    // Test Normal Dinamic And Prerequisites
  
 
    @Test
    public void shouldCreateCivilAirshipsWithASpecificFlightId() throws InvalidArgumentException {
        
        // Arrange
        airship4 = new CivilAirship( 0, 0, 0, 100, 50, 20, "id3" );
        
        // Assert
        Assert.assertEquals(  "Flight ID: id3\r\n"
                                    +"Latitude: 0.0 Longitude: 0.0 Altitude: 0.0\r\n"
                                    + "Maximum Altitude Permited: 100.0 Minimum Altitude Permited: 50.0\r\n"
                                    + "Is Outside The Given Corridor: true\r\nNumber of Passengers: 20\r\n" , airship4.toString());
    }
    
    @Test
    public
            void
            shouldCreateMilitaryAirshipsWithASpecificFlightIdUsingTheStaticMethodCreateANewAirshipWithAPreDefinedIdentification()
                throws InvalidArgumentException {
        
        // Arrange
        airship4 = new MilitaryAirship( 0, 0, 60, 100, 50, false, "id3" );
        
        // Assert
              
        Assert.assertEquals( "Flight ID: id3\r\n"
                + "Latitude: 0.0 Longitude: 0.0 Altitude: 60.0\r\n"
                + "Maximum Altitude Permited: 100.0 Minimum Altitude Permited: 50.0\r\n"
                + "Is Outside The Given Corridor: false\nCarries Weapons: false\n", airship4.toString() );
    }
    
    @Test
    public void shouldVerifyIfAnAirshipIsNotWhitinItsAirCorridor() {
        
        // Assert
        Assert.assertFalse( airship1.isTransgressing() );
        Assert.assertTrue( airship2.isTransgressing() );
        Assert.assertTrue( airship3.isTransgressing() );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateCivilAirshipsWithASpecificFlightIdGivenANegativeNumberOfPassengers()
                throws InvalidArgumentException {
        
        new CivilAirship( 0, 0, 0, 100, 50, -3, "id3" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfCivilAirshipHasLessThatZeroPassengers()
        throws InvalidArgumentException {
        
        new CivilAirship( 30, 40, 2000, 3000, 300, -3 );
    }
    
    @Test
    public void shouldThrowExceptionWithCorrectMessageIfLatitudeIsNotWhitinTheValidLimits() {
        
        try {
            new CivilAirship( 100, 40, 2000, 3000, 300, 20 );
        }
        catch( InvalidArgumentException e ) {
            Assert.assertEquals( "Invalid value 100 is greater than maximum value allowed (90) for latitude.",
                                 e.getMessage() );
        }
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfLongitudeIsNotWhitinTheValidLimits()
        throws InvalidArgumentException {
        
        new CivilAirship( 10, -10, 2000, 3000, 300, 20 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfAltitudeIsNotWhitinTheValidLimits()
        throws InvalidArgumentException {
        
        new CivilAirship( 10, 60, 300000, 3000, 300, 20 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void
            shouldThrowInvalidArgumentExceptionIfTheMaximumAltitudeIsLessThanTheMinimumAltitude()
                throws InvalidArgumentException {
        
        new CivilAirship( 10, 60, 3000, 200, 3000, 20 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionIfTheMinimumAltitudeIsLessThanZero()
        throws InvalidArgumentException {
        
        new CivilAirship( 10, 60, 3000, 200, -10, 20 );
    }
}
