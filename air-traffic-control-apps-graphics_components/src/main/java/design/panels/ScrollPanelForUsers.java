package design.panels;


import javax.swing.JButton;
import javax.swing.JTextArea;
import entities.SimpleUser;
import functionalcomponents.infobuttons.SimpleUserInfoButton;



/**
 * Class who's instances represents a ScroolPanel with User information. This class extends
 * {@link ScrollPanelForEntities}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

@SuppressWarnings( "serial" )
public class ScrollPanelForUsers extends ScrollPanelForEntities< SimpleUser > {
    
    @Override
    protected JButton newButton( String identification, JTextArea textArea ) {
    
        return new SimpleUserInfoButton( identification, textArea );
    }
}
