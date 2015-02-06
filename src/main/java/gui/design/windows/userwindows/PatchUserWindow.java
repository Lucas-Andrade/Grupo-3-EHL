package main.java.gui.design.windows.userwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JLablePlusJTextField;
import main.java.gui.design.windows.WindowBase;


@SuppressWarnings( "serial" )
public class PatchUserWindow extends WindowBase {
    
    
    private static final int WINDOWWIDTH = 400;
    private static final int WINDOWHEIGHT = 600;
    private static final int JTEXTFIELDSIZE = 20;
    private static final int TOPINSECTS = 0;
    private static final int LEFTINSECTS = 0;
    private static final int BOTTOMINSECTS = 10;
    private static final int RIGHTINSECTS = 0;
    private static final int XPOSITIONFORPANELS = 0;
    private static final int YPOSITIONFORUSERPANEL = 1;
    private static final int YPOSITIONFOROLDPASSWORD = 2;
    private static final int YPOSITIONFORNEWPASSWORD = 3;
    private static final int YPOSITIONFORCONFIRMNEWPASSWORD = 4;



    
    private JLablePlusJTextField userPanel;
    private JLablePlusJTextField oldPasswordPanel;
    private JLablePlusJTextField newPasswordPanel;
    private JLablePlusJTextField newPasswordConfirmationPanel;
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    public PatchUserWindow() {
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/pacthUser.png" );
        initialize();
    }
    
    private void initialize() {
        
        userPanel = new JLablePlusJTextField( "Username", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( userPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORUSERPANEL,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );
        
        oldPasswordPanel = new JLablePlusJTextField( "Password", JTEXTFIELDSIZE, Color.WHITE, true );
        this.getContentPane()
            .add( oldPasswordPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFOROLDPASSWORD,
                                                         new Insets( TOPINSECTS, LEFTINSECTS,BOTTOMINSECTS, RIGHTINSECTS ) ) );
        
        newPasswordPanel = new JLablePlusJTextField( "New Password", JTEXTFIELDSIZE, Color.WHITE, true );
        this.getContentPane()
            .add( newPasswordPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORNEWPASSWORD,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );
        
        newPasswordConfirmationPanel =
                new JLablePlusJTextField( "Confirm New Password", JTEXTFIELDSIZE, Color.WHITE, true );
        this.getContentPane()
            .add( newPasswordConfirmationPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORCONFIRMNEWPASSWORD,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );
        this.setVisible( true );
        
    }
    
    public JLablePlusJTextField getUserPanel() {
        return userPanel;
    }
    
    public JLablePlusJTextField getOldPasswordPanel() {
        return oldPasswordPanel;
    }
    
    public JLablePlusJTextField getNewPasswordPanel() {
        return newPasswordPanel;
    }
    
    public JLablePlusJTextField getNewPasswordConfirmationPanel() {
        return newPasswordConfirmationPanel;
    }
    
    
    
}
