package main.java.gui.design.windows.popupwindows;

import java.awt.event.ActionListener;

import javax.swing.JDialog;

/**
 * Class whose instances are {@link JDialog} with an {@code "UnderConstruction"}
 * image, (i.e., to represent buttons without their {@link ActionListener}) and
 * a {@code Ok-button}, that dispose this window.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class UnderConstrutionWindow
	extends PopupWindow
{

	/**
	 * Creates an {@code "Under Construction"} Pop-up with an {@code image} and a
	 * {@code ok-button}.
	 */
	public UnderConstrutionWindow()
	{
		super( null,  "/images/UnderConstruction.png"  );
	}
}
