package getcommands;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import commands.getcommands.GetAirshipsOfOwnerCommand;

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
 * {@link GetAirshipsOfOwnerCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsOfOwnerCommand_Tests {
    
    private InMemoryAirshipsDatabase airshipsDatabase;
    private InMemoryUsersDatabase userDatabase;
    private User user1, user2;
    private Airship airship1, airship2, airship3;
    private GetAirshipsOfOwnerCommand getAirshipsOfOwner;
    
    // Before
    
    @Before
    public void createAirshipsAndUsersAndTheirDatabases() throws InvalidArgumentException {
    
        // Arrange
        airshipsDatabase = new InMemoryAirshipsDatabase( "Airships Database" );
        userDatabase = new InMemoryUsersDatabase( "Users Database" );
        
        user1 = new User( "Daniel", "pass", "@daniel" );
        user2 = new User( "Pedro", "pass2", "@pedro", "Pedro Antunes" );
        
        airship1 = new CivilAirship( 0, 0, 0, 100, 50, 20 );
        airship2 = new CivilAirship( 0, 0, 0, 100, 50, 25 );
        airship3 = new MilitaryAirship( 0, 0, 0, 100, 50, false );
        
        userDatabase.add( user1, user1 );
        userDatabase.add( user2, user1 );
        
        airshipsDatabase.add( airship1, user1 );
        airshipsDatabase.add( airship2, user1 );
        airshipsDatabase.add( airship3, user2 );
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetAllTheAirshipsRegisteredByTheUserCorrespondeingToTheGivenIdentification()
        throws Exception {
    
        // Act
        getAirshipsOfOwner = new GetAirshipsOfOwnerCommand( airshipsDatabase, "Daniel" );
        Iterable< Airship > testedAirships = getAirshipsOfOwner.call().get();
        
        List< Airship > airships = new ArrayList< Airship >();
        
        airships.add( airship1 );
        airships.add( airship2 );
        
        // Assert
        Assert.assertEquals( testedAirships.toString(), airships.toString() );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullDatabase()
        throws InvalidArgumentException {
    
        getAirshipsOfOwner = new GetAirshipsOfOwnerCommand( null, "Daniel" );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenANullUsername()
        throws InvalidArgumentException {
    
        getAirshipsOfOwner = new GetAirshipsOfOwnerCommand( airshipsDatabase, null );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public
            void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateTheCommandGivenAEmptyStringAsUsername()
                throws InvalidArgumentException {
    
        getAirshipsOfOwner = new GetAirshipsOfOwnerCommand( airshipsDatabase, "" );
    }
}
