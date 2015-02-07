package main.java.gui.design.windows.userwindows;


import User;
import java.awt.Dimension;
import main.java.Database;
import main.java.gui.design.panels.JScrollPanelForUsers;
import main.java.gui.design.windows.popupwindows.PopupWindow;


@SuppressWarnings( "serial" )
public class GetUsersWindow extends PopupWindow {
    
    private static final int WINDOWWIDTH = 500;
    private static final int WINDOWHEIGHT = 300;
    
    
    public GetUsersWindow( Database< User > dataBase, Iterable< User > users ) {
        super( new JScrollPanelForUsers().produceAJScrollPaneWithAllElements( dataBase, users ) );
        setPreferredSize( new Dimension( WINDOWWIDTH, WINDOWHEIGHT ) );
    }
}
