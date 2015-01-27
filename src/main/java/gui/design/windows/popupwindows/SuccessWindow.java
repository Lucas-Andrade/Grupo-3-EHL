package main.java.gui.design.windows.popupwindows;

import javax.swing.ImageIcon;
import javax.swing.JDialog;

/**
 * Class whose instances are {@link JDialog} with a success image and message,
 * and a {@code Ok-button}, that dispose this window.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class SuccessWindow
	extends PopupWindow
{

	/**
	 * Creates a Success Pop-up with an {@code image}, a {@code message}, and a
	 * {@code ok-button}.
	 * 
	 * @param message
	 */
	public SuccessWindow( String message )
	{
		super( message, new ImageIcon( "src/main/resources/images/successIcon.png" ) );
	}
}
