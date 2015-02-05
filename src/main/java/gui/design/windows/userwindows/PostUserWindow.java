package main.java.gui.design.windows.userwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JLablePlusJTextField;
import main.java.gui.design.windows.WindowBase;


@SuppressWarnings( "serial" )
public class PostUserWindow extends WindowBase {
    
    private static final int WINDOWWIDTH = 400;
    private static final int WINDOWHEIGHT = 600;
    private static final int JTEXTFIELDSIZE = 20;
    private static final int TOPINSECTS = 0;
    private static final int LEFTINSECTS = 0;
    private static final int BOTTOMINSECTS = 10;
    private static final int RIGHTINSECTS = 0;
    private static final int XPOSITIONFORPANELS = 0;
    private static final int YPOSITIONFORUSERNAME = 1;
    private static final int YPOSITIONFORPASSWORD = 2;
    private static final int YPOSITIONFORCONFIRMPASSWORD = 3;
    private static final int YPOSITIONFOREMAIL = 4;
    private static final int YPOSITIONFORFULLNAME = 5;

    
    private JLablePlusJTextField username;
    private JLablePlusJTextField password;
    private JLablePlusJTextField confirmPassword;
    private JLablePlusJTextField email;
    private JLablePlusJTextField fullname;
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    public PostUserWindow() {
        
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/User.png" );
        initialize();
        
    }
    
    private void initialize() {
        
        
        username = new JLablePlusJTextField( "Username", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( username,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORUSERNAME,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );
        
        password = new JLablePlusJTextField( "Password", JTEXTFIELDSIZE, Color.WHITE, true );
        this.getContentPane()
            .add( password,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORPASSWORD,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );            
        
        confirmPassword = new JLablePlusJTextField( "Confirm Password", JTEXTFIELDSIZE, Color.WHITE, true );
        this.getContentPane()
            .add( confirmPassword,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORCONFIRMPASSWORD,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );     
        
        email = new JLablePlusJTextField( "Email", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( email,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFOREMAIL,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );            
        
        fullname = new JLablePlusJTextField( "Fullname (Optional)", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( fullname,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS, YPOSITIONFORFULLNAME,
                                                         new Insets( TOPINSECTS, LEFTINSECTS, BOTTOMINSECTS, RIGHTINSECTS ) ) );            
        
        this.setVisible( true );
        
    }
    
    public JLablePlusJTextField getUsername() {
        return username;
    }
    
    public JLablePlusJTextField getPassword() {
        return password;
    }
    
    public JLablePlusJTextField getConfirmPassword() {
        return confirmPassword;
    }
    
    public JLablePlusJTextField getEmail() {
        return email;
    }
    
    public JLablePlusJTextField getFullname() {
        return fullname;
    }
    
}
