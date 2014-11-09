package app;


import java.util.InputMismatchException;
import java.util.Scanner;
import utils.Database;


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
	
	
	
	// MÉTODOS RELACIONADOS COM INPUT DE int'S
	
	
	
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
	
	
	
	// MÉTODOS RELACIONADOS COM INPUT DE String'S
	
	
	/**
	 * Returns a {@code String} chosen by the user, guaranteed to be the
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
	 */
	public static String getAValidFlightIDFromUser( Database flightsDB,
			String instruction ) {
		
		int inputNumber = max + 1;
		while( inputNumber == max + 1 )
			// the same as "while the selectedOption is invalid"
			try
			{
				inputNumber = askTheUserForAFlightID( flightsDB, instruction );
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
	 *         is a {@link Airship#flightID flightID} of a flight in
	 *         {@code flightsDB}; or {@code null}, if the user inserted an
	 *         invalid {@link Airship#flightID flightID} (not a
	 *         {@link Airship#flightID flightID} or a valid one that corresponds
	 *         to a flight not stored in {@code flightsDB}).
	 * @throws IllegalArgumentException
	 *             If the user inserted something that is not a flightID in the
	 *             console; e.g. an empty string ("") or a string that contains
	 *             paragraph characters ("{@code \n}") or space characters(
	 *             {@code " "}).
	 * @throws FlightNotFoundInDatabaseException
	 *             If the flightId corresponds to a flight that is not stored in
	 *             {@code flightsDB}.
	 */
	private static int askTheUserForAFlightID( Database flightsDB,
			String instruction ) throws IllegalArgumentException,
			FlightNotFoundInDatabaseException {
		
		System.out.print( instruction );
		
		int inputNumber = IN.nextInt(); // throws InputMismatchException if
										// receives a non-number
		
		if( inputNumber < min || inputNumber > max )
			// throw new InvalidOptionNumberException();
			
			IN.nextLine(); // limpeza
		return inputNumber;
		
	}
	
}
