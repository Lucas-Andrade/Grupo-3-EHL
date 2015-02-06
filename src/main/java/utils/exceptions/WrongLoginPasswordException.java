package main.java.utils.exceptions;


import java.text.MessageFormat;


/**
 * Thrown when the received password is not the correct password of a certain user.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class WrongLoginPasswordException extends Exception {
    
    /**
     * Constructs a {@link WrongLoginPasswordException} with the message <i>«Wrong password:
     * {@code loginName}'s password is not {@code wrongPassword} .»</i>.
     * 
     * @param loginName
     *            The user's username.
     * @param wrongPassword
     *            The wrong password that was received.
     */
    public WrongLoginPasswordException( String loginName, String wrongPassword ) {
    
        super( MessageFormat.format( "Wrong password: {0}''s password is not {1}.", loginName,
                                     wrongPassword ) );
    }
    
}
