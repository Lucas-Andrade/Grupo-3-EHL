package main.java.gui.designWindows.windows.popupWindows;

import javax.swing.ImageIcon;

@SuppressWarnings( "serial" )
public class SuccessWindow extends PopupWindow
{
	public SuccessWindow( String message )
	{
		super( message, new ImageIcon( "src/main/resources/images/successIcon.png" ) );
	}
}
