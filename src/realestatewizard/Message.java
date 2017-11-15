package realestatewizard;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class Message implements Serializable {
	public String senderMail;
	public String receiverMail;
	public String text;
	public Date date;

	/**
	 * Message constructor
	 * 
         * @param senderMail
         * @param receiverMail
	 * @param text
	 * @param date
	 */
	public Message(String senderMail, String receiverMail, String text, Date date) {
		this.senderMail = senderMail;
		this.receiverMail = receiverMail;
		this.text = text;
		this.date = date;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
        @Override
	public String toString() {
		return senderMail + ":   " + text + "\n(" + date.toString() + ")\n";
	}

	/**
	 * @return
	 */
	public String getCurrentDayForDatabase() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = df.format(date);
		return currentDate;
	}

	public boolean equals(Message m) {
		return senderMail.equals(m.senderMail) && receiverMail.equals(m.receiverMail) && text.equals(m.text)
				&& date.equals(m.date);
	}
}
