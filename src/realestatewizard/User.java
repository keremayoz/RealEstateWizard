package realestatewizard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class User implements Serializable {
	public String name;
	public String surname;
	public String password;
	public String email;
	public String details;
	public String phoneNumber;
	public ArrayList<RealEstate> estates;
	public ArrayList<RealEstate> favourites;
	public ArrayList<Conversation> inbox;
	DatabaseConnector db = new DatabaseConnector();
	DatabaseMessage dbm = new DatabaseMessage();

	/**
	 * User constructor
	 * 
	 * @param name
	 * @param surname
	 * @param password
	 * @param email
	 * @param details
	 * @param phoneNumber
	 * @param estates
	 * @param favourites
	 */
	public User(String name, String surname, String password, String email, String details, String phoneNumber,
			ArrayList<RealEstate> estates, ArrayList<RealEstate> favourites) {
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.email = email;
		this.details = details;
		this.phoneNumber = phoneNumber;
		this.estates = estates;
		this.favourites = favourites;
	}

    public User()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	/**
	 * Marks estates as favourite, adds to the ArrayList
	 * 
	 * @param estate
	 */
	public void markFavourite(RealEstate estate) {
		favourites.add(estate);
		db.updateUser(this, this);
	}

	/**
	 * Removes estate from the favourites
	 * 
	 * @param estate
	 */
	public void removeFavourite(RealEstate estate) {
		favourites.remove(estate);
		db.updateUser(this, this);
	}

	/**
	 * Puts the ad to the user estates list
	 * 
	 * @param estate
	 */
	public void putAd(RealEstate estate) {
		estates.add(estate);
		db.addRealEstate(estate);
		db.updateUser(this, this);
	}

	/**
	 * Removes the ad from the user
	 * 
	 * @param estate
	 */
	public void removeAd(RealEstate estate) {
		estates.remove(estate);
		db.removeRealEstate(estate);
		db.updateUser(this, this);
	}

	/**
	 * Checks the equality
	 * 
	 * @param m
	 * @return
	 */
	public boolean equals(User m) {
		return this.email.equals(m.email);
	}

	/**
	 * Returns all the conversations of given user
	 * 
	 * @return
	 */
	public ArrayList<Conversation> getConversationsOf() {
		ArrayList<Conversation> all = dbm.readAllConversations();
		ArrayList<Conversation> his = new ArrayList<Conversation>();

		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).ml.get(0).senderMail.equals(this.email)
					|| all.get(i).ml.get(0).receiverMail.equals(this.email))
				his.add(all.get(i));
		}

		return his;
	}

	/**
	 * Returns the id in array of conversation u1 and u2
	 * 
	 * @param u1
	 * @param u2
	 * @return
	 */
	private int getConversationIdOf(User u1, User u2) {
		ArrayList<Conversation> all = dbm.readAllConversations();
		int out = -1;
		for (int i = 0; i < all.size(); i++) {
			if ((all.get(i).ml.get(0).senderMail.equals(u1.email) && all.get(i).ml.get(0).receiverMail.equals(u2.email))
					|| (all.get(i).ml.get(0).senderMail.equals(u2.email)
							&& all.get(i).ml.get(0).receiverMail.equals(u1.email))) {
				out = i;
			}
		}
		return out;
	}

	/**
	 * @param u
	 * @return
	 */
	public boolean hasConversationWith(User u) {
		ArrayList<Conversation> his = this.getConversationsOf();
		for (int i = 0; i < his.size(); i++) {
			if (his.get(i).ml.get(0).senderMail.equals(u.email) || his.get(i).ml.get(0).receiverMail.equals(u.email))
				return true;
		}
		return false;
	}

	/**
	 * Returns the list of people which user communicates
	 * 
	 * @return
	 */
	public ArrayList<String> withWho() {
		ArrayList<String> als = new ArrayList<String>();
		ArrayList<Conversation> cl = dbm.readAllConversations();

		for (int i = 0; i < cl.size(); i++) {
			if (cl.get(i).ml.get(0).senderMail.equals(this.email))
				als.add(cl.get(i).ml.get(0).receiverMail);
			else
				als.add(cl.get(i).ml.get(0).senderMail);
		}
		return als;

	}

	/**
	 * Returns the conversation with the given user
	 * 
	 * @param u
	 * @return
	 */
	public Conversation getConversationsWith(User u) {
		ArrayList<Conversation> cl = new ArrayList<Conversation>();
		ArrayList<Conversation> all = dbm.readAllConversations();

		for (int i = 0; i < all.size(); i++) {
			if ((all.get(i).ml.get(0).senderMail.equals(this.email)
					&& all.get(i).ml.get(0).receiverMail.equals(u.email))
					|| (all.get(i).ml.get(0).senderMail.equals(u.email)
							&& all.get(i).ml.get(0).receiverMail.equals(this.email)))
				return all.get(i);

		}
		return null;
	}

	/**
	 * Just send messages
	 * 
	 * @param to
	 * @param text
         * @return 
	 */
	public Message sendMessageTo(User to, String text) {
		Message newMessage = new Message(this.email, to.email, text, new Date());
		ArrayList<Conversation> all = dbm.readAllConversations();
		if (!this.hasConversationWith(to)) {
			ArrayList<Message> newMessages = new ArrayList<Message>();
			newMessages.add(newMessage);
			Conversation newConversation = new Conversation(newMessages);
			all.add(newConversation);
		} else {
			all.get(getConversationIdOf(this, to)).ml.add(newMessage);
		}
		dbm.writeAllConversations(all);
                return newMessage;
	}
}