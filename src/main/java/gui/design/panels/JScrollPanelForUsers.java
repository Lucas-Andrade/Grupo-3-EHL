package main.java.gui.design.panels;


import User;

    /**
     * Class who's instances represents a ScroolPanel with User information. 
     * This class extends {@link JScrollPanelForElements}.
     * 
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class JScrollPanelForUsers extends JScrollPanelForElements< User > {
    
    /**
     * An override of  {@link JScrollPanelForElements#getString(main.java.domain.model.Element)} 
     * from {@link JScrollPanelForElements}.
     */    

    @Override
    protected String getString( User user ) {

        return user.toStringWithoutPassword();
        
    }
        
}
