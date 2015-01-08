package test.java;


import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.getfactories.GetTheNearestAirshipsToGeographicPositionCommandsFactory;
import main.java.domain.commands.getcommands.GetTheNearestAirshipsToGeographicPositionCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.parsingexceptions.commandparserexceptions.InvalidRegisterException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetTheNearestAirshipsToGeographicPosition_Test {

	
	CommandParser cmdParser = new CommandParser();
	InMemoryAirshipsDatabase airshipsDatabase;
	
	User user1;
	 
	@Before
	public void BeforeTests() throws InvalidRegisterException, InvalidArgumentException{
		
	airshipsDatabase= new InMemoryAirshipsDatabase("FirstAirshipsDatabse");
	user1 = new User("Pantunes","pass", "Pantunes@gmail","Pantunes");
		cmdParser.registerCommand("GET", "/airships/find" , new GetTheNearestAirshipsToGeographicPositionCommandsFactory(airshipsDatabase));
		
	}
		
	@Test
	public void shouldGiveAllTheAirshipsNearestOfTheGeograficCoordinate() throws Exception{
	 	
		
		Airship air1 = new CivilAirship(30, 225, 10000, 20000, 0, 100);
		Airship air2 = new MilitaryAirship(0, 315, 15000, 20000, 0, true);
		Airship air3 = new CivilAirship(45, 180, 12000, 20000, 0, 50);
		Airship air4 = new MilitaryAirship(-60, 90, 15000, 20000, 0, false);
		Airship air5 = new CivilAirship(-60, 225, 12000, 20000, 0, 50);
		Airship air6 = new CivilAirship(-90, 360, 12000, 20000, 0, 50);
		Airship air7 = new CivilAirship(30, 45, 12000, 20000, 0, 50);

		airshipsDatabase.add(air1, user1);
		airshipsDatabase.add(air2, user1);
		airshipsDatabase.add(air3, user1);
		airshipsDatabase.add(air4, user1);
		airshipsDatabase.add(air5, user1);
		airshipsDatabase.add(air6, user1);
		airshipsDatabase.add(air7, user1); 
		 
		
		 
		Parser parser = new Parser(cmdParser,"GET", "/airships/find", "nbAirships=2&latitude=60&longitude=225");
		
		String result = parser.getCommand().call().toString();
			
		Assert.assertEquals(
								new StringBuilder("[").append(air1.toString())
									.append(", ").append(air3.toString())
									.append("]").toString()
																, result);
				
	}
	
	@Test(expected=InvalidArgumentException.class)	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullAirshipDatabase()  throws Exception{
					
		new GetTheNearestAirshipsToGeographicPositionCommand(null,3, 45, 100);
			
	}	
	
	@Test(expected=InvalidArgumentException.class)	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANegativeValueToDesiredAirshipsNumber() throws Exception  {
					
		 new GetTheNearestAirshipsToGeographicPositionCommand(airshipsDatabase,-4, 45, 100).call();
			
	}	
		
	@Test(expected=InvalidArgumentException.class)	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveAInvalidValueToLatitude()  throws Exception{
					
		 new GetTheNearestAirshipsToGeographicPositionCommand(airshipsDatabase,2, -245, 100).call();
			
	}
	
	@Test(expected=InvalidArgumentException.class)	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveAInvalidValueToLongitude()  throws Exception{
					
		 new GetTheNearestAirshipsToGeographicPositionCommand(airshipsDatabase,2, 45, 1000).call();
			
	}
	
	@Test(expected=InvalidArgumentException.class)	
	public void shouldThrowInvalidArgumentExceptionWhenTryingToGiveANullAirShipDatabaseInTheFactory()  throws Exception{
					
		new GetTheNearestAirshipsToGeographicPositionCommandsFactory(null);
			
	}		
}
