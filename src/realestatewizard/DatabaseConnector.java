package realestatewizard;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * DatabaseConnector class
 * 
 * @author Kerem
 */

public class DatabaseConnector implements Serializable {

	private Connection con;
	private Statement st, st2, st3;
	private ResultSet rs, rs2, rs3;

	/**
	 * Constructor of the databaseconnector
	 */
	public DatabaseConnector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/realestatewizard?useUnicode=true&characterEncoding=UTF-8", "root", "");
			st = con.createStatement();
			st2 = con.createStatement();
			st3 = con.createStatement();
		} catch (Exception ex) {
		}
	}

	/**
	 * Changing the given user, mostly used for editing user. First removing,
	 * than adding new one
	 * 
	 * @param oldUser:old
	 *            User
	 * 
	 * @param newUser:new
	 *            Edited User
	 */
	public void updateUser(User oldUser, User newUser) {
		String query = "UPDATE user SET name = \"" + newUser.name + "\",surname = \"" + newUser.surname
				+ "\",password = \"" + newUser.password + "\",email = \"" + newUser.email + "\",details = \""
				+ newUser.details;
		query += "\",phoneNumber = \"" + newUser.phoneNumber + "\",favourites =\""
				+ encode(toIdArray(newUser.favourites)) + "\",estates =\"" + encode(toIdArray(newUser.estates))
				+ "\" WHERE id = " + findUserId(oldUser);
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Changing the given estate, mostly used for editing estate. First
	 * removing, than adding new one
	 * 
	 * @param oldRr:old
	 *            estate
	 * 
	 * @param newR:new
	 *            Edited estate
	 */
	public void changeEstate(RealEstate oldR, RealEstate newR) {
		removeRealEstate(oldR);
		addRealEstate(newR);
	}

	/**
	 * Returns all estates in the database
	 * 
	 * @return al
	 */
	public ArrayList<RealEstate> getAllEstates() {
		ArrayList<RealEstate> al = new ArrayList<RealEstate>();
		String query = "SELECT id FROM realestate";
		try {
			rs2 = st2.executeQuery(query);
			while (rs2.next()) {
				int a = rs2.getInt("id");
				al.add(getEstate(a));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	/**
	 * Returns all the users from the database
	 * 
	 * @return al
	 */
	public ArrayList<User> getAllUsers() {
		ArrayList<User> al = new ArrayList<User>();
		String query = "SELECT id FROM user";
		try {
			rs = st.executeQuery(query);
			while (rs.next())
				al.add(getUser(rs.getInt("id")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	/**
	 * Getting user from database with given id
	 * 
	 * @param userId:
	 *            given user id to be looked
	 * 
	 * @return User: user with given id
	 */
	public User getUser(int userId) {
		// Creating variables
		String name = null, surname = null, password = null, phoneNumber = null, details = null, email = null,
				favourites = null, estates = null;
		String query = "SELECT * FROM user WHERE id = " + userId;
		// Assigning variables from the database
		try {
			rs = st.executeQuery(query);
			rs.next();
			name = rs.getString("name");
			surname = rs.getString("surname");
			password = rs.getString("password");
			email = rs.getString("email");
			details = rs.getString("details");
			phoneNumber = rs.getString("phoneNumber");
			favourites = rs.getString("favourites");
			estates = rs.getString("estates");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Returning new User by using variables and needed methods
		return new User(name, surname, password, email, details, phoneNumber, decodeEstate(decodeInt(estates)),
				decodeEstate(decodeInt(favourites)));
	}

	/**
	 * Getting realEstate with the given id query1: getting data from realestate
	 * table query2: getting data from picturepath table query3: getting data
	 * from component table, also 31 32 33 are for getting components query4:
	 * getting data from the house/office/land tables whatever the id th estate
	 * type is.
	 * 
	 * @param id:
	 *            given id to be looked
	 * 
	 * @return RealEstate: returning element house/office/land
	 */
	public RealEstate getEstate(int id) {
		// Creating all the variables for realestate rable data to store
		String title = "", description = "";
		int size = 0;
		boolean isExchangable = false;
		Date date = null;
		String address = "";
		int userId = 0, estateType = 0;
		String u = "";
		// Getting data from the real estate table query1
		String query1 = "SELECT * FROM realestate WHERE id = " + id;
		try {
			rs = st.executeQuery(query1);
			rs.next();
			title = rs.getString("title");
			description = rs.getString("description");
			size = rs.getInt("size");
			isExchangable = rs.getBoolean("isExchangable");
			date = rs.getDate("date");
			estateType = rs.getInt("estateType");
			address = rs.getString("address");
			userId = rs.getInt("userId");
			u = findEmail(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Creating variable pl
		ArrayList<String> pl = null;
		// Getting picture path String from the table query2
		String query2 = "SELECT * FROM picturepath WHERE estateId = " + id;
		try {
			rs = st.executeQuery(query2);
			rs.next();
			String s = rs.getString("path");
			pl = decodeString(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Creating variables for component
		int montlyPrice = 0, dues, deposit, minContract, maxContract, dailyPrice, minDays, maxDays, price, type = 0;
		ArrayList<Component> cl = null;
		// Getting component items from 4 different tables using query3
		String query3 = "SELECT * FROM component WHERE estateId = " + id;
		try {
			rs = st.executeQuery(query3);
			// Getting the component types and creating its objects
			rs.next();
			type = rs.getInt("type");
			cl = new ArrayList<Component>();
			// For long term loan estates
			if (type == 1 || type == 3 || type == 5 || type == 6) {
				String query31 = "SELECT * FROM longtermloan WHERE componentId = " + id;
				try {
					rs = st.executeQuery(query31);
					rs.next();
					montlyPrice = rs.getInt("montlyPrice");
					dues = rs.getInt("dues");
					deposit = rs.getInt("deposit");
					minContract = rs.getInt("minContract");
					maxContract = rs.getInt("maxContract");
					cl.add(new LongTermLoan(dues, deposit, minContract, maxContract, montlyPrice));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// For short term loan estates
			if (type == 2 || type == 3 || type == 6 || type == 7) {
				String query32 = "SELECT *FROM shorttermloan WHERE componentId = " + id;
				try {
					rs = st.executeQuery(query32);
					rs.next();
					dailyPrice = rs.getInt("dailyPrice");
					deposit = rs.getInt("deposit");
					minDays = rs.getInt("minDays");
					maxDays = rs.getInt("maxDays");
					cl.add(new ShortTermLoan(deposit, minDays, maxDays, dailyPrice));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// For sale estates
			if (type == 4 || type == 5 || type == 6 || type == 7) {
				String query33 = "SELECT * FROM sale WHERE componentId = " + id;
				try {
					rs = st.executeQuery(query33);
					rs.next();
					price = rs.getInt("price");
					dues = rs.getInt("dues");
					cl.add(new Sale(price, dues));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Determining the type of the estate and returning the true one

		// Creating variables for all house/office/land
		boolean elevator = false, security = false, carPark = false, indoorGarage = false, waterBoosters = false,
				soundIsolation = false, termalIsolation = false, airConditioner = false, alarm = false, painted = false,
				terrace = false, windowBlind = false, parentsBathroom = false, laminated = false, smartHome = false,
				steelDoor = false, waterHeater = false, garden = false, movieSystem = false, tv = false, oven = false,
				dishwasher = false, wireless = false, barbeque = false, hasTable = false, gamingTools = false,
				kitchenTools = false, sauna = false, gardenTools = false, mosque = false, school = false,
				pharmacy = false, supermarket = false, hospital = false, subway = false, busStop = false,
				isUsed = false, isFurnished = false, automative = false, cafe = false, bakery = false, grocery = false,
				restaurant = false, workshop = false, policilinic = false, butcher = false, camera = false,
				generator = false, kitchen = false, wc = false, electric = false, water = false, naturelGas = false,
				pathOpened = false, closeToMainRoad = false, closeToTransportation = false;
		int age = 0, floorNumber = 0, roomNumber = 0, numberOfFloor = 0, heating = 0, front = 0, types = 0,
				classNumber = 0, zoningStatus = 0, deedStatus = 0;
		String parcel = "";
		// Getting data from the house table if the type is house(1)
		if (estateType == 1) {
			String query4 = "SELECT * FROM house WHERE id = " + id;
			try {
				rs = st.executeQuery(query4);
				rs.next();
				elevator = rs.getBoolean("elevator");
				security = rs.getBoolean("security");
				carPark = rs.getBoolean("carPark");
				indoorGarage = rs.getBoolean("indoorGarage");
				waterBoosters = rs.getBoolean("waterBoosters");
				soundIsolation = rs.getBoolean("soundIsolation");
				termalIsolation = rs.getBoolean("termalIsolation");
				airConditioner = rs.getBoolean("airConditioner");
				alarm = rs.getBoolean("alarm");
				painted = rs.getBoolean("painted");
				terrace = rs.getBoolean("terrace");
				windowBlind = rs.getBoolean("windowBlind");
				parentsBathroom = rs.getBoolean("parentsBathroom");
				laminated = rs.getBoolean("laminated");
				smartHome = rs.getBoolean("smartHome");
				steelDoor = rs.getBoolean("steelDoor");
				gardenTools = rs.getBoolean("gardenTools");
				waterHeater = rs.getBoolean("waterHeater");
				garden = rs.getBoolean("garden");
				movieSystem = rs.getBoolean("movieSystem");
				tv = rs.getBoolean("tv");
				oven = rs.getBoolean("oven");
				dishwasher = rs.getBoolean("dishwasher");
				wireless = rs.getBoolean("wireless");
				barbeque = rs.getBoolean("barbeque");
				hasTable = rs.getBoolean("hasTable");
				gamingTools = rs.getBoolean("gamingTools");
				kitchenTools = rs.getBoolean("kitchenTools");
				sauna = rs.getBoolean("sauna");
				age = rs.getInt("age");
				floorNumber = rs.getInt("floorNumber");
				roomNumber = rs.getInt("roomNumber");
				numberOfFloor = rs.getInt("numberOfFloor");
				heating = rs.getInt("heating");
				front = rs.getInt("front");
				types = rs.getInt("type");
				isFurnished = rs.getBoolean("isFurnished");
				isUsed = rs.getBoolean("isUsed");
				gardenTools = rs.getBoolean("gardenTools");
				mosque = rs.getBoolean("mosque");
				school = rs.getBoolean("school");
				pharmacy = rs.getBoolean("pharmacy");
				supermarket = rs.getBoolean("supermarket");
				hospital = rs.getBoolean("hospital");
				subway = rs.getBoolean("subway");
				busStop = rs.getBoolean("busStop");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Assigning the booleans, because they are not in the constructor,
			// also creating new house
			House h = new House(age, floorNumber, roomNumber, numberOfFloor, heating, front, types, isUsed, isFurnished,
					address, size, title, pl, description, date, cl, isExchangable, u);
			h.hasElevator = elevator;
			h.hasSecurity = security;
			h.hasCarPark = carPark;
			h.hasIndoorGarage = indoorGarage;
			h.waterBooster = waterBoosters;
			h.soundIsolation = soundIsolation;
			h.thermalIsolation = termalIsolation;
			h.hasAirConditioner = airConditioner;
			h.hasAlarm = alarm;
			h.isPainted = painted;
			h.hasTerrace = terrace;
			h.hasWindowBlind = windowBlind;
			h.hasPArentsBathroom = parentsBathroom;
			h.isLaminated = laminated;
			h.isSmartHome = smartHome;
			h.hasSteelDoor = steelDoor;
			h.hasGardenTools = gardenTools;
			h.hasWaterHeater = waterHeater;
			h.hasGarden = garden;
			h.hasMovieSystem = movieSystem;
			h.hasTV = tv;
			h.hasOven = oven;
			h.hasDishWasher = dishwasher;
			h.hasWireless = wireless;
			h.hasBarbeque = barbeque;
			h.hasTable = hasTable;
			h.hasGamingTools = gamingTools;
			h.hasKitchenTools = kitchenTools;
			h.hasSauna = sauna;
			h.mosque = mosque;
			h.school = school;
			h.supermarket = supermarket;
			h.subway = subway;
			h.hospital = hospital;
			h.busStop = busStop;
			return h;
		}
		// If type is office(2)
		else if (estateType == 2) {
			String query5 = "SELECT * FROM office WHERE id = " + id;
			try {
				rs = st.executeQuery(query5);
				rs.next();
				classNumber = rs.getInt("classNumber");
				age = rs.getInt("age");
				floorNumber = rs.getInt("floorNumber");
				roomNumber = rs.getInt("roomNumber");
				numberOfFloor = rs.getInt("numberOfFloor");
				heating = rs.getInt("heating");
				isFurnished = rs.getBoolean("isFurnished");
				types = rs.getInt("type");
				isUsed = rs.getBoolean("isUsed");
				automative = rs.getBoolean("automative");
				cafe = rs.getBoolean("cafe");
				bakery = rs.getBoolean("bakery");
				grocery = rs.getBoolean("grocery");
				restaurant = rs.getBoolean("restaurant");
				workshop = rs.getBoolean("workshop");
				policilinic = rs.getBoolean("policilinic");
				butcher = rs.getBoolean("butcher");
				pharmacy = rs.getBoolean("pharmacy");
				camera = rs.getBoolean("camera");
				alarm = rs.getBoolean("alarm");
				generator = rs.getBoolean("generator");
				kitchen = rs.getBoolean("kitchen");
				wc = rs.getBoolean("wc");
				front = rs.getInt("front");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Office o = new Office(classNumber, age, floorNumber, roomNumber, numberOfFloor, heating, front, type,
					isUsed, isFurnished, address, size, title, pl, description, date, cl, isExchangable, u);
			o.availableForAutomative = automative;
			o.availableForCafe = cafe;
			o.availableForBakery = bakery;
			o.availableForGrocery = grocery;
			o.availableForRestaurant = restaurant;
			o.availableForWorkShop = workshop;
			o.availableForPoliclinic = policilinic;
			o.availableForButcher = butcher;
			o.availableForPharmacy = pharmacy;
			o.hasCamera = camera;
			o.hasAlarm = alarm;
			o.hasGenerator = generator;
			o.hasKitchen = kitchen;
			o.hasWC = wc;
			return o;
		}
		// If type is land(3)
		else if (estateType == 3) {
			String query6 = "SELECT * FROM land WHERE id = " + id;
			try {
				rs = st.executeQuery(query6);
				rs.next();
				electric = rs.getBoolean("electric");
				water = rs.getBoolean("water");
				naturelGas = rs.getBoolean("naturelGas");
				pathOpened = rs.getBoolean("pathOpened");
				closeToMainRoad = rs.getBoolean("closeToMainRoad");
				closeToTransportation = rs.getBoolean("closeToTransportation");
				zoningStatus = rs.getInt("zoningStatus");
				deedStatus = rs.getInt("deedStatus");
				parcel = rs.getString("parcel");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Land l = new Land(zoningStatus, parcel, deedStatus, address, size, title, pl, description, date, cl,
					isExchangable, u);
			l.hasElectric = electric;
			l.hasWater = water;
			l.hasNaturalGas = naturelGas;
			l.isPathOpened = pathOpened;
			l.isCloseToMainRoad = closeToMainRoad;
			l.isCloseToTransportation = closeToTransportation;
			return l;
		} else
			return null;
	}

	/**
	 * Static method for decoding string. It takes the comma seperated values
	 * from the string and places them into array This one is for
	 * comma-seperated integer string
	 * 
	 * @param s:
	 *            String to be decoded
	 * 
	 * @return intArray: Array
	 */
	private static int[] decodeInt(String s) {
		String[] strArray = s.split(",");
		int[] intArray = new int[strArray.length];
		if (s.equals(""))
			return new int[0];
		else {
			for (int i = 0; i < strArray.length; i++) {
				intArray[i] = Integer.parseInt(strArray[i]);
			}
			return intArray;
		}
	}

	/**
	 * Static method for decoding string. It takes the comma seperated values
	 * from the string and places them into array This one is for
	 * comma-seperated integer string, integers for the id of estate
	 * 
	 * @param s:
	 *            String to be decoded
	 * 
	 * @return al: ArrayList to be returned
	 */
	private ArrayList<RealEstate> decodeEstate(int[] k) {
		ArrayList<RealEstate> al = new ArrayList<RealEstate>();
		if (k == null)
			return new ArrayList<RealEstate>();
		else {
			for (int i = 0; i < k.length; i++) {
				al.add(getEstate(k[i]));
			}
			return al;
		}
	}

	/**
	 * Static method for decoding string. It takes the comma seperated values
	 * from the string and places them into array This one is for
	 * comma-seperated string string
	 * 
	 * @param s:
	 *            String to be decoded
	 * 
	 * @return pl: ArrayList to be returned
	 */
	private static ArrayList<String> decodeString(String s) {
		String[] strArray = s.split(",");
		ArrayList<String> pl = new ArrayList<String>();
		if (s.equals(""))
			return new ArrayList<String>();
		else {
			for (int i = 0; i < strArray.length; i++)
				pl.add(strArray[i]);
			return pl;
		}
	}

	/**
	 * Static method for taking array and filling the string with comma
	 * seperated
	 * 
	 * @param intArray:
	 *            array to be taken
	 * 
	 * @return String: comma seperated integer string
	 */
	private static String encode(int[] intArray) {
		StringBuilder builder = new StringBuilder();
		// Looping through the list
		if (intArray.length == 0)
			return "";
		else {
			for (int i = 0; i < intArray.length; i++) {
				// append the value into the builder
				builder.append(intArray[i]);
				// if the value is not the last element of the list
				// then append the comma(,) as well
				if (i != intArray.length - 1)
					builder.append(",");
			}
			return builder.toString();
		}
	}

	/**
	 * Takes estate list and fills the integer array with their id's
	 * 
	 * @param: rel:
	 *             RealEstate list for finding their id
	 * 
	 * @return idArray: id array of rel
	 */
	private int[] toIdArray(ArrayList<RealEstate> rel) {
		if (rel == null)
			return new int[0];
		else {
			int[] idArray = new int[rel.size()];
			for (int i = 0; i < rel.size(); i++)
				idArray[i] = findEstateId(rel.get(i));
			return idArray;
		}
	}

	/**
	 * Adding user to the database by using needed methods and with one query
	 * 
	 * @param u:
	 *            User to be added
	 */
	public void addUser(User u) {
		// Inserting user table
		String query1 = "INSERT INTO `user`(`name`, `surname`, `password`, `email`, `details`, `phoneNumber`, `favourites`, `estates`) VALUES (";
		query1 += "\"" + u.name + "\"," + "\"" + u.surname + "\",\"" + u.password + "\",\"" + u.email + "\",\""
				+ u.details + "\",\"" + u.phoneNumber + "\",\"" + encode(toIdArray(u.favourites)) + "\",\""
				+ encode(toIdArray(u.estates)) + "\")";
		try {
			st.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removing the given user, assuming that user is always in the database
	 * 
	 * @param u:User
	 *            to be removed
	 */
	public void removeUser(User u) {
		String query = "DELETE FROM user WHERE id = " + findUserId(u);
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes the given element from the database query1: removing from
	 * picturepath table.query2: removing from types of component.query3:
	 * removing general component details.query4: removing from the estate type
	 * data.query5: removing general estate data
	 * 
	 * @param r:
	 *            Given estate to be removed
	 */
	public void removeRealEstate(RealEstate r) {
		// Removing picture path data/QUERY1
		String query1 = "DELETE FROM picturepath WHERE estateId = " + findEstateId(r);
		try {
			st.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Removing component data/QUERY2
		for (int i = 0; i < r.cl.size(); i++) {
			String query2 = "";
			if (r.cl.get(i).getType().equals("LongTermLoan"))
				query2 = "DELETE FROM longtermloan WHERE componentId = " + findEstateId(r);
			else if (r.cl.get(i).getType().equals("ShortTermLoan"))
				query2 = "DELETE FROM shorttermloan WHERE componentId = " + findEstateId(r);
			else if (r.cl.get(i).getType().equals("Sale"))
				query2 = "DELETE FROM sale WHERE componentId = " + findEstateId(r);
			try {
				st.executeUpdate(query2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// Removing general component data/QUERY3
		String query3 = "DELETE FROM component WHERE estateId = " + findEstateId(r);
		try {
			st.executeUpdate(query3);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Removing type data/QUERY4
		String query4 = "";
		if (r.getType().equals("House"))
			query4 = "DELETE FROM house WHERE id = " + findEstateId(r);
		else if (r.getType().equals("Office"))
			query4 = "DELETE FROM office WHERE id = " + findEstateId(r);
		else if (r.getType().equals("Land"))
			query4 = "DELETE FROM land WHERE id = " + findEstateId(r);

		try {
			st.executeUpdate(query4);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Removing realestate data/QUERY5
		String query5 = "DELETE FROM realestate WHERE id = " + findEstateId(r);
		try {
			st.executeUpdate(query5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adding data to the realestate table
	 * 
	 * @param r:
	 *            real estate to be added
	 */
	private void addToRealEstateTable(RealEstate r) {
		// Inserting data into the realestate table
		String query1 = "INSERT INTO `realestate`(`title`, `description`, `size`, `isExchangable`, `date`, `address`, `userId`, `estateType`) VALUES (";
		query1 += "\"" + r.title + "\"" + "," + "\"" + r.description + "\"" + "," + r.m2 + "," + r.isExchangable + ","
				+ "\"" + r.getCurrentDayForDatabase() + "\"" + "," + "\"" + r.address + "\"" + ",";

		// To find user's id in database we create send another query
		String userIdFinder = "SELECT id FROM user WHERE email = \"" + r.sharedUser + "\"";
		int userId = 0;
		try {
			rs = st.executeQuery(userIdFinder);
			rs.next();
			userId = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		query1 += userId + ",";

		// To find the object type we check the type
		String classType = r.getType();
		int types;
		if (classType.equals("Land"))
			types = 3;
		else if (classType.equals("House"))
			types = 1;
		else
			types = 2;
		query1 += types + ")";

		// Executing the final query
		try {
			st.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adding data to the component tables
	 * 
	 * @param r:
	 *            real estate to be added
	 */
	private void addToComponentTables(RealEstate r) {
		int estateId = findEstateId(r);
		// Inserting component
		String query2 = "INSERT INTO `component`(`estateId`, `type`) VALUES (";
		query2 += estateId + ",";

		// Finding the component types
		int type = 0;
		for (int i = 0; i < r.cl.size(); i++) {
			if (r.cl.get(i).getType().equals("LongTermLoan"))
				type += 1;
			else if (r.cl.get(i).getType().equals("ShortTermLoan"))
				type += 2;
			else if (r.cl.get(i).getType().equals("Sale"))
				type += 4;
		}
		query2 += type + ")";
		// Executing the query
		try {
			st.executeUpdate(query2);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		// Inserting the component details
		String query3;
		for (int i = 0; i < r.cl.size(); i++) {
			query3 = "";
			// Checking the type of component and adding them to their correct
			// position
			if (r.cl.get(i).getType().equals("LongTermLoan")) {
				query3 += "INSERT INTO `longtermloan`(`componentId`, `montlyPrice`, `dues`, `deposit`, `minContract`, `maxContract`) VALUES ("
						+ estateId;
				query3 += "," + ((LongTermLoan) r.cl.get(i)).price + "," + ((LongTermLoan) r.cl.get(i)).dues + ","
						+ ((LongTermLoan) r.cl.get(i)).deposit + "," + ((LongTermLoan) r.cl.get(i)).minContractLength
						+ "," + ((LongTermLoan) r.cl.get(i)).maxContractLength + ")";
			} else if (r.cl.get(i).getType().equals("ShortTermLoan")) {
				query3 += "INSERT INTO `shorttermloan`(`componentId`, `dailyPrice`, `deposit`, `minDays`, `maxDays`) VALUES ("
						+ estateId;
				query3 += "," + ((ShortTermLoan) r.cl.get(i)).price + "," + ((ShortTermLoan) r.cl.get(i)).deposit + ","
						+ ((ShortTermLoan) r.cl.get(i)).minDays + "," + ((ShortTermLoan) r.cl.get(i)).maxDays + ")";
			} else if (r.cl.get(i).getType().equals("Sale")) {
				query3 += "INSERT INTO `sale`(`componentId`, `price`, `dues`) VALUES (" + estateId;
				query3 += "," + ((Sale) r.cl.get(i)).price + "," + ((Sale) r.cl.get(i)).dues + ")";
			}
			// Executing the query
			try {
				st.executeUpdate(query3);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Adding data to the picturepath tables
	 * 
	 * @param r:
	 *            real estate to be added
	 */
	private void addToPictureTable(RealEstate r) {
		int estateId = findEstateId(r);
		// Inserting picture paths
		String query4 = "";
		for (int i = 0; i < r.album.size(); i++) {
			query4 = "INSERT INTO `picturepath`(`path`, `estateId`) VALUES (" + "\"" + r.album.get(i) + "\"" + ","
					+ estateId + ")";
			try {
				st.executeUpdate(query4);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Adding data to the house/office/land tables
	 * 
	 * @param r:
	 *            real estate to be added
	 */
	private void addToHOLTables(RealEstate r) {
		int estateId = findEstateId(r);
		String query5 = "";
		if (r.getType().equals("House")) {
			// Inserting house
			query5 = "INSERT INTO `house`(`id`, `elevator`, `security`, `carPark`, `indoorGarage`, `waterBoosters`, `soundIsolation`, `termalIsolation`, `airConditioner`, `alarm`, `painted`, `terrace`, `windowBlind`, `parentsBathroom`, `laminated`, `heating`, `smartHome`, `steelDoor`, ";
			query5 += "`waterHeater`, `garden`, `type`, `front`, `movieSystem`, `tv`, `oven`, `dishwasher`, `wireless`, `barbeque`, `hasTable`, `gamingTools`, `kitchenTools`, `sauna`, `gardenTools`, `mosque`, `school`, `pharmacy`, `supermarket`, `hospital`, `subway`, `busStop`, `age`, `floorNumber`, `roomNumber`, `numberOfFloor`, `isUsed`, `isFurnished`) ";
			query5 += "VALUES(" + estateId + "," + ((House) r).hasElevator + "," + ((House) r).hasSecurity + ","
					+ ((House) r).hasCarPark + "," + ((House) r).hasIndoorGarage + "," + ((House) r).waterBooster + ","
					+ ((House) r).soundIsolation + "," + ((House) r).thermalIsolation + ",";
			query5 += ((House) r).hasAirConditioner + "," + ((House) r).hasAlarm + "," + ((House) r).isPainted + ","
					+ ((House) r).hasTerrace + "," + ((House) r).hasWindowBlind + "," + ((House) r).hasPArentsBathroom
					+ "," + ((House) r).isLaminated + "," + ((House) r).heating + "," + ((House) r).isSmartHome + ","
					+ ((House) r).hasSteelDoor + ",";
			query5 += ((House) r).hasWaterHeater + "," + ((House) r).hasGarden + "," + ((House) r).type + ","
					+ ((House) r).front + "," + ((House) r).hasMovieSystem + "," + ((House) r).hasTV + ","
					+ ((House) r).hasOven + "," + ((House) r).hasDishWasher + "," + ((House) r).hasWireless + ","
					+ ((House) r).hasBarbeque + "," + ((House) r).hasTable + "," + ((House) r).hasGamingTools + ",";
			query5 += ((House) r).hasKitchenTools + "," + ((House) r).hasSauna + "," + ((House) r).hasGardenTools + ","
					+ ((House) r).mosque + "," + ((House) r).school + "," + ((House) r).pharmacy + ","
					+ ((House) r).supermarket + "," + ((House) r).hospital + "," + ((House) r).subway + ","
					+ ((House) r).busStop + "," + ((House) r).age + "," + ((House) r).floorNumber + ","
					+ ((House) r).roomNumber + "," + ((House) r).numberOfFloor + "," + ((House) r).isUsed + ","
					+ ((House) r).isFurnished + ")";
		} else if (r.getType().equals("Office")) {
			query5 = "INSERT INTO `office`(`id`, `classNumber`, `age`, `floorNumber`, `roomNumber`, `numberOfFloor`, `heating`, `isFurnished`, `type`, `isUsed`, `automative`, `cafe`, `bakery`, `grocery`, `restaurant`, `workshop`, `policilinic`, `butcher`, `pharmacy`, `camera`, `alarm`, `generator`, `kitchen`, `wc`, `front`) VALUES (";
			query5 += estateId + "," + ((Office) r).classNumber + "," + ((Office) r).age + ","
					+ ((Office) r).floorNumber + "," + ((Office) r).roomNumber + "," + ((Office) r).numberOfFloor + ","
					+ ((Office) r).heating + "," + ((Office) r).isFurnished + "," + ((Office) r).type + ","
					+ ((Office) r).isUsed;
			query5 += "," + ((Office) r).availableForAutomative + "," + ((Office) r).availableForCafe + ","
					+ ((Office) r).availableForBakery + "," + ((Office) r).availableForGrocery + ","
					+ ((Office) r).availableForRestaurant + "," + ((Office) r).availableForWorkShop;
			query5 += "," + ((Office) r).availableForPoliclinic + "," + ((Office) r).availableForButcher + ","
					+ ((Office) r).availableForPharmacy + "," + ((Office) r).hasCamera + "," + ((Office) r).hasAlarm
					+ "," + ((Office) r).hasGenerator + "," + ((Office) r).hasKitchen + "," + ((Office) r).hasWC + ","
					+ ((Office) r).front + ")";
		} else if (r.getType().equals("Land")) {
			query5 = "INSERT INTO `land`(`id`, `electric`, `water`, `naturelGas`, `pathOpened`, `closeToMainRoad`, `closeToTransportation`, `zoningStatus`, `deedStatus`, `parcel`) VALUES ("
					+ estateId + ",";
			query5 += ((Land) r).hasElectric + "," + ((Land) r).hasWater + "," + ((Land) r).hasNaturalGas + ","
					+ ((Land) r).isPathOpened + "," + ((Land) r).isCloseToMainRoad + ","
					+ ((Land) r).isCloseToTransportation + "," + ((Land) r).zoningStatus + "," + ((Land) r).deedStatus
					+ ",\"" + ((Land) r).parcel + "\")";
		}
		try {
			st.executeUpdate(query5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method for finding the given estate's id
	 * 
	 * @param r:
	 *            real estate
	 * 
	 * @return estateId: estate's id
	 */
	private int findEstateId(RealEstate r) {
		// Finding realEstate id which is inserted
		int estateId = 0;
		String estateIdFinder = "SELECT id FROM realestate WHERE title = \"" + r.title + "\"";
		try {
			rs = st.executeQuery(estateIdFinder);
			rs.next();
			estateId = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estateId;
	}

	/**
	 * @param id
	 * @return
	 */
	private String findEmail(int id) {
		String query = "SELECT email FROM user WHERE id = " + id;
		String s = "";
		try {
			rs = st.executeQuery(query);
			rs.next();
			s = rs.getString("email");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * Method for finding user's id of given email
	 * 
	 * @param mail
	 * @return int c :id
	 */
	public int findIdMail(String mail) {
		String query = "SELECT id FROM user WHERE email = \"" + mail + "\"";
		int c = 0;
		try {
			rs = st.executeQuery(query);
			while (rs.next())
				c = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * Method for finding the given user's id
	 * 
	 * @param u:
	 *            user
	 * 
	 * @return userId: user's id
	 */
	private int findUserId(User u) {
		// To find user's id in database we create send another query
		String userIdFinder = "SELECT id FROM user WHERE email = \"" + u.email + "\"";
		int userId = 0;
		try {
			rs = st.executeQuery(userIdFinder);
			rs.next();
			userId = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	/**
	 * Saves a real estate to the database
	 * 
	 * @param r:
	 *            RealEstate object to be added
	 */
	public void addRealEstate(RealEstate r) {
		addToRealEstateTable(r);
		addToComponentTables(r);
		addToPictureTable(r);
		addToHOLTables(r);
		String query0 = "SELECT estates FROM user WHERE id = " + findIdMail(r.sharedUser);
		String fav = "";
		try {
			rs = st.executeQuery(query0);
			rs.next();
			fav = rs.getString("estates");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		if (fav.equals("")) {
			String query = "UPDATE user SET estates = CONCAT(estates,\"" + findEstateId(r) + "\") WHERE id = "
					+ findIdMail(r.sharedUser);
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String query = "UPDATE user SET estates = CONCAT(estates,\"," + findEstateId(r) + "\") WHERE id = "
					+ findIdMail(r.sharedUser);
			try {
				st.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 
	 */
	public void clearDatabase() {
		String query = "DELETE FROM user WHERE 1";
		String query2 = "DELETE FROM realestate WHERE 1";
		try {
			st.executeUpdate(query);
			st.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}