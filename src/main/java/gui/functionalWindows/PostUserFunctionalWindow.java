package main.java.gui.functionalWindows;

import main.java.domain.commands.postcommands.PostUserCommand;
import main.java.domain.model.users.InMemoryUsersDatabase;
import main.java.domain.model.users.User;
import main.java.gui.fromDG_to_P.windows.PostUserWindow;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserFunctionalWindow extends FunctionalWindow<String> {
	
	private PostUserWindow window;
	
	/**
	 * users database
	 */
	private InMemoryUsersDatabase usersDatabase;
	
	private User userWhoIsPosting;
	
	/**
	 * 
	 * @param window
	 * @param usersDatabase
	 * @param userWhoIsPosting
	 */
	public PostUserFunctionalWindow(PostUserWindow window, InMemoryUsersDatabase usersDatabase,
		User userWhoIsPosting) {
	
		super(window);
		
		this.usersDatabase = usersDatabase;
		this.userWhoIsPosting = userWhoIsPosting;
	}
	
	@Override
	protected FunctionalWindowSwingWorker getSwingWorker() {
	
		return new FunctionalWindowSwingWorker() {
			
			String username = window.getUserPanel().getTextField().getText();
			String password = window.getPasswordPanel().getTextField().getText();
			String email = window.getEmailPanel().getTextField().getText();
			String fullName = window.getFullNamePanel().getTextField().getText();
			
			@Override
			protected String doInBackground() throws Exception {
			
				return new PostUserCommand(username, password, email, fullName, usersDatabase,
					userWhoIsPosting).call();
			}
		};
	}
	
	@Override
	public void functionalWindowDone(String resultOfDoInBackGround) {
	
		// TODO
// JOptionPane.showOptionDialog( parentComponent, message, title, optionType, messageType, icon,
// options, initialValue );
	}
}
