package realestatewizard;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class ShortTermLoan extends Component {
	public int deposit;
	public int minDays;
	public int maxDays;

	/**
	 * ShortTermLoan constructor
	 * 
	 * @param deposit
	 * @param minDays
	 * @param maxDays
	 * @param price
	 */
	public ShortTermLoan(int deposit, int minDays, int maxDays, int price) {
		super(price);
		this.deposit = deposit;
		this.minDays = minDays;
		this.maxDays = maxDays;
	}
}
