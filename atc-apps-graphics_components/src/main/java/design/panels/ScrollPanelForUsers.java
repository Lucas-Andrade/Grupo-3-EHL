package design.panels;


import javax.swing.JButton;
import javax.swing.JTextArea;
import entities.SimpleUser;
import functionalcomponents.infobuttons.SimpleUserInfoButton;



/**
 * Class whose instances represent a ScrollPanel with a list of users.
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
