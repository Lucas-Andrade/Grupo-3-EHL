

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.DatabaseException;
import exceptions.InvalidArgumentException;
import exceptions.NoSuchElementInDatabaseException;


/**
 *
 * This Test class tests the following classes:
 * 
 * <pre>
 * {@link InMemoryAirshipsDatabase};
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryAirshipsDatabase_Tests {
    
    InMemoryUsersDatabase userDatabase;
    private InMemoryAirshipsDatabase airshipDatabase;
    private Airship airship, airship2, airship3;
    private User user, user2;
    
    // Before
    
    @Before
    public void createUserAndAirshipAndTheirDatabases() {
        
        try {
            
            // Arrange
            userDatabase = new InMemoryUsersDatabase( "newUsersDataBase" );
            
            airshipDatabase = new InMemoryAirshipsDatabase( "newAirshipsdataBase" );
            
            airship = new MilitaryAirship( 0, 0, 0, 10, 0, false );
            user = new User( "X", "P", "T@", "O" );
            
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
        
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldAddAnAirshipToTheInMemoryAirshipsDatabase() {
        
        try {
            // Assert
            assertTrue( airshipDatabase.add( airship, user ) );
            
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldNotAddTheSameaAirshipToTheInMemoryAirshipsDatabase() {
        
        try {
            // Act
            assertTrue( airshipDatabase.add( airship, user ) );
            
            // Assert
            assertFalse( airshipDatabase.add( airship, user ) );
            
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldRemoveAnAirship() throws DatabaseException {
        
        try {
            // Act
            
            airshipDatabase.add( airship, user );
            airshipDatabase.add( airship, user );
            
            // Assert
            assertTrue( airshipDatabase.removeByIdentification( airship.getIdentification() ) );
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldGetAllAirshipsRegistedByTheSameUser() throws NoSuchElementInDatabaseException {
        
        try {
            // Arrange
            user2 = new User( "daniel", "d", "@d" );
            airship2 = new MilitaryAirship( 0, 0, 0, 10, 5, false );
            airship3 = new CivilAirship( 0, 0, 0, 10, 5, 20 );
            List< Airship > airships = new ArrayList<>();
            
            // Act
            airshipDatabase.add( airship, user );
            airshipDatabase.add( airship2, user );
            airshipDatabase.add( airship3, user2 );
            
            airships.add( airship );
            airships.add( airship2 );
            
            // Assert
            Assert.assertEquals( airshipDatabase.getElementsByUser( "X" ).toString(),
                                 airships.toString() );
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void shouldReceiveAMessageConfirmingThatAnyAirshipWasAddedByANullUser()
        throws InvalidArgumentException {
        
        Assert.assertEquals( "No airship added by null", airshipDatabase.getElementsByUser( null )
                                                                        .toString() );
    }
    
    @Test
    public void shouldRemoveAnAirshipButInMemoryAirshipsDatabaseKeepsNotEmpty()
        throws InvalidArgumentException, DatabaseException {
        
        Airship airship2 = new CivilAirship( 0, 0, 0, 10, 0, 100 );
        
        airshipDatabase.add( airship, user );
        airshipDatabase.add( airship2, user );
        
        assertTrue( airshipDatabase.removeByIdentification( airship.getIdentification() ) );
    }
    
    
    @Test
    public void shouldReturnFalseWhenTryingToRemoveAnAirshipThatDoesNotExistInTheDatabase()
        throws DatabaseException, InvalidArgumentException {
        
        Assert.assertFalse( airshipDatabase.removeByIdentification( airship.getIdentification() ) );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryToAddNullElementsToADatabase()
        throws InvalidArgumentException {
        
        // Assert
        
        airshipDatabase.add( null, user );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryToAddElementsToADatabaseGivenNullUser()
    
    throws InvalidArgumentException {
        
        // Assert
        airshipDatabase.add( airship, null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryToRemoveNullElementsFromADatabase()
        throws InvalidArgumentException, DatabaseException {
        
        airshipDatabase.removeByIdentification( null );
    }
}
