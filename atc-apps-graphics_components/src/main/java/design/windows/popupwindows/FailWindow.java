package design.windows.popupwindows;


import javax.swing.JDialog;


/**
 * Class whose instances are {@link JDialog} with a fail image and message, and a
 * {@code Ok-button}, that dispose this window.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class FailWindow extends PopupWindow {
    
    /**
     * Creates a Fail Pop-up with an {@code image}, a {@code message}, and a {@code ok-button}.
     * 
     * @param message
     */
    public FailWindow( String message ) {
        super( message, "/images/failIcon.png" );
    }
}
