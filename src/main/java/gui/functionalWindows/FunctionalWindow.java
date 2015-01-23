package main.java.gui.functionalWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

import javax.swing.SwingWorker;

import main.java.gui.fromDG_to_P.WindowBase;

/**
 *  
 * 
 *@param <T> the return of the respective {@link Callable#call()}
 *
 *@author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class FunctionalWindow< T >
{
	/**
	 * The {@code WindowBase} where will be added its functionality  
	 */
	private WindowBase functionalWindow;


	/**
	 * Add the {@link ActionListener}s to the {@code window buttons}
	 *  
	 * @param nonFunctionalWindow
	 */
	public FunctionalWindow( WindowBase nonFunctionalWindow )
	{
		functionalWindow = nonFunctionalWindow;

		addRightButtonAction();
		addLeftButtonAction();

		functionalWindow.setVisible( true );
	}

	// Private Methods
	/**
	 * Right button -> dispose
	 */
	private void addRightButtonAction()
	{
		functionalWindow.getButtonsPanel().getRightButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent click )
			{
				functionalWindow.dispose();
			}
		} );
	}

	/**
	 * Left button
	 * <ul>
	 * <li> collect the respective window info;
	 * <li> get a {@link SwingWorker} and {@code run} it (i.e. {@code call} the command);
	 * <li> get the return command and show it.
	 * <ul> 
	 */
	private void addLeftButtonAction()
	{
		functionalWindow.getButtonsPanel().getLeftButton().addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( ActionEvent click )
			{
				getSwingWorker().run();
			}
		} );
	}


	// Protected Abstract Method
	
	protected abstract FunctionalWindowSwingWorker getSwingWorker();

	protected abstract void functionalWindowDone( T resultOfDoInBackGround );


	//InnerClass
	protected abstract class FunctionalWindowSwingWorker
		extends SwingWorker< T, Void >
	{
		@Override
		protected void done()
		{
			try
			{
				functionalWindowDone( get() );
			}
			catch( Exception e )
			{
				functionalWindow.getErrorLabel().setText( e.getCause().getMessage() );
				functionalWindow.getErrorLabel().setVisible( true );
			}
		}
	}
	
	

	/**
	 * @return the window
	 */
	public WindowBase getFunctionalWindow()
	{
		return functionalWindow;
	}
}
