package main.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.Borders.TextRoundBorder;
import main.java.gui.JPanels.JButtonsPanelForMainWindow;
import main.java.gui.JPanels.JUserForMainWindowPanel;
import main.java.gui.JPanels.JWorldMapWithAirships;

public class MainWindow extends JFrame {

	//private User user;
	private static final long serialVersionUID = 1L;
	
	private User user;

//	public static void main(String[] args) {
//		
//		
//		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					new MainWindow();
//								
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}


	
//	public MainWindow(User user) throws InvalidArgumentException {
	
	public MainWindow(Database<Airship> airshipdatabase, Database<User> userdatabase, User user) throws Exception {
		
		
		//Collection<Airship> airshipdatabase= new ArrayList<Airship>();

		////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////

//		Airship air1 = new MilitaryAirship(-10, 210, 10000, 20000, 0, true);
//		Airship air2 =new CivilAirship(38, 171, 10000, 20000, 0, 130);  ///// Lisboa
//		Airship air3 = new CivilAirship( 59, 198, 6000, 3000, 300, 6 );  ///// ESTOCOLMO 59°22′10″N	18°38′00″E
//		Airship air4 = new MilitaryAirship( 40, 107, 5000, 4000, 200, false );  ///// NY 40°45′40″N	73°59′29″W
//		Airship air5 = new MilitaryAirship( 55, 217, 2000, 3000, 200, true );   ////	Moscovo 55° 45' 7 N	37° 36' 56E
//		Airship air6 = new MilitaryAirship(-22, 137, 10000, 20000, 0, true); //// Rio de Janeiro 22º 54' S 43º 10' W
//		Airship air7 =new CivilAirship(30, 156, 10000, 20000, 0, 130);
//		Airship air8 = new CivilAirship( -60, 260, 6000, 3000, 300, 6 );
//		Airship air9 = new MilitaryAirship( 45, 75, 5000, 4000, 200, false );
//		Airship air10 = new MilitaryAirship( -60, 156, 2000, 3000, 200, true );
//		
//		airshipdatabase.add(air1);
//		airshipdatabase.add(air2);
//		airshipdatabase.add(air3);
//		airshipdatabase.add(air4);
//		airshipdatabase.add(air5);
//		airshipdatabase.add(air6);
//		airshipdatabase.add(air7);
//		airshipdatabase.add(air8);
//		airshipdatabase.add(air9);
//		airshipdatabase.add(air10);		
		
		////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////
		
		
		
	//	this.user=user;
		
	
		this.user = user;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1350, 720);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/radar.png"));
		this.setTitle("Air Traffic Control");
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(65,72,78));
		getContentPane().add(contentPane);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1300, 0};
		gridBagLayout.rowHeights = new int[]{100, 10, 420, 50,	20, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gridBagLayout);
		
		
		contentPane.setLayout(gridBagLayout);
		
		GridBagConstraints Box = new GridBagConstraints();		
		Box.insets = new Insets(0, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 0;
		contentPane.add(new JUserForMainWindowPanel());
		
		JSeparator line0 = new JSeparator(JSeparator.HORIZONTAL);
		line0.setForeground(Color.WHITE);
		line0.setPreferredSize(new Dimension(1330,1));
		
		Box.insets = new Insets(0, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 1;		
		contentPane.add(line0,Box);
				
		JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(new JWorldMapWithAirships().createAJPanelWithWorldMapAndAirships(airshipdatabase));
		mainPanel.add(new JWorldMapWithAirships().produceAJScrollPaneWithAllElements(airshipdatabase));
		mainPanel.setBackground(new Color(65,72,78));
		mainPanel.setBorder(new TextRoundBorder(Color.WHITE,6,12,0) );	
		
		Box.insets = new Insets(0, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 2;		
		contentPane.add(mainPanel,Box);
		
		
		Box.insets = new Insets(0, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 3;

		contentPane.add(new JButtonsPanelForMainWindow(),Box);
		
		JLabel errorLabel = new JLabel("ERROROROROROROROOROR");
		errorLabel.setForeground(Color.RED);
		errorLabel.setFont(errorLabel.getFont().deriveFont(20f));
		errorLabel.setVisible(true);
		Box.insets = new Insets(0, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 4;
		
		contentPane.add(errorLabel,Box);
		
		
		pack();
		setVisible(true);
		
		
	}



	public User getUser() {
		return user;
	}


	

}
