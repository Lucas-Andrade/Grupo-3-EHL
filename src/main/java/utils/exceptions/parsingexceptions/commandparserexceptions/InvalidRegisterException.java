package main.java.utils.exceptions.parsingexceptions.commandparserexceptions;



/**
 * Thrown when a parsing error occurs, result of
 * <ol>
 * <li>having been received several factories for the same method+path or</li>
 * <li>having been received several paths that are equal until a certain token and their next token
 * are different placeholders (e.g. by trying to register "
 * <code>GET /fixed/{ph1}/fixed2/{ph}</code>" and " <code>GET /fixed/{ph2}/fixed2/{ph}</code>
 * ", this exception would be thrown since the tokens after "{@code fixed}" are different
 * placeholders, <code>{ph1}</code> and <code>{ph2}</code>).</li>
 * </ol>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class InvalidRegisterException extends CommandParserException {
    
    /**
     * Constructs an {@link InvalidRegisterException} with the message <i>«Several registers with
     * the same method and path.»</i>.
     */
    public InvalidRegisterException() {
        
        super( "Several registers with the same method and path." );
    }
    
    /**
     * Constructs an {@link InvalidRegisterException} with the message <i>«A path has been
     * registered with the placeholder {@code existingPlaceholder} in this position. Cannot register
     * new command with placeholder {@code placeholderToAdd} in this position.»</i>.
     * 
     * @param placeholderToAdd
     *            The placeholder contained in the string-command that failed registration.
     * @param existingPlaceholder
     *            The existing placeholder.
     */
    public InvalidRegisterException( String placeholderToAdd, String existingPlaceholder ) {
        
        super( new StringBuilder().append( "A path has been registered with the placeholder " )
                                  .append( existingPlaceholder ).append( " in this position." )
                                  .append( "\nCannot register new command with placeholder " )
                                  .append( placeholderToAdd ).append( " in this position." )
                                  .toString() );
    }
}
