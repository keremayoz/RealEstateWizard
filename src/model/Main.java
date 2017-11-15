/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import realestatewizard.RealEstate;
import realestatewizard.User;

/**
 *
 * @author Emre
 */
public class Main extends Application
{
    private static Stage mainStage;
    private static boolean loginStatus;
    private static User currentUser;
    private static RealEstate currentRealEstate;
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        mainStage = primaryStage;
        mainStage.setTitle("Real Estate Wizard");
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/logo.png")));
        
        //Opening scene
        Parent root = FXMLLoader.load(getClass().getResource("/view/Opening.fxml"));
        mainStage.setScene(new Scene (root, 800, 600));
        
        mainStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    public static void setMainStage(Stage mainStage)
    {
        Main.mainStage = mainStage;
    }

    public static Stage getMainStage()
    {
        return mainStage;
    }

    public static boolean isLoginStatus()
    {
        return loginStatus;
    }

    public static void setLoginStatus(boolean loginStatus)
    {
        Main.loginStatus = loginStatus;
    }

    public static User getCurrentUser()
    {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser)
    {
        Main.currentUser = currentUser;
    }
    
    public static RealEstate getCurrentRealEstate()
    {
        return currentRealEstate;
    }
    
    public static void setCurrentRealEstate(RealEstate currentRealEstate)
    {
        Main.currentRealEstate = currentRealEstate;
    }
    
    
}
