package main.java.gui.design.windows.userwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JLablePlusJTextField;
import main.java.gui.design.windows.WindowBase;


@SuppressWarnings( "serial" )
public class LogInWindow extends WindowBase {
    
    private static final int WINDOWWIDTH = 380;
    private static final int WINDOWHEIGHT = 380;
    private static final int JTEXTFIELDSIZE = 20;
    private static final int TOPINSECTS = 5;
    private static final int LEFTINSECTS = 0;
    private static final int RIGHTINSECTS = 0;
    private static final int BOTTOMINSECTSFORUSERPANEL = 0;
    private static final int BOTTOMINSECTSFORPASSWORDPANEL = 15;
    private static final int XPOSITIONFORPANELS = 0;
    private static final int YPOSITIONFORUSERPANEL = 1;
    private static final int YPOSITIONFORPASSWORDPANEL = 2;

    
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    private JLablePlusJTextField userPanel;
    private JLablePlusJTextField passwordPanel;


    public LogInWindow() {
        
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/radar.png", "/images/logo.png" );
        
        initialize();
        
    }
    
    private void initialize() {
        
        
        userPanel = new JLablePlusJTextField( "Username", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( userPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORUSERPANEL, 
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTSFORUSERPANEL, RIGHTINSECTS ) ) );
        passwordPanel =
                new JLablePlusJTextField( "Password", JTEXTFIELDSIZE, Color.WHITE, true, "/images/locker.png" );
        this.getContentPane()
            .add( passwordPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORPASSWORDPANEL,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTSFORPASSWORDPANEL, RIGHTINSECTS ) ) );
        
        
        this.setVisible( true );
        
        
        
    }
    
    public JLablePlusJTextField getUserPanel() {
        return userPanel;
    }
    
    public JLablePlusJTextField getPasswordPanel() {
        return passwordPanel;
    }
}
