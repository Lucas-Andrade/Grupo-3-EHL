package main.java.gui.functionalcomponents.functionaluserwindows;

import java.awt.Dialog;
import java.awt.event.ActionListener;

import main.java.domain.commands.AuthenticateUserCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.design.windows.MainWindow;
import main.java.gui.design.windows.userwindows.LogInWindow;
import main.java.gui.functionalcomponents.FunctionalWindow;
import main.java.gui.functionalcomponents.functionalmainwindow.FunctionalMainWindow;

/**
 * Class whose instances have the responsibility to add the
 * {@link ActionListener}s to the given {@link LogInWindow} buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalLoginWindow
	extends FunctionalWindow< User >
{

	private LogInWindow functionalWindow;

	private Database< User > usersDatabase;
	private Database< Airship > airshipsDatabase;

	/**
	 * Give an {@link ActionListener}s to the given
	 * {@link FunctionalLoginWindow} buttons. To return the functional
	 * {@code FunctionalLoginWindow} use the
	 * {@link FunctionalWindow#getFunctionalWindow()} method
	 * 
	 * @param nonFunctionalWindow
	 * @param usersDatabase
	 * @param airshipsDatabase
	 */
	public FunctionalLoginWindow( LogInWindow nonFunctionalWindow, Database< User > usersDatabase,
			Database< Airship > airshipsDatabase )
	{

		super( nonFunctionalWindow );

		this.functionalWindow = nonFunctionalWindow;

		this.usersDatabase = usersDatabase;
		this.airshipsDatabase = airshipsDatabase;
		functionalWindow.setModalityType( Dialog.ModalityType.TOOLKIT_MODAL );
	}

	@Override
	protected FunctionalWindowSwingWorker getSwingWorker()
	{

		return new FunctionalWindowSwingWorker()
		{

			String username = functionalWindow.getUserPanel().getJTextField().getText();
			String password = functionalWindow.getPasswordPanel().getJTextField().getText();

			@Override
			protected User doInBackground() throws Exception
			{

				return new AuthenticateUserCommand( username, password, usersDatabase ).call().get();
			}
		};
	}

	@Override
	protected void functionalWindowDone( User resultOfDoInBackGround ) throws Exception
	{

		new FunctionalMainWindow(
				new MainWindow( airshipsDatabase, airshipsDatabase.getAllElements().get() ), usersDatabase,
				airshipsDatabase, resultOfDoInBackGround );

		functionalWindow.dispose();
	}
}
