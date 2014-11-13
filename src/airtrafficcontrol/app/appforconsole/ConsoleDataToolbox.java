package airtrafficcontrol.app.appforconsole;


import airtrafficcontrol.app.exceptions.InvalidArgumentException;



/**
 * Class whose instances agregate tools for apps of air traffic control with
 * user-app interaction established through a console. Instances have two fields
 * of types {@link ConsoleOutputFormatter} and {@link ConsoleInputHandler}.
 * 
 * <p style="font-size:14">
 * <b>Implementation notes</b>
 * </p>
 * <ul>
 * <li>Fields are final and public.</li>
 * </ul>
 * 
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ConsoleDataToolbox
{
	
	// CAMPOS DA CLASSE
	
	/**
	 * The {@link ConsoleOutputFormatter console output formatter}.
	 */
	public final ConsoleOutputFormatter outputStylizer;
	
	/**
	 * The {@link ConsoleInputHandler console input handler}.
	 */
	public final ConsoleInputHandler inputHandler;
	
	
	
	// CONSTRUTOR
	
	
	
	/**
	 * Creates an instance of {@link ConsoleDataToolbox}.
	 * <p>
	 * This instance provides:
	 * <ul>
	 * <li>a {@link #outputStylizer STYLIZER} with a
	 * {@link ConsoleOutputFormatter#sectionDelimiter section delimiter} that is
	 * a line filled with a sequence of {@code lengthOfSectionDelimiter}
	 * repetitions of the symbol {@code symbolOfSectionDelimiter} and whose
	 * {@link ConsoleOutputFormatter#spaceBetweenSections space between
	 * sections} is {@code numberOfBlankLinesBetweenSections} blank lines</li>
	 * <li>an
	 * </ul>
	 * for the app.
	 * </p>
	 * 
	 * @param symbolOfSectionDelimiter
	 *            The symbol to be used in the sections' delimiters.
	 * @param lengthOfSectionDelimiter
	 *            The length of the sections' delimiters.
	 * @param numberOfBlankLinesBetweenSections
	 *            The number of blank lines that must appear between sections.
	 * @throws InvalidArgumentException
	 *             If {@code lengthOfSectionDelimiter} or
	 *             {@code numberOfBlankLinesBetweenSections} are {@code <1}.
	 */
	public ConsoleDataToolbox( char symbolOfSectionDelimiter,
			int lengthOfSectionDelimiter, int numberOfBlankLinesBetweenSections )
			throws InvalidArgumentException {
		
		if( lengthOfSectionDelimiter < 1
				|| numberOfBlankLinesBetweenSections < 1 )
			throw new InvalidArgumentException( "INVALID TOOLBOX ARGUMENTS!" );
		
		outputStylizer = new ConsoleOutputFormatter( symbolOfSectionDelimiter,
				lengthOfSectionDelimiter, numberOfBlankLinesBetweenSections );
		inputHandler = new ConsoleInputHandler();
	}
	
	
}