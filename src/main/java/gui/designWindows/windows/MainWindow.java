package main.java.gui.designWindows.windows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.GridBagUtils;
import main.java.gui.designWindows.jPanels.forMainWindow.JBodyPanelForMainWindow;
import main.java.gui.designWindows.jPanels.forMainWindow.JFooterPanelForMainWindow;
import main.java.gui.designWindows.jPanels.forMainWindow.JHeaderForMainWindowPanel;
import main.java.gui.designWindows.windows.userWindows.LogInWindow;
import main.java.gui.functionalWindows.functionalUserWindows.FunctionalLoginWindow;


@SuppressWarnings("serial")
public class MainWindow extends JFrame {


	private GridBagConstraints constraints = GridBagUtils.createGridBagConstraints();
	
	private User user;

	public MainWindow(Database<Airship> airshipsDatabase, Database<User> usersDatabase, User user) {
		
		this.user = user;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/images/radar.png"));
		this.setTitle("Air Traffic Control");
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(65,72,78));
		getContentPane().add(contentPane);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gridBagLayout);
		
		
		JHeaderForMainWindowPanel headerPanel = new JHeaderForMainWindowPanel(usersDatabase, user);
		headerPanel.getUserPanel().getLogoutButton().addActionListener( new ActionListener()
		{
			
			@Override
			public void actionPerformed( ActionEvent e )
			{
				dispose();
				new FunctionalLoginWindow(new LogInWindow(), usersDatabase, airshipsDatabase);
			}
		} );
		contentPane.add(headerPanel,GridBagUtils.updateGridBagConstraints(constraints, 0));
		
		headerPanel.getUserPanel().getTurnOffButton().addActionListener( new ActionListener()
		{
			
			@Override
			public void actionPerformed( ActionEvent e )
			{
				dispose();
			}
		} );
		contentPane.add(headerPanel,GridBagUtils.updateGridBagConstraints(constraints, 0));
		
		
		

//TODO
//		JSeparator line0 = new JSeparator(SwingConstants.HORIZONTAL);
//		line0.setForeground(Color.WHITE);
//		line0.setPreferredSize(new Dimension(0,1));	
//		contentPane.add(line0,GridBagUtils.updateGridBagConstraints(constraints, 1));
				
		JBodyPanelForMainWindow bodyPanel = new JBodyPanelForMainWindow(airshipsDatabase);	
		contentPane.add(bodyPanel,GridBagUtils.updateGridBagConstraints(constraints, 2));
		
		JFooterPanelForMainWindow footerPanel = new JFooterPanelForMainWindow();
		contentPane.add(footerPanel,GridBagUtils.updateGridBagConstraints(constraints, 3));
		
//		JLabel errorLabel = new JLabel("ERROROROROROROROOROR");
//		errorLabel.setForeground(Color.RED);
//		errorLabel.setFont(errorLabel.getFont().deriveFont(20f));
//		errorLabel.setVisible(true);		
//		contentPane.add(errorLabel,GridBagUtils.updateGridBagConstraints(constraints, 4));
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	


	public User getUser() {
		return user;
	}
}
