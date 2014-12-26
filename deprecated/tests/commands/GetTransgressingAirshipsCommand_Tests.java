package test.java.commandlineuserinterface.commands;

import main.java.commandlineuserinterface.CommandParser;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetTransgressingAirshipsCommand;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.commandlineuserinterface.model.airships.InMemoryAirshipDatabase;
import main.java.commandlineuserinterface.model.airships.MilitaryAirship;
import main.java.commandlineuserinterface.model.users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetTransgressingAirshipsCommand_Tests {  

	private CommandParser commandParser = new CommandParser();
	
	private InMemoryAirshipDatabase airshipsDatabaseWhereToSearch = new InMemoryAirshipDatabase();

	private User pantunes = new User("pantunes","pass","pantunes@gmail.com","Pantunes");
	

	@Before
	public void BeforeTests() throws InvalidRegisterException {	
		

		commandParser.registerCommand("GET", "/airships/reports/{flightId}", new GetTransgressingAirshipsCommand.Factory(airshipsDatabaseWhereToSearch));	

	}
		
	@Test
	
	public void VerifyThatAirshipTransgressedTheAltitudeParameter() throws Exception  {
		
		//public void VerifyThatAirshipTransgressedTheAltitudeParameter() throws Exception  {
		
		MilitaryAirship bombardeiro = new MilitaryAirship(-60, 120, 8000, 20000, 10000, true);
		
		airshipsDatabaseWhereToSearch.add(bombardeiro, pantunes);
		
		GetTransgressingAirshipsCommand getReport = (GetTransgressingAirshipsCommand) commandParser.getCommand("GET","/airships/reports/1");	
		getReport.execute();
		
		Assert.assertEquals(getReport.getResult(),"It's Transgressing");
		
	}
	@Test
	
	public void VerifyThatAirshipDidNotTransgresTheAltitudeParameter() throws Exception  {
	
	MilitaryAirship f16 = new MilitaryAirship(50, 100, 10000, 15000, 5000, true);

	airshipsDatabaseWhereToSearch.add(f16, pantunes);
		
	GetTransgressingAirshipsCommand getReport = (GetTransgressingAirshipsCommand) commandParser.getCommand("GET","/airships/reports/2");	
	getReport.execute();
	
	Assert.assertEquals(getReport.getResult(),"It's Not Transgressing");
	
	}
	
}
