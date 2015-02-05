package test.java.domain.commands.patchcommands;
import static org.junit.Assert.assertEquals;
import main.java.domain.commands.patchcommands.PatchAirshipCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PatchAirshipCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipCommands_Tests {
    
    private InMemoryAirshipsDatabase airshipsDatabase;
    private User user1;
    private Airship airship1, airship2;
    private PatchAirshipCommand patchAirship;
    
    // Before
    
    @Before
    public void createAirshipsAndUserAndAirshipDatabaseWithTheCreatedAirshipsToBePatched()
        throws InvalidArgumentException {
    
        // Arrange
        user1 = new User( "Daniel", "pass", "@daniel" );
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        airship1 = new CivilAirship( 0, 0, 0, 100, 50, 20 );
        airship2 = new MilitaryAirship( 0, 0, 30, 100, 50, false );
        
        airshipsDatabase.add( airship1, user1 );
        airshipsDatabase.add( airship2, user1 );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldPatchACivilAirshipWithTheCorrectParametersInTheGivenDatabase()
        throws Exception {
    
        // Act
        patchAirship =
                new PatchAirshipCommand( airshipsDatabase, airship1.getIdentification(), user1,
                                         2.0, 4.0, 13.0, 100.0, 50.0 );
        
        // Assert
        assertEquals( "Airship successfully patched", patchAirship.call().getMessage() );
        assertEquals( "Flight ID: " + airship1.getIdentification()
                              + "\nLatitude: 2.0 Longitude: 4.0 Altitude: 13.0\n"
                              + "Maximum Altitude Permited: 100.0Minimum Altitude Permited: 50.0\n"
                              + "Is Outside The Given Corridor: true\n"
                              + "Number of Passengers: 20\n",
                      airshipsDatabase.getElementByIdentification( airship1.getIdentification() )
                                      .toString() );
    }
    
    @Test
    public void shouldPatchAMilitaryAirshipWithTheCorrectParametersInTheGivenDatabase()
        throws Exception {
    
        // Act
        patchAirship =
                new PatchAirshipCommand( airshipsDatabase, airship2.getIdentification(), user1,
                                         20., 10., 30., 100., 50. );
        
        // Assert
        assertEquals( "Airship successfully patched", patchAirship.call().getMessage() );
        assertEquals( "Flight ID: " + airship2.getIdentification()
                              + "\nLatitude: 20.0 Longitude: 10.0 Altitude: 30.0\n"
                              + "Maximum Altitude Permited: 100.0Minimum Altitude Permited: 50.0\n"
                              + "Is Outside The Given Corridor: true\n"
                              + "Carries Weapons: false\n",
                      airshipsDatabase.getElementByIdentification( airship2.getIdentification() )
                                      .toString() );
    }
    
    @Test
    public void shouldPatchAnAirshipCorrectlyGivenNullParameters() throws Exception {
    
        // Act
        patchAirship =
                new PatchAirshipCommand( airshipsDatabase, airship2.getIdentification(), user1,
                                         null, null, null, null, 50. );
        
        // Assert
        assertEquals( "Airship successfully patched", patchAirship.call().getMessage() );
        assertEquals( "Flight ID: " + airship2.getIdentification()
                              + "\nLatitude: 0.0 Longitude: 0.0 Altitude: 30.0\n"
                              + "Maximum Altitude Permited: 100.0Minimum Altitude Permited: 50.0\n"
                              + "Is Outside The Given Corridor: true\n"
                              + "Carries Weapons: false\n",
                      airshipsDatabase.getElementByIdentification( airship2.getIdentification() )
                                      .toString() );
    }
    
    @Test
    public void shouldNotPatchAnAirshipIfNoneOfTheParametersWereGiven() throws Exception {
    
        // Act
        patchAirship =
                new PatchAirshipCommand( airshipsDatabase, "id20", user1, null, null, null, null,
                                         null );
        
        // Assert
        Assert.assertEquals( "Airship not patched beacause no new parameter was given",
                             patchAirship.call().getMessage() );
    }
    
    @Test
    public void shouldNotPatchAnAirshipThatDoesNotExistInTheDatabase() throws Exception {
    
        // Act
        patchAirship =
                new PatchAirshipCommand( airshipsDatabase, "id20", user1, 0.0, 0.0, 0.0, 100.0,
                                         50.0 );
        
        // Assert
        assertEquals( "Airship does not exist in the database", patchAirship.call().getMessage() );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchCivilAirshipCommandGivenANullDatabase()
                throws InvalidArgumentException {
    
        patchAirship = new PatchAirshipCommand( null, "id20", user1, 0.0, 0.0, 0.0, 100.0, 50.0 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchCivilAirshipCommandGivenANullIdentification()
                throws InvalidArgumentException {
    
        patchAirship =
                new PatchAirshipCommand( airshipsDatabase, null, user1, 0.0, 0.0, 0.0, 100.0, 50.0 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePatchAirshipCommandGivenANullUser()
                throws InvalidArgumentException {
    
        patchAirship =
                new PatchAirshipCommand( airshipsDatabase, "id2", null, 0.0, 0.0, 0.0, 100.0, 50.0 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToPatchAnAirshipGivenInvalidParametersForItsFields()
                throws Exception {
    
        new PatchAirshipCommand( airshipsDatabase, airship1.getIdentification(), user1, 360.0, 0.0,
                                 0.0, 100.0, null ).call();
    }
}
