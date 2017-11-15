package realestatewizard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */

public abstract class RealEstate {
	public String address;
	public int m2;
	public String title;
	public ArrayList<String> album;
	public String description;
	public Date date;
	public ArrayList<Component> cl;
	public boolean isExchangable;
	public String sharedUser;

	/**
	 * RealEstate constructor
	 * 
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
	public RealEstate(String address, int m2, String title, ArrayList<String> album, String description, Date date,
			ArrayList<Component> cl, boolean isExchangable, String sharedUser) {
		this.address = address;
		this.m2 = m2;
		this.title = title;
		this.album = album;
		this.description = description;
		this.date = date;
		this.cl = cl;
		this.isExchangable = isExchangable;
		this.sharedUser = sharedUser;
	}

	/**
	 * @return simple class type string
	 */
	public String getType() {
		return this.getClass().getSimpleName();
	}

	/**
	 * @return date
	 */
	public Date getCurrentDay() {
		return date;
	}

	/**
	 * @return currentDate
	 */
	public String getCurrentDayForDatabase() {
		DateFormat df = new SimpleDateFormat("dd-MM-yy");
		String currentDate = df.format(date);
		return currentDate;
	}

	/**
	 * @return
	 */
	public int getSalePrice() {
		for (int i = 0; i < cl.size(); i++) {
			if (cl.get(i).getType().equals("Sale"))
				return cl.get(i).price;
		}
		return 0;
	}

	/**
	 * @return
	 */
	public int getLongPrice() {
		for (int i = 0; i < cl.size(); i++) {
			if (cl.get(i).getType().equals("LongTermLoan"))
				return cl.get(i).price;
		}
		return 0;
	}

	/**
	 * @return
	 */
	public int getShortPrice() {
		for (int i = 0; i < cl.size(); i++) {
			if (cl.get(i).getType().equals("ShortTermLoan"))
				return cl.get(i).price;
		}
		return 0;
	}
}
