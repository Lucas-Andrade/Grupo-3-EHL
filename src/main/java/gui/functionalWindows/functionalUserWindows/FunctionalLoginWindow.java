package main.java.gui.functionalWindows.functionalUserWindows;

import java.awt.Dialog;

import main.java.domain.commands.AuthenticateUserCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.MainWindow;
import main.java.gui.designWindows.windows.userWindows.LogInWindow;
import main.java.gui.functionalWindows.FunctionalWindow;
import main.java.gui.functionalWindows.functionalMainWindow.FunctionalMainWindow;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalLoginWindow extends FunctionalWindow<User> {
	
	private LogInWindow functionalWindow;
	
	private Database<User> usersDatabase;
	private Database<Airship> airshipsDatabase;
	
	public FunctionalLoginWindow(LogInWindow nonFunctionalWindow, Database<User> usersDatabase,
		Database<Airship> airshipsDatabase) {
	
		super(nonFunctionalWindow);
		
		this.functionalWindow = nonFunctionalWindow;
		
		this.usersDatabase = usersDatabase;
		this.airshipsDatabase = airshipsDatabase;
		functionalWindow.setModalityType( Dialog.ModalityType.TOOLKIT_MODAL );
	}
	
	@Override
	protected FunctionalWindowSwingWorker getSwingWorker() {
	
		return new FunctionalWindowSwingWorker() {
			
			String username = functionalWindow.getUserPanel().getJTextField().getText();
			String password = functionalWindow.getPasswordPanel().getJTextField().getText();
			
			@Override
			protected User doInBackground() throws Exception {
			
				return new AuthenticateUserCommand(username, password, usersDatabase).call().get();
			}
		};
	}
	
	@Override
	protected void functionalWindowDone(User resultOfDoInBackGround) throws Exception {
	
		new FunctionalMainWindow(new MainWindow(airshipsDatabase), usersDatabase, airshipsDatabase,
			resultOfDoInBackGround);
		
		functionalWindow.dispose();
	}
}
