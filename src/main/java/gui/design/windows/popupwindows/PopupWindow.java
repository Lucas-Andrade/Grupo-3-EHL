package main.java.gui.design.windows.popupwindows;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.gui.design.GridBagUtils;


/**
 * Abstract class, whose instances are {@link JDialog} with a {@code Ok} button,
 * that dispose this window.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public abstract class PopupWindow
	extends JDialog
{
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();

	/**
	 * Creates a Pop-up window with a {@code image}, a {@code message}, and a
	 * {@code ok-button}.
	 * 
	 * @param message
	 * @param image
	 */
	public PopupWindow( String message, String image )
	{
		setLayout( new GridBagLayout() );
		add( getLabel( message, image ), GridBagUtils.updateGridBagConstraints( constraints, 0 ) );

		setDefaultSettings();
	}

	/**
	 * Creates a Pop-up window with a {@code component} and a {@code ok-button}.
	 * 
	 * @param component
	 */
	public PopupWindow( Component component )
	{
		setLayout( new GridBagLayout() );
		add( component, GridBagUtils.updateGridBagConstraints( constraints, 0 ) );

		setDefaultSettings();
	}

	/**
	 * Set all shared Settings: Title, Color, Icon, size, ... Also is added the
	 * {@code ok-button}
	 */
	private void setDefaultSettings()
	{
		setTitle( "Air Traffic Controll" );
		getContentPane().setBackground( new Color( 65, 72, 78 ) );
		setSize( 400, 10 );
		setIconImage( Toolkit.getDefaultToolkit().getImage( "/images/radar.png" ) );

		add( createButton(), GridBagUtils.updateGridBagConstraints( constraints, 1 ) );

		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		setModalityType( ModalityType.TOOLKIT_MODAL );

		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}



	/**
	 * @param message
	 * @param image
	 * @return the Label with a {@code image} and a {@code message}
	 */
	private JPanel getLabel( String message, String image )
	{
		JPanel mainPanel = new JPanel();
		JLabel imageLabel;
		try {
			imageLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream(image))));
	
		JLabel messageLabel = new JLabel( message, JLabel.CENTER );
		messageLabel.setForeground( Color.WHITE );

		
		mainPanel.setLayout( new FlowLayout( FlowLayout.LEADING, 50, 50 ) );
		mainPanel.setBackground( new Color( 65, 72, 78 ) );
		mainPanel.add( imageLabel );
		mainPanel.add( messageLabel );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mainPanel;
	}



	/**
	 * @return the buttonPanel, that contains the button
	 */
	private JPanel createButton()
	{
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground( new Color( 65, 72, 78 ) );
		JButton okButton = new JButton( "Ok" );
		okButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				dispose();
			}
		} );
		buttonPanel.add( okButton );
		getRootPane().setDefaultButton( okButton );
		return buttonPanel;
	}
}
