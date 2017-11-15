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

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class LoginController implements Initializable
{

    @FXML
    private Button loginButton;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text warningMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    

    @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException
    {
        DatabaseConnector db1 = new DatabaseConnector();
        if(db1.findIdMail(emailField.getText()) == 0)
        {
            warningMessage.setText("Kayıtlı değilsiniz. kaydolun");
        }
        
        else if(!(db1.getUser(db1.findIdMail(emailField.getText())).password.equals(passwordField.getText())))
        {
            warningMessage.setText("Your password is invalid");
        }
        
        else
        {
            warningMessage.setText("Login successful");
            Main.setLoginStatus(true);
            Main.setCurrentUser(db1.getUser(db1.findIdMail(emailField.getText())));
           
            Parent root = FXMLLoader.load(getClass().getResource("/view/Opening.fxml")); //lands seçili olmalı
            Main.getMainStage().setScene(new Scene(root));
            
        }
    }
    
}
