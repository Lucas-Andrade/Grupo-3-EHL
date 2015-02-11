package design.windows.userwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import design.GridBagUtils;
import design.panels.JLablePlusJTextField;
import design.windows.WindowBase;

/**
 *   Class who's instances represents panel that extends {@link WindowBase},  
 *   so it inherits a {@link JPanelImage}, {@link JTwoButtonsPanel} and {@link JTextArea} with error information.
 *   This instance also add five {@link JLablePlusJTextField} and has this configuration: 
 *   
 *  <pre>
 *     ____________________________________
 *    |                                    |
 *    |               Image                |
 *    |____________________________________|
 *    |                                    |
 *    |          JLabelPlusJTextFied       |
 *    |____________________________________|
 *    |                                    |
 *    |          JLabelPlusJTextFied       |
 *    |____________________________________|
 *    |                                    |
 *    |          JLabelPlusJTextFied       |
 *    |____________________________________|
 *    |                                    |
 *    |          JLabelPlusJTextFied       |
 *    |____________________________________|
 *    |                                    |
 *    |          JLabelPlusJTextFied       |
 *    |____________________________________|
 *    |                                    |
 *    |             Two buttons            |
 *    |____________________________________|
 *    |                                    |
 *    |             error text area        |
 *    |____________________________________|
 *    
 * </pre>
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */


@SuppressWarnings( "serial" )
public class PostUserWindow extends WindowBase {
    
    //////////////////////////////////////////////////// 
    // Graphical Fields used only for design purposes //
   ////////////////////////////////////////////////////

   /**
   * {@code WINDOWWIDTH} int value that represents {@link LogInWindow} width. 
   */
    private static final int WINDOWWIDTH = 400;
    /**
     * {@code WINDOWWIDTH} int value that represents {@link LogInWindow} height. 
     */
    private static final int WINDOWHEIGHT = 600;
    /**
     * {@code JTEXTFIELDSIZE} int value that represents the {@link JTextField} size. 
     */
    private static final int JTEXTFIELDSIZE = 20;
    /**
     * {@code TOPINSETS} int value that represents the size of top borders used in
     * {@code GridBagConstraints}.
     */
    private static final int TOPINSECTS = 0;
    /**
     * {@code LEFTINSETS} int value that represents the size of left borders used in
     * {@code GridBagConstraints}.
     */
    private static final int LEFTINSECTS = 0;
    /**
     * {@code BOTTOMINSETS} int value that represents the size of bottom borders used in
     * {@code GridBagConstraints}.
     */
    private static final int BOTTOMINSECTS = 10;
    /**
     * {@code RIGHTINSETS} int value that represents the size of right borders for {@code userPanel} used in
     * {@code GridBagConstraints}.
     */
    private static final int RIGHTINSECTS = 0;
    /**
     * {@code XPOSITIONFORPANELS} int value that represents the column where is insert the components
     * into {@code GridBagConstraints}.
     */
    private static final int XPOSITIONFORPANELS = 0;
    /**
     * {@code YPOSITIONFORUSERNAME} int value that represents the rows where is insert the {@code username} components
     * into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORUSERNAME = 1;
    /**
     * {@code YPOSITIONFORPASSWORD} int value that represents the rows where is insert the {@code password} components
     * into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORPASSWORD = 2;
    /**
     * {@code YPOSITIONFORCONFIRMPASSWORD} int value that represents the rows where is insert the {@code confirmPassword}
     *  components into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORCONFIRMPASSWORD = 3;
    /**
     * {@code YPOSITIONFOREMAIL} int value that represents the rows where is insert the {@code email}
     *  components into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFOREMAIL = 4;
    /**
     * {@code YPOSITIONFORFULLNAME} int value that represents the rows where is insert the {@code fullname}
     *  components into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORFULLNAME = 5;
    
    ////////////////////////////
    ///// Components Fields ////
    ////////////////////////////

    /**
     * {@code username} variable  that represents {@link JTextField}.
     */
    
    private JLablePlusJTextField username;
    /**
     * {@code password} variable  that represents {@link JTextField}.
     */
    private JLablePlusJTextField password;
    /**
     * {@code confirmPassword} variable  that represents {@link JTextField}.
     */
    private JLablePlusJTextField confirmPassword;
    /**
     * {@code email} variable  that represents {@link JTextField}.
     */
    private JLablePlusJTextField email;
    /**
     * {@code fullname} variable  that represents {@link JTextField}.
     */
    private JLablePlusJTextField fullname;
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */ 
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    /**
     * Public constructor that creates a new {@link PostUserWindow} adding five {@link JLablePlusJTextField}. 
     */
    public PostUserWindow() {
        
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/User.png" );  
        
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
    /////////////////   
    // Get Methods //
   /////////////////  

    /**
     * @return the {@code username}.
     */    
    public JLablePlusJTextField getUsername() {
        return username;
    }
    /**
     * @return the {@code password}.
     */    
    public JLablePlusJTextField getPassword() {
        return password;
    }
    /**
     * @return the {@code confirmPassword}.
     */  
    public JLablePlusJTextField getConfirmPassword() {
        return confirmPassword;
    }
    /**
     * @return the {@code email}.
     */
    public JLablePlusJTextField getEmail() {
        return email;
    }
    /**
     * @return the {@code fullname}.
     */
    public JLablePlusJTextField getFullname() {
        return fullname;
    }
    
}
