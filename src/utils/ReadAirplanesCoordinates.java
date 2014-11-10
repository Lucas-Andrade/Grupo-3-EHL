package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import app.InvalidArgumentException;
import app.InvalidFlightIDException;

/**
 * Allows to update the Airplanes coordinates from a file
 * 
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 */

public class ReadAirplanesCoordinates {

	private String emptyFields;
	private String unrecognizedFlights;
	private String sourceOfFlights = "src/FilesToRead/newCoordinates.txt";

/**
 * Reads the airplaine's id and coordinates from a file, converts the data of id in String and the coordinates in doubles
 * Updates the new coordinates in the airplane.
 * If this method doesn't find the id of a plane in database it will save the information in the String unrecognizedFlight
 * If this method doesn't read the 4 parameters (id, latitude, longitude and altitude) in a line it will save the number of the line
 */
	public void readFromFile()throws InvalidFlightIDException, InvalidArgumentException
	{
		readFromFile(sourceOfFlights);
	}
	
/**
 * Reads the airplaine's id and coordinates from a file, converts the data of id in String and the coordinates in doubles
 * Updates the new coordinates in the airplane.
 * If this method doesn't find the id of a plane in database it will save the information in the String unrecognizedFlight
 * If this method doesn't read the 4 parameters (id, latitude, longitude and altitude) in a line it will save the number of the line
 * @param sourceOfFlights file that has the new airplanes coordinates
 */
	public void readFromFile(String sourceOfFlights) throws InvalidFlightIDException, InvalidArgumentException
	{

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(sourceOfFlights));

			emptyFields = "";
			unrecognizedFlights = "";
			String delim = " ";

			Database data = new Database();

			String nextLine;		
			nextLine = reader.readLine();
			int countLine = 0;

			while(nextLine != null)
			{	countLine++;
			StringTokenizer tokenizer = new StringTokenizer(nextLine, delim);

			if(tokenizer.countTokens() == 4)
			{			
				String id = tokenizer.nextToken();
				double lat = Double.parseDouble(tokenizer.nextToken());
				double lon = Double.parseDouble(tokenizer.nextToken());
				double alt = Double.parseDouble(tokenizer.nextToken());

				if(data.contains(id))
				{
					Airship a = data.getAirplane(id);
					GeographicalPosition newGeoP = new GeographicalPosition(lat, lon, alt);
					a.updateGeographicPosition(newGeoP);
				}

				else
					unrecognizedFlights += "Unrecognized flight ID: " + id +"Latitude: " + lat + "Longitude: " + lon + "Altitude: " + alt + "\n";

			}
			else 
				emptyFields += "Empty Fields at Line: " + countLine + "\n";


			nextLine = reader.readLine();
			}
		}

		catch (FileNotFoundException e)
		{
			System.out.println(sourceOfFlights + " not found or is inaccessible");
			e.printStackTrace();
		}

		catch (IOException e)
		{
			System.out.println("Fail reading" + sourceOfFlights);
			e.printStackTrace();
		}
	}
	/**
	 * Gets the emptyFields
	 * @return a String with the number of the line of empty fields
	 */
	public String getEmptyFields()
	{
		return emptyFields;
	}
	
	/**
	 * Gets the unrecognized Flights information
	 * @return a String with the unrecognized flights
	 */
	public String getunrecognizedFlights()
	{
		return unrecognizedFlights;
	}
	
	/**
	 * Sets a new path of the text file with the new geographical coordinates
	 * @param newSource new path of text file
	 */
	public void setsourceOfFlights(String newSource)
	{
		this.sourceOfFlights = newSource;
	}
	
	/**
	 * Gets the path of the text file with the new geographical coordinates
	 * @return path with source of flights
	 */
	public String getsourceOfFlights()
	{
		return sourceOfFlights;
	}
}