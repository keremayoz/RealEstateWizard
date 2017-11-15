package realestatewizard;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class Sale extends Component {
	public int dues;

	/**
	 * Sale constructor
	 * 
	 * @param price
	 * @param dues
	 */
	public Sale(int price, int dues) {
		super(price);
		this.dues = dues;
	}
}
