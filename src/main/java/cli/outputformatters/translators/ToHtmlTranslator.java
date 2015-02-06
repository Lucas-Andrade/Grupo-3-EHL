package main.java.cli.outputformatters.translators;


import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import main.java.cli.outputformatters.Translatable;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.formattersexceptions.UnknownTranslatableException;


/**
 * Class whose instances have the task of translating {@link Translatable} instances into formatted
 * strings.
 * <p>
 * <b>ATTENTION:</b> trying to encode {@link Translatable} instances that do not follow the
 * conventions established in the {@link Translatable} class documentation may produce unexpected
 * results.
 * </p>
 * <p>
 * <b>Documentation notes:</b> this class's documentation was written using the technical terms as
 * defined in <a href ="http://en.wikipedia.org/wiki/HTML_element#Syntax">this weblink</a>.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class ToHtmlTranslator implements Translator {
    
    
    // INSTANCE FIELD
    /**
     * A string composed by spaces, as many as told in the constructor.
     */
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
    public ToHtmlTranslator( int indentationNumberOfSpaces ) throws InvalidArgumentException {
    
        indentationSpaces = createIdentationSpaces( indentationNumberOfSpaces );
    }
    
    
    
    // PUBLIC METHOD
    /**
     * Returns a string representation of {@code translatable} in HTML.
     * <p>
     * <b>Attention!</b> Trying to encode {@link Translatable} instances that do not follow the
     * conventions established in the {@link Translatable} class documentation may produce
     * unexpected results.
     * </p>
     * 
     * @param translatable
     *            The {@link Translatable} to be translated to HMTL.
     * @return A string representation of {@code translatable} in HTML.
     * @throws UnknownTranslatableException
     *             If {@code translatable} does not follow the conventions specified in the
     *             {@link Translatable} documentation.
     */
    @Override
    public String encode( Translatable translatable ) throws UnknownTranslatableException {
    
        /* the order of the if statements on this method must not be changed unless you
        * thought really well about what you're about to do! */
        
        
        // if translatable represents a String
        if( translatable.getTag() == null )
            return listToString( createNormalElements( "html",
                                                       getPropertiesAsRawTextElements( translatable ) ) );
        
        // if translatable represents simple elements, iterable or map<str,str>
        return listToString( createNormalElements( "html", encodeInSeparateLines( translatable ) ) );
        
    }
    
    
    
    // AUXILIARY PRIVATE METHODS
    
    
    // used in the constructor
    /**
     * Returns a string with the space-character (" ") repeated {@code indentationNumberOfSpaces}
     * times. (e.g if {@code indentationNumberOfSpaces==6}, returns
     * "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp").
     * 
     * @param indentationNumberOfSpaces
     *            The number of spaces to appear in the indentation.
     * @return A string with the space-character (" ") repeated {@code indentationNumberOfSpaces}
     *         times.
     * @throws InvalidArgumentException
     *             If {@code indentationNumberOfSpaces<1}.
     */
    private String createIdentationSpaces( int indentationNumberOfSpaces )
        throws InvalidArgumentException {
    
        if( indentationNumberOfSpaces < 1 )
            throw new InvalidArgumentException( "Invalid negative number of spaces for identation." );
        
        
        StringBuilder spaces = new StringBuilder();
        for( int i = 0; i < indentationNumberOfSpaces; ++i )
            spaces.append( " " );
        return spaces.toString();
    }
    
    
    
    // used in the method encode and encodeInSeparateLines
    /**
     * Returns an {@link ArrayList} in which each entry is {@code "<key>value</key>"}, where
     * {@code key} and {@code value} are obtained from each entry of the properties bag of {@code t}
     * .
     * 
     * @param t
     *            The translatable whose properties bag is to be converted into an {@link ArrayList}
     *            of HTML raw text elements.
     * @return An {@link ArrayList} whose strings are HTML raw text elements.
     */
    private List< String > getPropertiesAsRawTextElements( Translatable t ) {
    
        List< String > al = new ArrayList<>();
        for( Entry< String, Object > entry : t.getPropertiesBag().entrySet() )
            al.add( createRawTextElement( entry.getKey(), entry.getValue().toString() ) );
        return al;
    }
    
    // used in the method encode
    /**
     * Returns a string representation of {@code translatable} where each line is contained in a
     * different entry of the {@link ArrayList}.
     * 
     * @param translatable
     *            The {@link Translatable} to be translated to HMTL.
     * @return A string representation of {@code translatable} in HTML where each line is contained
     *         in a different entry of the {@link ArrayList}.
     * @throws UnknownTranslatableException
     *             If {@code translatable} does not follow the conventions specified in the
     *             {@link Translatable} documentation.
     */
    private List< String > encodeInSeparateLines( Translatable translatable )
        throws UnknownTranslatableException {
    
        /* the order of the if statements on this method must not be changed unless you
        * thought really well about what you're about to do! */
        
        
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
     * <li>has the string <code>{@literal <}elementName{@literal >}</code> in the first entry,</li>
     * <li>the next entries result of indenting all the strings of {@code internalLines} (assumes
     * each of them is a line) and</li>
     * <li>has the string <code>{@literal </}elementName{@literal >}</code> in the last entry of the
     * list.</li>
     * </ul>
     * </p>
     * 
     * @param elementName
     *            The element name (to appear inside the tags).
     * @param internalLines
     *            The content of the element.
     * @return An {@link ArrayList} whose entries are the described above.
     */
    private List< String > createNormalElements( String elementName, List< String > internalLines ) {
    
        List< String > lines = new ArrayList< String >();
        
        lines.add( createStartTag( elementName ) );
        lines.addAll( indent( internalLines ) );
        lines.add( createEndTag( elementName ) );
        
        return lines;
    }
    
    // used in the method encode
    /**
     * Returns the string that contains all the entries of {@code list} separated by the
     * paragraph-character ({@code \r\n}). It is assumed that each entry represents a line. This
     * string starts and ends with a paragraph-character.
     * 
     * @param list
     *            The lines to be concatenated.
     * @return The string that concatenates all the entries of {@code list} separated by the
     *         paragraph-character ({@code \r\n}).
     */
    private String listToString( List< String > list ) {
    
        StringBuilder sb = new StringBuilder( "\r\n" );
        for( String line : list )
            sb.append( line ).append( "\r\n" );
        return sb.toString();
    }
    
    
    
    // used in method encodeInSeparateLines
    /**
     * Returns a string representation in HTML of {@code t}, which is assumed to represent an
     * {@link Iterable}.
     * 
     * @param t
     *            The {@link Translatable} to be translated to HMTL.
     * @return A string representation of {@code t} in HTML where each line is contained in a
     *         different entry of the {@link ArrayList}.
     * @throws UnknownTranslatableException
     *             If {@code t} does not follow the conventions specified in the
     *             {@link Translatable} documentation.
     */
    private List< String > encodeIterable( Translatable t ) throws UnknownTranslatableException {
    
        List< String > internalLines = new ArrayList< String >();
        try {
            for( Object element : t.getPropertiesBag().values() )
                internalLines.addAll( encodeInSeparateLines( (Translatable)element ) );
        }
        catch( ClassCastException e ) {
            throw new UnknownTranslatableException(
                                                    "Translatable representing iterable has non-translatable values in the properties bag.",
                                                    e );
        }
        
        return createNormalElements( t.getTag(), internalLines );
    }
    
    // used in method encodeInSeparateLines
    /**
     * Returns a string representation of {@code t}, which is assumed to represent an {@code Map},
     * where each line is contained in a different entry of an {@link ArrayList}.
     * 
     * @param t
     *            The {@link Translatable} to be translated to HMTL.
     * @return A string representation of {@code t} in HTML where each line is contained in a
     *         different entry of the {@link ArrayList}.
     */
    private List< String > encodeMap( Translatable t ) {
    
        List< String > internalLines = new ArrayList< String >();
        
        for( Entry< String, Object > entry : t.getPropertiesBag().entrySet() ) {
            ArrayList< String > codedEntry = new ArrayList< String >();
            codedEntry.add( createRawTextElement( t.getKeyTag(), entry.getKey() ) );
            codedEntry.add( createRawTextElement( t.getValueTag(), entry.getValue().toString() ) );
            
            internalLines.addAll( createNormalElements( t.getEntryTag(), codedEntry ) );
        }
        
        return createNormalElements( t.getTag(), internalLines );
    }
    
    
    
    // used in the method createNormalElements
    /**
     * Concatenates {@link #indentationSpaces} in the beginning of each string of {@code list},
     * assuming each entry is a line.
     * 
     * @param list
     *            The list of lines to be indent.
     * @return The indented lines in a new list of strings.
     */
    private List< String > indent( List< String > list ) {
    
        List< String > al = new ArrayList<>();
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
     * @return The string <code>{@literal <}elementName{@literal >} elementContent
     *         {@literal <}/elementName{@literal >}</code>.
     */
    private String createRawTextElement( String elementName, String elementContent ) {
    
        return new StringBuilder( createStartTag( elementName ) ).append( elementContent )
                                                                 .append( createEndTag( elementName ) )
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
    
        return new StringBuilder( "<" ).append( elementName ).append( ">" ).toString();
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
    
        return new StringBuilder( "</" ).append( elementName ).append( ">" ).toString();
    }
    
    
    
}
