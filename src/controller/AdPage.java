/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Main;
import realestatewizard.DatabaseConnector;
import realestatewizard.House;
import realestatewizard.Land;
import realestatewizard.LongTermLoan;
import realestatewizard.Office;
import realestatewizard.RealEstate;
import realestatewizard.Sale;
import realestatewizard.ShortTermLoan;
import realestatewizard.User;
import realestatewizard.Wizard;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class AdPage implements Initializable
{

    @FXML
    private  RadioButton houseButton;
    @FXML
    private  RadioButton saleButton;
    @FXML
    private  RadioButton officeButton;
    @FXML
    private  RadioButton longTermButton;
    @FXML
    private  RadioButton landButton;
    @FXML
    private  RadioButton shortTermButton;
    @FXML
    private  RadioButton exchangableButton;
    @FXML
    private  TitledPane housePane;
    @FXML
    private  RadioButton usedButton;
    @FXML
    private  RadioButton furnishedButton;
    @FXML
    private  RadioButton elevatorButton;
    @FXML
    private  RadioButton securityButton;
    @FXML
    private  RadioButton carParkButton;
    @FXML
    private  RadioButton indoorGarageButton;
    @FXML
    private  RadioButton waterBoosterButton;
    @FXML
    private  RadioButton thermalIsolationButton;
    @FXML
    private  RadioButton soundIsolationButton;
    @FXML
    private  RadioButton gardenButton;
    @FXML
    private  RadioButton paintedButton;
    @FXML
    private  RadioButton laminatedButton;
    @FXML
    private  RadioButton airConditionerButton;
    @FXML
    private  RadioButton alarmButton;
    @FXML
    private  RadioButton terraceButton;
    @FXML
    private  RadioButton windowBlindButton;
    @FXML
    private  RadioButton parentBathroomButton;
    @FXML
    private  RadioButton smartHomeButton;
    @FXML
    private  RadioButton steelDoorButton;
    @FXML
    private  RadioButton waterHeaterButton;
    @FXML
    private  RadioButton movieSystemButton;
    @FXML
    private  RadioButton tvButton;
    @FXML
    private  RadioButton ovenButton;
    @FXML
    private  RadioButton dishWasherButton;
    @FXML
    private  RadioButton wirelesButton;
    @FXML
    private  RadioButton barbequeButton;
    @FXML
    private  RadioButton tableButton;
    @FXML
    private  RadioButton gamingToolsButton;
    @FXML
    private  RadioButton kitchenToolsButton;
    @FXML
    private  RadioButton saunaButton;
    @FXML
    private  RadioButton gardenToolsButton;
    @FXML
    private  RadioButton mosqueButton;
    @FXML
    private  RadioButton schoolButton;
    @FXML
    private  RadioButton pharmacyButton;
    @FXML
    private  RadioButton supermarketButton;
    @FXML
    private  RadioButton hospitalButton;
    @FXML
    private  RadioButton subwayButton;
    @FXML
    private  RadioButton busStopButton;
    @FXML
    private  TitledPane officePane;
    @FXML
    private  RadioButton usedButtonOffice;
    @FXML
    private  RadioButton furnishedButtonOffice;
    @FXML
    private  RadioButton automativeButton;
    @FXML
    private  RadioButton cafeButton;
    @FXML
    private  RadioButton bakeryButton;
    @FXML
    private  RadioButton groceryButton;
    @FXML
    private  RadioButton restaurantButton;
    @FXML
    private  RadioButton workShopButton;
    @FXML
    private  RadioButton policlinicButton;
    @FXML
    private  RadioButton butcherButton;
    @FXML
    private  RadioButton pharmacyButtonOffice;
    @FXML
    private  RadioButton cameraButton;
    @FXML
    private  RadioButton alarmButtonOffice;
    @FXML
    private  RadioButton generatorButton;
    @FXML
    private  RadioButton kitchenButton;
    @FXML
    private  RadioButton wcButton;
    @FXML
    private  TitledPane landPane;
    @FXML
    private  RadioButton ElectricButton;
    @FXML
    private  RadioButton waterButton;
    @FXML
    private  RadioButton naturalGAsButton;
    @FXML
    private  RadioButton pathOpenedButton;
    @FXML
    private  RadioButton closeToMainRoadButton;
    @FXML
    private  RadioButton closeToTransportationButton;
    @FXML
    private  TitledPane salePane;
    @FXML
    private  TitledPane longTermPane;
    @FXML
    private  TitledPane shortTermPane;
    @FXML
    private Text titleText;
    @FXML
    private  Text addressText;
    @FXML
    private  Text squareMeterText;
    @FXML
    private  Text descriptipnText;
    @FXML
    private  Text ageOfBuildingText;
    @FXML
    private  Text floorNumberText;
    @FXML
    private  Text roomNumberText;
    @FXML
    private  Text numberOfFloorText;
    @FXML
    private  Text typeText;
    @FXML
    private  Text heatingText;
    @FXML
    private  Text classNumberText;
    @FXML
    private  Text ageOfTheBuildingText;
    @FXML
    private  Text floorNumberTextOffice;
    @FXML
    private  Text roomNumberTextOffice;
    @FXML
    private  Text numberOfFloorTextOffice;
    @FXML
    private  Text frontTextOffice;
    @FXML
    private  Text zoningStatusText;
    @FXML
    private  Text parcelText;
    @FXML
    private  Text deedStatusText;
    @FXML
    private  Text priceText;
    @FXML
    private  Text duesText;
    @FXML
    private  Text monthlyPriceText;
    @FXML
    private  Text depositText;
    @FXML
    private  Text duesTextLongTerm;
    @FXML
    private  Text minimumContractLengthText;
    @FXML
    private  Text maximumContractLengthText;
    @FXML
    private  Text minimumDayText;
    @FXML
    private  Text maximumDayText;
    @FXML
    private  Text dailyPriceText;
    @FXML
    private  Text depositTextShortTerm;
    @FXML
    private  Text heatingTextOffice;
    @FXML
    private  Text typeTextOffice;
    @FXML
    private  Text frontText;
    @FXML
    private VBox photosVBox;
    @FXML
    private Label addFavouritesLabel;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private Button sendButton;
    
    User currentUser;
    RealEstate currentRealEstate;
    @FXML
    private ImageView mapImage;

    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        currentUser = Main.getCurrentUser();
        if(currentUser == null)
        {
            addFavouritesLabel.setDisable(true);
            messageTextArea.setDisable(true);
            sendButton.setDisable(true);
        }
    }    

    
    
    public void showRealEstate(RealEstate re) throws IOException
    {
        currentRealEstate = re;
        Wizard w1 = new Wizard();
        Image image = SwingFXUtils.toFXImage(w1.map(re), null);
        mapImage.setImage(image);
        
        //Common fields for all real estates
        titleText.setText(re.title);        
        addressText.setText(re.address);
        squareMeterText.setText("" + re.m2);
        descriptipnText.setText(re.description);
        exchangableButton.setSelected(re.isExchangable);
        for(int i=0; i<re.album.size(); i++)
        {
            ImageView img = new ImageView(re.album.get(i));
            img.setFitHeight(120);
            img.setFitWidth(120);
            photosVBox.getChildren().add(img);
        }
        
        if(re.getType().equals("House"))
        {
            houseButton.setSelected(true);
            housePane.setExpanded(true);
            
            ageOfBuildingText.setText("" + ((House)re).age); //Burası hatalı olabilir
            floorNumberText.setText("" + ((House)re).floorNumber);
            roomNumberText.setText("" + ((House)re).roomNumber);
            numberOfFloorText.setText("" + ((House)re).numberOfFloor);
            heatingText.setText("" + ((House)re).heating);
            frontText.setText("" + ((House)re).front);
            typeText.setText("" + ((House)re).type);
            usedButton.setSelected(((House)re).isUsed);
            furnishedButton.setSelected(((House)re).isFurnished);
            elevatorButton.setSelected(((House)re).hasElevator);
            securityButton.setSelected(((House)re).hasSecurity);
            carParkButton.setSelected(((House)re).hasCarPark);
            indoorGarageButton.setSelected(((House)re).hasIndoorGarage);
            waterBoosterButton.setSelected(((House)re).waterBooster);
            thermalIsolationButton.setSelected(((House)re).thermalIsolation);
            soundIsolationButton.setSelected(((House)re).soundIsolation);
            gardenButton.setSelected(((House)re).hasGarden);
            paintedButton.setSelected(((House)re).isPainted);
            laminatedButton.setSelected(((House)re).isLaminated);
            airConditionerButton.setSelected(((House)re).hasAirConditioner);
            alarmButton.setSelected(((House)re).hasAlarm);
            terraceButton.setSelected(((House)re).hasTerrace);
            windowBlindButton.setSelected(((House)re).hasWindowBlind);
            parentBathroomButton.setSelected(((House)re).hasPArentsBathroom);
            smartHomeButton.setSelected(((House)re).isSmartHome);
            steelDoorButton.setSelected(((House)re).hasSteelDoor);
            waterHeaterButton.setSelected(((House)re).hasWaterHeater);
            movieSystemButton.setSelected(((House)re).hasMovieSystem);
            tvButton.setSelected(((House)re).hasTV);
            ovenButton.setSelected(((House)re).hasOven);
            dishWasherButton.setSelected(((House)re).hasDishWasher);
            wirelesButton.setSelected(((House)re).hasWireless);
            barbequeButton.setSelected(((House)re).hasBarbeque);
            tableButton.setSelected(((House)re).hasTable);
            gamingToolsButton.setSelected(((House)re).hasGamingTools);
            kitchenToolsButton.setSelected(((House)re).hasKitchenTools);
            saunaButton.setSelected(((House)re).hasSauna);
            gardenToolsButton.setSelected(((House)re).hasGardenTools);
            mosqueButton.setSelected(((House)re).mosque);
            schoolButton.setSelected(((House)re).school);
            pharmacyButton.setSelected(((House)re).pharmacy);
            supermarketButton.setSelected(((House)re).supermarket);
            hospitalButton.setSelected(((House)re).hospital);
            subwayButton.setSelected(((House)re).subway);
            busStopButton.setSelected(((House)re).busStop);
            
            
        }
        
        else if(re.getType().equals("Office"))
        {
            officeButton.setSelected(true);
            officePane.setExpanded(true);
            
            classNumberText.setText("" + ((Office)re).classNumber);
            ageOfTheBuildingText.setText("" + ((Office)re).age);
            floorNumberTextOffice.setText("" + ((Office)re).floorNumber);
            roomNumberTextOffice.setText("" + ((Office)re).roomNumber);
            numberOfFloorTextOffice.setText("" + ((Office)re).numberOfFloor);
            heatingTextOffice.setText("" + ((Office)re).heating);
            frontTextOffice.setText("" + ((Office)re).front);
            typeTextOffice.setText("" + ((Office)re).type);
            usedButtonOffice.setSelected(((Office)re).isUsed);
            furnishedButtonOffice.setSelected(((Office)re).isFurnished);
            automativeButton.setSelected(((Office)re).availableForAutomative);
            cafeButton.setSelected(((Office)re).availableForCafe);
            bakeryButton.setSelected(((Office)re).availableForBakery);
            groceryButton.setSelected(((Office)re).availableForGrocery);
            restaurantButton.setSelected(((Office)re).availableForRestaurant);
            workShopButton.setSelected(((Office)re).availableForWorkShop);
            policlinicButton.setSelected(((Office)re).availableForPoliclinic);
            butcherButton.setSelected(((Office)re).availableForButcher);
            pharmacyButtonOffice.setSelected(((Office)re).availableForPharmacy);
            cameraButton.setSelected(((Office)re).hasCamera);
            alarmButtonOffice.setSelected(((Office)re).hasAlarm);
            generatorButton.setSelected(((Office)re).hasGenerator);
            kitchenButton.setSelected(((Office)re).hasKitchen);
            wcButton.setSelected(((Office)re).hasWC);
        }
        
        else if(re.getType().equals("Land"))
        {
            landButton.setSelected(true);
            landPane.setExpanded(true);
            
            zoningStatusText.setText("" + ((Land)re).zoningStatus);
            parcelText.setText("" + ((Land)re).parcel);
            deedStatusText.setText("" + ((Land)re).deedStatus);
            ElectricButton.setSelected(((Land)re).hasElectric);
            waterButton.setSelected(((Land)re).hasWater);
            naturalGAsButton.setSelected(((Land)re).hasNaturalGas);
            pathOpenedButton.setSelected(((Land)re).isPathOpened);
            closeToMainRoadButton.setSelected(((Land)re).isCloseToMainRoad);
            closeToTransportationButton.setSelected(((Land)re).isCloseToTransportation);
        }
        
        for(int i=0; i<re.cl.size(); i++)
        {
            if(re.cl.get(i).getType().equals("Sale"))
            {
                salePane.setDisable(false);
                salePane.setExpanded(true);
                priceText.setText("" + ((Sale)re.cl.get(i)).price);
                duesText.setText("" +  ((Sale)re.cl.get(i)).dues);
                saleButton.setSelected(true);
            }
            
            if(re.cl.get(i).getType().equals("LongTermLoan"))
            {
                longTermPane.setDisable(false);
                longTermPane.setExpanded(true);
                monthlyPriceText.setText("" + ((LongTermLoan)re.cl.get(i)).price);
                depositText.setText("" + ((LongTermLoan)re.cl.get(i)).deposit);
                duesTextLongTerm.setText("" + ((LongTermLoan)re.cl.get(i)).dues);
                minimumContractLengthText.setText("" + ((LongTermLoan)re.cl.get(i)).minContractLength);
                maximumContractLengthText.setText("" + ((LongTermLoan)re.cl.get(i)).maxContractLength);
                longTermButton.setSelected(true);
            }
            
            if(re.cl.get(i).getType().equals("ShortTermLoan"))
            {
                shortTermPane.setDisable(false);
                shortTermPane.setExpanded(true);
                dailyPriceText.setText("" + ((ShortTermLoan)re.cl.get(i)).price);
                depositTextShortTerm.setText("" + ((ShortTermLoan)re.cl.get(i)).deposit);
                minimumDayText.setText("" + ((ShortTermLoan)re.cl.get(i)).minDays);
                maximumDayText.setText("" + ((ShortTermLoan)re.cl.get(i)).maxDays);
                shortTermButton.setSelected(true);
            }
        }
    }

    @FXML
    private void addFavouritesLabelClicked(MouseEvent event)
    {
        currentUser.markFavourite(currentRealEstate);
        addFavouritesLabel.setText("Added to your favourites");
    }

    @FXML
    private void sendButtonClicked(ActionEvent event)
    {
        DatabaseConnector db1 = new DatabaseConnector();
        User seller = db1.getUser(db1.findIdMail(currentRealEstate.sharedUser));
        currentUser.sendMessageTo(seller, messageTextArea.getText());
        messageTextArea.clear();
    }
}
