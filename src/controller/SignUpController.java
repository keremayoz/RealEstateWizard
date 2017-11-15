/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import realestatewizard.DatabaseConnector;
import realestatewizard.RealEstate;
import realestatewizard.User;

/**
 * FXML Controller class for Sign Up Scene
 *
 * @author Emre Sülün
 */
public class SignUpController
{

    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField confirmEmailField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextArea detailsField;
    @FXML
    private CheckBox signUpCheckBox;
    @FXML
    private Text warningMessage;
    @FXML
    private ComboBox cityList;

    DatabaseConnector dc1 = new DatabaseConnector();
    
    
    
    public void cityListClicked()
    {
        cityList.getItems().addAll("Şehir1", "Şehir2");
    }
    
    /**
     * Events when sign up button is clicked
     */
    public void signUpClicked()
    {
        if(dc1.findIdMail(emailField.getText()) == 0)
        {
            if(emailField.getText().equals(confirmEmailField.getText()))
            {
                if(passwordField.getText().equals(confirmPasswordField.getText()))
                {
                    signUpCheckBox.setIndeterminate(false);
                    if(signUpCheckBox.isSelected())
                    {
                        dc1.addUser(new User(nameField.getText(), 
                                surnameField.getText(), 
                                passwordField.getText(), 
                                emailField.getText(), 
                                detailsField.getText(), 
                                phoneField.getText(), 
                                new ArrayList<RealEstate>(), 
                                new ArrayList<RealEstate>()));
                        warningMessage.setText("Signup successful");
                    }
                    else
                        warningMessage.setText("You have to accept to continue");
                }
                else
                    warningMessage.setText("Passwords don't match");
            }
            else
                warningMessage.setText("Emails don't match");          
        }
        else
            warningMessage.setText("This email already exists");       
    }  
}
