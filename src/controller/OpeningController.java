/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import model.Main;
import realestatewizard.DatabaseConnector;
import realestatewizard.Wizard;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class OpeningController implements Initializable
{
    @FXML private Button searchButton;
    @FXML private TextField searchField;
    @FXML private WebView deneme;

    public void searchButtonClicked() throws IOException
    {
        Wizard w1 = new Wizard();
        DatabaseConnector db1 = new DatabaseConnector();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AfterSearch.fxml"));
        Parent root = loader.load();
        AfterSearchController asc1 = loader.getController();
        asc1.showListToAfterSearchScene(w1.search(db1.getAllEstates(), searchField.getText()));
        Main.getMainStage().setScene(new Scene(root));
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

//    public void denemeClicked()
//    {
//        WebEngine webEngine = deneme.getEngine();
//        webEngine.load("file:///E:/AllAboutJava/projects/REW/REW/src/images/html.html");
//        deneme.setContextMenuEnabled(true);
//    }
}
