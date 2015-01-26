package main.java.gui.designWindows.windows.userWindows;

import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.jPanels.JScrollPanelForUsers;
import main.java.gui.designWindows.windows.WindowBase;

@SuppressWarnings("serial")
public class GetUsersWindow extends WindowBase {

	private Database<User> database;
		
	public GetUsersWindow(Database<User> database) throws Exception {
		super(400, 200);
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

