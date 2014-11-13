package airtrafficcontrol.app.utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;


/**
 * Class whose instances read a list of flights from a text file and create an
 * instance of {@link Database}.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ReadListOfFlights
{
	
	private static Airship airplane1;
	private static Airship airplane2;
	private static Airship airplane3;
	private static Airship airplane4;
	
	static
	{
		try
		{
			airplane1 = new Airliner( "xpto01", new GeographicalPosition( 20,
					130, 0 ),
					new FlightPlan(
							new GregorianCalendar( 2014, 11, 10, 00, 15 ),
							new GregorianCalendar( 2014, 11, 10, 04, 15 ) ), 50 );
			airplane2 = new PrivateJet( "xpto02", new GeographicalPosition( 30,
					30, 0 ),
					new FlightPlan(
							new GregorianCalendar( 2014, 11, 11, 00, 15 ),
							new GregorianCalendar( 2014, 11, 11, 04, 15 ) ), 10 );
			airplane3 = new CargoAircraft( "xpto03", new GeographicalPosition(
					40, 30, 0 ), new FlightPlan( new GregorianCalendar( 2014,
					11, 12, 00, 15 ), new GregorianCalendar( 2014, 11, 12, 04,
					15 ) ) );
			airplane4 = new Transport( "xpto04", new GeographicalPosition(
					20.00, 130.00, 0.00 ), new FlightPlan(
					new GregorianCalendar( 2014, 11, 13, 00, 15 ),
					new GregorianCalendar( 2014, 11, 13, 04, 15 ) ), false );
		}
		catch( InvalidArgumentException e )
		{
			airplane1 = airplane2 = airplane3 = airplane4 = null;
		}
	}
	
	
	/**
	 * Reads a text file and creates a new database with the flights'
	 * information contained in the file.
	 * 
	 * @param name
	 *            The name of the file to read, which must be in the directory
	 *            «src/airtrafficcontrol/filestoread/».
	 * @return An instance of {@link Database} with all the flights whose
	 *         information was correctly written in the text file.
	 * @throws InvalidArgumentException
	 *             If {@code name} is {@code null}.
	 * @throws InvalidFlightIDException
	 * @throws IOException
	 *             If it is not possible to read the text file or close the
	 *             reader.
	 */
	public Database readFlights( String name ) throws InvalidFlightIDException,
			InvalidArgumentException, IOException
	
	{
		if( name == null )
			throw new InvalidArgumentException();
		
		
		// the database to return
		Database database = new Database();
		
		
		
		// reads the file and stores the lines one by one in an ArrayList of
		// strings
		ArrayList< String > listOfFlights = convertFileLinesIntoStringArrayList( name );
		
		
		
		// reads each line of the ArrayList and creates a new Airplane
		
		for( int i = 0; i < listOfFlights.size(); i++ )
		{
			StringTokenizer tokenizer = new StringTokenizer(
					listOfFlights.get( i ), " " );
			
			String flightID = tokenizer.nextToken();
			String typeOfAirship = tokenizer.nextToken();
			Integer passengers;
			Calendar dateTakeOff;
			Double latitude;
			Double longitude;
			GeographicalPosition pos;
			Calendar dateLanding;
			FlightPlan plan;
			
			switch( typeOfAirship )
			{
				case "a":
					passengers = Integer.parseInt( tokenizer.nextToken() );
					tokenizer.nextToken();
					dateTakeOff = getDate( tokenizer.nextToken() );
					
					latitude = Double.parseDouble( tokenizer.nextToken() );
					longitude = Double.parseDouble( tokenizer.nextToken() );
					
					pos = new GeographicalPosition( latitude, longitude, 0 );
					
					dateLanding = getDate( tokenizer.nextToken() );
					
					tokenizer.nextToken();
					tokenizer.nextToken();
					
					plan = getFlightPlan( dateTakeOff, dateLanding, tokenizer,
							airplane1.getNumberOfMinutesToTakeOff(),
							airplane1.getNumberOfMinutesToLand(),
							airplane1.getNumberOfMinutesToSwitchCorridor() );
					
					Airliner airliner = new Airliner( flightID, pos, plan,
							passengers );
					database.addAirplane( airliner );
					break;
				
				case "j":
					passengers = Integer.parseInt( tokenizer.nextToken() );
					tokenizer.nextToken();
					dateTakeOff = getDate( tokenizer.nextToken() );
					
					latitude = Double.parseDouble( tokenizer.nextToken() );
					longitude = Double.parseDouble( tokenizer.nextToken() );
					
					pos = new GeographicalPosition( latitude, longitude, 0 );
					
					dateLanding = getDate( tokenizer.nextToken() );
					
					tokenizer.nextToken();
					tokenizer.nextToken();
					
					plan = getFlightPlan( dateTakeOff, dateLanding, tokenizer,
							airplane2.getNumberOfMinutesToTakeOff(),
							airplane2.getNumberOfMinutesToLand(),
							airplane2.getNumberOfMinutesToSwitchCorridor() );
					
					PrivateJet jet = new PrivateJet( flightID, pos, plan,
							passengers );
					database.addAirplane( jet );
					break;
				
				case "c":
					tokenizer.nextToken();
					tokenizer.nextToken();
					dateTakeOff = getDate( tokenizer.nextToken() );
					
					latitude = Double.parseDouble( tokenizer.nextToken() );
					longitude = Double.parseDouble( tokenizer.nextToken() );
					
					pos = new GeographicalPosition( latitude, longitude, 0 );
					
					dateLanding = getDate( tokenizer.nextToken() );
					
					tokenizer.nextToken();
					tokenizer.nextToken();
					
					plan = getFlightPlan( dateTakeOff, dateLanding, tokenizer,
							airplane3.getNumberOfMinutesToTakeOff(),
							airplane3.getNumberOfMinutesToLand(),
							airplane3.getNumberOfMinutesToSwitchCorridor() );
					
					CargoAircraft cargo = new CargoAircraft( flightID, pos,
							plan );
					database.addAirplane( cargo );
					break;
				
				case "t":
					tokenizer.nextToken();
					Integer armament = Integer.parseInt( tokenizer.nextToken() );
					dateTakeOff = getDate( tokenizer.nextToken() );
					
					latitude = Double.parseDouble( tokenizer.nextToken() );
					longitude = Double.parseDouble( tokenizer.nextToken() );
					
					pos = new GeographicalPosition( latitude, longitude, 0 );
					
					dateLanding = getDate( tokenizer.nextToken() );
					
					tokenizer.nextToken();
					tokenizer.nextToken();
					
					plan = getFlightPlan( dateTakeOff, dateLanding, tokenizer,
							airplane4.getNumberOfMinutesToTakeOff(),
							airplane4.getNumberOfMinutesToLand(),
							airplane4.getNumberOfMinutesToSwitchCorridor() );
					
					Transport t = new Transport( flightID, pos, plan,
							(armament == 0) ? false : true );
					database.addAirplane( t );
					break;
				
				default:
					throw new IllegalArgumentException(
							"Unrecognised airship type" );
			}
			
		}
		
		return database;
		
	}
	
	
	/**
	 * Reads a text file and converts each line into a String, then stores all
	 * the Strings in a ArrayList.
	 * 
	 * @param name
	 *            The name of the text, which must be in the directory
	 *            «src/airtrafficcontrol/filestoread/».
	 * @return An ArrayList of strings in which each entry corresponds to a line
	 *         of the file.
	 * @throws IOException
	 */
	public ArrayList< String > convertFileLinesIntoStringArrayList( String name )
			throws IOException {
		
		BufferedReader reader = new BufferedReader( new FileReader(
				"src/airtrafficcontrol/filestoread/" + name ) );
		
		ArrayList< String > listOfFlights = new ArrayList<>();
		String nextLine;
		
		while( (nextLine = reader.readLine()) != null )
		{
			listOfFlights.add( nextLine );
		}
		reader.close();
		
		return listOfFlights;
	}
	
	
	/**
	 * Builds a Calendar instance from a String with format "yyyy-MM-dd,hh:mm".
	 * 
	 * @param date
	 *            The string to be transformed into a calendar instance.
	 * @return The GregorianCalendar in which the string was converted.
	 * @throws InvalidArgumentException
	 *             If {@code date} has not the correct format.
	 */
	private Calendar getDate( String date ) throws InvalidArgumentException {
		
		Integer year, month, day, hour, minute;
		try
		{
			year = Integer.parseInt( date.substring( 0, 4 ) );
			month = Integer.parseInt( date.substring( 5, 7 ) ) - 1;
			day = Integer.parseInt( date.substring( 8, 10 ) );
			hour = Integer.parseInt( date.substring( 11, 13 ) );
			minute = Integer.parseInt( date.substring( 14 ) );
		}
		catch( NumberFormatException e )
		{
			throw new InvalidArgumentException( "INVALID DATE!" );
		}
		
		return new GregorianCalendar( (int)year, (int)month, (int)day,
				(int)hour, (int)minute );
	}
	
	
	/**
	 * Builds an instance of {@link FlightPlan} from the data read from the file
	 * 
	 * @param takeOffDate
	 *            - take off date of the flight
	 * @param landingDate
	 *            - landing date of the flight
	 * @param tokenizer
	 *            - tokenizer with the information read from the flight
	 * @param takeOff
	 *            - number of minutes this type of aircraft needs to take off
	 *            and get into the first corridor
	 * @param land
	 *            - number of minutes this type of aircraft needs to get off the
	 *            corridor and land
	 * @param switchCorridor
	 *            - number of minutes this type of aircraft needs to go from a
	 *            corridor to another
	 * @return the plan of the flight
	 * @throws InvalidArgumentException
	 */
	private FlightPlan getFlightPlan( Calendar takeOffDate,
			Calendar landingDate, StringTokenizer tokenizer, int takeOff,
			int land, int switchCorridor ) throws InvalidArgumentException {
		
		if( takeOffDate == null || landingDate == null || tokenizer == null )
			throw new InvalidArgumentException();
		
		FlightPlan plan = new FlightPlan( takeOffDate, landingDate );
		
		Calendar dateOld = createDefensiveCopyOfTheDate( takeOffDate );
		
		dateOld.add( Calendar.MINUTE, takeOff );
		
		AirCorridorInTime gainingAltitude = new AirCorridorInTime( takeOffDate,
				dateOld, null );
		plan.addEvent( gainingAltitude );
		
		Double altMin = Double.parseDouble( tokenizer.nextToken() );
		Double altMax = Double.parseDouble( tokenizer.nextToken() );
		AltitudeCorridor corr = new AltitudeCorridor( altMin, altMax );
		Calendar dateNew;
		
		while( tokenizer.hasMoreTokens() )
		{
			dateNew = getDate( tokenizer.nextToken() );
			altMin = Double.parseDouble( tokenizer.nextToken() );
			altMax = Double.parseDouble( tokenizer.nextToken() );
			
			AirCorridorInTime airCorridor = new AirCorridorInTime( dateOld,
					dateNew, corr );
			plan.addEvent( airCorridor );
			
			corr = new AltitudeCorridor( altMin, altMax );
			dateOld = createDefensiveCopyOfTheDate( dateNew );
			
			Calendar dateOldBeforeSwitch = createDefensiveCopyOfTheDate( dateOld );
			dateOld.add( Calendar.MINUTE, switchCorridor );
			AirCorridorInTime switchCorridorEvent = new AirCorridorInTime(
					dateOldBeforeSwitch, dateOld, null );
			plan.addEvent( switchCorridorEvent );
		}
		
		Calendar dateLandingMinusLanding = createDefensiveCopyOfTheDate( landingDate );
		dateLandingMinusLanding.add( Calendar.MINUTE, -land );
		AirCorridorInTime lastCorridor = new AirCorridorInTime( dateOld,
				dateLandingMinusLanding, corr );
		AirCorridorInTime landingEvent = new AirCorridorInTime(
				dateLandingMinusLanding, landingDate, null );
		
		plan.addEvent( lastCorridor );
		plan.addEvent( landingEvent );
		
		return plan;
	}
	
	
	/**
	 * Makes a defensive copy of the calendar, so that when the copy is altered,
	 * the original is not.
	 * 
	 * @param dateToCopy
	 *            The date we want to copy.
	 * @return The copied date.
	 */
	static Calendar createDefensiveCopyOfTheDate( Calendar dateToCopy )
			throws InvalidArgumentException {
		
		if( dateToCopy == null )
			throw new InvalidArgumentException();
		
		Date aux = dateToCopy.getTime();
		Calendar newDate = new GregorianCalendar();
		newDate.setTime( aux );
		return newDate;
	}
}
