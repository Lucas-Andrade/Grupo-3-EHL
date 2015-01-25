package main.java.gui.designWindows.windows;

import main.java.domain.model.users.User;
import main.java.gui.designWindows.JPanels.JScrollPanelForElements;

@SuppressWarnings("serial")
public class  JScrollPanelForUsers extends JScrollPanelForElements<User>{

	
	
	@Override
	protected String getString(User User){
		
		
		return User.toStringWithoutPassword();
		
	}
	

}
