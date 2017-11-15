package realestatewizard;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class Office extends RealEstate {
	public int classNumber;
	public int age;
	public int floorNumber;
	public int roomNumber;
	public int numberOfFloor;
	public int heating;
	public int front;
	public int type;
	public boolean isUsed;
	public boolean isFurnished;

	// features
	public boolean availableForAutomative;
	public boolean availableForCafe;
	public boolean availableForBakery;
	public boolean availableForGrocery;
	public boolean availableForRestaurant;
	public boolean availableForWorkShop;
	public boolean availableForPoliclinic;
	public boolean availableForButcher;
	public boolean availableForPharmacy;
	public boolean hasCamera;
	public boolean hasAlarm;
	public boolean hasGenerator;
	public boolean hasKitchen;
	public boolean hasWC;

	/**
	 * Office constructor
	 * 
	 * @param classNumber
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
	public Office(int classNumber, int age, int floorNumber, int roomNumber, int numberOfFloor, int heating, int front,
			int type, boolean isUsed, boolean isFurnished, String address, int m2, String title,
			ArrayList<String> album, String description, Date date, ArrayList<Component> cl, boolean isExchangable,
			String sharedUser) {
		super(address, m2, title, album, description, date, cl, isExchangable, sharedUser);
		this.classNumber = classNumber;
		this.age = age;
		this.floorNumber = floorNumber;
		this.roomNumber = roomNumber;
		this.numberOfFloor = numberOfFloor;
		this.heating = heating;
		this.front = front;
		this.type = type;
		this.isUsed = isUsed;
		this.isFurnished = isFurnished;
		availableForAutomative = false;
		availableForCafe = false;
		availableForBakery = false;
		availableForGrocery = false;
		availableForRestaurant = false;
		availableForWorkShop = false;
		availableForPoliclinic = false;
		availableForButcher = false;
		availableForPharmacy = false;
		hasCamera = false;
		hasAlarm = false;
		hasGenerator = false;
		hasKitchen = false;
		hasWC = false;
	}
}
