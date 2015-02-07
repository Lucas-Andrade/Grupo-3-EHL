package exceptions;



/**
 * Runtime exception thrown when an internal operation produces unexpected
 * exceptions that interrupt the request proccess.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class InternalErrorException
    extends RuntimeException
{

    /**
     * Constructs an {@link InternalErrorException} with the message <i>«Sorry!
     * An internal error that was supposed to never happen happened.»</i>.
     * 
     * @param message
     *        The detail message.
     */
    public InternalErrorException()
    {

        super( "Sorry! An internal error that was supposed to never happen happened." );
    }

    /**
     * Constructs an {@link InternalErrorException} with the specified detail
     * message.
     * 
     * @param message
     *        The detail message.
     */
    public InternalErrorException( String message )
    {

        super( message );
    }

    /**
     * Constructs an {@link InternalErrorException} with the message <i>«Sorry!
     * An internal error that was supposed to never happen happened.»</i> and
     * the specified cause.
     * 
     * @param cause
     *        The cause (saved for later retrieval by the {@link #getCause()}
     *        method). {@code null} values are allowed and indicate that the
     *        cause is nonexistent or unknown.
     */
    public InternalErrorException( Throwable cause )
    {

        super( "Sorry! An internal error that was supposed to never happen happened.", cause );
    }

    /**
     * Constructs an {@link InternalErrorException} with the specified detail
     * message and the specified cause.
     * 
     * @param message
     *        The detail message.
     * @param cause
     *        The cause (saved for later retrieval by the {@link #getCause()}
     *        method). {@code null} values are allowed and indicate that the
     *        cause is nonexistent or unknown.
     */
    public InternalErrorException( String message, Throwable cause )
    {

        super( message, cause );
    }

}
