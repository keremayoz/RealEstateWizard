package realestatewizard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Kerem
 *
 */
public class Conversation implements Serializable {
	public ArrayList<Message> ml;

	/**
	 * Conversation constructor
	 * 
	 * @param sender
	 * @param receiver
	 * @param ml
	 */
	public Conversation(ArrayList<Message> ml) {
		this.ml = ml;
	}

	/**
	 * Displaying conversation messages
	 * 
	 * @return output
	 */
	public String displayConversation() {
		String output = " ";
		String separator = System.getProperty("line.separator");
		for (int i = 0; i < ml.size(); i++) {
			output += "-" + ml.get(i) + separator;
		}
		return output;
	}

        @Override
	public String toString() {
		return this.displayConversation();
	}

	public boolean equals(Conversation c) {
		for(int i = 0; i < c.ml.size() && i < ml.size(); i++) {
			if (!ml.get(i).equals(c.ml.get(i)))
				return false;
		}
		return true;
	}
}
