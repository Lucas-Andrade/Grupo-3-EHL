package main.java.gui.design.windows.userwindows;

import java.awt.Dimension;

import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.design.panels.JScrollPanelForUsers;
import main.java.gui.design.windows.popupwindows.PopupWindow;

@SuppressWarnings( "serial" )
public class GetUsersWindow
	extends PopupWindow
{
	public GetUsersWindow( Database<User> dataBase, Iterable< User > users )
	{
		super(new JScrollPanelForUsers().produceAJScrollPaneWithAllElements( dataBase, users ));
		setPreferredSize( new Dimension( 500, 300 ));
	}
}
