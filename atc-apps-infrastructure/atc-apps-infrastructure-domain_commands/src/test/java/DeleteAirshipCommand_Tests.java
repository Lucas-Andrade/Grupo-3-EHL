import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.CompletionStatus;
import commands.DeleteAirshipCommand;
import databases.Database;
import databases.InMemoryAirshipsDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import exceptions.InvalidArgumentException;


/**
 * Test case that targets the class {@link DeleteAirshipCommand}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class DeleteAirshipCommand_Tests {
    
    Database< Airship > airshipsDatabase;
    
    @Before
    public void initDatabase() throws InvalidArgumentException {
        airshipsDatabase = new InMemoryAirshipsDatabase( "airshipsDatabase" );
    }
    
    // Test Normal Dynamic And Prerequisites
    
    @Test
    public void shouldDeleteAnAirhipsMemberOfInMemoryAirshipsDatabase() throws Exception {
        
        // Arrange
        
        Database< Airship > airshipsDatabase = new InMemoryAirshipsDatabase( "airshipsDatabase" );
        Airship air1 = new CivilAirship( 30, 230, 10000, 20000, 0, 199 );
        User user = new User( "pantunes", "pantunespassword", "pantunes@gmail.com" );
        airshipsDatabase.add( air1, user );
        String air1_id = air1.getIdentification();
        
        // Act
        CompletionStatus status = new DeleteAirshipCommand( airshipsDatabase, air1_id ).call();
        
        // Assert
        Assert.assertTrue( status.completedSuccessfully() );
        Assert.assertEquals( "Airship successfully removed", status.getMessage() );
    }
    
    @Test
    public void shouldFailToDeleteAnAirhipNotContainedInTheDatabase() throws Exception {
        
        CompletionStatus status =
                ((CompletionStatus)new DeleteAirshipCommand( airshipsDatabase, "inexistentID" ).call());
        
        Assert.assertFalse( status.completedSuccessfully() );
        Assert.assertEquals( "Airship doesn't exist in the database", status.getMessage() );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenGiveAnNullInMemoryAirshipsDatabase()
        throws Exception {
        
        new DeleteAirshipCommand( null, "aFlightId" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenGiveAnNullIdentification() throws Exception {
        
        new DeleteAirshipCommand( new InMemoryAirshipsDatabase( "test" ), null );
    }
}
