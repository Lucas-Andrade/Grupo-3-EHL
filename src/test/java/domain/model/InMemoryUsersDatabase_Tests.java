package test.java.domain.model;


import static org.junit.Assert.assertTrue;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * {@link InMemoryUsersDatabase};
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryUsersDatabase_Tests {
    
    InMemoryUsersDatabase userDatabase;
    private InMemoryAirshipsDatabase airshipDatabase;
    private Airship airship;
    private User user;
    
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
    public void shouldAddAnElementToTheDatabase() {
    
        try {
            // Assert
            assertTrue( airshipDatabase.add( airship, user ) );
            assertTrue( userDatabase.add( user, user ) );
            
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldNotAddTheSameUserIntoInMemoryUsersDatabase() throws InvalidArgumentException {
    
        Assert.assertTrue( userDatabase.add( user, user ) );
        Assert.assertFalse( userDatabase.add( user, user ) );
    }
    
    @Test
    public void shouldRemoveAnUserFromInMemoryUsersDatabase()
        throws InvalidArgumentException, DatabaseException {
    
        userDatabase.add( user, user );
        Assert.assertTrue( userDatabase.removeByIdentification( user.getIdentification() ) );
    }
    
    // Test Exceptions
    
    @Test( expected = DatabaseException.class )
    public void shouldThrowDatabaseExceptionWhenTryingToRemoveTheMasterUserFromAUserDatabase()
        throws DatabaseException, InvalidArgumentException {
    
        // Assert
        
        userDatabase.removeByIdentification( "MASTER" );
    }
}
