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
 * Class who's instances represents panel that extends {@link WindowBase}, so it inherits a
 * {@link JPanelImage}, {@link JTwoButtonsPanel} and {@link JTextArea} with error information. This
 * instance also add two {@link JLablePlusJTextField} and has this configuration:
 * 
 * <pre>
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
 *    |             Two buttons            |
 *    |____________________________________|
 *    |                                    |
 *    |             error text area        |
 *    |____________________________________|
 * 
 * </pre>
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */



@SuppressWarnings( "serial" )
public class LogInWindow extends WindowBase {
    
    
    // //////////////////////////////////////////////////
    // Graphical Fields used only for design purposes //
    // //////////////////////////////////////////////////
    
    /**
     * {@code WINDOWWIDTH} int value that represents {@link LogInWindow} width.
     */
    private static final int WINDOWWIDTH = 380;
    /**
     * {@code WINDOWWIDTH} int value that represents {@link LogInWindow} height.
     */
    private static final int WINDOWHEIGHT = 380;
    /**
     * {@code JTEXTFIELDSIZE} int value that represents the {@link JTextField} size.
     */
    private static final int JTEXTFIELDSIZE = 20;
    /**
     * {@code TOPINSETS} int value that represents the size of top borders used in
     * {@code GridBagConstraints}.
     */
    private static final int TOPINSECTS = 5;
    /**
     * {@code LEFTINSETS} int value that represents the size of left borders used in
     * {@code GridBagConstraints}.
     */
    private static final int LEFTINSECTS = 0;
    /**
     * {@code RIGHTINSECTS} int value that represents the size of bottom borders used in
     * {@code GridBagConstraints}.
     */
    private static final int RIGHTINSECTS = 0;
    /**
     * {@code BOTTOMINSECTSFORUSERPANEL} int value that represents the size of bottom borders for
     * {@code userPanel} used in {@code GridBagConstraints}.
     */
    private static final int BOTTOMINSECTSFORUSERPANEL = 0;
    /**
     * {@code BOTTOMINSECTSFORPASSWORDPANEL} int value that represents the size of bottom borders
     * for {@code passwordPanel} used in {@code GridBagConstraints}.
     */
    private static final int BOTTOMINSECTSFORPASSWORDPANEL = 15;
    /**
     * {@code XPOSITIONFORPANELS} int value that represents the column where is insert the
     * components into {@code GridBagConstraints}.
     */
    private static final int XPOSITIONFORPANELS = 0;
    /**
     * {@code YPOSITIONFORUSERPANEL} int value that represents the rows where is insert the
     * {@code userPanel} components into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORUSERPANEL = 1;
    /**
     * {@code YPOSITIONFORPASSWORDPANEL} int value that represents the rows where is insert the
     * {@code passwordPanel} components into {@code GridBagConstraints}.
     */
    private static final int YPOSITIONFORPASSWORDPANEL = 2;
    
    // //////////////////////////
    // /// Components Fields ////
    // //////////////////////////
    
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    /**
     * {@code userPanel} variable that represents {@link JTextField}.
     */
    private JLablePlusJTextField userPanel;
    /**
     * {@code passwordPanel} variable that represents {@link JTextField}.
     */
    private JLablePlusJTextField passwordPanel;
    
    /**
     * Public constructor that creates a new {@link LogInWindow} adding two
     * {@link JLablePlusJTextField}.
     */
    
    public LogInWindow() {
    
        super( WINDOWWIDTH, WINDOWHEIGHT, "/images/radar.png", "/images/logo.png" );
        
        userPanel = new JLablePlusJTextField( "Username", JTEXTFIELDSIZE, Color.WHITE );
        this.getContentPane()
            .add( userPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS,
                                                         YPOSITIONFORUSERPANEL,
                                                         new Insets( TOPINSECTS, LEFTINSECTS,
                                                                     BOTTOMINSECTSFORUSERPANEL,
                                                                     RIGHTINSECTS ) ) );
        passwordPanel =
                new JLablePlusJTextField( "Password", JTEXTFIELDSIZE, Color.WHITE, true,
                                          "/images/locker.png" );
        this.getContentPane()
            .add( passwordPanel,
                  GridBagUtils.updateGridBagConstraints( constraints, XPOSITIONFORPANELS,
                                                         YPOSITIONFORPASSWORDPANEL,
                                                         new Insets( TOPINSECTS, LEFTINSECTS,
                                                                     BOTTOMINSECTSFORPASSWORDPANEL,
                                                                     RIGHTINSECTS ) ) );
        
    }
    
    // ///////////////
    // Get Methods //
    // ///////////////
    
    /**
     * @return the {@code userPanel}.
     */
    public JLablePlusJTextField getUserPanel() {
    
        return userPanel;
    }
    
    /**
     * @return the {@code passwordPanel}.
     */
    public JLablePlusJTextField getPasswordPanel() {
    
        return passwordPanel;
    }
}
