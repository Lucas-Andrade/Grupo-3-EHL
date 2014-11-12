package airtrafficcontrol.app.appforconsole;


/**
 * The instances of this class provide tools for formatting console output.
 * 
 * <p>
 * The instances of this class provide a customized section delimiter
 * established in the constructor whose presence in the output contributes to
 * the output's readability (the {@code sectionDelimiter}); provide a string
 * with a fixed number of blank lines established in the constructor intended
 * for separating sections (the {@code spaceBetweenSections}) and have a method
 * that formats a section title to match the style of the section delimiter.
 * </p>
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class ConsoleOutputFormatter
{
	
	// CAMPOS
	
	
	/**
	 * The section delimiter. The section delimiter is a long sequence of the
	 * same symbol that occupies a line. Intended for visually separating output
	 * sections.
	 */
	public final String sectionDelimiter;
	
	/**
	 * The established blank lines to appear between the output sections.
	 */
	public final String spaceBetweenSections;
	
	
	
	// CONSTRUTORES
	
	
	
	/**
	 * Creates a new ConsoleOutputFormatter. By omission, its section delimiter
	 * is "--------------------------------------------------" (50x the symbol
	 * '-') and the space between sections is 2 blank lines.
	 */
	public ConsoleOutputFormatter() {
		sectionDelimiter = "--------------------------------------------------";
		spaceBetweenSections = "\n\n";
	}
	
	/**
	 * Creates a new ConsoleOutputFormatter whose section delimiter is
	 * {@code lengthOfTheDelimiter} times the symbol {@code symbol} and space
	 * between sections is {@code numberOfBlankLines} blank lines.
	 * 
	 * <p>
	 * If a non-positive number is introduced either in
	 * {@code lengthOfTheDelimiter} or in {@code numberOfBlankLines}, this
	 * constructor acts as the constructor with no parameters.
	 * </p>
	 * 
	 * @param symbol
	 *            The symbol repeated in the delimiter.
	 * @param lengthOfTheDelimiter
	 *            The number of repetitions of {@code symbol}.
	 * @param numberOfBlankLines
	 *            The number of blank lines in the space between sections.
	 */
	public ConsoleOutputFormatter( char symbol, int lengthOfTheDelimiter,
			int numberOfBlankLines ) {
		
		if( lengthOfTheDelimiter < 1 || numberOfBlankLines < 1 )
		{
			sectionDelimiter = "--------------------------------------------------";
			spaceBetweenSections = "\n\n";
		}
		else
		{
			StringBuilder delimiter = new StringBuilder();
			for( int time = 0; time < lengthOfTheDelimiter; ++time )
				delimiter.append( symbol );
			sectionDelimiter = delimiter.toString();
			
			StringBuilder space = new StringBuilder( "" );
			for( int line = 0; line < numberOfBlankLines; ++line )
				space.append( "\n" );
			spaceBetweenSections = space.toString();
		}
		
	}
	
	
	
	// MÉTODOS RELACIONADOS COM A APRESENTAÇÃO DO OUTPUT
	
	
	/**
	 * Prints the section delimiter to the console. After printing the section
	 * delimiter, the prompt is left in a new line.
	 */
	public void printSectionDelimiter() {
		System.out.println( "\n" + sectionDelimiter );
	}
	
	
	/**
	 * Formats a section title.
	 * 
	 * <p>
	 * The {@code title} will be returned in a single line which is constructed
	 * from the section delimiter of this app (see the method
	 * {@code sectionDelimiter()}) by removing the middle symbols and inserting
	 * {@code title} in its place; the formatted title string has the same
	 * length as the section delimiter string. </br>For example, if the section
	 * delimiter was "......................" and {@code title} was "My Title",
	 * the formatted section title would be the line "...... MY TITLE ......".
	 * <p>
	 * In case the length of {@code title} equals or exceeds the length of the
	 * section delimiter, the title will simply be printed in upper case. Also,
	 * if the string {@code title} has line changing characters, the title will
	 * simply be printed in upper case using has many lines as the string
	 * {@code title} contains.
	 * </p>
	 * 
	 * @param title
	 *            The section title to be formatted.
	 * @return The string containing the formatted section title.
	 */
	public String formatSectionTitle( String title ) {
		
		if( title == null )
			return "";
		
		// the length of the app's section delimiter, which will also be the
		// length of the string which is the formatted version of title:
		int theLength = sectionDelimiter.length();
		
		
		// cases in which the format will be kept more simple:
		if( title.length() + 3 >= theLength || title.contains( "\n" ) )
			return title.toUpperCase();
		
		
		// construction of a formatted title:
		
		int numOfCharsToEachSideOfTheTitle = (theLength - title.length() - 2) / 2;
		String charsToTheLeft = sectionDelimiter.substring( 0,
				numOfCharsToEachSideOfTheTitle );
		String charsToTheRight = sectionDelimiter.substring( theLength
				- numOfCharsToEachSideOfTheTitle, theLength );
		
		StringBuilder formattedSectionTitle = new StringBuilder( charsToTheLeft )
				.append( " " ).append( title.toUpperCase() ).append( " " );
		
		// if the parity of the title length is different from the parity of
		// theLength, an extra char is printed after the space after the title
		if( (title.length() & 1) != (theLength & 1) )
			formattedSectionTitle.append( sectionDelimiter.charAt( theLength
					- numOfCharsToEachSideOfTheTitle - 1 ) );
		
		formattedSectionTitle.append( charsToTheRight );
		
		// return
		return formattedSectionTitle.toString();
		
	}
	
	
	/**
	 * Prints a formatted section title to the console (see the method
	 * {@code formatSectionTitle(String title)}).
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
		System.out.print( "\n" + spaceBetweenSections );
	}
	
	
//	/**
//	 * Formats a text.
//	 * 
//	 * <p>
//	 * The {@code text} will be returned a
//	 * @param text
//	 * @return
//	 */
//	public String formatText(String text){
//		
//		if(text==null) return "";
//		
//		// the length of the app's section delimiter, which will also be the
//		// length of the lines of the formatted text
//		int linesLength = sectionDelimiter.length();
//		int nrOfWrittenChars = text.length();
//		
//		StringBuilder newText = new StringBuilder("");
//		int lastLineWrittenInNewText = 0;
//		if(text.contains("\n")) ;//TODO
//		while(nrOfWrittenChars>0) newText
//		
//		
//	}
}
