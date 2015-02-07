


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import utils.CompletionStatus;
import commands.DeleteAirshipCommand;
import databases.InMemoryAirshipsDatabase;
import databases.InMemoryUsersDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import exceptions.InvalidArgumentException;


/**
 * This Test class tests the following classes:
 * 
 * <pre>
 * 
 * {@link DeleteAirshipCommand}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class DeleteAirshipCommand_Tests {
    
    CommandParser cmdParser;
    InMemoryAirshipsDatabase airshipsDatabase;
    InMemoryUsersDatabase userDatabase;
    User user;
    
    // Before
    
    @Before
    public void createUserAndAirshipAndTheirDatabases()
        throws InvalidRegisterException, InvalidArgumentException {
    
        cmdParser = new CommandParser();
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "airshipsDatabase" );
        userDatabase = new InMemoryUsersDatabase( "userDatabase" );
        
        user = new User( "pantunes", "pantunespassword", "pantunes@gmail.com" );
        userDatabase.add( user, user );
        
        cmdParser.registerCommand( "DELETE",
                                   "/airships/{flightId}",
                                   new DeleteAirshipCommandsFactory( userDatabase, airshipsDatabase ) );
        
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldDeleteAnAirhipsMemberOfInMemoryAirshipsDatabase() throws Exception {
    
        Airship air1 = new CivilAirship( 30, 230, 10000, 20000, 0, 199 );
        
        airshipsDatabase.add( air1, user );
        
        Parser parser =
                new Parser( cmdParser, "DELETE",
                            new StringBuilder( "/airships/" ).append( air1.getIdentification() )
                                                             .toString(),
                            "loginName=pantunes&loginPassword=pantunespassword" );
        
        Assert.assertEquals( "Airship successfully removed",
                             ((CompletionStatus)parser.getCommand().call()).getMessage() );
    }
    
    @Test
    public void shouldNotDeleteAnAirshipBecauseAnInvalidLoginPassword() throws Exception {
    
        Airship air1 = new CivilAirship( 30, 230, 10000, 20000, 0, 199 );
        
        Parser parser =
                new Parser( cmdParser, "DELETE",
                            new StringBuilder( "/airships/" ).append( air1.getIdentification() )
                                                             .toString(),
                            "loginName=pantunes&loginPassword=pantunespassword" );
        
        Assert.assertEquals( "Airship doesn't exist in the database",
                             ((CompletionStatus)parser.getCommand().call()).getMessage() );
    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenGiveAnNullInMemoryAirshipsDatabase()
        throws Exception {
    
        Airship air1 = new CivilAirship( 30, 230, 10000, 20000, 0, 199 );
        
        new DeleteAirshipCommand( null, air1.getIdentification() );
    }
    
    @Test( expected = InvalidArgumentException.class )
    public void shouldThrowInvalidArgumentExceptionWhenGiveAnNullIdentification() throws Exception {
    
        new DeleteAirshipCommand( airshipsDatabase, null );
    }
}
