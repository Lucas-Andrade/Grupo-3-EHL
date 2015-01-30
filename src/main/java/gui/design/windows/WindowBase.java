package main.java.gui.design.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.JPanelImage;
import main.java.gui.design.panels.JTwoButtonsPanel;



/**
 * Abstract swing window, with:
 * <ul>
 * <li> an Image
 * <li> two buttons, without {@link ActionListener}s
 * <li> error Label, where the exception message will be printed.
 * <ul>
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public abstract class WindowBase
	extends JDialog
{
	/**
	 * Panel with two buttons, the first have the point to {@code call} a
	 * {@link Callable}, and the second to dispose this {@code JDialog}.
	 */
	private JTwoButtonsPanel buttonsPanel;
	/**
	 * Label where the exception message will be printed.
	 */
	private JLabel errorLabel;

	private GridBagConstraints constraints;

	/**
	 * Create a {@code JDialog} with two buttons and a error label
	 * 
	 * @param width
	 * @param height
	 */
	public WindowBase( int width, int height, String... imagePath)
	{
		setDefaultWindow( width, height );

		constraints = GridBagUtils.createGridBagConstraints();

		addComponent( new JPanelImage(imagePath), 0 );
		
		createButtonsAndErrorLabel();		

		getRootPane().setDefaultButton( buttonsPanel.getLeftButton() );
	}

	
	
	//Private methods
	/**
	 * 
	 * @param width
	 * @param height
	 */
	private void setDefaultWindow( int width, int height )
	{
		getContentPane().setLayout( new GridBagLayout() );

		setSize( width, height );
		getContentPane().setBackground( new Color( 65, 72, 78 ) );

		setIconImage( Toolkit.getDefaultToolkit().getImage( "src/main/resources/images/radar.png" ) );
		
		setTitle( "Air Traffic Controll" );
		setLocationRelativeTo( null );
		setResizable( false );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
	}
	
	/**
	 * 
	 */
	private void createButtonsAndErrorLabel()
	{
		buttonsPanel = new JTwoButtonsPanel();
		buttonsPanel.setBackground(new Color(0,0,0,0));
		addComponent( buttonsPanel, GridBagConstraints.BASELINE_TRAILING );
			
		errorLabel = new JLabel( " " );
		errorLabel.setForeground( Color.RED );
		
	
		addComponent( errorLabel, GridBagConstraints.BELOW_BASELINE );
	}

	// Protected Methods
	/**
	 * Add a JComponent to the line {@code y}
	 * 
	 * @param component
	 * @param constraints
	 */
	protected void addComponent( JComponent component, int y )
	{
		constraints.gridy = y;
		getContentPane().add( component, constraints );
	}

	// Public Get Methods
	/**
	 * @return the buttonsPanel
	 */
	public JTwoButtonsPanel getButtonsPanel()
	{
		return buttonsPanel;
	}

	/**
	 * @return errorLabel
	 */
	public JLabel getErrorLabel()
	{
		return errorLabel;
	}
}
