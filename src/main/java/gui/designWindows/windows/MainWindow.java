package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.jPanels.forMainWindow.JBodyPanelForMainWindow;
import main.java.gui.designWindows.jPanels.forMainWindow.JFooterPanelForMainWindow;
import main.java.gui.designWindows.jPanels.forMainWindow.JHeaderForMainWindowPanel;


@SuppressWarnings ("serial")
public class MainWindow extends JFrame {
	
	// Fields
	
	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	
	private JHeaderForMainWindowPanel headerPanel;

	private JBodyPanelForMainWindow bodyPanel;

	private JFooterPanelForMainWindow footerPanel;
	
	// Constructor
	
	public MainWindow(Database<Airship> airshipsDatabase, Iterable<Airship> airshipsFound) {
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
			"src/main/resources/images/radar.png"));
		this.setTitle("Air Traffic Control");
		
		JPanel contentPane = new JPanel();

		contentPane.setBackground(new Color(65, 72, 78));
		getContentPane().add(contentPane);
		
		contentPane.setLayout(new GridBagLayout());
		
		headerPanel = new JHeaderForMainWindowPanel();
		
		contentPane.add(headerPanel, constraints);
		
		contentPane.add(headerPanel, constraints);
		
// TODO
// JSeparator line0 = new JSeparator(SwingConstants.HORIZONTAL);
// line0.setForeground(Color.WHITE);
// line0.setPreferredSize(new Dimension(0,1));
// contentPane.add(line0,GridBagUtils.updateGridBagConstraints(constraints, 1));
		
		bodyPanel = new JBodyPanelForMainWindow(airshipsDatabase, airshipsFound);
		contentPane.add(bodyPanel, GridBagUtils.updateGridBagConstraints(constraints, 1));
		
		footerPanel = new JFooterPanelForMainWindow();
		contentPane.add(footerPanel, GridBagUtils.updateGridBagConstraints(constraints, 2));
		
// JLabel errorLabel = new JLabel("ERROROROROROROROOROR");
// errorLabel.setForeground(Color.RED);
// errorLabel.setFont(errorLabel.getFont().deriveFont(20f));
// errorLabel.setVisible(true);
// contentPane.add(errorLabel,GridBagUtils.updateGridBagConstraints(constraints, 3));
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	// Public Set Methods
	
	public void setHeaderPanel(JHeaderForMainWindowPanel headerPanel) {
	
		this.headerPanel = headerPanel;
	}
	
	public void setFooterPanel(JFooterPanelForMainWindow footerPanel) {
	
		this.footerPanel = footerPanel;
	}
	
	public void setBodyPanel(JBodyPanelForMainWindow bodyPanel) {
		
		this.bodyPanel = bodyPanel;
	}

	
	// Public Get Methods
	
	public JHeaderForMainWindowPanel getHeaderPanel() {
	
		return headerPanel;
	}

	public JBodyPanelForMainWindow getBodyPanel()
	{

		return bodyPanel;
	}

	public JFooterPanelForMainWindow getFooterPanel()
	{

		return footerPanel;
	}
}
