package main.java.gui.designWindows.windows.popupWindows;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.gui.designWindows.GridBagUtils;


/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public abstract class PopupWindow
	extends JDialog
{
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();

	public PopupWindow( String message, Icon image )
	{
		setLayout( new GridBagLayout() );
		add( getLabel( message, image ), GridBagUtils.updateGridBagConstraints( constraints, 0 ) );

		defaultSettings();
	}
	
	public PopupWindow( Component component  )
	{
		setLayout( new GridBagLayout() );
		add( component, GridBagUtils.updateGridBagConstraints( constraints, 0 ) );

		defaultSettings();
	}

	private void defaultSettings()
	{
		add( createButton(), GridBagUtils.updateGridBagConstraints( constraints, 1 ) );
		getContentPane().setBackground( new Color( 65, 72, 78 ) );
		setTitle( "Air Traffic Controll" );
		setIconImage( Toolkit.getDefaultToolkit().getImage( "src/main/resources/images/radar.png" ) );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		setSize( 400, 10 );
		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}



	private JPanel getLabel( String message, Icon image )
	{
		
		JLabel imageLabel = new JLabel( image );
		JLabel messageLabel = new JLabel( message, JLabel.CENTER );
		messageLabel.setForeground( Color.WHITE );
		JPanel c = new JPanel();
		c.setLayout( new FlowLayout( FlowLayout.LEADING, 50, 50 ));
		c.add( imageLabel );
		c.add( messageLabel );
		c.setBackground( new Color( 65, 72, 78 ) );
		return c;
	}



	private JPanel createButton()
	{
		JPanel buttonPanel = new JPanel();
		// panel.setSize( 400, 10 );
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
