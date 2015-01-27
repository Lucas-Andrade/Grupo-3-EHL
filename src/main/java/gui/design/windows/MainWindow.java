package main.java.gui.design.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.design.GridBagUtils;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JHeaderPanelForMainWindow;


@SuppressWarnings( "serial" )
public class MainWindow
	extends JFrame
{

	// Fields

	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();

	private JHeaderPanelForMainWindow headerPanel;

	private JBodyPanelForMainWindow bodyPanel;

	private JFooterPanelForMainWindow footerPanel;

	// Constructor

	public MainWindow( Database< Airship > airshipsDatabase, Iterable< Airship > airshipsFound )
	{

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setIconImage( Toolkit.getDefaultToolkit().getImage( "src/main/resources/images/radar.png" ) );
		this.setTitle( "Air Traffic Control" );

		JPanel contentPane = new JPanel();

		contentPane.setBackground( new Color( 65, 72, 78 ) );
		getContentPane().add( contentPane );

		contentPane.setLayout( new GridBagLayout() );

		headerPanel = new JHeaderPanelForMainWindow();

		contentPane.add( headerPanel, constraints );

		contentPane.add( headerPanel, constraints );


		bodyPanel = new JBodyPanelForMainWindow( airshipsDatabase, airshipsFound );
		contentPane.add( bodyPanel, GridBagUtils.updateGridBagConstraints( constraints, 1 ) );

		footerPanel = new JFooterPanelForMainWindow();
		contentPane.add( footerPanel, GridBagUtils.updateGridBagConstraints( constraints, 2 ) );

		JLabel errorLabel = new JLabel( " " );
		errorLabel.setForeground( Color.RED );
		errorLabel.setFont( errorLabel.getFont().deriveFont( 20f ) );
		contentPane.add( errorLabel, GridBagUtils.updateGridBagConstraints( constraints, 3 ) );

		pack();
		setLocationRelativeTo( null );
		setResizable( false );
		setVisible( true );
	}

	// Public Set Methods

	public void setHeaderPanel( JHeaderPanelForMainWindow headerPanel )
	{

		this.headerPanel = headerPanel;
	}

	public void setFooterPanel( JFooterPanelForMainWindow footerPanel )
	{

		this.footerPanel = footerPanel;
	}

	public void setBodyPanel( JBodyPanelForMainWindow bodyPanel )
	{

		this.bodyPanel = bodyPanel;
	}


	// Public Get Methods

	public JHeaderPanelForMainWindow getHeaderPanel()
	{

		return headerPanel;
	}

	public JBodyPanelForMainWindow getBodyPanel()
	{

		return bodyPanel;
	}

	public JFooterPanelForMainWindow getFooterPanel()
	{

		return footerPanel;
	}
}
