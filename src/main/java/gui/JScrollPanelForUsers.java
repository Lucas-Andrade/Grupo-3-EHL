package main.java.gui;

import main.java.domain.model.users.User;
import main.java.gui.JPanels.JScrollPanelForElements;

@SuppressWarnings("serial")
public class  JScrollPanelForUsers extends JScrollPanelForElements<User>{

	
	
	@Override
	protected String getString(User User){
		
		
		return User.toStringWithoutPassword();
		
	}
	

}
