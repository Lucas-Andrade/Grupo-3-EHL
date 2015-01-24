package main.java.gui.functionalWindows;

import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.gui.MainWindow;
import main.java.gui.fromDG_to_P.windows.LogInWindow;
import main.java.utils.exceptions.InvalidArgumentException;

public class FunctionalLoginWindow extends FunctionalWindow<Void> {
	
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
	protected FunctionalWindow<Void>.FunctionalWindowSwingWorker getSwingWorker() {
	
		return new FunctionalWindowSwingWorker() {
			
			String username = functionalWindow.getUserPanel().getTextField().getText();
			String password = functionalWindow.getPasswordPanel().getTextField().getText();
			
			@Override
			protected Void doInBackground() throws Exception {
			
				// TODO
				User user = usersDatabase.getElementByIdentification(username).get();
				
				if (usersDatabase.getElementByIdentification(username).get()
					.authenticatePassword(password)) {
					
					new MainWindow(null, usersDatabase, user);
					
					functionalWindow.dispose();
					
				} else {
					throw new InvalidArgumentException("Wrong Password!");
				}
				
				return null;
			}
		};
	}
	
	@Override
	protected void functionalWindowDone(Void resultOfDoInBackGround) {
	
		// TODO Auto-generated method stub
		
	}
}
