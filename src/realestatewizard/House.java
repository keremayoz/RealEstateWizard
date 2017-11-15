package realestatewizard;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class House extends RealEstate {

	public int age;
	public int floorNumber;
	public int roomNumber;
	public int numberOfFloor;
	public int heating;
	public int front;
	public int type;
	public boolean isUsed;
	public boolean isFurnished;

	// exterior features
	public boolean hasElevator;
	public boolean hasSecurity;
	public boolean hasCarPark;
	public boolean hasIndoorGarage;
	public boolean waterBooster;
	public boolean thermalIsolation;
	public boolean soundIsolation;
	public boolean hasGarden;

	// interior features
	public boolean isPainted;
	public boolean isLaminated;
	public boolean hasAirConditioner;
	public boolean hasAlarm;
	public boolean hasTerrace;
	public boolean hasWindowBlind;
	public boolean hasPArentsBathroom;
	public boolean isSmartHome;
	public boolean hasSteelDoor;
	public boolean hasWaterHeater;

	// goods
	public boolean hasMovieSystem;
	public boolean hasTV;
	public boolean hasOven;
	public boolean hasDishWasher;
	public boolean hasWireless;
	public boolean hasBarbeque;
	public boolean hasTable;
	public boolean hasGamingTools;
	public boolean hasKitchenTools;
	public boolean hasSauna;
	public boolean hasGardenTools;

	// nearlines
	public boolean mosque;
	public boolean school;
	public boolean pharmacy;
	public boolean supermarket;
	public boolean hospital;
	public boolean subway;
	public boolean busStop;

	/**
	 * House constructor
	 * 
	 * @param age
	 * @param floorNumber
	 * @param roomNumber
	 * @param numberOfFloor
	 * @param heating
	 * @param front
	 * @param type
	 * @param isUsed
	 * @param isFurnished
	 * @param address
	 * @param m2
	 * @param title
	 * @param album
	 * @param description
	 * @param date
	 * @param cl
	 * @param isExchangable
	 * @param sharedUser
	 */
	public House(int age, int floorNumber, int roomNumber, int numberOfFloor, int heating, int front, int type,
			boolean isUsed, boolean isFurnished, String address, int m2, String title, ArrayList<String> album,
			String description, Date date, ArrayList<Component> cl, boolean isExchangable, String sharedUser) {
		super(address, m2, title, album, description, date, cl, isExchangable, sharedUser);
		this.age = age;
		this.floorNumber = floorNumber;
		this.roomNumber = roomNumber;
		this.numberOfFloor = numberOfFloor;
		this.heating = heating;
		this.front = front;
		this.type = type;
		this.isUsed = isUsed;
		this.isFurnished = isFurnished;
		hasElevator = false;
		hasSecurity = false;
		hasCarPark = false;
		hasIndoorGarage = false;
		waterBooster = false;
		thermalIsolation = false;
		soundIsolation = false;
		hasGarden = false;
		isPainted = false;
		isLaminated = false;
		hasAirConditioner = false;
		hasAlarm = false;
		hasTerrace = false;
		hasWindowBlind = false;
		hasPArentsBathroom = false;
		isSmartHome = false;
		hasSteelDoor = false;
		hasWaterHeater = false;
		hasMovieSystem = false;
		hasTV = false;
		hasOven = false;
		hasDishWasher = false;
		hasWireless = false;
		hasBarbeque = false;
		hasTable = false;
		hasGamingTools = false;
		hasKitchenTools = false;
		hasSauna = false;
		hasGardenTools = false;
		mosque = false;
		school = false;
		pharmacy = false;
		supermarket = false;
		hospital = false;
		subway = false;
		busStop = false;
	}

}
