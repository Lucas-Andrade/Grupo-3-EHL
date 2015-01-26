package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 * 
 * 
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class PopupWindow
{
	private JDialog dialog;
	private JButton okButton;

	public PopupWindow( String message, Icon image )
	{
		Color backGroundColor = new Color( 65, 72, 78 );
		UIManager.put( "Panel.background", backGroundColor );

		createButton();
		JOptionPane optionPane =
				new JOptionPane( getLabelPanel(message), JOptionPane.INFORMATION_MESSAGE,
						JOptionPane.DEFAULT_OPTION, image, new Object[]{ okButton }, okButton );
		optionPane.setBackground( backGroundColor );

		dialog = optionPane.createDialog( "Air Traffic Controll" );
		dialog.setIconImage( Toolkit.getDefaultToolkit().getImage( "src/main/resources/images/radar.png" ) );
		dialog.setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		dialog.setVisible( true );
	}


	private JLabel getLabelPanel( String message )
	{
		JLabel messageLabel= new JLabel( message, JLabel.CENTER );
		messageLabel.setForeground( Color.WHITE );
		return messageLabel;
	}

	private void createButton()
	{
		okButton = new JButton( "Ok" );
		okButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent e )
			{
				dialog.dispose();
			}
		} );
	}
}
