package main.java.gui.designWindows.windows.userWindows;

import java.awt.Dimension;

import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.jPanels.JScrollPanelForUsers;
import main.java.gui.designWindows.windows.popupWindows.PopupWindow;

@SuppressWarnings( "serial" )
public class GetUsersWindow
	extends PopupWindow
{

	private Iterable< User > database;

	public GetUsersWindow( Database<User> dataBase, Iterable< User > users )
	{
		super(new JScrollPanelForUsers().produceAJScrollPaneWithAllElements( dataBase, users ));
		setPreferredSize( new Dimension( 500, 300 ));
		this.database = users;
	}

}
