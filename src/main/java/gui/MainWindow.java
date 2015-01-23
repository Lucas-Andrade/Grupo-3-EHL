package main.java.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.gui.Borders.TextRoundBorder;
import main.java.gui.JPanels.JLogoPanel;
import main.java.gui.JPanels.JPanelImage;
import main.java.gui.JPanels.JScrollPanelForElements;
import main.java.gui.JPanels.JUserForMainWindowPanel;
import main.java.utils.exceptions.InvalidArgumentException;

public class MainWindow extends JFrame {

	private User user;
	private static final long serialVersionUID = 1L;

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


	
	public MainWindow(User user) throws InvalidArgumentException {
	
		
		this.user=user;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 1350, 720);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/radar.png"));
		this.setTitle("Air Traffic Control");
	
		
		JPanel contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(65, 72, 78));
		setContentPane(contentPane);
			
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{250, 350, 250, 0};
		gbl_contentPane.rowHeights = new int[]{0,0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};		
		contentPane.setLayout(gbl_contentPane);
		
		
		JLogoPanel logo = new JLogoPanel();
		GridBagConstraints Box = new GridBagConstraints();
		Box.insets = new Insets(5, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 0;		
		contentPane.add(logo,Box);		
		
		JUserForMainWindowPanel userForMain = new JUserForMainWindowPanel();
		Box.insets = new Insets(5, 0, 0, 0);
		Box.gridx = 2;
		Box.gridy = 0;		
		
		
		contentPane.add(userForMain,Box);		
		
		JSeparator line0 = new JSeparator(JSeparator.HORIZONTAL);
		line0.setForeground(Color.WHITE);
		line0.setPreferredSize(new Dimension(300,1));
		Box.insets = new Insets(170, 0, 0, 0);
		Box.gridx = 0;
		Box.gridy = 0;
		contentPane.add(line0,Box);
		
		JSeparator line1 = new JSeparator(JSeparator.HORIZONTAL);
		line1.setForeground(Color.WHITE);
		line1.setPreferredSize(new Dimension(722,1));
		Box.insets = new Insets(170, 0, 0, 0);
		Box.gridx = 1;
		Box.gridy = 0;
		contentPane.add(line1,Box);

		
		JSeparator line2 = new JSeparator(JSeparator.HORIZONTAL);
		line2.setForeground(Color.WHITE);
		line2.setPreferredSize(new Dimension(311,1));
		Box.insets = new Insets(170, 0, 0, 0);
		Box.gridx = 2;
		Box.gridy = 0;
		contentPane.add(line2,Box);


		
		JPanelImage worldMap = new JPanelImage("src/main/resources/images/planisphere.png");
		worldMap.setBorder( new TextRoundBorder(Color.WHITE,10,15,0));
		
		Box.insets = new Insets(0, 0, 0, 0);
		Box.gridx = 1;
		Box.gridy = 1;		
		getContentPane().add(worldMap,Box);
					
//		JLabel militaryAirship = new JLabel("ola");
		
//		militaryAirship.setBounds(0, 0, 50, 50);
//		add(militaryAirship);
//		worldMap.add(militaryAirship);		
		 
		/////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////////////
		
		Collection<Airship> airshipdatabase= new ArrayList<Airship>();
	
		
		Airship air2 = new MilitaryAirship(-30, 100, 10000, 20000, 0, true);
		Airship air1 =new CivilAirship(30, 0, 10000, 20000, 0, 130);
		Airship air3 = new CivilAirship( 30, 40, 6000, 3000, 300, 6 );
		Airship air4 = new MilitaryAirship( 30, 40, 5000, 4000, 200, false );
		Airship air5 = new MilitaryAirship( 30, 40, 2000, 3000, 200, true );
		Airship air6 = new MilitaryAirship(-30, 100, 10000, 20000, 0, true);
		Airship air7 =new CivilAirship(30, 0, 10000, 20000, 0, 130);
		Airship air8 = new CivilAirship( 30, 40, 6000, 3000, 300, 6 );
		Airship air9 = new MilitaryAirship( 30, 40, 5000, 4000, 200, false );
		Airship air10 = new MilitaryAirship( 30, 40, 2000, 3000, 200, true );
		
		airshipdatabase.add(air1);
		airshipdatabase.add(air2);
		airshipdatabase.add(air3);
		airshipdatabase.add(air4);
		airshipdatabase.add(air5);
		airshipdatabase.add(air6);
		airshipdatabase.add(air7);
		airshipdatabase.add(air8);
		airshipdatabase.add(air9);
		airshipdatabase.add(air10);
		
		//////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////
		GridBagConstraints Box2 = new GridBagConstraints();
		
		Box2.insets = new Insets(0, 0, 0, 0);
		Box2.gridx = 2;
		Box2.gridy = 1;
		
		getContentPane().add(new JScrollPanelForElements<Airship>(airshipdatabase) ,Box2);
		setSize(1350,720); 
		setVisible(true);
		
		
		
		
	}



	public User getUser() {
		return user;
	}


	

}
