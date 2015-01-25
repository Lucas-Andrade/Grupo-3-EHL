package main.java.gui.designWindows.JPanels;

import main.java.domain.model.users.User;

@SuppressWarnings("serial")
public class  JScrollPanelForUsers extends JScrollPanelForElements<User>{

	
	
	@Override
	protected String getString(User User){
		
		
		return User.toStringWithoutPassword();
		
	}
	

}
