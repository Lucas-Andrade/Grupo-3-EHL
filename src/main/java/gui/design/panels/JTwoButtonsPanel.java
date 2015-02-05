package main.java.gui.design.panels;


import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *  Class who's instances represents panel who contains two {@link JButton}. 
 *  This panel extends {@link JPanel}. The panel has this configuration : 
 * 
 *   <p>    {@literal ------------------------------} </p>
 *   <p>   {@literal |}  Left Button {@literal |} Right Button {@literal |} </p> 
 *   <p>    {@literal ------------------------------} </p>
 *
 *  @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

@SuppressWarnings( "serial" )
public class JTwoButtonsPanel extends JPanel {
    
    
    // INSTANCE FIELD
    
    /**
     * {@code leftButton} {@link JButton} variable that represents left button Component for panel. 
     */
    private JButton leftButton;
    
    /**
     * {@code rightButton} {@link JButton} variable that represents right button Component for panel. 
     */
    private JButton rightButton;
    
    //////////////////////
    //// Constructors ////
    //////////////////////
             
    /**
     * Public construct responsible for create a {@link JTwoButtonsPanel},
     * giving the user an opportunity to specific the text inside both JButtons.
     *   
     * @param leftButtonText - {@link JButton} variable that represents the left button of the panel. 
     * @param rightButtonText -  {@link JButton} variable that represents the right button of the panel. 
     */
    
    
    public JTwoButtonsPanel( String leftButtonText, String rightButtonText ) {
        
        if( leftButtonText == null || rightButtonText == null )
            throw new IllegalArgumentException();
        
        this.leftButton = new JButton( leftButtonText );
        this.add( leftButton );
        
        this.rightButton = new JButton( rightButtonText );
        this.add( rightButton );
        
    }

    /**
     * Public constructor responsible for for create a {@link JTwoButtonsPanel}, 
     * with "OK" text in the left {@link JButton} and "Cancel" in the right {@link JButton}. 
     * 
     */
    public JTwoButtonsPanel() {
        
        this( "OK", "Cancel" );
    }
    
    /////////////////  
   // Get Methods //
  /////////////////  
   
    /**
     * @return the {@code leftButton}.
     */       
    public JButton getLeftButton() {
        return leftButton;
    }
    
    /**
     * @return the {@code rightButton}.
     */      
    public JButton getRightButton() {
        return rightButton;
    }  
    
}
