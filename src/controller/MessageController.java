/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Main;
import realestatewizard.Conversation;
import realestatewizard.DatabaseConnector;
import realestatewizard.User;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class MessageController implements Initializable
{
    @FXML
    private ListView<String> conversationList;
    @FXML
    private TextArea messageArea;
    @FXML
    private TextField messageField;
    @FXML
    private Button sendButton;
    
    User currentUser;
    DatabaseConnector db1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
         db1 = new DatabaseConnector();
        currentUser = Main.getCurrentUser();
        for(int i=0; i<currentUser.withWho().size(); i++)
        {
            conversationList.getItems().add(currentUser.withWho().get(i));
        }
        
    }

    @FXML
    private void sendButtonClicked(ActionEvent event)
    {
        String otherPersonEmail = conversationList.getSelectionModel().getSelectedItem();
        String sendingMessage = messageField.getText();
        
        messageArea.appendText(currentUser.sendMessageTo(db1.getUser(db1.findIdMail(otherPersonEmail)), sendingMessage).toString());      
        messageField.clear();
    }
    
    @FXML
    public void showMessagesClicked()
    {
        messageArea.clear();
        String otherPersonEmail = conversationList.getSelectionModel().getSelectedItem();
        Conversation chatWithOtherPerson = currentUser.getConversationsWith(db1.getUser(db1.findIdMail(otherPersonEmail)));
        messageArea.appendText(chatWithOtherPerson.displayConversation());
          
    }
}
