/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Main;
import realestatewizard.DatabaseConnector;
import realestatewizard.User;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class MyRewAccountController implements Initializable
{

    @FXML
    private Button saveButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Text warningMessage;
    
    private User currentUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        currentUser = Main.getCurrentUser();
        // TODO
    }    

    @FXML
    private void saveButtonClicked(ActionEvent event)
    {
        DatabaseConnector db1 = new DatabaseConnector();
        if(passwordField.getText().equals(confirmPasswordField.getText()))
        {
            User updatedUser = new User(nameField.getText(), surnameField.getText(), passwordField.getText(), currentUser.email, currentUser.details, phoneField.getText(), currentUser.estates, currentUser.favourites);
            db1.updateUser(currentUser, updatedUser);
            warningMessage.setText("Updated succesfully!");
            Main.setCurrentUser(updatedUser);
        }
        
        else
            warningMessage.setText("Passwords don't match!");
                    
    }

    @FXML
    private void logOutButtonClicked(ActionEvent event) throws IOException
    {
        Main.setLoginStatus(false);
        Main.setCurrentUser(null);
        
        Parent root = FXMLLoader.load(getClass().getResource("/view/Opening.fxml"));
        Main.getMainStage().setScene(new Scene(root));
    }
    
}
