package airtrafficcontrol.app.utils.towerControl;


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
import airtrafficcontrol.app.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.utils.airshipplan.AirCorridorInTime;
import airtrafficcontrol.app.utils.airshipplan.AltitudeCorridor;
import airtrafficcontrol.app.utils.airshipplan.FlightPlan;
import airtrafficcontrol.app.utils.hangar.AirCraft;
import airtrafficcontrol.app.utils.hangar.Airship;
import airtrafficcontrol.app.utils.hangar.CivilAirPlane;
import airtrafficcontrol.app.utils.hangar.CivilHelicopter;
import airtrafficcontrol.app.utils.hangar.MilitaryAirPlane;
import airtrafficcontrol.app.utils.hangar.MilitaryHelicopter;
import airtrafficcontrol.app.utils.hangar.OVNI;


/**
 * Class whose instances read a list of flights from a text file and create an
 * instance of {@link Database}.
 * 
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class ReadListOfFlights
{
	private static Airship airship1;
	private static Airship airship2;
	private static Airship airship3;
	private static Airship airship4;
	private static AirCraft airCraft1;
	
	static
	{
		try
		{
			airship1 = new CivilAirPlane( "xpto01", new GeographicalPosition( 20,130, 0 ),
					new FlightPlan(
							new GregorianCalendar( 2014, 11, 10, 00, 15 ),
							new GregorianCalendar( 2014, 11, 10, 04, 15 ) , 8, 10, 4), 50 );
			airship2 = new MilitaryHelicopter( "xpto02", new GeographicalPosition( 30,
					30, 0 ),
					new FlightPlan(
							new GregorianCalendar( 2014, 11, 11, 00, 15 ),
							new GregorianCalendar( 2014, 11, 11, 04, 15 ),9 ,9, 6 ), true );
			airship3 = new MilitaryAirPlane( "xpto03", new GeographicalPosition(
					40, 30, 0 ), new FlightPlan( new GregorianCalendar( 2014,
					11, 12, 00, 15 ), new GregorianCalendar( 2014, 11, 12, 04,
					15 ), 13, 15, 7 ) , false);
			airship4 = new MilitaryAirPlane( "xpto04", new GeographicalPosition(
					40, 30, 0 ), new FlightPlan( new GregorianCalendar( 2014,
					11, 13, 00, 15 ), new GregorianCalendar( 2014, 11, 13, 04,
					15 ), 13, 15, 7 ) , false);
			airCraft1 = new OVNI(  new GeographicalPosition(20.00, 130.00, 0.00 ) );
		}
		catch( InvalidArgumentException e ){}
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
					listOfFlights.get( i ), " \t" );
			
			String flightID = tokenizer.nextToken();
			String typeOfAirship = tokenizer.nextToken();
			Integer passengers;
			Calendar dateTakeOff;
			Double latitude;
			Double longitude;
			GeographicalPosition pos;
			Calendar dateLanding;
			FlightPlan plan;
			
			
			//
			switch( typeOfAirship )
			{
				case "cap":					//civil Airplane
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
							airship1.getPlan().getNumberOfMinutesToTakeOff(),
							airship1.getPlan().getNumberOfMinutesToLand(),
							airship1.getPlan().getNumberOfMinutesToSwitchCorridor() );
					
					CivilAirPlane airliner = new CivilAirPlane( flightID, pos, plan, passengers );
					database.addAirship( airliner );
					break;
				
					
				case "ch":				//civil helicopter
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
							airship2.getPlan().getNumberOfMinutesToTakeOff(),
							airship2.getPlan().getNumberOfMinutesToLand(),
							airship2.getPlan().getNumberOfMinutesToSwitchCorridor() );
					
					CivilHelicopter cHeli = new CivilHelicopter( flightID, pos, plan,	passengers );
					database.addAirship( cHeli );
					break;
				
					
				case "map":					// military Airplane
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
							airship3.getPlan().getNumberOfMinutesToTakeOff(),
							airship3.getPlan().getNumberOfMinutesToLand(),
							airship3.getPlan().getNumberOfMinutesToSwitchCorridor() );
					
					MilitaryAirPlane cargo = new MilitaryAirPlane( flightID, pos, plan,(armament == 0) ? false : true );
					database.addAirship( cargo );
					break;
				
					
				case "mh":                 //military helicopter
					tokenizer.nextToken();
					Integer armament1 = Integer.parseInt( tokenizer.nextToken() );
					dateTakeOff = getDate( tokenizer.nextToken() );
					
					latitude = Double.parseDouble( tokenizer.nextToken() );
					longitude = Double.parseDouble( tokenizer.nextToken() );
					
					pos = new GeographicalPosition( latitude, longitude, 0 );
					
					dateLanding = getDate( tokenizer.nextToken() );
					
					tokenizer.nextToken();
					tokenizer.nextToken();
					
					plan = getFlightPlan( dateTakeOff, dateLanding, tokenizer,
							airship4.getPlan().getNumberOfMinutesToTakeOff(),
							airship4.getPlan().getNumberOfMinutesToLand(),
							airship4.getPlan().getNumberOfMinutesToSwitchCorridor() );
					
					MilitaryHelicopter t = new MilitaryHelicopter( flightID, pos, plan,(armament1 == 0) ? false : true );
					database.addAirship( t );
					break;
					
					
				case "ufo":                 //Unknown flight object
					tokenizer.nextToken();
					
					latitude = Double.parseDouble( tokenizer.nextToken() );
					longitude = Double.parseDouble( tokenizer.nextToken() );
					
					pos = new GeographicalPosition( latitude, longitude, 0 );
					
					dateLanding = getDate( tokenizer.nextToken() );
					
					tokenizer.nextToken();
					tokenizer.nextToken();
					
					OVNI ovni = new OVNI(  pos );
//							(armament == 0) ? false : true );
					
//					plan = getFlightPlan( dateTakeOff, dateLanding, tokenizer,
//							airCraft1.getPlan().getNumberOfMinutesToTakeOff(),
//							airCraft1.getPlan().getNumberOfMinutesToLand(),
//							airCraft1.getPlan().getNumberOfMinutesToSwitchCorridor() );
//					
//					Transport t = new Transport( flightID, pos, plan,
//							(armament == 0) ? false : true );
					database.addAirship( ovni );
					break;
				
				default:
					throw new InvalidArgumentException(
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
		
		FlightPlan plan = new FlightPlan( takeOffDate, landingDate, takeOff, land, switchCorridor);
		
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
	public static Calendar createDefensiveCopyOfTheDate( Calendar dateToCopy )
			throws InvalidArgumentException {
		
		if( dateToCopy == null )
			throw new InvalidArgumentException();
		
		Date aux = dateToCopy.getTime();
		Calendar newDate = new GregorianCalendar();
		newDate.setTime( aux );
		return newDate;
	}
}
