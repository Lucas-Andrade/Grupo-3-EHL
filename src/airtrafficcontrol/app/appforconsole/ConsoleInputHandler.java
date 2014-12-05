package airtrafficcontrol.app.appforconsole;


import java.util.Scanner;
import airtrafficcontrol.app.exceptions.*;
import airtrafficcontrol.app.utils.towerControl.Database;


/**
 * The instances of this class treat data received from console.
 * 
 * <p style="font-size:14">
 * <b>Implementation notes</b>
 * </p>
 * <ul>
 * <li>Methods are static so that one can use them without having to create an
 * instance of type {@link ConsoleInputHandler}.</li>
 * </ul>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ConsoleInputHandler
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * The class's scanner. Receives input from console.
	 */
	private static final Scanner IN = new Scanner( System.in );
	
	
	
	// METODOS RELACIONADOS COM INPUT DE int'S
	
	
	
	/**
	 * Returns a {@code int} chosen by the user, guaranteed to be between
	 * {@code min} and {@code max}.
	 * 
	 * <p>
	 * Asks the user for a number until the number inserted by the user is valid
	 * (meaning, is between {@code min} and {@code max}) and returns the valid
	 * number chosen.
	 * </p>
	 * 
	 * @param min
	 *            The minimum value allowed.
	 * @param max
	 *            The maximum value allowed.
	 * @param instruction
	 *            The instructions to be printed to console before the user
	 *            chooses a number.
	 * @return The number chosen by the user.
	 * @throws InvalidArgumentException
	 *             If {@code min>max}.
	 */
	public static int getAValidIntFromUser( int min, int max, String instruction )
			throws InvalidArgumentException {
		
		if( instruction == null )
			instruction = "";
		if( min > max )
			throw new InvalidArgumentException( "INVALID MIN, MAX VALUES!" );
		
		int inputNumber = max + 1;
		while( inputNumber == max + 1 )
			// the same as "while the selectedOption is invalid"
			try
			{
				inputNumber = askTheUserForAnInt( min, max, instruction );
			}
			catch( NumberFormatException e )
			{
				System.out.println( "INVALID SYMBOL!\n" );
			}
			catch( InvalidArgumentException e )
			{
				System.out.println( e.getMessage() );
			}
		return inputNumber;
	}
	
	// to be used by getAValidIntFromUser
	/**
	 * Asks the user a for a {@code int} between {@code min} and {@code max}.
	 * 
	 * @param min
	 *            The minimum value allowed.
	 * @param max
	 *            The maximum value allowed.
	 * @param instruction
	 *            The instructions to be printed to console before the user
	 *            chooses a number.
	 * @return The number inserted by the user, if it is between {@code min} and
	 *         {@code max}; or 0, if the user inserted an invalid value.
	 * @throws NumberFormatException
	 *             If the user inserted something that is not a number in the
	 *             console.
	 * @throws InvalidArgumentException
	 *             If the user inserted an invalid number in the console.
	 */
	public static int askTheUserForAnInt( int min, int max, String instruction )
			throws NumberFormatException, InvalidArgumentException {
		
		System.out.print( instruction );
		
		int inputNumber = Integer.parseInt( IN.nextLine() );
		// -throws NumberFormatException if receives a non-number
		// -used nextLine to avoid rest of the line from pending
		
		if( inputNumber < min || inputNumber > max )
			throw new InvalidArgumentException( "INVALID NUMBER, NOT BETWEEN "
					+ min + " AND " + max + "!" );
		
		return inputNumber;
		
	}
	
	
	
	// METODOS RELACIONADOS COM INPUT DE String'S
	
	
	
	/**
	 * Returns a {@link String} chosen by the user, guaranteed to be the
	 * {@link Airship#flightID flightID} of a flight stored in the
	 * {@link Database database} {@code flightsDB}.
	 * 
	 * <p>
	 * Asks the user for a {@link Airship#flightID flightID} until the string
	 * inserted by the user is valid (meaning, is the {@link Airship#flightID
	 * flightID} of a flight existent in the {@code flightsDB}) and returns the
	 * valid {@link Airship#flightID flightID} chosen.
	 * </p>
	 * <p>
	 * If the user writes "ABORT" in the console, the message will be returned.
	 * </p>
	 * 
	 * @param flightsDB
	 *            The {@link Database database} in which the flight with the
	 *            {@link Airship#flightID flightID} chosen must be stored.
	 * @param instruction
	 *            The instructions to be printed to console before the user
	 *            chooses a {@link Airship#flightID flightID}.
	 * @return The valid flightID of an airship stored in {@code flightsDB}
	 *         chosen by the user; or "ABORT" if the user writes so in the
	 *         console.
	 * @throws DatabaseNotFoundException
	 *             When this method tries to reach a null or inexistent flights'
	 *             database.
	 */
	public static String get_AFlightIDExistentInACertainDatabase_FromUser(
			Database flightsDB, String instruction )
			throws DatabaseNotFoundException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException();
		if( instruction == null )
			instruction = "";
		
		String flightID = null;
		while( flightID == null || !flightsDB.contains( flightID ) )
		{
			flightID = getAValidFlightIDFromUser( instruction );
			
			if( flightID.equals( "ABORT" ) )
				break;
			
			if( !flightsDB.contains( flightID ) )
				System.out.println( "FLIGHT NOT FOUND IN DATABASE!\n" );
		}
		
		return flightID;
	}
	
	
	/**
	 * Returns a {@link String} chosen by the user, guaranteed to be a valid
	 * {@link Airship#flightID flightID}.
	 * 
	 * <p>
	 * Asks the user for a {@link Airship#flightID flightID} until the string
	 * inserted by the user is a valid {@link Airship#flightID flightID} and
	 * returns it. A valid flightID is a sequence of alphanumeric characters
	 * that can not be a null string nor an empty string ("") nor contain
	 * paragraph characters ("{@code \n}") nor contain characters that are not
	 * numbers nor letters (!"#$%.:,;- , the space character, etc).
	 * </p>
	 * 
	 * @param instruction
	 *            The instructions to be printed to console before the user
	 *            chooses a {@link Airship#flightID flightID}.
	 * @return The flightID chosen by the user.
	 */
	public static String getAValidFlightIDFromUser( String instruction ) {
		
		if( instruction == null )
			instruction = "";
		
		String flightID = null;
		while( flightID == null )
			// the same as "while the selectedOption is invalid"
			try
			{
				flightID = askTheUserForAFlightID( instruction );
			}
			catch( InvalidFlightIDException e )
			{
				System.out.println( e.getMessage() );
			}
		return flightID;
	}
	
	
	// to be used by the getFlightIDs methods above
	/**
	 * Asks the user a for a {@code String} that is a {@link Airship#flightID
	 * flightID} of a flight in {@code flightsDB}.
	 * 
	 * 
	 * @param flightsDB
	 *            The {@link Database database} in which the flight with the
	 *            {@link Airship#flightID flightID} chosen must be stored.
	 * @param instruction
	 *            The instructions to be printed to console before the user
	 *            chooses a {@link Airship#flightID flightID}.
	 * @return The {@link Airship#flightID flightID} inserted by the user, if it
	 *         is a {@link Airship#flightID flightID} ; or {@code null}, if the
	 *         user inserted an invalid {@link Airship#flightID flightID}.
	 * @throws IllegalArgumentException
	 *             If the user inserted something that is not a flightID in the
	 *             console; e.g. an empty string ("") or a string that contains
	 *             paragraph characters ("{@code \n}") or space characters(
	 *             {@code " "}).
	 */
	public static String askTheUserForAFlightID( String instruction )
			throws InvalidFlightIDException {
		
		if( instruction == null )
			instruction = "";
		
		System.out.print( instruction );
		
		String flightID = IN.nextLine();
		
		if( isNotAnAlphanumericString( flightID ) )
			throw new InvalidFlightIDException( "INVALID FlightID!\n" );
		
		return flightID;
		
	}
	
	
	// to be used by the three methods above
	/**
	 * Checks whether a string is not null, nor empty, nor contains the
	 * paragraph character ("{@code \n}") and is only constituted by
	 * alphanumeric characters.
	 * 
	 * @param str
	 *            The string to test.
	 * @return {@code true} if {@code str} is null or empty or contains the
	 *         paragraph character or has at least one character that is not a
	 *         letter nor a number; {@code false} if {@code str} has only
	 *         numbers and letters.
	 */
	public static boolean isNotAnAlphanumericString( String str ) {
		
		if( str == null || str.equals( "" ) || str.contains( "\n" ) )
			return true;
		
		for( int index = 0; index < str.length(); ++index )
		{
			if( !Character
					.isLetterOrDigit( new Character( str.charAt( index ) ) ) )
				return true;
			// if( c < '1' || ('9' < c < 'A') || 'Z' < c < 'a') || 'z' < c )
			// then str is not alphanumeric
		}
		return false;
	}
	
	
	
}
