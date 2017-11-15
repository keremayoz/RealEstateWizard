package realestatewizard;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class Land extends RealEstate {

	public int zoningStatus;
	public String parcel;
	public int deedStatus;

	// features
	public boolean hasElectric;
	public boolean hasWater;
	public boolean hasNaturalGas;
	public boolean isPathOpened;
	public boolean isCloseToMainRoad;
	public boolean isCloseToTransportation;

	/**
	 * Land constructor
	 * 
	 * @param zoningStatus
	 * @param parcel
	 * @param deedStatus
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
	public Land(int zoningStatus, String parcel, int deedStatus, String address, int m2, String title,
			ArrayList<String> album, String description, Date date, ArrayList<Component> cl, boolean isExchangable,
			String sharedUser) {
		super(address, m2, title, album, description, date, cl, isExchangable, sharedUser);
		this.zoningStatus = zoningStatus;
		this.parcel = parcel;
		this.deedStatus = deedStatus;
		hasElectric = false;
		hasWater = false;
		hasNaturalGas = false;
		isPathOpened = false;
		isCloseToMainRoad = false;
		isCloseToTransportation = false;
	}
}
