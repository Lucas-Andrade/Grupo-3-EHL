package main.java.gui.design.panels.postairshipwindowpanels;


import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


@SuppressWarnings( "serial" )
public class JPanelWithSpecificMilitaryAirhipParameters extends JPanel {
    
    private static final int TOPBORDER = 10;
    private static final int LEFTBORDER = 0;
    private static final int BOTTOMBORDER = 16;
    private static final int RIGHTBORDER = 100;
    
    private static final String YES = "Yes";
    private static final String NO = "No";
    private JRadioButton yesButton;
    private JRadioButton noButton;
    private ButtonGroup groupButtons;
    
    
    
    
    public JPanelWithSpecificMilitaryAirhipParameters() {
        initial();
    }
    
    
    private void initial() {
        
        this.setOpaque( false );
        
        JLabel militaryArmorLabel = new JLabel( "Has Armor?" );
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
    
    
    public JRadioButton getYesButton() {
        return yesButton;
    }
    
    public JRadioButton getNoButton() {
        return noButton;
    }
    
    
    public ButtonGroup getGroupButtons() {
        return groupButtons;
    }
    
    
    
}
