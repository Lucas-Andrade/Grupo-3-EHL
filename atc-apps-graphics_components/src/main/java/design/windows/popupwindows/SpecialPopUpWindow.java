package design.windows.popupwindows;

import java.util.Random;
import design.panels.JGIFPanel;


@SuppressWarnings( "serial" )
public class SpecialPopUpWindow extends PopupWindow {

    public SpecialPopUpWindow( ) {
    
        
        super( new JGIFPanel( "/images/"+createnumber()+".gif" ) );

    
    }

    private static int createnumber() {
    
        Random rn = new Random();
        return  rn.nextInt(3) + 1;
        
    }
    
}
