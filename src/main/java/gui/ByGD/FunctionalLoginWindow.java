package main.java.gui.ByGD;

import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.gui.MainWindow;
import main.java.utils.exceptions.InvalidArgumentException;

public class FunctionalLoginWindow extends FunctionalWindow {
	
	// Fields
	
	private InMemoryUsersDatabase usersDatabase;
	
	private LogInWindow functionalWindow;
	
	// Constructor
	
	public FunctionalLoginWindow(LogInWindow nonFunctionalWindow,
		InMemoryUsersDatabase usersDatabase) {
	
		super(nonFunctionalWindow);
		
		this.functionalWindow = nonFunctionalWindow;
		
		this.usersDatabase = usersDatabase;
	}
	
	// Implementation of the method inherited from the FunctionalWindow class
	
	@Override
	protected void leftButtonAction() throws Exception {
	
		String username = functionalWindow.getUserPanel().getTextField().getText();
		String password = functionalWindow.getPasswordPanel().getTextField().getText();
		
		if (usersDatabase.getElementByIdentification(username).get().authenticatePassword(password)) {
			
			new MainWindow();
			
			functionalWindow.getAbstractDialogWindow().dispose();
			
		} else {
			throw new InvalidArgumentException("Wrong Password!");
		}
		
	}
}
