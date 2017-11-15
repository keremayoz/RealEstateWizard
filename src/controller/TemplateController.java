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
import model.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class TemplateController implements Initializable
{
    @FXML private Text userNameText;
    @FXML private Button loginButton;
    @FXML private Button signUpButton;
    @FXML private HBox header;
    @FXML
    private Button userButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(Main.isLoginStatus())
        {
            header.getChildren().remove(loginButton);
            header.getChildren().remove(signUpButton);
            userButton.setText(Main.getCurrentUser().name);
        }
        else
        {
            header.getChildren().remove(userButton);
        }
    }
    
    @FXML public void homePageClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Opening.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
    public void housesClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AfterSearch.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
    public void officesClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AfterSearch.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
    public void landsClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AfterSearch.fxml")); //lands seçili olmalı
        Main.getMainStage().setScene(new Scene(root));
    }
    
    @FXML
    public void detailedSearchClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AfterSearch.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
    @FXML
    public void eventFSClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/EventFocusedSearch.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
    @FXML
    public void putAdClicked() throws IOException
    {
        if(Main.isLoginStatus())
        {
            Parent root = FXMLLoader.load(getClass().getResource("/view/PutAd.fxml"));
            Main.getMainStage().setScene(new Scene(root));
        }
        
        else
        {
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(Main.getMainStage());
            alert.setTitle("Warning");
            alert.setHeaderText("You cannot put an ad before login!");

            alert.showAndWait();
        }
        
    }
    
    @FXML
    public void userNameClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MyRewAccount.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
    
    @FXML
    public void loginClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
    @FXML
    public void signUpClicked() throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SignUp.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }

//    public static Button getLoginButton()
//    {
//        return loginButton;
//    }
//
//    public static void setLoginButton(Button loginButton)
//    {
//        TemplateController.loginButton = loginButton;
//    }
//
//    public static HBox getHeader()
//    {
//        return header;
//    }


    
}
