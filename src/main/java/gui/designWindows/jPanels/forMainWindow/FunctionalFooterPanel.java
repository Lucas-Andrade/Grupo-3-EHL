package main.java.gui.designWindows.jPanels.forMainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.windows.airshipWindows.PostAirshipsWindow;
import main.java.gui.designWindows.windows.popupWindows.UnderConstrutionWindow;
import main.java.gui.functionalWindows.functionalAirshipWindows.FunctionalPostAirshipWindow;

public class FunctionalFooterPanel {
	
	// Fields
	
	private JFooterPanelForMainWindow footerPanel;
	
	private Database<Airship> airshipsDatabase;
	
	private User user;
	
	// Constructor
	
	public FunctionalFooterPanel(JFooterPanelForMainWindow footerPanel,
		Database<Airship> airshipsDatabase, User user) {
	
		this.footerPanel = footerPanel;
		this.airshipsDatabase = airshipsDatabase;
		this.user = user;
		
		addGetNearestAirshipsButtonAction();
		addGetTransgressingAirshipsButtonAction();
		addGetAirshipsWithLessPassengerThanButtonAction();
		addPatchAirshipButtonAction();
		addPostAirshipButtonAction();
		addDeleteAirshipButtonAction();
	}
	
	// Private Auxiliar Methods
	
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

	
	public JFooterPanelForMainWindow getFooterPanel() {
	
		return footerPanel;
	}
}