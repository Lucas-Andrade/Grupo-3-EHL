package parsingtools_tests.commandfactories.getfactories;


import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.getfactories.CheckIfAirshipIsTransgressingCommandsFactory;
import utils.StringCommandsDictionary;
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
 * {@link CheckIfAirshipIsTransgressingCommandsFactory}
 * 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommandsFactory_Tests {
    
    InMemoryAirshipsDatabase airshipsDatabase;
    InMemoryUsersDatabase userDatabase;
    User user;
    Airship airship;
    CommandFactory< String > checkTransgressorsAirships;
    Map< String, String > parametersMap;    
    String flightId_ParameterName;
    
    
    // Before Class
    
    @Before
    public void createUserAndAirshipAndTheirDatabases() throws InvalidArgumentException{
       
       
        userDatabase = new InMemoryUsersDatabase( "first user database" );        
        user = new User("pantunes","pantunespassword","pantunes@gmail.com");
        userDatabase.add( user, user );
        
        airshipsDatabase = new InMemoryAirshipsDatabase( "first airship database" );
        airship = new CivilAirship( 38, 171, 15000, 20000, 10000, 100 );  
        airshipsDatabase.add(airship,user);
        
        parametersMap = new HashMap<>();        
        checkTransgressorsAirships = new CheckIfAirshipIsTransgressingCommandsFactory( airshipsDatabase );           
        flightId_ParameterName = StringCommandsDictionary.FLIGHTID;
 
        parametersMap.put( flightId_ParameterName, airship.getIdentification() );   
        
    }
    
    // Test Normal Dinamic And Prerequisites
    
    @Test
    public void shouldGetCorrectCommandsDescription(){
        
                
        Assert.assertEquals("Checks whether an airship is transgressing its air corridor.",
                            checkTransgressorsAirships.getCommandsDescription());

    }
    
    @Test
    public void shouldCheckIfAirshipIsTransgressingCorridorLimits() throws Exception{
        
   
    Assert.assertEquals("The Airship with the Flight ID "+airship.getIdentification()+" is not transgressing its air corridor.",
                      checkTransgressorsAirships.newCommand( parametersMap ).call());

    }
    
    // Test Exceptions
    
    @Test( expected = InvalidArgumentException.class )
    public  void
            shouldThrowInvalidArgumentExceptionWhenTryingToCreateACheckIfAirshipIsTransgressingCommandsFactoryGivenANullDatabase()                throws InvalidArgumentException {
        
        new CheckIfAirshipIsTransgressingCommandsFactory( null );
    }
}
