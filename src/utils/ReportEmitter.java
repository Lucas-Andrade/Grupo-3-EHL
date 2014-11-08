package utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Allows to emit various types of reports. These reports can be returned 
 * as an array of strings, or written out to files
 *
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 */
public class ReportEmitter {
	
	String[] latestReport;
	String[] unrecognisedID;
	
	/**
	 * emits a report that shows the flight ID and geographic position of all the known airplanes
	 * @param database - the database where the airplanes are saved
	 * @return an array of strings with the information about the flight ID of all the known airplanes
	 * and their position
	 */
	public String[] reportAll(Map<String, Airship> database)
	{
		//chamar o método que o Hugo está a fazer
		
		Calendar now = new GregorianCalendar();
		
		Set<String> idSet = database.keySet();
		Iterator<String> iterator = idSet.iterator();
		
		ArrayList<String> listToReturn = new ArrayList<>(idSet.size());
		
		while (iterator.hasNext())
		{
			Airship airplane = database.get(iterator.next());
			if (now.compareTo(airplane.getTakeOffDate()) > 0 && airplane.wasPositionUpdated())
			{
				listToReturn.add(airplane.positionToString());
			}
		}
		
		int planesNumber = listToReturn.size();
		String[] arrayToReturn = new String[planesNumber];
		for (int i = 0; i < planesNumber; i++)
		{
			arrayToReturn[i] = listToReturn.get(i);
		}
		
		latestReport = arrayToReturn;
		return arrayToReturn;
	}
	
	/**
	 * emits a report that shows the flight ID and geographic position of all the known airplanes, and 
	 * saves that report into a file
	 * @param database - the database where the airplanes are saved
	 */
	public void reportAllToTxt(Map<String, Airship> database) throws IOException
	{
		writeToTxt(reportAll(database), "allReport");
	}
	
	
	/**
	 * emits a report with the airplanes that are out of the corridor they should be in
	 * @param database - the database where the airplanes are saved
	 * @return an array of strings with the flight ID of all the airplanes outside of the corridor
	 * they should be, at the time this method was called
	 */
	public String[] reportAirplanesOutOfCorridor(Map<String, Airship> database)
	{
		ArrayList<String> airplanesOut = new ArrayList<>();
		
		Set<String> idSet = database.keySet();
		Iterator<String> iterator = idSet.iterator();
		
		while (iterator.hasNext())
		{
			Airship airplane = database.get(iterator.next());
			AltitudeCorridor corridor = airplane.getCurrentCorridor();
			double altitude = airplane.getGeographicPosition().getAltitude();
			
			if(altitude < corridor.getLowerLimit() || altitude > corridor.getUpperLimit())
				airplanesOut.add(airplane.getFlightID());
		}
		
		int transgressorsNumber = airplanesOut.size();
		String[] arrayOfAirplanesOut = new String[transgressorsNumber];
		for (int i = 0; i < transgressorsNumber; i++)
			arrayOfAirplanesOut[i] = airplanesOut.get(i);
		
		return arrayOfAirplanesOut;
	}
	
	/**
	 * emits a report with the airplanes that are out of the corridor they should be in,
	 * and saves it into a text file
	 * @param database - the database where the airplanes are saved
	 * @throws IOException
	 */
	public void reportAirplanesOutOfCorridorToTxt(Map<String, Airship> database) throws IOException
	{
		writeToTxt(reportAirplanesOutOfCorridor(database), "outOfCorridorReport");
	}
	
	/**
	 * reports the information about a specific airplane
	 * @param airplane - the airplane to present information about
	 * @return an array of strings with the information about the airplane
	 */
	public String[] reportFlightInformation(Airship airplane)
	{
		return null;
	}
	
	/**
	 * reports the information about a specific airplane, and writes it into a text file
	 * @param airplane - the airplane to present information about
	 * @throws IOException
	 */
	public void reportFlightInformationToTxt(Airship airplane) throws IOException
	{
		writeToTxt(reportFlightInformation(airplane), "informationOn_" + airplane.getFlightID());
	}
	
	/**
	 * reports the airplanes that were detected and do not have a unrecognized/valid flight ID
	 * @return an array of strings with the airplanes without a valid ID
	 */
	public String[] reportAirplanesWithUnknownFlightID()
	{
		return unrecognisedID;
	}
	
	/**
	 * reports the airplanes that were detected and do not have a unrecognized/valid flight ID,
	 * and writes it into a text file
	 * @throws IOException
	 */
	public void reportAirplanesWithUnknownFlightIDToTxt() throws IOException
	{
		writeToTxt(unrecognisedID, "unrecognisedIDReport");
	}
	
	/**
	 * writes a in a file the array of string passed as parameter
	 * @param toWrite - array of strings to write
	 * @param name - the name of the file
	 * @throws IOException
	 */
	private void writeToTxt(String[] toWrite, String name) throws IOException
	{		
		Calendar now = new GregorianCalendar();
		DateFormat format = new SimpleDateFormat( "hh:mm_dd/MM/yyyy" );
		
		
		String destFile = "src/filesToWrite/" + name + "_" + format.format(now.getTime()) +".txt";
		
		BufferedWriter writer = new BufferedWriter(new PrintWriter(destFile));
		for (int i = 0; i < toWrite.length; i++)
		{
			writer.write(toWrite[i]);
			writer.newLine();
		}
		writer.close();
	}
	
}
