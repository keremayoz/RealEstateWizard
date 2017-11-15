/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import model.Main;
import realestatewizard.Component;
import realestatewizard.DatabaseConnector;
import realestatewizard.House;
import realestatewizard.Land;
import realestatewizard.LongTermLoan;
import realestatewizard.Office;
import realestatewizard.Sale;
import realestatewizard.ShortTermLoan;
import realestatewizard.User;

/**
 * FXML Controller class
 *
 * @author Emre
 */
public class PutAdController implements Initializable
{

    @FXML
    private RadioButton houseButton;
    @FXML
    private RadioButton saleButton;
    @FXML
    private RadioButton officeButton;
    @FXML
    private RadioButton longTermButton;
    @FXML
    private RadioButton landButton;
    @FXML
    private RadioButton shortTermButton;
    @FXML
    private TextField addressField;
    @FXML
    private TextField squareMeterField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private RadioButton exchangableButton;
    @FXML
    private TitledPane housePane;
    @FXML
    private TextField ageOfBuildingField;
    @FXML
    private TextField floorNumberField;
    @FXML
    private TextField roomNumberField;
    @FXML
    private TextField numberOfFloorField;
    @FXML
    private TextField frontField;
    @FXML
    private TextField typeFild;
    @FXML
    private TextField heatingFieldHouse;
    @FXML
    private RadioButton usedButton;
    @FXML
    private RadioButton furnishedButton;
    @FXML
    private RadioButton elevatorButton;
    @FXML
    private RadioButton securityButton;
    @FXML
    private RadioButton carParkButton;
    @FXML
    private RadioButton indoorGarageButton;
    @FXML
    private RadioButton waterBoosterButton;
    @FXML
    private RadioButton thermalIsolationButton;
    @FXML
    private RadioButton soundIsolationButton;
    @FXML
    private RadioButton gardenButton;
    @FXML
    private RadioButton paintedButton;
    @FXML
    private RadioButton laminatedButton;
    @FXML
    private RadioButton airConditionerButton;
    @FXML
    private RadioButton alarmButton;
    @FXML
    private RadioButton terraceButton;
    @FXML
    private RadioButton windowBlindButton;
    @FXML
    private RadioButton parentBathroomButton;
    @FXML
    private RadioButton smartHomeButton;
    @FXML
    private RadioButton steelDoorButton;
    @FXML
    private RadioButton waterHeaterButton;
    @FXML
    private RadioButton movieSystemButton;
    @FXML
    private RadioButton tvButton;
    @FXML
    private RadioButton ovenButton;
    @FXML
    private RadioButton dishWasherButton;
    @FXML
    private RadioButton wirelesButton;
    @FXML
    private RadioButton barbequeButton;
    @FXML
    private RadioButton tableButton;
    @FXML
    private RadioButton gamingToolsButton;
    @FXML
    private RadioButton kitchenToolsButton;
    @FXML
    private RadioButton saunaButton;
    @FXML
    private RadioButton gardenToolsButton;
    @FXML
    private RadioButton mosqueButton;
    @FXML
    private RadioButton schoolButton;
    @FXML
    private RadioButton pharmacyButton;
    @FXML
    private RadioButton supermarketButton;
    @FXML
    private RadioButton hospitalButton;
    @FXML
    private RadioButton subwayButton;
    @FXML
    private RadioButton busStopButton;
    @FXML
    private TitledPane officePane;
    @FXML
    private TextField classNumberField;
    @FXML
    private TextField ageOfTheBuildingField;
    @FXML
    private TextField floorNumberFieldOffice;
    @FXML
    private TextField roomNumberFieldOffice;
    @FXML
    private TextField numberOfFloorFieldOffice;
    @FXML
    private TextField heatingField;
    @FXML
    private TextField frontFieldOffice;
    @FXML
    private TextField typeField;
    @FXML
    private RadioButton usedButtonOffice;
    @FXML
    private RadioButton furnishedButtonOffice;
    @FXML
    private RadioButton automativeButton;
    @FXML
    private RadioButton cafeButton;
    @FXML
    private RadioButton bakeryButton;
    @FXML
    private RadioButton groceryButton;
    @FXML
    private RadioButton restaurantButton;
    @FXML
    private RadioButton workShopButton;
    @FXML
    private RadioButton policlinicButton;
    @FXML
    private RadioButton butcherButton;
    @FXML
    private RadioButton pharmacyButtonOffice;
    @FXML
    private RadioButton cameraButton;
    @FXML
    private RadioButton alarmButtonOffice;
    @FXML
    private RadioButton generatorButton;
    @FXML
    private RadioButton kitchenButton;
    @FXML
    private RadioButton wcButton;
    @FXML
    private TitledPane landPane;
    @FXML
    private TextField zoningStatusField;
    @FXML
    private TextField parcelField;
    @FXML
    private TextField deedStatusField;
    @FXML
    private RadioButton ElectricButton;
    @FXML
    private RadioButton waterButton;
    @FXML
    private RadioButton naturalGAsButton;
    @FXML
    private RadioButton pathOpenedButton;
    @FXML
    private RadioButton closeToMainRoadButton;
    @FXML
    private RadioButton closeToTransportationButton;
    @FXML
    private TitledPane salePane;
    @FXML
    private TitledPane longTermPane;
    @FXML
    private TitledPane shortTermPane;
    @FXML
    private Button saveButton;
    @FXML
    private TextField titleField;
    @FXML
    private Text uploadDescription;
    @FXML
    private TextField priceField;
    @FXML
    private TextField duesField;
    @FXML
    private TextField monthlyPriceField;
    @FXML
    private TextField depositField;
    @FXML
    private TextField duesFieldLongTerm;
    @FXML
    private TextField minimumContractLengthField;
    @FXML
    private TextField maximumContractLengthField;
    @FXML
    private TextField dailyPriceField;
    @FXML
    private TextField depositFieldShortTerm;
    @FXML
    private TextField minimumDayField;
    @FXML
    private TextField maximumDayField;
    User currentUser;
    ArrayList<String> album;
    @FXML
    private TextField cityField;
    @FXML
    private TextField townField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField noField;
    @FXML
    private ComboBox<String> frontBox;
    @FXML
    private ComboBox<String> typeBox;
    @FXML
    private ComboBox<String> heatingBox;
    @FXML
    private ComboBox<String> heatingBoxOffice;
    @FXML
    private ComboBox<String> frontBoxOffice;
    @FXML
    private ComboBox<String> typeBoxOffice;
    @FXML
    private ComboBox<String> zoningStatusBox;
    @FXML
    private ComboBox<String> deedStatusBox;
    @FXML
    private Text warningMessage;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ToggleGroup tg1 = new ToggleGroup();
        houseButton.setToggleGroup(tg1);
        officeButton.setToggleGroup(tg1);
        landButton.setToggleGroup(tg1);
        
//        ToggleGroup tg2 = new ToggleGroup();
//        saleButton.setToggleGroup(tg2);
//        longTermButton.setToggleGroup(tg2);
//        shortTermButton.setToggleGroup(tg2);
        
        currentUser = Main.getCurrentUser();
        album = new ArrayList<String>();
        
        frontBox.getItems().addAll(
                "North",
                "South",
                "East",
                "West");
        
        typeBox.getItems().addAll(
                "Flat",
                "Residance",
                "Detached house",
                "Villa",
                "Mansion",
                "Prefabric house");
        
        heatingBox.getItems().addAll(
                "None",
                "Heating Stoves",
                "Natural Gas Stove",
                "Heater(Central)",
                "Heater(Floor)");
        
        heatingBoxOffice.getItems().addAll(
                "None",
                "Heating Stoves",
                "Natural Gas Stove",
                "Heater(Central)",
                "Heater(Floor)");
        
        frontBoxOffice.getItems().addAll(
                "North",
                "South",
                "East",
                "West");
        
        typeBoxOffice.getItems().addAll(
                "Factory",
                "Depot",
                "Store",
                "Workshop");
        
        zoningStatusBox.getItems().addAll(
                "Island",
                "Residentially",
                "Industrially",
                "Sports Area",
                "Commercially",
                "Vineyard - Garden");
        
        deedStatusBox.getItems().addAll(
                "Joint title deed",
                "Detached parcel",
                "Allocation",
                "Right-to-use ownership");
    }    

    @FXML
    private void applyButtonClicked(ActionEvent event)
    {
        if(houseButton.isSelected())
            housePane.setDisable(false);
        if(officeButton.isSelected())
            officePane.setDisable(false);
        if(landButton.isSelected())
            landPane.setDisable(false);
        
        if(saleButton.isSelected())
            salePane.setDisable(false);
        if(longTermButton.isSelected())
            longTermPane.setDisable(false);
        if(shortTermButton.isSelected())
            shortTermPane.setDisable(false);
        
        saveButton.setDisable(false);
    }

    @FXML
    private void chooseImageClicked(ActionEvent event)
    {
        FileChooser fc1 = new FileChooser();
        fc1.setTitle("Upload a picture");
        List<File> imageFiles = fc1.showOpenMultipleDialog(Main.getMainStage());
        uploadDescription.setText("Uploaded " + imageFiles.size() + " files");
        album = new ArrayList<String>(imageFiles.size());
        
        for(int i=0; i<imageFiles.size(); i++)
        {
            String newPath = imageFiles.get(i).getAbsolutePath().replace("\\", "/");
            newPath = "file:///" + newPath;
            album.add(newPath);
        }
    }

    @FXML
    private void saveButtonClicked(ActionEvent event)
    {
        DatabaseConnector db1 = new DatabaseConnector();
        ArrayList<Component> cmp = new ArrayList<Component>();
        String composedAddress = cityField.getText() + " " + townField.getText() + " " + streetField.getText() + " " + noField.getText();
        
        if(longTermButton.isSelected()) {
            cmp.add(new LongTermLoan(Integer.parseInt(duesFieldLongTerm.getText()),
            Integer.parseInt(depositField.getText()),
            Integer.parseInt(minimumContractLengthField.getText()),
            Integer.parseInt(maximumContractLengthField.getText()),
            Integer.parseInt(monthlyPriceField.getText())));
        }
        
        if(shortTermButton.isSelected())
        {
            cmp.add(new ShortTermLoan(Integer.parseInt(depositFieldShortTerm.getText()),
                    Integer.parseInt(minimumDayField.getText()),
                    Integer.parseInt(maximumDayField.getText()),
                    Integer.parseInt(dailyPriceField.getText())));
        }
        
        if(saleButton.isSelected())
        {
            cmp.add(new Sale(Integer.parseInt(priceField.getText()),
                    Integer.parseInt(duesField.getText())));
        }
        
        
        if(houseButton.isSelected())
        {
            
            House h1 = new House(Integer.parseInt(ageOfBuildingField.getText()),
                    Integer.parseInt(floorNumberField.getText()),
                    Integer.parseInt(roomNumberField.getText()),
                    Integer.parseInt(numberOfFloorField.getText()),
                    heatingBox.getSelectionModel().getSelectedIndex(),
                    frontBox.getSelectionModel().getSelectedIndex() + 1,
                    typeBox.getSelectionModel().getSelectedIndex() + 1,
                    usedButton.isSelected(),
                    furnishedButton.isSelected(),
                    composedAddress,
                    Integer.parseInt(squareMeterField.getText()),
                    titleField.getText(),
                    album,
                    descriptionField.getText(),
                    new Date(),
                    cmp,
                    exchangableButton.isSelected(),
                    currentUser.email);
            
                h1.hasElevator = elevatorButton.isSelected();
		h1.hasSecurity = securityButton.isSelected();
		h1.hasCarPark = carParkButton.isSelected();
		h1.hasIndoorGarage = indoorGarageButton.isSelected();
		h1.waterBooster = waterBoosterButton.isSelected();
		h1.thermalIsolation = thermalIsolationButton.isSelected();
		h1.soundIsolation = soundIsolationButton.isSelected();
		h1.hasGarden = gardenButton.isSelected();
		h1.isPainted = paintedButton.isSelected();
		h1.isLaminated = laminatedButton.isSelected();
		h1.hasAirConditioner = airConditionerButton.isSelected();
		h1.hasAlarm = alarmButton.isSelected();
		h1.hasTerrace = terraceButton.isSelected();
		h1.hasWindowBlind = windowBlindButton.isSelected();
		h1.hasPArentsBathroom = parentBathroomButton.isSelected();
		h1.isSmartHome = smartHomeButton.isSelected();
		h1.hasSteelDoor = steelDoorButton.isSelected();
		h1.hasWaterHeater = waterHeaterButton.isSelected();
		h1.hasMovieSystem = movieSystemButton.isSelected();
		h1.hasTV = tvButton.isSelected();
		h1.hasOven = ovenButton.isSelected();
		h1.hasDishWasher = dishWasherButton.isSelected();
		h1.hasWireless = wirelesButton.isSelected();
		h1.hasBarbeque = barbequeButton.isSelected();
		h1.hasTable = tableButton.isSelected();
		h1.hasGamingTools = gamingToolsButton.isSelected();
		h1.hasKitchenTools = kitchenToolsButton.isSelected();
		h1.hasSauna = saunaButton.isSelected();
		h1.hasGardenTools = gardenToolsButton.isSelected();
		h1.mosque = mosqueButton.isSelected();
		h1.school = schoolButton.isSelected();
		h1.pharmacy = pharmacyButton.isSelected();
		h1.supermarket = supermarketButton.isSelected();
		h1.hospital = hospitalButton.isSelected();
		h1.subway = subwayButton.isSelected();
		h1.busStop = busStopButton.isSelected();
                
                db1.addRealEstate(h1);
                
        }
        
        else if(officeButton.isSelected())
        {
            Office o1 = new Office(Integer.parseInt(classNumberField.getText()),
                    Integer.parseInt(ageOfTheBuildingField.getText()),
                    Integer.parseInt(floorNumberFieldOffice.getText()),
                    Integer.parseInt(roomNumberFieldOffice.getText()),
                    Integer.parseInt(numberOfFloorFieldOffice.getText()),
                    heatingBoxOffice.getSelectionModel().getSelectedIndex(),
                    frontBoxOffice.getSelectionModel().getSelectedIndex() + 1,
                    typeBoxOffice.getSelectionModel().getSelectedIndex() + 1,
                    usedButtonOffice.isSelected(),
                    furnishedButtonOffice.isSelected(),
                    composedAddress,
                    Integer.parseInt(squareMeterField.getText()),
                    titleField.getText(),
                    album,
                    descriptionField.getText(),
                    new Date(),
                    cmp,
                    exchangableButton.isSelected(),
                    currentUser.email);
            
            o1.availableForAutomative = automativeButton.isSelected();
            o1.availableForCafe = cafeButton.isSelected();
            o1.availableForBakery = bakeryButton.isSelected();
            o1.availableForGrocery = groceryButton.isSelected();
            o1.availableForRestaurant = restaurantButton.isSelected();
            o1.availableForWorkShop = workShopButton.isSelected();
            o1.availableForPoliclinic = policlinicButton.isSelected();
            o1.availableForButcher = butcherButton.isSelected();
            o1.availableForPharmacy = pharmacyButtonOffice.isSelected();
            o1.hasCamera = cameraButton.isSelected();
            o1.hasAlarm = alarmButtonOffice.isSelected();
            o1.hasGenerator = generatorButton.isSelected();
            o1.hasKitchen = kitchenButton.isSelected();
            o1.hasWC = wcButton.isSelected();
            
            db1.addRealEstate(o1);
        }
        
        else if(landButton.isSelected())
        {
            Land l1 = new Land(zoningStatusBox.getSelectionModel().getSelectedIndex() + 1,
                    parcelField.getText(),
                    deedStatusBox.getSelectionModel().getSelectedIndex() + 1,
                    composedAddress,
                    Integer.parseInt(squareMeterField.getText()),
                    titleField.getText(),
                    album,
                    descriptionField.getText(),
                    new Date(),
                    cmp,
                    exchangableButton.isSelected(),
                    currentUser.email);
            
            l1.hasElectric = ElectricButton.isSelected();
            l1.hasWater = waterButton.isSelected();
            l1.hasNaturalGas = naturalGAsButton.isSelected();
            l1.isPathOpened = pathOpenedButton.isSelected();
            l1.isCloseToMainRoad = closeToMainRoadButton.isSelected();
            l1.isCloseToTransportation = closeToTransportationButton.isSelected();
            
            db1.addRealEstate(l1);
        }
        
        warningMessage.setText("Successful! \n You can show your add in \n Detailed Search page now.");
    }
}
