package main.java.gui.functionalcomponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingWorker;

import main.java.gui.design.windows.WindowBase;

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
	 * Contract for subclasses to return a {@link SwingWorker} with its {@code doInBackground()}
	 * method.
	 * 
	 * @return a {@link FunctionalWindowSwingWorker}
	 */
	protected abstract FunctionalWindowSwingWorker<T> getSwingWorker();

	/**
	 * @return the window
	 */
	public WindowBase getFunctionalWindow() {
	
		return functionalWindow;
	}
}
