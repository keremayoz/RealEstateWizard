/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import model.Main;
import realestatewizard.DatabaseConnector;
import realestatewizard.RealEstate;
import realestatewizard.Wizard;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class AfterSearchController implements Initializable
{

    @FXML
    private TextField minPriceField;
    @FXML
    private TextField maxPriceField;
    @FXML
    private GridPane listGridPane;
    @FXML
    private RadioButton room2;
    @FXML
    private RadioButton room3;
    @FXML
    private RadioButton room4;
    @FXML
    private RadioButton room5;
    @FXML
    private RadioButton roomMore;
    @FXML
    private RadioButton age01;
    @FXML
    private RadioButton age25;
    @FXML
    private RadioButton age610;
    @FXML
    private RadioButton ageMore;
    @FXML
    private RadioButton sale;
    @FXML
    private RadioButton longTerm;
    @FXML
    private RadioButton shortTerm;
    @FXML
    private RadioButton house;
    @FXML
    private RadioButton office;
    @FXML
    private RadioButton land;
    @FXML
    private TitledPane pricePane;
    
    ArrayList<RealEstate> commonList; //ortalık arrayi
    DatabaseConnector db1;
    Wizard w1;
    //object for swapping screen from list to ad
    @FXML
    private ScrollPane parentOfGridPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {   
        db1 = new DatabaseConnector();
        w1 = new Wizard();
        
        showListToGridPane(db1.getAllEstates(), listGridPane);
        commonList = db1.getAllEstates();
        
        ToggleGroup rooms = new ToggleGroup();
        rooms.getToggles().addAll(room2, room3, room4, room5, roomMore);
        
        ToggleGroup status = new ToggleGroup();
        status.getToggles().addAll(sale, longTerm, shortTerm);
        
        ToggleGroup age = new ToggleGroup();
        age.getToggles().addAll(age01, age25, age610, ageMore);
        
        ToggleGroup type = new ToggleGroup();
        type.getToggles().addAll(house, office, land);
    }
    
    @FXML
    private void priceButtonClicked()
    {
        if(sale.isSelected())
            commonList = w1.priceSale(commonList, Integer.parseInt(minPriceField.getText()), Integer.parseInt(maxPriceField.getText()));
        else if(longTerm.isSelected())
            commonList = w1.priceLong(commonList, Integer.parseInt(minPriceField.getText()), Integer.parseInt(maxPriceField.getText()));
        else if(shortTerm.isSelected())
            commonList = w1.priceShort(commonList, Integer.parseInt(minPriceField.getText()), Integer.parseInt(maxPriceField.getText()));
    }

    @FXML
    public void roomsButtonClicked()
    {
        if(room2.isSelected())
            commonList = w1.roomNumber(commonList, 2, 2);
        else if(room3.isSelected())
            commonList = w1.roomNumber(commonList, 3, 3);
        else if(room4.isSelected())
            commonList = w1.roomNumber(commonList, 4, 4);
        else if(room5.isSelected())
            commonList = w1.roomNumber(commonList, 5, 5);
        else if(roomMore.isSelected())
            commonList = w1.roomNumber(commonList, 6, 100);
    }
    
    @FXML
    public void statusButtonClicked()
    {
        if(sale.isSelected())
            commonList = w1.estatesForSale(commonList);
        else if(shortTerm.isSelected())
            commonList = w1.estatesForShort(commonList);
        else if(longTerm.isSelected())
            commonList = w1.estatesForLong(commonList);
        
        if(sale.isSelected() || longTerm.isSelected() || shortTerm.isSelected())
            pricePane.setDisable(false);
    }
    
    @FXML
    public void ageOfBuildingButtonClicked()
    {
        if(age01.isSelected())
            commonList = w1.age(commonList, 0, 1);
        else if(age25.isSelected())
            commonList = w1.age(commonList, 2, 5);
        else if(age610.isSelected())
            commonList = w1.age(commonList, 6, 10);
        else if(ageMore.isSelected())
            commonList = w1.age(commonList, 11, 100);
    }
    
    @FXML
    public void  typeButtonClicked()
    {
        if(house.isSelected())
            commonList = w1.estateType("House", commonList);
        else if(office.isSelected())
            commonList = w1.estateType("Office", commonList);
        else if(land.isSelected())
            commonList = w1.estateType("Land", commonList);
    }
    
    @FXML
    public void searchButtonClicked()
    {
        typeButtonClicked();
        statusButtonClicked();
        priceButtonClicked();
        roomsButtonClicked();
        ageOfBuildingButtonClicked();
        showListToGridPane(commonList, listGridPane);        
    }
    
    /**
     * This method presents a RealEstate ArrayList in a gridpane.
     * First column image, second type, third name, fourth size,
     * fifth price, sixth address.
     * @param list real estate ArrayList will be shown
     * @param gp host gridPane
     */
    public void showListToGridPane(ArrayList<RealEstate> list, GridPane gp)
    {
        gp.getChildren().clear();

        gp.add(new Text("Image"), 0, 0);
        gp.add(new Text("Type"), 1, 0);
        gp.add(new Text("Title"), 2, 0);
        gp.add(new Text("Square meter"), 3, 0);
        gp.add(new Text("Price"), 4, 0);
        gp.add(new Text("Address"), 5, 0);

        for (int i = 0; list != null && i < list.size(); i++)
        {
            Main.setCurrentRealEstate(list.get(i));
            if(list.get(i).album != null && !list.get(i).album.isEmpty())
            {
                ImageView img = new ImageView(list.get(i).album.get(0));
                img.setFitHeight(70);
                img.setFitWidth(70);
                gp.add(img, 0, i + 1);
            }

            Hyperlink titleLink = new Hyperlink(list.get(i).title);
            
            final RealEstate re1 = list.get(i); //final because of inner class of hyperlink
            titleLink.setOnAction((ActionEvent e) ->
            {
                try
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AdPage.fxml"));
                    Parent root = loader.load();
                    AdPage ap1 = loader.getController();
                    ap1.showRealEstate(re1);
                    
                    Main.getMainStage().setScene(new Scene(root));
                } catch (IOException ex)
                {
                    Logger.getLogger(AfterSearchController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            
            Text type = new Text(list.get(i).getType());
            Text m2 = new Text(list.get(i).m2 + "");
            Text price = new Text(list.get(i).cl.get(0).price + "");
            Text address = new Text(list.get(i).address + "");

            gp.add(type, 1, i + 1);
            gp.add(titleLink, 2, i + 1);
            gp.add(m2, 3, i + 1);
            gp.add(price, 4, i + 1);
            gp.add(address, 5, i + 1);
            gp.setGridLinesVisible(false);
        }
    }

    public void showListToAfterSearchScene(ArrayList<RealEstate> list)
    {
        showListToGridPane(list, listGridPane);
    }

    public static ArrayList<RealEstate> removeDuplicates(ArrayList<RealEstate> list)
    {

        // Store unique items in result.
        ArrayList<RealEstate> result = new ArrayList<>();

        // Record encountered RealEstates in HashSet.
        HashSet<RealEstate> set = new HashSet<>();

        // Loop over argument list.
        for (RealEstate item : list)
        {

            // If RealEstate is not in set, add it to the list and the set.
            if (!set.contains(item))
            {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }

    @FXML
    private void ascendingPriceClicked(ActionEvent event)
    {
        
    }

    @FXML
    private void descendingPriceClicked(ActionEvent event)
    {
    }

    @FXML
    private void ascendingDateClicked(ActionEvent event)
    {
        w1.newerToOlder(commonList, 0, commonList.size()-1);
        showListToGridPane(commonList, listGridPane);
    }

    @FXML
    private void descendingDateClicked(ActionEvent event)
    {
        w1.olderToNewer(commonList, 0, commonList.size()-1);
        showListToGridPane(commonList, listGridPane);
    }
}
