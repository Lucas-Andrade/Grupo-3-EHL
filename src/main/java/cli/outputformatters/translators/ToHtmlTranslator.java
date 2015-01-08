package main.java.cli.outputformatters.translators;


import java.util.ArrayList;
import java.util.Map.Entry;
import main.java.cli.outputformatters.Translatable;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.formattersexceptions.UnknownTranslatableException;


/**
 * Class whose instances have the task of translating {@link Translatable}
 * instances into formatted strings.
 * <p>
 * <b>ATTENTION:</b>
 * </p>
 * <p>
 * It is advised to only try to encode {@link Translatable} instances that
 * follow the conventions on the {@link Translatable} documentation.
 * </p>
 * <p>
 * <b>Documentation notes:</b>
 * </p>
 * <p>
 * This documentation was written using the technical terms as defined in <a
 * href ="http://en.wikipedia.org/wiki/HTML_element#Syntax">this weblink</a>.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToHtmlTranslator implements Translator
{
	
	// INSTANCE FIELD
	private final String indentationSpaces;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of {@link ToHtmlTranslator}.
	 * 
	 * @param indentationNumberOfSpaces
	 *            The number of spaces to appear in the indentation.
	 * @throws InvalidArgumentException
	 *             If {@code indentationNumberOfSpaces<1}.
	 */
	public ToHtmlTranslator( int indentationNumberOfSpaces )
			throws InvalidArgumentException {
		
		indentationSpaces = createIdentationSpaces( indentationNumberOfSpaces );
	}
	
	
	
	// PUBLIC METHOD
	/**
	 * Returns a string representation of {@code translatable} in HTML. <b>It is
	 * advised to only try to encode {@link Translatable} instances that follow
	 * the conventions on the {@link Translatable} documentation.</b>
	 * 
	 * @param translatable
	 *            The {@link Translatable} to be translated to HMTL.
	 * @return A string representation of {@code translatable} in HTML.
	 * @throws UnknownTranslatableException
	 *             If {@code translatable} does not follow the conventions
	 *             specified in the {@link Translatable} documentation.
	 */
	@Override
	public String encode( Translatable translatable )
			throws UnknownTranslatableException {
		
		// ATTENTION: the order of the if statements on this method must not be
		// changed unless you thought really well about what you're about to do!
		
		// if translatable represents a String
		if( translatable.getTag() == null )
			return arrayListToString( createNormalElements( "html",
					getPropertiesAsRawTextElements( translatable ) ) );
		
		// if translatable represents simple elements, iterable or map<str,str>
		return arrayListToString( createNormalElements( "html",
				encodeInSeparateLines( translatable ) ) );
		
	}
	
	
	
	// AUXILIARY PRIVATE METHOD
	
	
	
	// used in the constructor
	/**
	 * Returns a string with the space-character (" ") repeated
	 * {@code indentationNumberOfSpaces} times. (e.g if
	 * {@code indentationNumberOfSpaces==6}, returns
	 * "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp").
	 * 
	 * @param indentationNumberOfSpaces
	 *            The number of spaces to appear in the indentation.
	 * @return A string with the space-character (" ") repeated
	 *         {@code indentationNumberOfSpaces} times.
	 * @throws InvalidArgumentException
	 *             If {@code indentationNumberOfSpaces<1}.
	 */
	private String createIdentationSpaces( int indentationNumberOfSpaces )
			throws InvalidArgumentException {
		
		if( indentationNumberOfSpaces < 1 )
			throw new InvalidArgumentException(
					"Invalid negative number of spaces for identation." );
		
		
		StringBuilder spaces = new StringBuilder();
		for( int i = 0; i < indentationNumberOfSpaces; ++i )
			spaces.append( " " );
		return spaces.toString();
	}
	
	
	
	// used in the method encode and encodeInSeparateLines
	/**
	 * Returns an {@link ArrayList} in which each entry is
	 * {@code "<key>value</key>"}, where {@code key} and {@code value} are
	 * obtained from each entry of the properties bag of {@code t}.
	 * 
	 * @param t
	 *            The translatable whose properties bag is to be converted in an
	 *            {@link ArrayList} of HTML raw text elements.
	 * @return An {@link ArrayList} whose strings are HTML raw text elements.
	 */
	private ArrayList< String > getPropertiesAsRawTextElements( Translatable t ) {
		
		ArrayList< String > al = new ArrayList<>();
		for( Entry< String, Object > entry : t.getPropertiesBag().entrySet() )
			al.add( createRawTextElement( entry.getKey(), entry.getValue()
					.toString() ) );
		return al;
	}
	
	// used in the method encode
	/**
	 * Returns a string representation of {@code translatable} where each line
	 * is contained in a different entry of the {@link ArrayList}.
	 * 
	 * @param translatable
	 *            The {@link Translatable} to be translated to HMTL.
	 * @return A string representation of {@code translatable} in HTML where
	 *         each line is contained in a different entry of the
	 *         {@link ArrayList}.
	 * @throws UnknownTranslatableException
	 *             If {@code translatable} does not follow the conventions
	 *             specified in the {@link Translatable} documentation.
	 */
	private ArrayList< String > encodeInSeparateLines( Translatable translatable )
			throws UnknownTranslatableException {
		
		// ATTENTION: the order of the if statements on this method must not be
		// changed unless you thought really well about what you're about to do!
		
		
		// if translatable represents a simple instance
		if( translatable.getEntryTag() == null )
			return createNormalElements( translatable.getTag(),
					getPropertiesAsRawTextElements( translatable ) );
		
		
		// if translatable represents an iterable
		if( translatable.getKeyTag() == null )
			return encodeIterable( translatable );
		
		// if translatable represents a Map<String,String>
		return encodeMap( translatable );
	}
	
	// used in the method encode and encodeInSeparateLines
	/**
	 * Creates a HTML normal element.
	 * <p>
	 * The returned {@link ArrayList}
	 * <ul>
	 * <li>has the string <code>{@literal <}elementName{@literal >}</code> in
	 * the first entry,</li>
	 * <li>the next entries result of indenting all the strings of
	 * {@code internalLines} (assumes each of them is a line) and</li>
	 * <li>has the string <code>{@literal <}elementName{@literal >}</code> in
	 * the last entry of the list.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param elementName
	 *            The element name (to appear inside the tags).
	 * @param internalLines
	 *            The content of the element.
	 * @return An {@link ArrayList} whose entries are the described above.
	 */
	private ArrayList< String > createNormalElements( String elementName,
			ArrayList< String > internalLines ) {
		
		ArrayList< String > lines = new ArrayList< String >();
		
		lines.add( createStartTag( elementName ) );
		
		internalLines = indent( internalLines );
		for( String line : internalLines )
			lines.add( line );
		
		lines.add( createEndTag( elementName ) );
		
		return lines;
	}
	
	// used in the method encode(Translatable)
	/**
	 * Returns the string that contains all the entries of {@code list}
	 * separated by the paragraph-character ({@code \n}). It is assumed that
	 * each entry represents a line. This string starts and ends with a
	 * paragraph-character.
	 * 
	 * @param list
	 *            The lines to be concatenated.
	 * @return
	 */
	private String arrayListToString( ArrayList< String > list ) {
		
		StringBuilder sb = new StringBuilder( "\r\n" );
		for( String line : list )
			sb.append( line ).append( "\r\n" );
		return sb.toString();
	}
	
	
	
	// used in method encodeInSeparateLines
	/**
	 * Returns a string representation of {@code t}, which is assumed to
	 * represent an {@link Iterable}, where each line is contained in a
	 * different entry of an {@link ArrayList}.
	 * 
	 * @param t
	 *            The {@link Translatable} to be translated to HMTL.
	 * @return A string representation of {@code t} in HTML where each line is
	 *         contained in a different entry of the {@link ArrayList}.
	 * @throws UnknownTranslatableException
	 *             If {@code t} does not follow the conventions specified in the
	 *             {@link Translatable} documentation.
	 */
	private ArrayList< String > encodeIterable( Translatable t )
			throws UnknownTranslatableException {
		
		ArrayList< String > internalLines = new ArrayList< String >();
		try
		{
			for( Object element : t.getPropertiesBag().values() )
				internalLines
						.addAll( encodeInSeparateLines( (Translatable)element ) );
		}
		catch( ClassCastException e )
		{
			throw new UnknownTranslatableException(
					"Translatable representing iterable has non-translatable values in the properties bag." );
		}
		
		return createNormalElements( t.getTag(), internalLines );
	}
	
	// used in method encodeInSeparateLines
	/**
	 * Returns a string representation of {@code t}, which is assumed to
	 * represent an {@code Map}, where each line is contained in a different
	 * entry of an {@link ArrayList}.
	 * 
	 * @param t
	 *            The {@link Translatable} to be translated to HMTL.
	 * @return A string representation of {@code t} in HTML where each line is
	 *         contained in a different entry of the {@link ArrayList}.
	 */
	private ArrayList< String > encodeMap( Translatable t ) {
		
		ArrayList< String > internalLines = new ArrayList< String >();
		
		for( Entry< String, Object > entry : t.getPropertiesBag().entrySet() )
		{
			ArrayList< String > codedEntry = new ArrayList< String >();
			codedEntry
					.add( createRawTextElement( t.getKeyTag(), entry.getKey() ) );
			codedEntry.add( createRawTextElement( t.getValueTag(), entry
					.getValue().toString() ) );
			
			internalLines.addAll( createNormalElements( t.getEntryTag(),
					codedEntry ) );
		}
		
		return createNormalElements( t.getTag(), internalLines );
	}
	
	
	
	// used in the method createNormalElements
	/**
	 * Concatenates {@link #indentationSpaces} in the beginning of each string
	 * of {@code list}, assuming each entry is a line.
	 * 
	 * @param list
	 *            The list of lines to be indent.
	 * @return The indented lines in a new list of strings.
	 */
	private ArrayList< String > indent( ArrayList< String > list ) {
		
		ArrayList< String > al = new ArrayList<>();
		for( String line : list )
			al.add( indentationSpaces + line );
		return al;
	}
	
	// used in the method getPropertiesAsRawTextElements
	/**
	 * Creates a HTML raw text element.
	 * 
	 * @param elementName
	 *            The element name (to appear inside the tags).
	 * @param elementContent
	 *            The element content (to appear between the tags).
	 * @return The string
	 *         <code>{@literal <}elementName{@literal >} elementContent
	 *         {@literal <}/elementName{@literal >}</code>.
	 */
	private String createRawTextElement( String elementName,
			String elementContent ) {
		
		return new StringBuilder( createStartTag( elementName ) )
				.append( elementContent ).append( createEndTag( elementName ) )
				.toString();
	}
	
	// used in method createNormalElements and createRawTextElement
	/**
	 * Creates a HMTL element's start tag.
	 * 
	 * @param elementName
	 *            The element name (to appear inside the tags).
	 * @return The string <code>{@literal <}elementName{@literal >}</code>.
	 */
	private String createStartTag( String elementName ) {
		
		return new StringBuilder( "<" ).append( elementName ).append( ">" )
				.toString();
	}
	
	// used in method createNormalElements and createRawTextElement
	/**
	 * Creates a HMTL element's end tag.
	 * 
	 * @param elementName
	 *            The element name (to appear inside the tags).
	 * @return The string <code>{@literal <}/elementName{@literal >}</code>.
	 */
	private String createEndTag( String elementName ) {
		
		return new StringBuilder( "</" ).append( elementName ).append( ">" )
				.toString();
	}
	
	
	
}
