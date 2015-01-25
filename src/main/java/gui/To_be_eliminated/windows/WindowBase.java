package main.java.gui.To_be_eliminated.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.concurrent.Callable;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;

import main.java.gui.To_be_eliminated.panelsandutils.ButtonsPanel;
import main.java.gui.To_be_eliminated.panelsandutils.GridBagUtils;
import main.java.gui.designWindows.JPanels.JPanelImage;

/**
 * 
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings ("serial")
public abstract class WindowBase extends JDialog {
	
	// Fields
	
	/**
	 * Panel with two buttons, the first have the point to {@code call} a {@link Callable}, and the
	 * second to dispose this {@code JDialog}.
	 */
	private ButtonsPanel buttonsPanel;
	/**
	 * Label where a exception message will be printed.
	 */
	private JLabel errorLabel;
	
	private GridBagConstraints constraints;
	
	// Constructor
	
	/**
	 * Create a {@code JDialog} with two buttons and a error label
	 * 
	 * @param width
	 * @param height
	 * @param string
	 */
	public WindowBase(int width, int height, String primaryImagePath) {
	
		setDefaultWindow(width, height);
		
		constraints = GridBagUtils.createGridBagConstraints();
		
		if (primaryImagePath != null)
			add(new JPanelImage(primaryImagePath), constraints);
		
		createAndAddSpecificComponents();
		
		createAndAddComponents();
		
		getRootPane().setDefaultButton(buttonsPanel.getLeftButton());
		
		this.setVisible(true);
	}
	
	// Private methods
	private void setDefaultWindow(int width, int height) {
	
		this.getContentPane().setLayout(new GridBagLayout());
		
		this.setSize(width, height);
		this.getContentPane().setBackground(new Color(65, 72, 78));
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
			"src/main/resources/images/Air-icon.png"));
		
		this.setTitle("Air Traffic Controll");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	// Private Auxiliar Method
	
	private void createAndAddComponents() {
	
		buttonsPanel = new ButtonsPanel();
		
		errorLabel = new JLabel(" ");
		errorLabel.setForeground(Color.RED);
		
		addComponent(buttonsPanel, GridBagConstraints.BASELINE_TRAILING);
		addComponent(errorLabel, GridBagConstraints.BELOW_BASELINE);
	}
	
	// Protected Abstract Method To Be Implemented
	
	protected abstract void createAndAddSpecificComponents();
	
	// Protected Auxiliar Method
	
	/**
	 * Add a JComponent to the line {@code y}
	 * 
	 * @param component
	 * @param constraints
	 */
	protected void addComponent(JComponent component, int y) {
	
		constraints.gridy = y;
		getContentPane().add(component, constraints);
	}
	
	// Public Get Methods
	
	/**
	 * @return the buttonsPanel
	 */
	public ButtonsPanel getButtonsPanel() {
	
		return buttonsPanel;
	}
	
	/**
	 * @return errorLabel
	 */
	public JLabel getErrorLabel() {
	
		return errorLabel;
	}
}
