package main.java.gui.functionalWindows.functionalMainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.jPanels.forMainWindow.JBodyPanelForMainWindow;
import main.java.gui.designWindows.jPanels.forMainWindow.JFooterPanelForMainWindow;
import main.java.gui.designWindows.windows.airshipWindows.PostAirshipsWindow;
import main.java.gui.designWindows.windows.popupWindows.UnderConstrutionWindow;
import main.java.gui.functionalWindows.functionalAirshipWindows.FunctionalPostAirshipWindow;
import main.java.utils.exceptions.InternalErrorException;

public class FunctionalFooterPanel {
	
	// Fields
	
	private JFooterPanelForMainWindow footerPanel;
	private JBodyPanelForMainWindow bodyPanel;
	
	private Database<Airship> airshipsDatabase;
	
	private User user;
	
	// Constructor
	
	public FunctionalFooterPanel(JFooterPanelForMainWindow footerPanel,
		JBodyPanelForMainWindow bodyPanel, Database<Airship> airshipsDatabase, User user) {
	
		this.footerPanel = footerPanel;
		this.bodyPanel = bodyPanel;
		
		this.airshipsDatabase = airshipsDatabase;
		this.user = user;
		
//		addGetAllAirshipsButtonAction();
		addGetNearestAirshipsButtonAction();
		addGetTransgressingAirshipsButtonAction();
		addGetAirshipsWithLessPassengerThanButtonAction();
		addPatchAirshipButtonAction();
		addPostAirshipButtonAction();
		addDeleteAirshipButtonAction();
	}
	
	// Private Auxiliar Methods
	
//	private void addGetAllAirshipsButtonAction() {
//	
//		footerPanel.getShowAllAirships().addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent click) {
//			
//				try {
//					updateBodyPanel(airshipsDatabase, new GetAllElementsInADatabaseCommand<Airship>(
//						airshipsDatabase).call().get());
//					
//				} catch (Exception exception) {
//					throw new InternalErrorException(exception);
//				}
//			}
//		});
//	}
	
	private void addGetNearestAirshipsButtonAction() {
	
		footerPanel.getNearestAirships().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new UnderConstrutionWindow();
			}
		});
	}
	
	private void addGetTransgressingAirshipsButtonAction() {
	
		footerPanel.getTransgressingAirships().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new UnderConstrutionWindow();
			}
		});
	}
	
	private void addGetAirshipsWithLessPassengerThanButtonAction() {
	
		footerPanel.getAirshipsWithLessPassengerThan().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new UnderConstrutionWindow();
			}
		});
	}
	
	private void addPatchAirshipButtonAction() {
	
		footerPanel.getPatchAirship().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new UnderConstrutionWindow();
			}
		});
	}
	
	private void addPostAirshipButtonAction() {
	
		footerPanel.getPostAirship().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new FunctionalPostAirshipWindow(new PostAirshipsWindow(), airshipsDatabase, user);
			}
		});
	}
	
	private void addDeleteAirshipButtonAction() {
	
		footerPanel.getDeleteAirship().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new UnderConstrutionWindow();
			}
		});
	}
	
//	private void updateBodyPanel(Database<Airship> airshipsDatabase, Iterable<Airship> airshipsFound) {
//	
//		bodyPanel.setAirshipsScrollPane(airshipsDatabase, airshipsFound);
//		bodyPanel.setWorldMapWithAirships(airshipsFound);
//		bodyPanel.repaint();
//	}
	
	// Public Get Methods
	
	public JFooterPanelForMainWindow getFooterPanel() {
	
		return footerPanel;
	}
}
