package airtrafficcontrol.app.appforconsole;


/**
 * Class whose instances provide tools for formatting console output.
 * <p>
 * Instances of this class provide a customized
 * {@link ConsoleOutputFormatter#sectionDelimiter section delimiter} established
 * in the constructor whose presence in the output contributes to the output's
 * readability; they also provide a string with a fixed number of blank lines
 * established in the constructor intended for separating sections (the
 * {@link ConsoleOutputFormatter#spaceBetweenSections space between sections}).
 * Instances may also invoke a method that formats a section title to match the
 * style of the section delimiter.
 * </p>
 * <p style="font-size:16">
 * <b> Implementation notes</b>
 * </p>
 * <p>
 * <ul>
 * <li>{@link ConsoleOutputFormatter} instances are immutable.</li>
 * <li>Fields {@link ConsoleOutputFormatter#sectionDelimiter section delimiter}
 * and {@link ConsoleOutputFormatter#spaceBetweenSections space between
 * sections} are final and public.</li>
 * </ul>
 * </p>
 *
 * @author (original) Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (2nd version) Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro
 *         Antunes
 */
public class ConsoleOutputFormatter
{
	
	// INSTANCE FIELDS
	
	/**
	 * The section delimiter. The section delimiter is a long sequence of the
	 * same symbol that occupies a line. It's intended to establish a visual
	 * separation of the output sections.
	 */
	public final String sectionDelimiter;
	
	/**
	 * The established blank lines to appear between the output sections.
	 */
	public final String spaceBetweenSections;
	
	/**
	 * The symbol repeated in the delimiter.
	 */
	private final char symbol;
	
	
	
	// CONSTRUCTORS
	
	/**
	 * Creates a new instance of {@link ConsoleOutputFormatter}. By omission,
	 * its section delimiter is
	 * "--------------------------------------------------" (50x the symbol '-')
	 * and the space between sections is 2 blank lines.
	 */
	public ConsoleOutputFormatter() {
		this( '-', 0, 0 ); // enters the exceptional case of the constructor
							// which takes less resources in computation
	}
	
	/**
	 * Creates a new instance of {@link ConsoleOutputFormatter} whose
	 * {@link ConsoleOutputFormatter#sectionDelimiter section delimiter} is
	 * {@code lengthOfTheDelimiter} times the symbol {@code symbol} and
	 * {@link ConsoleOutputFormatter#spaceBetweenSections space between
	 * sections} is {@code numberOfBlankLines} blank lines.
	 * <p>
	 * If a non-positive number is introduced either in
	 * {@code lengthOfTheDelimiter} or in {@code numberOfBlankLines}, this
	 * constructor acts as the constructor with no parameters.
	 * </p>
	 * 
	 * @param symbol
	 *            The symbol to be repeated in the
	 *            {@link ConsoleOutputFormatter#sectionDelimiter section
	 *            delimiter}.
	 * @param lengthOfTheDelimiter
	 *            The number of repetitions of {@code symbol} in the
	 *            {@link ConsoleOutputFormatter#sectionDelimiter section
	 *            delimiter}.
	 * @param numberOfBlankLines
	 *            The number of blank lines in the
	 *            {@link ConsoleOutputFormatter#spaceBetweenSections space
	 *            between sections}.
	 */
	public ConsoleOutputFormatter( char symbol, int lengthOfTheDelimiter,
			int numberOfBlankLines ) {
		
		if( lengthOfTheDelimiter < 1 || numberOfBlankLines < 1 )
		{
			sectionDelimiter = "--------------------------------------------------";
			this.symbol = '-';
			spaceBetweenSections = "\n\n";
		}
		else
		{
			sectionDelimiter = repeatThisSymbolNTimes( symbol,
					lengthOfTheDelimiter );
			this.symbol = symbol;
			spaceBetweenSections = repeatThisSymbolNTimes( '\n',
					numberOfBlankLines );
		}
		
	}
	
	
	
	// OUTPUT FORMATTING METHODS
	
	/**
	 * Prints the {@link ConsoleOutputFormatter#sectionDelimiter section
	 * delimiter} to a new line in the console. After printing the
	 * {@link ConsoleOutputFormatter#sectionDelimiter section delimiter}, the
	 * prompt is left in a new line.
	 */
	public void printSectionDelimiter() {
		System.out.println( "\n" + sectionDelimiter );
	}
	
	/**
	 * Formats a section title.
	 * 
	 * <p>
	 * This method returns a {@link String} which is obtained by removing the
	 * middle symbols of the {@link ConsoleOutputFormatter#sectionDelimiter
	 * section delimiter} and inserting {@code title} to the upper case in its
	 * place; the formatted title {@link String} returned has the same length as
	 * the {@link ConsoleOutputFormatter#sectionDelimiter section delimiter}.
	 * </p>
	 * <p>
	 * E.g. if the section delimiter was "{@code ......................}" and
	 * {@code title} was "My Title", the formatted section title would be the
	 * line "{@code ...... MY TITLE ......}".
	 * <p>
	 * In case the length of {@code title} equals or exceeds the length of the
	 * section delimiter, the title will simply be printed in upper case. Also,
	 * if the string {@code title} has line changing characters, the title will
	 * simply be printed in upper case using has many lines as the string
	 * {@code title} contains.
	 * </p>
	 * <p>
	 * If {@code title} is {@code null}, it will be returned the empty
	 * {@link String} "".
	 * 
	 * @param title
	 *            The section title to be formatted.
	 * @return The string containing the formatted section title.
	 */
	public String formatSectionTitle( String title ) {
		
		
		// exceptional cases: when title is null, or title's length exceeds the
		// delimiter's length, or title occupies more than one line.
		
		if( title == null )
			title = "";
		
		int theLength = sectionDelimiter.length();
		
		if( title.length() + 3 >= theLength || title.contains( "\n" ) )
			return title.toUpperCase();
		
		
		// construction of a formatted title: the formatted title will be a line
		// with the same length as the section delimiter, obtained from the
		// delimiter by replacing the middle symbols of the delimiter by the
		// title surrounded by two spaces (if the parity of the title length is
		// different from the parity of theLength, an extra symbol is printed
		// after the space after the title)
		
		int nrOfSymbolsToEachSideOfTitle = (theLength - title.length() - 2) / 2;
		String symbols = repeatThisSymbolNTimes( symbol,
				nrOfSymbolsToEachSideOfTitle );
		
		return new StringBuilder( symbols )
				.append( " " )
				.append( title.toUpperCase() )
				.append( " " )
				.append(
						((title.length() & 1) != (theLength & 1)) ? symbol : "" )
				.append( symbols ).toString();
		
	}
	
	/**
	 * Prints a formatted section title to a new line in the console (see the
	 * method {@code formatSectionTitle(String title)}). After printing the
	 * formatted section title, the prompt is left in a new line.
	 * 
	 * @param title
	 *            The title to be formatted and printed.
	 */
	public void printSectionTitle( String title ) {
		System.out.println( "\n" + formatSectionTitle( title ) );
	}
	
	/**
	 * Prints the space between sections to the console. After printing the
	 * established number of blank lines, the prompt is left in a new line.
	 */
	public void printSpaceBetweenSections() {
		System.out.println( spaceBetweenSections );
	}
	
	
	
	// PRIVATE AUXILIAR METHODS
	
	/**
	 * Returns a {@link String} obtained by repeating {@code numberOfTimes}
	 * times the {@link ConsoleOutputFormatter#symbol symbol}.
	 * 
	 * @param numberOfTimes
	 *            The number of repetitions.
	 * @return A {@link String} obtained by repeating {@code numberOfTimes}
	 *         times the char {@code symbol}.
	 */
	private String repeatThisSymbolNTimes( char symbol, int numberOfTimes ) {
		
		StringBuilder result = new StringBuilder();
		for( int time = 0; time < numberOfTimes; ++time )
			result.append( symbol );
		return result.toString();
	}
	
}