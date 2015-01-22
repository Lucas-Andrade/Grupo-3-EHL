package main.java.gui.ByGD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class FunctionalWindow {
	
	// Fields
	
	private WindowBase functionalWindow;
	
	// Constructor
	
	public FunctionalWindow(WindowBase nonFunctionalWindow) {
	
		this.functionalWindow = nonFunctionalWindow;
		
		createFunctionalWindow();
		
		functionalWindow.getAbstractDialogWindow().setVisible(true);
	}
	
	// Private Auxiliar Methods
	
	private void createFunctionalWindow() {
	
		addRightButtonAction();
		addLeftButtonAction();
		
	}
	
	private void addRightButtonAction() {
	
		functionalWindow.getButtonsPanel().getRightButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent click) {
			
				functionalWindow.getAbstractDialogWindow().dispose();
			}
		});
	}
	
	private void addLeftButtonAction() {
	
		this.getFunctionalWindow().getButtonsPanel().getLeftButton()
			.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent click) {
				
					try {
						functionalWindow.getErrorLabel().setVisible(false);
						leftButtonAction();
						
					} catch (Exception e) {
						functionalWindow.getErrorLabel().setText(e.getMessage());
						functionalWindow.getErrorLabel().setVisible(true);
					}
					
				}
			});
	}
	
	// Protected Abstract Method
	
	protected abstract void leftButtonAction() throws Exception;
	
	// Public Get Methods
	
	public WindowBase getFunctionalWindow() {
	
		return functionalWindow;
	}
}