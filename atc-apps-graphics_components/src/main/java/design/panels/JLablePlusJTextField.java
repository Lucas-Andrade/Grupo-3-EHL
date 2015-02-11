package design.panels;


import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import design.GridBagUtils;
import exceptions.InternalErrorException;


/**
 * 
 * Class who's instances represents panel who contains one {@link JLabel} and {@link JTextField}.
 * This panel extends {@link JPanel}. The panel has this configuration :
 * 
 * <pre>
 *        ____________________
 *       |                    |
 *       |      JLabel        |  
 *       |                    |
 *       |     JTextField     |
 *       |____________________| 
 * </pre>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

@SuppressWarnings( "serial" )
public class JLablePlusJTextField extends JPanel {
    
    // INSTANCE FIELD
    
      ////////////////////////////////////////////////////
     // Graphical Fields used only for design purposes //
    ////////////////////////////////////////////////////
    
    /**
     * {@code TRANSLUCENTCOMPONENT} int value that represents panel Color (translucent Color).
     */
    private static final int TRANSLUCENTCOMPONENT = 0;
    
    /**
     * {@code TOPINSETS} int value that represents the size of top borders used in
     * {@code GridBagConstraints}.
     */
    private static final int TOPINSETS = 10;
    /**
     * {@code LEFTINSETS} int value that represents the size of left borders used in
     * {@code GridBagConstraints}.
     */
    private static final int LEFTINSETS = 0;
    /**
     * {@code BOTTOMINSETS} int value that represents the size of bottom borders used in
     * {@code GridBagConstraints}.
     */
    private static final int BOTTOMINSETS = 0;
    /**
     * {@code RIGHTINSETS} int value that represents the size of right borders used in
     * {@code GridBagConstraints}.
     */
    private static final int RIGHTINSETS = 0;
    /**
     * {@code GRIDXPOSITION} int value that represents the column where is insert the components
     * into {@code GridBagConstraints}.
     */
    private static final int GRIDXPOSITION = 0;
    /**
     * {@code GRIDYPOSITIONFORLABEL} int value that represents the row where is insert the
     * {@link JLabel} into {@code GridBagConstraints}.
     */
    private static final int GRIDYPOSITIONFORLABEL = 1;
    /**
     * {@code GRIDYPOSITIONFORLABEL} int value that represents the row where is insert the
     * {@link JTextField} into {@code GridBagConstraints}.
     */
    private static final int GRIDYPOSITIONFORJTEXTFIELD = 2;
    
      ////////////////////////////
     ///// Components Fields ////
    ////////////////////////////
    
    /**
     * {@code label} variable that represents the {@link JLabel} that is part of this Panel.
     */
    private JLabel label;
    /**
     * {@code textField} variable that represents the {@link JTextField} that is part of this Panel.
     */
    private JTextField textField;
    /**
     * {@code constraints} variable that represents the configuration used in the panel layout.
     */
    private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
    
    // ////////////////////
    // // Constructors ////
    // ////////////////////
    
    /**
     * Public constructor that will create a {@link JLabelPlusTextField}. It will receive all the
     * parameters needed to create {@link JLabel} and {@link JTExtField}.
     * 
     * @param jLabelText
     *            - String variable for the {@code label}.
     * @param jTextFieldSize
     *            - int value for the number of columns of {@code textField}.
     * @param jLabelcolor
     *            - Color variable for the {@code label}.
     * @param isJPasswordField
     *            - boolean variable that indicates if {@code textField} is a {@link JPasswordField}
     *            .
     * @param iconLabelPath
     *            - String variable with the image localization for {@code label}.
     */
    
    public JLablePlusJTextField( String jLabelText, int jTextFieldSize, Color jLabelcolor,
                                 boolean isJPasswordField, String iconLabelPath ) {
    
        this.setLayout( new GridBagLayout() );
        this.setBackground( new Color( TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT,
                                       TRANSLUCENTCOMPONENT, TRANSLUCENTCOMPONENT ) );
        
        createJlabel( jLabelText, jLabelcolor, iconLabelPath );
        createTextField( jTextFieldSize, isJPasswordField );
        
    }
    
    /**
     * Public constructor that create a {@link JLablePlusJTextField} without a {@code label} image.
     * This constructor calls the previous one giving a empty string for the {@code label} image.
     * 
     * @param jLabelText
     *            - String variable for the {@code label}.
     * @param jTextFieldSize
     *            - int value for the number of columns of {@code textField}.
     * @param jLabelcolor
     *            - Color variable for the {@code label}.
     * @param isJPasswordField
     *            - boolean variable that indicates if {@code textField} is a {@link JPasswordField}
     *            .
     */
    public JLablePlusJTextField( String jLabelText, int jTextFieldSize, Color jLabelcolor,
                                 boolean isJPasswordField ) {
    
        this( jLabelText, jTextFieldSize, jLabelcolor, isJPasswordField, "" );
        
    }
    
    /**
     * 
     * Public constructor that creates a {@link JLablePlusJTextField} that is a {@link JTextField},
     * not a {@link JPasswordField} and does'nt has a {@code label} image.
     * 
     * @param jLabelText
     *            - String variable for the {@code label}.
     * @param jTextFieldSize
     *            - int value for the number of columns of {@code textField}.
     * @param jLabelcolor
     *            - Color variable for the {@code label}.
     */
    public JLablePlusJTextField( String jLabelText, int jTextFieldSize, Color jLabelcolor ) {
    
        this( jLabelText, jTextFieldSize, jLabelcolor, false, "" );
  
    }
    
    
      /////////////////////
     // Private Methods //
    /////////////////////
    
    /**
     * Method responsible to create the {@link JLabel} and insert it into
     * {@link JLablePlusJTextField} panel. That will receive all the information needed to process
     * the creation that are:
     * 
     * @param jLabelText
     *            - String variable for the {@code label}.
     * @param jLabelcolor
     *            - Color variable for the {@code label}.
     * @param iconLabelPath
     *            - String variable with the image localization for {@code label}.
     * @throws InternalErrorException
     *             - when {@code iconPath} is not found.
     */
    private void createJlabel( String jLabelText, Color jLabelcolor, String iconPath ) {
    
        
        try {
            
            this.label =
                    (!iconPath.equals( "" ))
                                            ? new JLabel(
                                                          jLabelText,
                                                          new ImageIcon(
                                                                         ImageIO.read( getClass().getResourceAsStream( iconPath ) ) ),
                                                          JLabel.CENTER )
                                            : new JLabel( jLabelText, JLabel.CENTER );
        }
        catch( IOException e ) {
            
            throw new InternalErrorException( "Image Not Found : " + iconPath, e );
        }
        
        label.setForeground( jLabelcolor );
        this.add( label, GridBagUtils.updateGridBagConstraints( constraints, GRIDXPOSITION,
                                                                GRIDYPOSITIONFORLABEL,
                                                                new Insets( TOPINSETS, LEFTINSETS,
                                                                            BOTTOMINSETS,
                                                                            RIGHTINSETS ) ) );
    }
    
    /**
     * Method responsible to create the {@link JTextField} and insert it into
     * {@link JLablePlusJTextField} panel. That will receive all the information needed to process
     * the creation that are:
     * 
     * @param jTextFieldSize
     *            - int value for the number of columns of {@code textField}.
     * @param isJPasswordField
     *            - boolean variable that indicates if {@code textField} is a {@link JPasswordField}
     *            .
     */
    private void createTextField( int jTextFieldSize, boolean isJPasswordField ) {
    
        textField =
                (isJPasswordField) ? new JPasswordField( jTextFieldSize )
                                  : new JTextField( jTextFieldSize );
        
        this.add( textField, GridBagUtils.updateGridBagConstraints( constraints, GRIDXPOSITION,
                                                                    GRIDYPOSITIONFORJTEXTFIELD,
                                                                    new Insets( TOPINSETS,
                                                                                LEFTINSETS,
                                                                                BOTTOMINSETS,
                                                                                RIGHTINSETS ) ) );
        
    }
    
      /////////////////
     // Get Methods //
    /////////////////
    
    /**
     * @return the {@code label}.
     */
    public JLabel getJLabel() {
    
        return label;
    }
    
    /**
     * @return the {@code textField}.
     */
    public JTextField getJTextField() {
    
        return textField;
    }
}
