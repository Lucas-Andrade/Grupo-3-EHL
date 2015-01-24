package main.java.gui.fromDG_to_P.windows.popup;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import main.java.gui.fromDG_to_P.panelsandutils.GridBagUtils;

@SuppressWarnings ("serial")
public abstract class PopUpWindow extends JDialog {
	
	// Fields
	
	private GridBagConstraints constraints;
	
	// Constructor
	
	public PopUpWindow(String message, Color color) {
	
		setWindowAspect();
		
		createAndAddComponents(message, color);
	}
	
	// Private Auxiliar Methods
	
	private void setWindowAspect() {
	
		this.setSize(300, 200);
		this.getContentPane().setBackground(new Color(65, 72, 78));
		this.getContentPane().setLayout(new GridBagLayout());
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
			"src/main/resources/images/Air-icon.png"));
		
		// this.setLocationRelativeTo(null);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	private void createAndAddComponents(String message, Color color) {
	
		constraints = GridBagUtils.createGridBagConstraints();
		
		createAndAddSpecificComponents();
		
		JLabel successLabel = new JLabel(message);
		successLabel.setForeground(color);
		
		JButton okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				dispose();
			}
		});
		
		this.getContentPane().add(successLabel,
			GridBagUtils.updateGridYBagConstraints(constraints, 1));
		
		this.getContentPane().add(okButton, GridBagUtils.updateGridYBagConstraints(constraints, 2));
		
		this.setVisible(true);
	}
	
	// Protected Abstract Method To Be Implemented
	
	protected abstract void createAndAddSpecificComponents();
	
	// Public Get Methods
	
	public GridBagConstraints getConstraints() {
	
		return constraints;
	}
}