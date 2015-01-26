package main.java.gui.designWindows.windows;

import javax.swing.ImageIcon;

public class SuccessPostUserWindow2 extends PopupWindow
{
	public SuccessPostUserWindow2( String message )
	{
		super( message, new ImageIcon( "src/main/resources/images/successIcon.png" ) );
	}
}
