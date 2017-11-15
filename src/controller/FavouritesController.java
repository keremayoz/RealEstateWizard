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
import javafx.scene.layout.GridPane;
import model.Main;
import realestatewizard.DatabaseConnector;
import realestatewizard.User;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class FavouritesController implements Initializable
{

    @FXML
    private GridPane adsList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        DatabaseConnector db1 = new DatabaseConnector();
        User currentUser = Main.getCurrentUser();
        
        AfterSearchController asc1 = new AfterSearchController();
        asc1.showListToGridPane(currentUser.favourites, adsList);
    }    
    
}
