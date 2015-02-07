package postcommands;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import commands.postcommands.PostCivilAirshipCommand;
import commands.postcommands.PostMilitaryAirshipCommand;

import databases.InMemoryAirshipsDatabase;
import elements.User;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link PostCivilAirshipCommand}
 * {@link PostMilitaryAirshipCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostAirshipCommands_Tests {
    
    private InMemoryAirshipsDatabase airshipsDatabase;
    private User user1;
    private PostMilitaryAirshipCommand postMilitaryAirship;
    private PostCivilAirshipCommand postCivilAirship;
    
    // Before
    
    @Before
    public void createUserAndAirshipDatabaseWhereToPostTheCreatedAirships()
        throws InvalidArgumentException {
    
        // Arrange
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        
        user1 = new User( "Daniel", "pass", "@daniel" );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldPostACivilAirshipWithTheCorrectParametersInTheGivenDatabase()
        throws Exception {
    
        // Act
        postCivilAirship =
                new PostCivilAirshipCommand( 0, 0, 0, 100, 50, 100, airshipsDatabase, user1 );
        String testedInformation = postCivilAirship.call().getMessage();
        
        // Assert
        assertEquals( "Airship successfully posted with flightId: "
                      + airshipsDatabase.getElementsByUser( user1.getIdentification() ).get()
                                        .iterator().next().getIdentification(), testedInformation );
    }
    
    @Test
    public void shouldPostAMilitaryAirshipWithTheCorrectParametersInTheGivenDatabase()
        throws Exception {
    
        // Act
        postMilitaryAirship =
                new PostMilitaryAirshipCommand( 0, 0, 0, 100, 50, false, airshipsDatabase, user1 );
        String testedInformation = postMilitaryAirship.call().getMessage();
        
        // Assert
        assertEquals( "Airship successfully posted with flightId: "
                      + airshipsDatabase.getElementsByUser( user1.getIdentification() ).get()
                                        .iterator().next().getIdentification(), testedInformation );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePostCivilAirshipCommandGivenANullDatabase()
                throws InvalidArgumentException {
    
        postCivilAirship = new PostCivilAirshipCommand( 0, 0, 0, 100, 50, 100, null, user1 );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateThePostMilitaryAirshipCommandGivenANullDatabase()
                throws InvalidArgumentException {
    
        postMilitaryAirship = new PostMilitaryAirshipCommand( 0, 0, 0, 100, 50, false, null, user1 );
    }
}
