/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import realestatewizard.DatabaseConnector;
import realestatewizard.Wizard;


/**
 * FXML Controller class
 *
 * @author Emre
 */
public class EventFocusedSearchController implements Initializable
{
    @FXML private GridPane listPane;
    @FXML
    private Button barbequeButton;
    @FXML
    private Button birthdayButton;
    @FXML
    private Button businessButton;
    @FXML
    private Button partyButton;
    @FXML
    private Button weddingButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
//        BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/images/barbeque.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
//        Background background = new Background(backgroundImage);
//
//        barbequeButton.setBackground(background);
    }
    
    @FXML
    public void barbequeClicked()
    {
        Wizard w1 = new Wizard();
        DatabaseConnector db1 = new DatabaseConnector();
        AfterSearchController asc1 = new AfterSearchController();
        asc1.showListToGridPane(w1.barbequeEFS(db1.getAllEstates()), listPane);        
    }
    
    @FXML
    public void birthdayClicked()
    {
        Wizard w1 = new Wizard();
        DatabaseConnector db1 = new DatabaseConnector();
        AfterSearchController asc1 = new AfterSearchController();
        asc1.showListToGridPane(w1.birthdayEFS(db1.getAllEstates()), listPane); 
        
    }
    
    @FXML
    public void businessClicked()
    {
        Wizard w1 = new Wizard();
        DatabaseConnector db1 = new DatabaseConnector();
        AfterSearchController asc1 = new AfterSearchController();
        asc1.showListToGridPane(w1.businessEFS(db1.getAllEstates()), listPane); 
    }
    
    @FXML
    public void partyClicked()
    {
        Wizard w1 = new Wizard();
        DatabaseConnector db1 = new DatabaseConnector();
        AfterSearchController asc1 = new AfterSearchController();
        asc1.showListToGridPane(w1.partyEFS(db1.getAllEstates()), listPane); 
    }
    
    @FXML
    public void weddingClicked()
    {
        Wizard w1 = new Wizard();
        DatabaseConnector db1 = new DatabaseConnector();
        AfterSearchController asc1 = new AfterSearchController();
        asc1.showListToGridPane(w1.barbequeEFS(db1.getAllEstates()), listPane); 
    }
}
