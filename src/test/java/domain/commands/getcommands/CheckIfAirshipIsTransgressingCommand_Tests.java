package test.java.domain.commands.getcommands;


import main.java.domain.commands.getcommands.CheckIfAirshipIsTransgressingCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.InMemoryUsersDatabase;
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
 * {@link CheckIfAirshipIsTransgressingCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommand_Tests {
    
    private InMemoryAirshipsDatabase airshipsDatabase;
    private InMemoryUsersDatabase userDatabase;
    private User user1, user2;
    private Airship airship1, airship2, airship3;
    private CheckIfAirshipIsTransgressingCommand checkIfAirshipIsTransgressing;
    
    // Before
    
    @Before
    public void createAirshipsAndUsersAndTheirDatabases() throws InvalidArgumentException {
    
        // Arrange
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        userDatabase = new InMemoryUsersDatabase( "Users Database" );
        
        user1 = new User( "Daniel", "pass", "@daniel" );
        user2 = new User( "Pedro", "pass2", "@pedro", "Pedro Antunes" );
        
        airship1 = new CivilAirship( 0, 0, 0, 100, 50, 20 );
        airship2 = new CivilAirship( 0, 0, 60, 100, 50, 25 );
        airship3 = new MilitaryAirship( 0, 0, 30, 100, 50, false );
        
        userDatabase.add( user1, user1 );
        userDatabase.add( user2, user1 );
        
        airshipsDatabase.add( airship1, user1 );
        airshipsDatabase.add( airship2, user1 );
        airshipsDatabase.add( airship3, user2 );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void
            shouldCheckCorrectlyIfAnAirshipCorrespondingToTheGivenIdentificationIsTransgressing()
                throws Exception {
    
        // Act
        checkIfAirshipIsTransgressing =
                new CheckIfAirshipIsTransgressingCommand( airshipsDatabase,
                                                          airship1.getIdentification() );
        
        String testedInformation1 = checkIfAirshipIsTransgressing.call();
        
        checkIfAirshipIsTransgressing =
                new CheckIfAirshipIsTransgressingCommand( airshipsDatabase,
                                                          airship2.getIdentification() );
        
        String testedInformation2 = checkIfAirshipIsTransgressing.call();
        
        
        // Assert
        Assert.assertEquals( testedInformation1,
                             "The Airship with the Flight ID " + airship1.getIdentification()
                                     + " is transgressing its air corridor." );
        
        Assert.assertEquals( testedInformation2,
                             "The Airship with the Flight ID " + airship2.getIdentification()
                                     + " is not transgressing its air corridor." );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
        throws InvalidArgumentException {
    
        checkIfAirshipIsTransgressing = new CheckIfAirshipIsTransgressingCommand( null, "ola" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullIdentification()
                throws InvalidArgumentException {
    
        checkIfAirshipIsTransgressing =
                new CheckIfAirshipIsTransgressingCommand( airshipsDatabase, null );
    }
}
