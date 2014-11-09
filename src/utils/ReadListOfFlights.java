package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

public class ReadListOfFlights {
	
	public Database readFlights(String name) throws IOException
	{
		Database database = new Database();
		
		BufferedReader reader = new BufferedReader(new FileReader("src/filesToRead/" + name));
		
		ArrayList<String> listOfFlights = new ArrayList<>();
		String nextLine;
		
		while((nextLine = reader.readLine()) != null)
		{
			listOfFlights.add(nextLine);
		}
		reader.close();
		
		for(int i = 0; i < listOfFlights.size(); i++)
		{
			StringTokenizer tokenizer = new StringTokenizer(listOfFlights.get(i), " ");
			
			String flightID = tokenizer.nextToken();	
			String typeOfAirship = tokenizer.nextToken();
			Integer passengers;
			Calendar dateTakeOff;
			Integer latitude;
			Integer longitude;
			GeographicalPosition pos;
			Calendar dateLanding;
			FlightPlan plan;
			
			switch (typeOfAirship)
			{
			case "a":
				passengers = Integer.parseInt(tokenizer.nextToken());
				tokenizer.nextToken();
				dateTakeOff = getDate(tokenizer.nextToken());
				
				latitude = Integer.parseInt(tokenizer.nextToken());
				longitude = Integer.parseInt(tokenizer.nextToken());
				
				pos = new GeographicalPosition(latitude, longitude, 0);
				
				dateLanding = getDate(tokenizer.nextToken());
				
				tokenizer.nextToken();
				tokenizer.nextToken();
				
				plan = getFlightPlan(dateTakeOff, dateLanding, tokenizer, Airliner.getNumberOfMinutesToTakeOff(),
						Airliner.getNumberOfMinutesToLand(), Airliner.getNumberOfMinutesToSwitchCorridor());
				
				Airliner airliner = new Airliner(flightID, pos, plan, passengers);
				database.addAirplane(airliner);
				break;
				
			case "j":
				passengers = Integer.parseInt(tokenizer.nextToken());
				tokenizer.nextToken();
				dateTakeOff = getDate(tokenizer.nextToken());
				
				latitude = Integer.parseInt(tokenizer.nextToken());
				longitude = Integer.parseInt(tokenizer.nextToken());
				
				pos = new GeographicalPosition(latitude, longitude, 0);
				
				dateLanding = getDate(tokenizer.nextToken());
				
				tokenizer.nextToken();
				tokenizer.nextToken();
				
				plan = getFlightPlan(dateTakeOff, dateLanding, tokenizer, PrivateJet.getNumberOfMinutesToTakeOff(),
						PrivateJet.getNumberOfMinutesToLand(), PrivateJet.getNumberOfMinutesToSwitchCorridor());
				
				PrivateJet jet = new PrivateJet(flightID, pos, plan, passengers);
				database.addAirplane(jet);
				break;
				
			case "c":
				tokenizer.nextToken();
				tokenizer.nextToken();
				dateTakeOff = getDate(tokenizer.nextToken());
				
				latitude = Integer.parseInt(tokenizer.nextToken());
				longitude = Integer.parseInt(tokenizer.nextToken());
				
				pos = new GeographicalPosition(latitude, longitude, 0);
				
				dateLanding = getDate(tokenizer.nextToken());
				
				tokenizer.nextToken();
				tokenizer.nextToken();
				
				plan = getFlightPlan(dateTakeOff, dateLanding, tokenizer, CargoAircraft.getNumberOfMinutesToTakeOff(),
						CargoAircraft.getNumberOfMinutesToLand(), CargoAircraft.getNumberOfMinutesToSwitchCorridor());
				
				CargoAircraft cargo = new CargoAircraft(flightID, pos, plan);
				database.addAirplane(cargo);
				break;
				
			case "t":
				tokenizer.nextToken();
				Integer armament = Integer.parseInt(tokenizer.nextToken());
				dateTakeOff = getDate(tokenizer.nextToken());
				
				latitude = Integer.parseInt(tokenizer.nextToken());
				longitude = Integer.parseInt(tokenizer.nextToken());
				
				pos = new GeographicalPosition(latitude, longitude, 0);
				
				dateLanding = getDate(tokenizer.nextToken());
				
				tokenizer.nextToken();
				tokenizer.nextToken();
				
				plan = getFlightPlan(dateTakeOff, dateLanding, tokenizer, Transport.getNumberOfMinutesToTakeOff(),
						Transport.getNumberOfMinutesToLand(), Transport.getNumberOfMinutesToSwitchCorridor());
				
				Transport t = new Transport(flightID, pos, plan, (armament == 0) ? false : true);
				database.addAirplane(t);
				break;
				
			default:
				throw new IllegalArgumentException("Unrecognised airship type");
			}
			
		}

		return database;
		
	}
	
	private Calendar getDate(String date)
	{
		Integer year = Integer.parseInt(date.substring(0,4));
		Integer month = Integer.parseInt(date.substring(5,7));
		Integer day = Integer.parseInt(date.substring(8,10));
		Integer hour = Integer.parseInt(date.substring(11,13));
		Integer minute = Integer.parseInt(date.substring(14));
		
		Calendar calendarDate = new GregorianCalendar((int)year, (int)month, (int)day, (int)hour, (int)minute);
		return calendarDate;
	}

	private FlightPlan getFlightPlan(Calendar dateTakeOff, Calendar dateLanding, StringTokenizer tokenizer, 
			int takeOff, int land, int switchCorridor)
	{
		FlightPlan plan = new FlightPlan(dateTakeOff, dateLanding);
		
		Calendar dateOld = dateTakeOff.getInstance();
		dateOld.add(12, takeOff);
		
		AirCorridorInTime gainingAltitude = new AirCorridorInTime(dateTakeOff, dateOld, null);
		plan.addEvent(gainingAltitude);
		
		Integer altMin = Integer.parseInt(tokenizer.nextToken());
		Integer altMax = Integer.parseInt(tokenizer.nextToken());
		AltitudeCorridor corr = new AltitudeCorridor(altMin, altMax);
		Calendar dateNew;
		
		while (true)
		{
			altMin = Integer.parseInt(tokenizer.nextToken());
			altMax = Integer.parseInt(tokenizer.nextToken());
			dateNew = getDate(tokenizer.nextToken());
			
			AirCorridorInTime airCorridor = new AirCorridorInTime(dateOld, dateNew, corr);
			plan.addEvent(airCorridor);
			
			corr = new AltitudeCorridor(altMin, altMin);
			dateOld = dateNew;
			
			if (tokenizer.hasMoreTokens())
			{
				Calendar dateOldBeforeSwitch = dateOld.getInstance();
				dateOld.add(12, switchCorridor);
				AirCorridorInTime switchCorridorEvent = new AirCorridorInTime(dateOldBeforeSwitch, dateOld, null);
				plan.addEvent(switchCorridorEvent);
			}
			else
				break;
		}
		
		Calendar dateLandingMinusLanding = dateLanding.getInstance();
		dateLandingMinusLanding.add(12, - land);
		AirCorridorInTime lastCorridor = new AirCorridorInTime(dateOld, dateLandingMinusLanding, corr);
		AirCorridorInTime landingEvent = new AirCorridorInTime(dateLandingMinusLanding, dateLanding, null);
		
		plan.addEvent(lastCorridor);
		plan.addEvent(landingEvent);
		
		return plan;
	}
	
	
	
}
