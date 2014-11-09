package app;


import java.util.InputMismatchException;
import java.util.Scanner;


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
	 * The class's scanner.Receives input from console.
	 */
	private static final Scanner IN = new Scanner( System.in );
	
	
	
	// MÉTODOS RELACIONADOS COM INPUT DE int'S
	
	
	
	/**
	 * Returns a {@code int} chosen by the user, ensured to be between
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
	 * @param instruction
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
	
	
	
}
