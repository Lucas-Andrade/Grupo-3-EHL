package design.windows.userwindows;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import design.GridBagUtils;
import design.panels.JLablePlusJTextField;
import design.windows.WindowBase;

 /**
  *   Class who's instances represents panel that extends {@link WindowBase},  
  *   so it inherits a {@link JPanelImage}, {@link JTwoButtonsPanel} and {@link JTextArea} with error information.
  *   This instance also add four {@link JLablePlusJTextField} and has this configuration: 
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
public class PatchUserWindow extends WindowBase {
 
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
     * {@code YPOSITIONFORUSERPANEL} int value that represents the rows where is insert the {@code userPanel} components
     * into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORUSERPANEL = 1;
    /**
     * {@code YPOSITIONFORPASSWORDPANEL} int value that represents the rows where is insert the {@code passwordPanel} components
     * into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFOROLDPASSWORD = 2;
    /**
     * {@code YPOSITIONFORNEWPASSWORD} int value that represents the rows where is insert the {@code newPasswordPanel} components
     * into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORNEWPASSWORD = 3;
    /**
     * {@code YPOSITIONFORCONFIRMNEWPASSWORD} int value that represents the rows where is insert the 
     * {@code newPasswordConfirmationPanel} components into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORCONFIRMNEWPASSWORD = 4;
    
     ////////////////////////////
    ///// Components Fields ////
   ////////////////////////////
    
    /**
     * {@code userPanel} variable that represents {@link JLablePlusJTextField} that is part of this {@link JDialog}.
     */    
    private JLablePlusJTextField userPanel;
    /**
     * {@code userPanel} variable that represents {@link JLablePlusJTextField} that is part of this {@link JDialog}.
     */
    private JLablePlusJTextField oldPasswordPanel;
    /**
     * {@code newPasswordPanel} variable that represents {@link JLablePlusJTextField} that is part of this {@link JDialog}.
     */
    private JLablePlusJTextField newPasswordPanel;
    /**
     * {@code newPasswordConfirmationPanel} variable that represents {@link JLablePlusJTextField} 
     * that is part of this {@link JDialog}.
     */
    private JLablePlusJTextField newPasswordConfirmationPanel;

    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */ 
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
        
    /**
     * Public constructor that creates a new {@link PatchUserWindow} adding four {@link JLablePlusJTextField}. 
     */
    public PatchUserWindow() {
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/pacthUser.png" );
            
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
