package main.java.gui.To_be_eliminated;


import java.awt.GridBagConstraints;

import main.java.domain.model.Database;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.gui.To_be_eliminated.windows.SuccessPostUserWindow;
import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JCommonPostAirshipPanel;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JPanelWithSpecificCivilAirhipParameters;
import main.java.gui.designWindows.jPanels.forPostAirshipWindow.JPanelWithSpecificMilitaryAirhipParameters;
import main.java.gui.designWindows.windows.airshipWindows.PostAirshipsWindow;
import main.java.gui.designWindows.windows.userWindows.GetUsersWindow;
import main.java.gui.designWindows.windows.userWindows.LogInWindow;
import main.java.gui.designWindows.windows.userWindows.PostUserWindow;



public class App {

	
private static final long serialVersionUID = 1L;
	
	

	
	
	public static void main(String[] args) throws Exception {

		
		final String MILITARYAIRSHIP = "Military Airship";
		final String CIVILAIRSHIP = "Civil Airship";
		GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
		
		JCommonPostAirshipPanel militaryAirshipCommonPainel;

		JCommonPostAirshipPanel civilAirshipCommonPainel;

		JPanelWithSpecificMilitaryAirhipParameters specificMilitaryPanel;

		JPanelWithSpecificCivilAirhipParameters specificCivilPanel;

//
		User user = new User("Pantunes","pass","email@gmail.com");
//
//		Database<Airship> airshipdatabase= new InMemoryAirshipsDatabase("first");
//
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
//		airshipdatabase.add(air1,user);
//		airshipdatabase.add(air2,user);
//		airshipdatabase.add(air3,user);
//		airshipdatabase.add(air4,user);
//		airshipdatabase.add(air5,user);
//		airshipdatabase.add(air6,user);
//		airshipdatabase.add(air7,user);
//		airshipdatabase.add(air8,user);
//		airshipdatabase.add(air9,user);
//		airshipdatabase.add(air10,user);	
////		
		Database<User> userCollection = new InMemoryUsersDatabase("first");
		
		User user1 = new User("Pedro","pass","email@gmail.com");
		User user2 = new User("Antunes","pass","email@gmail.com");
		User user3 = new User("cenas","pass","email@gmail.com");
		User user4 = new User("Pantunes","pass","email@gmail.com");
		User user5 = new User("Pedro","pass","email@gmail.com");
		User user6 = new User("Antunes","pass","email@gmail.com");
		User user7 = new User("cenas","pass","email@gmail.com");
		
		userCollection.add(user,user);
		userCollection.add(user1,user);
		userCollection.add(user2,user);
		userCollection.add(user3,user);		
		userCollection.add(user4,user);
		userCollection.add(user5,user);
		userCollection.add(user6,user);
		userCollection.add(user7,user);
		
		
		
	//	new MainWindow(airshipdatabase, userCollection, user1);
		
		new LogInWindow();
		
		new PostAirshipsWindow();
		new PostUserWindow();
		
		
		//------------------------- 
		new GetUsersWindow(userCollection);
		new JUnderConstrutionPanel();
		new SuccessPostUserWindow();
//
//		JDialog frame = new JDialog();
//
//
//		JTabbedPane TypeAirshipTabbedPane = new JTabbedPane();
//		TypeAirshipTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//		TypeAirshipTabbedPane.setPreferredSize(new Dimension(400, 600));
//		
//		JPanel civilpanel = new JPanel();
//		civilpanel.setBackground(new Color(65, 72, 78));
//		civilpanel.setPreferredSize(new Dimension(400, 600));
//		
//		JPanel militaryPanel = new JPanel();
//		militaryPanel.setBackground(new Color(65, 72, 78));
//		militaryPanel.setPreferredSize(new Dimension(400, 600));
//		
//		TypeAirshipTabbedPane.addTab(CIVILAIRSHIP, null, civilpanel, null);
//		TypeAirshipTabbedPane.addTab(MILITARYAIRSHIP, null, militaryPanel, null);
//
//		frame.getContentPane().add(TypeAirshipTabbedPane);
//
//	
//		civilpanel.add(new JPanelImage("src/main/resources/images/civil.png","src/main/resources/images/add.png"));
//		militaryPanel.add(new JPanelImage("src/main/resources/images/military.png","src/main/resources/images/add.png"));
//
//		
//		 civilAirshipCommonPainel = new JCommonPostAirshipPanel();
//		civilpanel.add(civilAirshipCommonPainel);
//	
//		 militaryAirshipCommonPainel = new JCommonPostAirshipPanel();
//		militaryPanel.add(militaryAirshipCommonPainel);
//
//		specificCivilPanel = new JPanelWithSpecificCivilAirhipParameters();
//		civilpanel.add(specificCivilPanel);
//		specificMilitaryPanel = new JPanelWithSpecificMilitaryAirhipParameters();
//		militaryPanel.add(specificMilitaryPanel);
//		
//		
//		frame.setSize(400,600);
//		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		frame.setVisible(true);
//		frame.setResizable(false);
//		
		
		
		
	}

}
