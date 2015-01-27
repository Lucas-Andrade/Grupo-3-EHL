package main.java.gui.functionalWindows.functionalUserWindows;

import java.awt.event.ActionListener;

import main.java.domain.commands.postcommands.PostUserCommand;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.popupWindows.SuccessWindow;
import main.java.gui.designWindows.windows.userWindows.PostUserWindow;
import main.java.gui.functionalWindows.FunctionalWindow;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances have the responsibility to add the
 * {@link ActionListener}s to the given {@link PostUserWindow} buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalPostUserWindow extends FunctionalWindow<String> {
	
	private PostUserWindow postUserWindow;
	
	/**
	 * users database
	 */
	private Database< User > usersDatabase;
	private User userWhoIsPosting;
	
	/**
	 * 
	 * @param postUserWindow
	 * @param usersDatabase
	 * @param userWhoIsPosting
	 */
	public FunctionalPostUserWindow( PostUserWindow postUserWindow, Database< User > usersDatabase,
			User userWhoIsPosting )
	{
		super( postUserWindow );
		this.postUserWindow = postUserWindow;
		this.usersDatabase = usersDatabase;
		this.userWhoIsPosting = userWhoIsPosting;

	}
	
	/**
	 * Get a {@link FunctionalWindowSwingWorker}, with all necessary info retrieved from {@code postUserWindow}.
	 * The {@code doInBackground} method is {@code override}, to call the {@link PostUserCommand}.
	 *  
	 */
	@Override
	protected FunctionalWindowSwingWorker getSwingWorker()
	{
		return new FunctionalWindowSwingWorker()
		{
			String username = postUserWindow.getUsername().getJTextField().getText();
			String password = postUserWindow.getPassword().getJTextField().getText();
			String confirmPassword = postUserWindow.getConfirmPassword().getJTextField().getText();
			String email = postUserWindow.getEmail().getJTextField().getText();
			String fullName = postUserWindow.getFullname().getJTextField().getText();

			@Override
			protected String doInBackground() throws Exception
			{
				if(!password.equals( confirmPassword ))
					throw new InvalidArgumentException(	"The Passwords Don't Match" ); 
				return new PostUserCommand( username, password, email, fullName, usersDatabase,
						userWhoIsPosting ).call();
			}
		};
	}
	
	/**
	 * Open the {@link SuccessWindow} {@code pop-up}
	 */
	@Override
	public void functionalWindowDone(String resultOfDoInBackGround)
	{
		new SuccessWindow(resultOfDoInBackGround);
		postUserWindow.dispose();
	}
}