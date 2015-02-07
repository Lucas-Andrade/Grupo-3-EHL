package functionalcomponents.functionalmainwindow;


import AirCorridor;
import Airship;
import User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import functionalcomponents.FunctionalWindowSwingWorker;
import functionalcomponents.functionalairshipwindows.FunctionalGetAirshipsWithLessPassengerThanWindow;
import functionalcomponents.functionalairshipwindows.FunctionalGetGeographicalCoordinatesParametersWindow;
import functionalcomponents.functionalairshipwindows.FunctionalPostAirshipWindow;
import main.java.Database;
import main.java.domain.commands.getcommands.GetAllElementsInADatabaseCommand;
import main.java.domain.commands.getcommands.GetAllTransgressingAirshipsCommand;
import main.java.gui.design.panels.mainwindowpanels.JBodyPanelForMainWindow;
import main.java.gui.design.panels.mainwindowpanels.JFooterPanelForMainWindow;
import main.java.gui.design.windows.MainWindow;
import main.java.gui.design.windows.airshipwindows.GetAirshipsWithLessPassengerThanWindow;
import main.java.gui.design.windows.airshipwindows.GetGeographicalCoordinatesParametersWindow;
import main.java.gui.design.windows.airshipwindows.PostAirshipsWindow;
import main.java.gui.design.windows.popupwindows.UnderConstrutionWindow;


/**
 * Class whose instances have the purpose of adding functionality to a
 * {@link JFooterPanelForMainWindow}. Giving functionality to a panel means adding an
 * {@link ActionListener} to all its buttons.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class FunctionalFooterPanel {
    
    // Fields
    
    /**
     * {@code footerPanel} - The {@link MainWindow} footer panel to which we will had functionality.
     */
    private JFooterPanelForMainWindow footerPanel;
    
    /**
     * {@code bodyPanel} - The {@link MainWindow} body panel that will be updated as part of the
     * actions performed by any of the buttons bellonging to the {@link #footerPanel}.
     */
    private JBodyPanelForMainWindow bodyPanel;
    
    /**
     * {@code airshipsDatabase} - The airships database.
     */
    private Database< Airship > airshipsDatabase;
    
    /**
     * {@code user} - The user who is currently logged in.
     */
    private User user;
    
    /**
     * {@code errorTextArea} - The text area where the error messages will be displayed.
     */
    private JTextArea errorTextArea;
    
    // Constructor
    
    /**
     * Public constructor that will add functionality to a given non functional
     * {@link JFooterPanelForMainWindow}.
     * 
     * @param footerPanel
     *            - The footer panel to which we will had functionality.
     * @param bodyPanel
     *            - The body panel that will be updated as part of the actions performed by any of
     *            the buttons bellonging to the {@link #footerPanel}. This panel as to be from the
     *            same {@code MainWindow} as the given {@code footerPanel}.
     * @param airshipsDatabase
     *            - The airships database.
     * @param user
     *            - The user who is currently logged in.
     * @param errorTextArea
     *            - The text area where the error messages will be displayed.
     */
    public FunctionalFooterPanel( JFooterPanelForMainWindow footerPanel,
                                  JBodyPanelForMainWindow bodyPanel,
                                  Database< Airship > airshipsDatabase, User user,
                                  JTextArea errorTextArea ) {
    
        this.footerPanel = footerPanel;
        this.bodyPanel = bodyPanel;
        
        this.airshipsDatabase = airshipsDatabase;
        this.user = user;
        this.errorTextArea = errorTextArea;
        
        addGetAllAirshipsButtonAction();
        addGetNearestAirshipsButtonAction();
        addGetTransgressingAirshipsButtonAction();
        addGetAirshipsWithLessPassengerThanButtonAction();
        addPatchAirshipButtonAction();
        addPostAirshipButtonAction();
        addDeleteAirshipButtonAction();
    }
    
    // Private Auxiliar Methods
    
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#showAllAirships
     * showAllAirships} button.
     * 
     * The given action will be to get all the elements from a given airships database using the
     * {@link GetAllElementsInADatabaseCommand} and then update the given {@link #bodyPanel} with
     * the information regarding the obtained airships.
     * 
     * This is done due to the {@code Override} of the
     * {@link FunctionalWindowSwingWorker#functionalDone(Iterable) functionalDone(Iterable)} method
     * existing in the {@link FunctionalGetWindowSwingWorker} class.
     */
    private void addGetAllAirshipsButtonAction() {
    
        footerPanel.getShowAllAirships().addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent e ) {
            
                new FunctionalGetWindowSwingWorker( airshipsDatabase, bodyPanel, errorTextArea ) {
                    
                    @Override
                    protected Iterable< Airship > doInBackground() throws Exception {
                    
                        return new GetAllElementsInADatabaseCommand< Airship >( airshipsDatabase ).call()
                                                                                                  .get();
                    }
                    
                }.run();
            }
        } );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#getNearestAirships getNearestAirships} button.
     * 
     * The given action will be to create a new
     * {@link FunctionalGetGeographicalCoordinatesParametersWindow} with the objective of getting
     * all the airships from a given airships database that are closer to a certain geographical
     * position and then update the given {@link #bodyPanel} with the information regarding the
     * obtained airships.
     */
    private void addGetNearestAirshipsButtonAction() {
    
        footerPanel.getNearestAirships()
                   .addActionListener( action -> new FunctionalGetGeographicalCoordinatesParametersWindow(
                                                                                                           new GetGeographicalCoordinatesParametersWindow(),
                                                                                                           airshipsDatabase,
                                                                                                           bodyPanel ) );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#getTransgressingAirships getTransgressingAirships} button.
     * 
     * The given action will be to get all the elements from a given airships database that are
     * transgressing their {@link AirCorridor} using the {@link GetAllTransgressingAirshipsCommand}
     * and then update the given {@link #bodyPanel} with the information regarding the obtained
     * airships.
     * 
     * This is done due to the {@code Override} of the
     * {@link FunctionalWindowSwingWorker#functionalDone(Iterable) functionalDone(Iterable)} method
     * existing in the {@link FunctionalGetWindowSwingWorker} class.
     */
    private void addGetTransgressingAirshipsButtonAction() {
    
        footerPanel.getTransgressingAirships().addActionListener( new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent e ) {
            
                new FunctionalGetWindowSwingWorker( airshipsDatabase, bodyPanel, errorTextArea ) {
                    
                    @Override
                    protected Iterable< Airship > doInBackground() throws Exception {
                    
                        return new GetAllTransgressingAirshipsCommand( airshipsDatabase ).call()
                                                                                         .get();
                    }
                }.run();
            }
        } );
    }
    
    /**
     * Method that will add functionality to the
     * {@link JFooterPanelForMainWindow#getAirshipsWithLessPassengerThan
     * getAirshipsWithLessPassengerThan} button.
     * 
     * The given action will be to create a new
     * {@link FunctionalGetAirshipsWithLessPassengerThanWindow} with the objective of getting all
     * the airships from a given airships database that have less than a certain number of
     * passengers and then update the given {@link #bodyPanel} with the information regarding the
     * obtained airships.
     */
    private void addGetAirshipsWithLessPassengerThanButtonAction() {
    
        footerPanel.getAirshipsWithLessPassengerThan()
                   .addActionListener( action -> new FunctionalGetAirshipsWithLessPassengerThanWindow(
                                                                                                       new GetAirshipsWithLessPassengerThanWindow(),
                                                                                                       airshipsDatabase,
                                                                                                       bodyPanel ) );
    }
    
    /**
     * PATCH -> Not Implemented!
     */
    private void addPatchAirshipButtonAction() {
    
        footerPanel.getPatchAirship().addActionListener( action -> new UnderConstrutionWindow() );
    }
    
    /**
     * Method that will add functionality to the {@link JFooterPanelForMainWindow#postAirship
     * postAirship} button.
     * 
     * The given action will be to create a new {@link FunctionalPostAirshipWindow} with the
     * objective of posting new {@link Airship} in the given database.
     */
    private void addPostAirshipButtonAction() {
    
        footerPanel.getPostAirship()
                   .addActionListener( action -> new FunctionalPostAirshipWindow(
                                                                                  new PostAirshipsWindow(),
                                                                                  airshipsDatabase,
                                                                                  user ) );
    }
    
    /**
     * DELETE AIRSHIP -> Not Implemented!
     */
    private void addDeleteAirshipButtonAction() {
    
        footerPanel.getDeleteAirship().addActionListener( action -> new UnderConstrutionWindow() );
    }
    
    // Public Get Method
    
    /**
     * @return {@code footerPanel}.
     */
    public JFooterPanelForMainWindow getFooterPanel() {
    
        return footerPanel;
    }
}
