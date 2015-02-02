package main.java.gui.functionalcomponents.functionalairshipwindows;


import main.java.domain.commands.postcommands.PostCivilAirshipCommand;
import main.java.domain.commands.postcommands.PostMilitaryAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.gui.design.windows.airshipwindows.PostAirshipsWindow;
import main.java.gui.design.windows.popupwindows.SuccessWindow;
import main.java.gui.functionalcomponents.FunctionalWindow;
import main.java.gui.functionalcomponents.FunctionalWindowSwingWorker;


public class FunctionalPostAirshipWindow extends FunctionalWindow< String > {
    
    private Database< Airship > airshipsDatabase;
    private User userWhoIsPosting;
    
    private PostAirshipsWindow functionalWindow;
    
    public FunctionalPostAirshipWindow( PostAirshipsWindow nonFunctionalWindow,
                                        Database< Airship > airshipsDatabase, User userWhoIsPosting ) {
        super( nonFunctionalWindow );
        
        this.functionalWindow = nonFunctionalWindow;
        
        this.userWhoIsPosting = userWhoIsPosting;
        this.airshipsDatabase = airshipsDatabase;
    }
    
    @Override
    protected FunctionalWindowSwingWorker< String > getSwingWorker() {
        return new FunctionalWindowSwingWorker< String >( functionalWindow.getErrorLabel() ) {
            
            String latitude;
            String longitude;
            String altitude;
            
            String minAltitude;
            String maxAltitude;
            
            String specificComponent;
            
            
            
            @Override
            protected String doInBackground() throws Exception {
                if( functionalWindow.getTypeAirshipTabbedPane().getSelectedIndex() == 0 ) {
                    latitude =
                            functionalWindow.getCivilAirshipCommonPainel().getGeoCoodinates()
                                            .getLatitude().getJTextField().getText();
                    longitude =
                            functionalWindow.getCivilAirshipCommonPainel().getGeoCoodinates()
                                            .getLongitude().getJTextField().getText();
                    altitude =
                            functionalWindow.getCivilAirshipCommonPainel().getGeoCoodinates()
                                            .getAltitude().getJTextField().getText();
                    
                    minAltitude =
                            functionalWindow.getCivilAirshipCommonPainel().getAirCorridor()
                                            .getMinAltitude().getJTextField().getText();
                    maxAltitude =
                            functionalWindow.getCivilAirshipCommonPainel().getAirCorridor()
                                            .getMaxAltitude().getJTextField().getText();
                    
                    specificComponent =
                            functionalWindow.getSpecificCivilPanel().getNumberPassengerJTextField()
                                            .getText();
                    
                    double latitude = Double.parseDouble( this.latitude );
                    double longitude = Double.parseDouble( this.longitude );
                    double altitude = Double.parseDouble( this.altitude );
                    
                    double minAltitude = Double.parseDouble( this.minAltitude );
                    double maxAltitude = Double.parseDouble( this.maxAltitude );
                    
                    int specificComponent = (int)Double.parseDouble( this.specificComponent );
                    
                    return new PostCivilAirshipCommand( latitude, longitude, altitude, maxAltitude,
                                                        minAltitude, specificComponent,
                                                        airshipsDatabase, userWhoIsPosting ).call();
                }
                else {
                    latitude =
                            functionalWindow.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                                            .getLatitude().getJTextField().getText();
                    longitude =
                            functionalWindow.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                                            .getLongitude().getJTextField().getText();
                    altitude =
                            functionalWindow.getMilitaryAirshipCommonPainel().getGeoCoodinates()
                                            .getAltitude().getJTextField().getText();
                    
                    minAltitude =
                            functionalWindow.getMilitaryAirshipCommonPainel().getAirCorridor()
                                            .getMinAltitude().getJTextField().getText();
                    maxAltitude =
                            functionalWindow.getMilitaryAirshipCommonPainel().getAirCorridor()
                                            .getMaxAltitude().getJTextField().getText();
                    
                    specificComponent =
                            functionalWindow.getSpecificMilitaryPanel().getGroupButtons()
                                            .getSelection().getActionCommand();
                    
                    double latitude = Double.parseDouble( this.latitude );
                    double longitude = Double.parseDouble( this.longitude );
                    double altitude = Double.parseDouble( this.altitude );
                    
                    double minAltitude = Double.parseDouble( this.minAltitude );
                    double maxAltitude = Double.parseDouble( this.maxAltitude );
                    
                    boolean specificComponent = Boolean.parseBoolean( this.specificComponent );
                    
                    return new PostMilitaryAirshipCommand( latitude, longitude, altitude,
                                                           maxAltitude, minAltitude,
                                                           specificComponent, airshipsDatabase,
                                                           userWhoIsPosting ).call();
                }
            }
            
            @Override
            public void functionalDone( String resultOfDoInBackGround ) {
                new SuccessWindow( "Airship Successfully Posted -> " + resultOfDoInBackGround );
                functionalWindow.dispose();
            }
            
        };
    }
}
