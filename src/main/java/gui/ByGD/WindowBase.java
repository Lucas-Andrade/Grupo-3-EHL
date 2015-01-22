package main.java.gui.ByGD;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class WindowBase {
	
	// Fields
	
	private final JDialog abstractDialogWindow;
	private ButtonsPanel buttonsPanel;
	private JLabel errorLabel;
	
	private GridBagConstraints constraints;
	
	// Constructor
	
	public WindowBase(int width, int height) {
	
		abstractDialogWindow = new JDialog();
		setDefaultWindow(width, height);
		
		constraints = GridBagUtils.createGridBagConstraints();
		
		createButtonsAndActions();
		
		createErrorLabel();
		
		addComponent(buttonsPanel, GridBagConstraints.BASELINE_TRAILING);
		
		addComponent(errorLabel, GridBagConstraints.BELOW_BASELINE);
	}
	
	// Private Auxiliar Methods
	
	private void setDefaultWindow(int width, int height) {
	
		abstractDialogWindow.getContentPane().setLayout(new GridBagLayout());
		
		abstractDialogWindow.setSize(width, height);
		abstractDialogWindow.getContentPane().setBackground(new Color(65, 72, 78));
		
		abstractDialogWindow.setIconImage(Toolkit.getDefaultToolkit().getImage(
			"src/ByD/Air-icon.png"));
		
		abstractDialogWindow.setTitle("Air Traffic Controll");
		abstractDialogWindow.setLocationRelativeTo(null);
		abstractDialogWindow.setResizable(false);
		abstractDialogWindow.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private void createButtonsAndActions() {
	
		buttonsPanel = new ButtonsPanel();
		
// buttonsPanel.getLeftButton().addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent click) {
//
// try {
// errorLabel.setVisible(false);
// leftButtonAction();
//
// } catch (Exception e) {
// errorLabel.setText(e.getMessage());
// errorLabel.setVisible(true);
// }
// }
// });
//
// buttonsPanel.getRightButton().addActionListener(new ActionListener() {
//
// @Override
// public void actionPerformed(ActionEvent e) {
//
// abstractDialogWindow.dispose();
// }
// });
	}
	
	private void createErrorLabel() {
	
		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.RED);
	}
	
	// Protected Auxiliar Methods
	
	/**
	 * Add a JComponent to the line {@code y}
	 * 
	 * @param component
	 * @param constraints
	 */
	protected void addComponent(JComponent component, int y) {
	
		constraints.gridy = y;
		abstractDialogWindow.getContentPane().add(component, constraints);
	}
	
	// Public Get Methods
	
	/**
	 * @return the abstractDialogWindow
	 */
	public JDialog getAbstractDialogWindow() {
	
		return abstractDialogWindow;
	}
	
	public ButtonsPanel getButtonsPanel() {
	
		return buttonsPanel;
	}
	
	public JLabel getErrorLabel() {
	
		return errorLabel;
	}
}