package main.java.gui.functionalWindows.functionalAirshipWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.designWindows.jPanels.forMainWindow.JFooterPanelForMainWindow;
import main.java.gui.designWindows.windows.airshipWindows.PostAirshipsWindow;
import main.java.gui.designWindows.windows.popupWindows.JUnderConstrutionPanel;

public class FunctionalJFooterPanelForMainWindow {
	
	// Fields
	
	private JFooterPanelForMainWindow footerPanel;
	
	private Database<Airship> airshipsDatabase;
	
	private User user;
	
	// Constructor
	
	public FunctionalJFooterPanelForMainWindow(JFooterPanelForMainWindow footerPanel,
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
			
				new JUnderConstrutionPanel();
			}
		});
	}
	
	private void addGetTransgressingAirshipsButtonAction() {
	
		footerPanel.getTransgressingAirships().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new JUnderConstrutionPanel();
			}
		});
	}
	
	private void addGetAirshipsWithLessPassengerThanButtonAction() {
	
		footerPanel.getAirshipsWithLessPassengerThan().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new JUnderConstrutionPanel();
			}
		});
	}
	
	private void addPatchAirshipButtonAction() {
	
		footerPanel.getPatchAirship().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				new JUnderConstrutionPanel();
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
			
				new JUnderConstrutionPanel();
			}
		});
	}
}