package test.java.commandlineuserinterface.commands;

 

import main.java.commandlineuserinterface.CommandParser;
import main.java.commandlineuserinterface.commands.getairshipscommands.GetReportOfTransgressionByIdCommand;
import main.java.commandlineuserinterface.exceptions.commandparserexceptions.InvalidRegisterException;
import main.java.commandlineuserinterface.model.airships.CivilAirship;
import main.java.commandlineuserinterface.model.airships.InMemoryAirshipDatabase;
import main.java.commandlineuserinterface.model.airships.MilitaryAirship;
import main.java.commandlineuserinterface.model.users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetReportOfTransgressionByIdCommand_Tests {
	
	private CommandParser commandParser = new CommandParser();
	private InMemoryAirshipDatabase airshipsDatabaseWhereToSearch = new InMemoryAirshipDatabase();
	
	private User pantunes = new User("pantunes","pass","pantunes@gmail.com","Pantunes");
		
	@Before
	public void BeforeTests() throws InvalidRegisterException {	
		
	
		commandParser.registerCommand("GET", "/airships/reports", new GetReportOfTransgressionByIdCommand.Factory(airshipsDatabaseWhereToSearch));	

	}
		
	@Test
	public void ShouldNotHaveAnyTransgressionRecords() throws Exception  {
		
		MilitaryAirship f16 = new MilitaryAirship(50, 100, 10000, 15000, 5000, true);
		MilitaryAirship bombardeiro = new MilitaryAirship(-60, 120, 12000, 20000, 10000, true);
		CivilAirship boing777 = new CivilAirship(60, 220, 10000, 10000, 8000, 300);
		
		airshipsDatabaseWhereToSearch.add(f16, pantunes);
		airshipsDatabaseWhereToSearch.add(bombardeiro, pantunes);
		airshipsDatabaseWhereToSearch.add(boing777, pantunes);
		
		
	GetReportOfTransgressionByIdCommand getReport = (GetReportOfTransgressionByIdCommand) commandParser.getCommand("GET","/airships/reports");	
	getReport.execute();
	Assert.assertEquals(getReport.getResult(),"There are no transgressions records");
	
	}
	
	@Test
	public void ShouldHaveAnyTransgressionRecords() throws Exception  {
		
		 CivilAirship boing747 = new CivilAirship(-70, 145, 9000, 20000, 10000, 350);
		 MilitaryAirship alouette = new MilitaryAirship(45, 200, 12000, 20000, 10000, false);
		 MilitaryAirship superblanik = new MilitaryAirship(45, 300, 18000, 15000, 10000, false);
	
	airshipsDatabaseWhereToSearch.add(boing747, pantunes);
	airshipsDatabaseWhereToSearch.add(alouette, pantunes); 
	airshipsDatabaseWhereToSearch.add(superblanik, pantunes);

	GetReportOfTransgressionByIdCommand getReport = (GetReportOfTransgressionByIdCommand) commandParser.getCommand("GET","/airships/reports");	
	getReport.execute();
	Assert.assertEquals(getReport.getResult(),"\n Airship flightID: 1"+"\n Airship flightID: 3");
	
	}
	
	
}
