package realestatewizard;

import java.io.*;
import java.util.ArrayList;

public class DatabaseMessage {

	File storageFile = new File("C:\\xampp\\mysql\\data\\database.txt");
	public int numberOfConversations;

	/**
	 * Default constructor
	 */
	public DatabaseMessage() {
		numberOfConversations = 0;
	}

	/**
	 * Writes all conversations to the txt file
	 * 
	 * @param cl
	 */
	public void writeAllConversations(ArrayList<Conversation> cl) {
		try {
			write(storageFile, cl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads all conversations from txt file
	 * 
	 * @return ArrayList<Conversation>: all conversations
	 */
	public ArrayList<Conversation> readAllConversations() {
		ArrayList<Conversation> all = new ArrayList<Conversation>();
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(storageFile));
			all = read(ois);
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return all;
	}

	/**
	 * Writes the conversations into the txt
	 * 
	 * @param storageFile
	 * @param o
	 * @throws IOException
	 */
	private void write(File storageFile, Object o) throws IOException {
		FileOutputStream fos = new FileOutputStream(storageFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);// getOOS(storageFile);
		oos.writeObject(o);
		oos.close();
	}

	/**
	 * Reads from the txt file
	 * 
	 * @param ois
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private ArrayList<Conversation> read(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ArrayList<Conversation> actual = (ArrayList) ois.readObject();
		return actual;
	}
}
