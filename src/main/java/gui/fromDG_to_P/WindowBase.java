package main.java.gui.fromDG_to_P;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.concurrent.Callable;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 
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
	private ButtonsPanel buttonsPanel;
	/**
	 * Label where a exception message will be printed.
	 */
	private JLabel errorLabel;

	private GridBagConstraints constraints;

	/**
	 * Create a {@code JDialog} with two buttons and a error label
	 * 
	 * @param width
	 * @param height
	 */
	public WindowBase( int width, int height )
	{
		setDefaultWindow( width, height );

		constraints = GridBagUtils.createGridBagConstraints();

		createButtonsAndErrorLabel();


		addComponent( buttonsPanel, GridBagConstraints.BASELINE_TRAILING );
		addComponent( errorLabel, GridBagConstraints.BELOW_BASELINE );

		getRootPane().setDefaultButton( buttonsPanel.getLeftButton() );
	}

	//Private methods
	private void setDefaultWindow( int width, int height )
	{
		getContentPane().setLayout( new GridBagLayout() );

		setSize( width, height );
		getContentPane().setBackground( new Color( 65, 72, 78 ) );

		setIconImage( Toolkit.getDefaultToolkit().getImage( "src/ByD/Air-icon.png" ) );

		setTitle( "Air Traffic Controll" );
		setLocationRelativeTo( null );
		setResizable( false );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
	}

	private void createButtonsAndErrorLabel()
	{
		buttonsPanel = new ButtonsPanel();

		errorLabel = new JLabel( " " );
		errorLabel.setForeground( Color.RED );
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
	public ButtonsPanel getButtonsPanel()
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
