package main.java.gui.functionalWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import main.java.gui.To_be_eliminated.windows.WindowBase;

/**
 * Abstract swing window, that have the responsibility to add the
 * {@link ActionListener}s to the given {@link WindowBase} buttons.
 * 
 * @param <T>
 *        return of {@link SwingWorker#doInBackground()}
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class FunctionalWindow<T> {
	
	/**
	 * The {@code WindowBase} where will be added its functionality
	 */
	private WindowBase functionalWindow;
	
	/**
	 * Add the {@link ActionListener}s to the {@code window buttons}
	 * 
	 * @param nonFunctionalWindow
	 */
	public FunctionalWindow(WindowBase nonFunctionalWindow) {
	
		functionalWindow = nonFunctionalWindow;
		
		addRightButtonAction();
		addLeftButtonAction();
		
		functionalWindow.setVisible( true );
	}
	
	// Private Methods
	
	/**
	 * Right button -> dispose
	 */
	private void addRightButtonAction() {
	
		functionalWindow.getButtonsPanel().getRightButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent click) {
			
				functionalWindow.dispose();
			}
		});
	}
	
	/**
	 * Left button
	 * <ul>
	 * <li>collect the respective window info;
	 * <li>get a {@link SwingWorker} and {@code run} it (i.e. {@code call} the
	 * command);
	 * <li>get the return command and show it.
	 * <ul>
	 */
	private void addLeftButtonAction() {
	
		functionalWindow.getButtonsPanel().getLeftButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent click) {
			
				getSwingWorker().run();
			}
		});
	}


	// Protected Abstract Method

	/**
	 * Contract for subclasses to return a {@link SwingWorker} with its
	 * {@code doInBackground()} method.
	 * 
	 * @return a {@link FunctionalWindowSwingWorker}
	 */
	protected abstract FunctionalWindowSwingWorker getSwingWorker();

	/**
	 * Contract for subclasses where the {@code doInBackground()} method result
	 * will be treated. Implementations decisions: The treatment of
	 * {@link Exception}s are caught in the
	 * {@link FunctionalWindowSwingWorker#done()} method.
	 * 
	 * @param resultOfDoInBackGround
	 */
	protected abstract void functionalWindowDone( T resultOfDoInBackGround ) throws Exception;


	// InnerClass
	/**
	 * Abstract SwingWorker Class that implements the {@code done()} method,
	 * where the {@link Exception}s are caught.
	 */
	protected abstract class FunctionalWindowSwingWorker
		extends SwingWorker< T, Void >
	{

		@Override
		final protected void done()
		{
			try
			{
				functionalWindowDone( get() );
			}
			catch( ExecutionException e )
			{
				functionalWindow.getErrorLabel().setText( e.getCause().getMessage() );
				functionalWindow.getErrorLabel().setVisible( true );
			}
			catch( Exception e )
			{
				functionalWindow.getErrorLabel().setText( e.getMessage() );
				functionalWindow.getErrorLabel().setVisible( true );
			}
		}
	}

	/**
	 * @return the window
	 */
	public WindowBase getFunctionalWindow() {
	
		return functionalWindow;
	}
}
