package test.java.domain.model;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import main.java.domain.model.InMemoryDatabase;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.AirshipComparators;
import main.java.domain.model.airships.AirshipPredicates;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.GeographicPosition;
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
 *
 * This Test class tests the following classes:
 * 
 * <pre>
 * {@link InMemoryDatabase};
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryDatabase_Tests {
    
    InMemoryUsersDatabase userDatabase;
    private InMemoryAirshipsDatabase airshipDatabase;
    private Airship airship, airship2;
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
    public void shouldCreateInMemoryUserDatabaseWithAMasterUser() {
    
        try {
            // Arrange
            User masterUser = new User( "MASTER", "master", "master@master" );
            
            // Assert
            Assert.assertEquals( userDatabase.getElementByIdentification( "MASTER" ).toString(),
                                 masterUser.toString() );
            
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
        
    }
    
    @Test
    public void shouldGetAllAirshipsThatAreTransgressing() {
    
        try {
            // Arrange
            airship2 = new MilitaryAirship( 0, 0, 0, 10, 5, false );
            List< Airship > airships = new ArrayList<>();
            
            // Act
            airshipDatabase.add( airship, user );
            airshipDatabase.add( airship2, user );
            
            airships.add( airship2 );
            
            // Assert
            Assert.assertEquals( airshipDatabase.getAllElementsThat( new AirshipPredicates.IsTrangressingItsAirCorridor() )
                                                .toString(), airships.toString() );
            
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldGetAllAirshipsThatVerifyHaveANumberOfPassengersBellowAThreshold() {
    
        try {
            // Arrange
            airship2 = new CivilAirship( 0, 0, 0, 10, 5, 100 );
            Airship airship3 = new CivilAirship( 0, 0, 0, 10, 5, 2 );
            Airship airship4 = new CivilAirship( 0, 0, 0, 10, 5, 200 );
            
            InMemoryAirshipsDatabase airships =
                    new InMemoryAirshipsDatabase( "secondUsersDataBase" );
            
            // Act
            airshipDatabase.add( airship, user );
            airshipDatabase.add( airship2, user );
            airshipDatabase.add( airship3, user );
            airshipDatabase.add( airship4, user );
            
            airships.add( airship3, user );
            airships.add( airship2, user );
            
            // Assert
            Assert.assertEquals( airshipDatabase.getAllElementsThat( new AirshipPredicates.HasPassagersNumberBelowAThreshold(
                                                                                                                              102 ) )
                                                .toString(), airships.getAllElements().toString() );
        }
        catch( InvalidArgumentException e ) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void shouldRemoveAnUserInTheInMemoryUsersDatabase()
        throws DatabaseException, InvalidArgumentException {
    
        User pantunes = new User( "pantunes", "pass", "pantunes@gmai.com" );
        userDatabase.add( pantunes, user );
        Assert.assertTrue( userDatabase.removeByIdentification( "pantunes" ) );
        
    }
    
    @Test
    public void
            shouldNotAddAnAirshipBecauseThisAirShipIsAlreadyRegistedIntoInMemoryAirshipsDatabase()
                throws DatabaseException, InvalidArgumentException {
    
        Assert.assertTrue( airshipDatabase.add( airship, user ) );
        Assert.assertFalse( airshipDatabase.add( airship, user ) );
        
    }
    
    @Test
    public void shouldNotRemoveAnUserBecauseTheUserIsNotRegistedIntoInMemoryUsersDatabase()
        throws DatabaseException, InvalidArgumentException {
    
        Assert.assertFalse( userDatabase.removeByIdentification( "pantunes" ) );
    }
    
    @Test
    public void shoulSortAllTheElementsInTheDatabaseByAGivenCriteria() throws Exception {
    
        // Arrange
        List< Airship > expectedAirshipList = new ArrayList< Airship >();
        
        airship = new CivilAirship( 30, 225, 10000, 20000, 0, 100 );
        airship2 = new MilitaryAirship( 0, 315, 15000, 20000, 0, true );
        Airship airship3 = new CivilAirship( 45, 180, 12000, 20000, 0, 50 );
        Airship airship4 = new MilitaryAirship( -60, 90, 15000, 20000, 0, false );
        Airship airship5 = new CivilAirship( -60, 225, 12000, 20000, 0, 50 );
        
        // Act
        airshipDatabase.add( airship, user );
        airshipDatabase.add( airship2, user );
        airshipDatabase.add( airship3, user );
        airshipDatabase.add( airship4, user );
        airshipDatabase.add( airship5, user );
        
        expectedAirshipList.add( airship4 );
        expectedAirshipList.add( airship3 );
        expectedAirshipList.add( airship );
        expectedAirshipList.add( airship5 );
        expectedAirshipList.add( airship2 );
        
        // Assert
        assertEquals( expectedAirshipList,
                      airshipDatabase.sortBy( new AirshipComparators.ComparatorByDistance(
                                                                                           new GeographicPosition(
                                                                                                                   0,
                                                                                                                   0,
                                                                                                                   0 ) ) )
                                     .get() );
    }
    
    // Test Exceptions
    
    @Test( expected = DatabaseException.class )
    public void shouldThrowDatabaseExceptionWhenTryingToRemoveTheMasterUserFromAUserDatabase()
        throws DatabaseException, InvalidArgumentException {
    
        // Assert
        
        userDatabase.removeByIdentification( "MASTER" );
        
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryToGetAnElementByIdentificationGivingNullIdenfitication()
                throws InvalidArgumentException {
    
        // Assert
        
        userDatabase.getElementByIdentification( null );
        
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryToCreateAnInMemoryUsersDatabaseWythNullDatabaseName()
                throws InvalidArgumentException {
    
        // Assert
        userDatabase = new InMemoryUsersDatabase( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryToGetAllElementsUsingAnNullPredicate()
        throws InvalidArgumentException {
    
        // Assert
        airshipDatabase.getAllElementsThat( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryToSortAllElementsUsingAnNullCriteria()
        throws InvalidArgumentException {
    
        // Assert
        airshipDatabase.sortBy( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void
            shouldThrowInvalidArgumentExceptionWhenTryToRemoveAnElementGivingANullIdentification()
                throws InvalidArgumentException, DatabaseException {
    
        // Assert
        airshipDatabase.removeByIdentification( null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryToAddAnAirshipGivingANullUser()
        throws InvalidArgumentException {
    
        // Assert
        airshipDatabase.add( airship, null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryToAddAnAirshipGivingANullAirship()
        throws InvalidArgumentException {
    
        // Assert
        airshipDatabase.add( null, user );
    }
}
