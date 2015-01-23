package main.java.gui;

import java.awt.Color;

import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.JPanels.JDialogWithBackground;

@SuppressWarnings("serial")
public class GetUsersWindow extends JDialogWithBackground {

	private Database<User> database;

//	public static void main(String[] args) {
//		
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//
//			try {
//				new GetUsersWindow();
//			} catch (InvalidArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			}
//		});
//	}
		
	public GetUsersWindow(Database<User> database) throws Exception {
		super(new Color(65, 72, 78), 400, 200);
		this.database = database;
		initial();
		
	}

	private void initial() throws Exception {

		
		this.getContentPane().add(new JScrollPanelForUsers().produceAJScrollPaneWithAllElements(database));
	
		this.pack();
		this.setVisible(true);
		this.setResizable(false);

		
	}
	
}

