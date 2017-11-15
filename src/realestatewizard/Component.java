package realestatewizard;

/**
 * Component class
 * 
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public abstract class Component {
	public int price;

	/**
	 * Component constructor
	 * 
	 * @param price: price may be daily monthly or permanent sale
	 */
	public Component(int price) {
		this.price = price;
	}

	/**
	 * Get the price of the component
	 * 
	 * @return price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Set the price of the component
	 * 
	 * @param newPrice
	 */
	public void setPrice(int newPrice) {
		price = newPrice;
	}

	/**
	 * Get the type of the component with simple string
	 * 
	 * @return type of the class
	 */
	public String getType() {
		return this.getClass().getSimpleName();
	}
}
