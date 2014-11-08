package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

public class ReadAirplanesCoordinates {


	public void readFromFile(String sourceOfFlights){

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(sourceOfFlights));

			String emptyFields = "";
			String toReturn = "";
			String delim = " ";

			Database data = new Database();
			Map<String, Airship> db = data.getDatabase();

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

				if(db.containsKey(id))
				{
					Airship a = data.getAirplane(id);
					GeographicalPosition newGeoP = new GeographicalPosition(lat, lon, alt);
					a.updateGeographicPosition(newGeoP);
				}

				else
					toReturn += "Unrecognized flight ID: " + id +"Latitude: " + lat + "Longitude: " + lon + "Altitude: " + alt + "\n";

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
}