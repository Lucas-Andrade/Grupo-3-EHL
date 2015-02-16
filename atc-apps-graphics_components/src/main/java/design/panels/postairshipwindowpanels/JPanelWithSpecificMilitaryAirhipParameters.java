package design.panels.postairshipwindowpanels;


import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
 

    /**
     * 
     * Class who's instances represents panel who contains one {@link JLabel} and two {@link JRadioButton}.
     * This panel extends {@link JPanel}. The panel has this configuration :
     * 
     * <pre>
     *        ___________________________________________________
     *       |                                                   |
     *       |      JLabel   o JRadioButton   o JRadioButton     |        
     *       |___________________________________________________| 
     * </pre>
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */

@SuppressWarnings( "serial" )
public class JPanelWithSpecificMilitaryAirhipParameters extends JPanel {
    
  // INSTANCE FIELD
    
    ////////////////////////////////////////////////////
   // Graphical Fields used only for design purposes //
  ////////////////////////////////////////////////////
    
    /**
     * {@code TOPBORDER} int value that represents width of the top, in pixels.
     */
    private static final int TOPBORDER = 10;
    /**
     * {@code LEFTBORDER} int value that represents width of the left, in pixels.
     */
    private static final int LEFTBORDER = 0;
    /**
     * {@code BOTTOMBORDER} int value that represents width of the bottom, in pixels.
     */
    private static final int BOTTOMBORDER = 16;
    /**
     * {@code RIGHTBORDER} int value that represents width of the right, in pixels.
     */
    private static final int RIGHTBORDER = 100;
    
     ////////////////////////////
    ///// Components Fields ////
   ////////////////////////////
    
    /**
     * {@code YES} variable that represents  the {@link String} displayed on the {@link JRadioButton}.
     */    
    private static final String YES = "Yes";
    /**
     * {@code NO} variable that represents  the {@link String} displayed on the {@link JRadioButton}.
     */    
    private static final String NO = "No";
    
    /**
     * {@code yesButton} variable that represents  the {@link JRadioButton} that can be selected or not, 
     * and which displays its state to the user.
     */    
    private JRadioButton yesButton;
    /**
     * {@code noButton} variable that represents  the {@link JRadioButton} that can be selected or not, 
     * and which displays its state to the user.
     */    
    private JRadioButton noButton;
    
    /**
     * {@code groupButtons} variable that represents the {@link ButtonGroup} that is a group of {@link JRadioButton}. 
     */   
    private ButtonGroup groupButtons;
    
    /**
     * {@code militaryArmorLabel} variable that represents the {@link JLabel} for specific Military airship panel. 
     */
    private JLabel militaryArmorLabel;
    
    /**
     * Public constructor that creates a new {@link JPanelWithSpecificMilitaryAirhipParameters} 
     * adding  the one {@link JLable} and two {@link JRadioButton} side to side.
     */ 
    
    public JPanelWithSpecificMilitaryAirhipParameters() {
        
        this.setOpaque( false );
        
        militaryArmorLabel = new JLabel( "Has Armor?" );
        militaryArmorLabel.setForeground( Color.WHITE );
        this.add( militaryArmorLabel );
        
        yesButton = new JRadioButton( YES );
        yesButton.setForeground( Color.WHITE );
        yesButton.setMnemonic( KeyEvent.VK_B );
        yesButton.setActionCommand( YES );
        yesButton.setSelected( true );
        yesButton.setOpaque( false );
        this.add( yesButton );
        
        
        noButton = new JRadioButton( NO );
        noButton.setForeground( Color.WHITE );
        noButton.setMnemonic( KeyEvent.VK_B );
        noButton.setActionCommand( NO );
        noButton.setOpaque( false );
        this.add( noButton );
        
        groupButtons = new ButtonGroup();
        groupButtons.add( yesButton );
        groupButtons.add( noButton );
        
        this.setBorder( BorderFactory.createEmptyBorder( TOPBORDER, LEFTBORDER, BOTTOMBORDER, RIGHTBORDER) );
        
    }
    
     /////////////////
    // Get Methods //
   /////////////////
   
   /**
    * @return the {@code yesButton}.
    */
    
    public JRadioButton getYesButton() {
        return yesButton;
    }
    
    /**
     * @return the {@code noButton}.
     */
     
    public JRadioButton getNoButton() {
        return noButton;
    }
    
    
    /**
     * @return the {@code groupButtons}.
     */    
    public ButtonGroup getGroupButtons() {
        return groupButtons;
    }
    
    public JLabel getArmorInfo(){
        
        return militaryArmorLabel;
    }
    
}
