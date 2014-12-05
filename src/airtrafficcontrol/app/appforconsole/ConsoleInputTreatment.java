package airtrafficcontrol.app.appforconsole;


import java.util.InputMismatchException;
import java.util.Scanner;
import airtrafficcontrol.app.exceptions.DatabaseNotFoundException;
import airtrafficcontrol.app.exceptions.FlightNotFoundInDatabaseException;
import airtrafficcontrol.app.exceptions.InvalidFlightIDException;
import airtrafficcontrol.app.exceptions.InvalidOptionNumberException;
import airtrafficcontrol.app.utils.towerControl.Database;


/**
 * Class that treats data received from console.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ConsoleInputTreatment
{
	
	/**
	 * The class's scanner. Receives input from console.
	 */
	private static final Scanner IN = new Scanner( System.in );
	private static Database flightsDB;
	
	
	
	// M�TODOS RELACIONADOS COM INPUT DE int'S
	
	
	
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
	 */
	public static int getAValidIntFromUser( int min, int max, String instruction ) {
		
		int inputNumber = max + 1;
		while( inputNumber == max + 1 )
			// the same as "while the selectedOption is invalid"
			try
			{
				inputNumber = askTheUserForAnInt( min, max, instruction );
			}
			catch( InputMismatchException e )
			{
				System.out.println( "INVALID SYMBOL!\n" );
				IN.nextLine(); // limpeza
			}
			catch( InvalidOptionNumberException e )
			{
				System.out.println( "INVALID NUMBER!\n" );
				IN.nextLine(); // limpeza
			}
		return inputNumber;
	}
	
	
	/**
	 * Asks the user a for a {@code int} between {@code min} and {@code max}.
	 * 
	 * @param min
	 *            The minimum value allowed.
	 * @param max
	 *            The maximum value allowed.
	 * @param repetitionInstruction
	 *            The instructions to be printed to console before the user
	 *            chooses a number.
	 * @return The number inserted by the user, if it is between {@code min} and
	 *         {@code max}; or 0, if the user inserted an invalid value.
	 * @throws InputMismatchException
	 *             If the user inserted something that is not a number in the
	 *             console.
	 * @throws InvalidOptionNumberException
	 *             If the user inserted an invalid number in the console.
	 */
	private static int askTheUserForAnInt( int min, int max, String instruction )
			throws InputMismatchException, InvalidOptionNumberException {
		
		System.out.print( instruction );
		
		int inputNumber = IN.nextInt(); // throws InputMismatchException if
										// receives a non-number
		
		if( inputNumber < min || inputNumber > max )
			throw new InvalidOptionNumberException();
		
		IN.nextLine(); // limpeza
		return inputNumber;
		
	}
	
	
	
	// M�TODOS RELACIONADOS COM INPUT DE String'S
	
	
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
	 * 
	 * @param flightsDB
	 *            The {@link Database database} in which the flight with the
	 *            {@link Airship#flightID flightID} chosen must be stored.
	 * @param instruction
	 *            The instructions to be printed to console before the user
	 *            chooses a {@link Airship#flightID flightID}.
	 * @return The flightID chosen by the user.
	 * @throws DatabaseNotFoundException
	 *             When this method tries to reach a null or inexistent flights'
	 *             database.
	 */
	public static String get_AFlightIDExistentInACertainADatabase_FromUser(
			Database flightsDB, String instruction )
			throws DatabaseNotFoundException {
		
		if( flightsDB == null )
			throw new DatabaseNotFoundException();
		
		String flightID = null;
		while( flightID == null )
			// the same as "while the selectedOption is invalid"
			try
			{
				flightID = askTheUserForAFlightID( flightsDB, instruction );
			}
			catch( InvalidFlightIDException e )
			{
				System.out.println( "INVALID FlightID!\n" );
			}
			catch( FlightNotFoundInDatabaseException e )
			{
				System.out.println( "FLIGHT NOT FOUND!\n" );
			};
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
	public static String getAValidFlightIDFromUser( String instruction )
			throws DatabaseNotFoundException {
		
		
		String flightID = null;
		while( flightID == null )
			// the same as "while the selectedOption is invalid"
			try
			{
				flightID = askTheUserForAFlightID( flightsDB, instruction );
			}
			catch( InvalidFlightIDException e )
			{
				System.out.println( "INVALID FlightID!\n" );
			}
			catch( FlightNotFoundInDatabaseException e )
			{
				System.out.println( "FLIGHT NOT FOUND!\n" );
			};
		return flightID;
	}
	
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
	private static String askTheUserForAFlightID( Database flightsDB,
			String instruction ) throws InvalidFlightIDException,
			FlightNotFoundInDatabaseException {
		
		System.out.print( instruction );
		
		String flightID = IN.nextLine(); // throws InputMismatchException if
											// receives a non-number
		
		if( isNotAnAlphanumericString( flightID ) )
			throw new InvalidFlightIDException( "INVALID FlightID!\n" );
		
		if( !flightsDB.contains( flightID ) )
			throw new FlightNotFoundInDatabaseException( "FLIGHT NOT FOUND!\n" );
		
		return flightID;
		
	}
	
	
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
	static boolean isNotAnAlphanumericString( String str ) {
		
		if( str == null || str == "" || str.contains( "\n" ) )
			return true;
		
		for( int index = 0; index < str.length(); ++index )
		{
			char c = str.charAt( index );
			if( c < 48 || (c > 57 && c < 'A') || (c > 'Z' && c < 'a')
					|| c > 'z' )
				return true;
		}
		return false;
	}
}
