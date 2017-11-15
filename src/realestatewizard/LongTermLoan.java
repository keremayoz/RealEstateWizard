package realestatewizard;

/**
 *
 * @author n.musevitoglu-ug Edited by kerem.ayoz-ug in 20-12-16
 */
public class LongTermLoan extends Component {
	public int dues;
	public int deposit;
	public int minContractLength;
	public int maxContractLength;

	/**
	 * LongTermLoan constructor
	 * 
	 * @param dues
	 * @param deposit
	 * @param minContractLength
	 * @param maxContractLength
	 * @param price
	 */
	public LongTermLoan(int dues, int deposit, int minContractLength, int maxContractLength, int price) {
		super(price);
		this.dues = dues;
		this.deposit = deposit;
		this.minContractLength = minContractLength;
		this.maxContractLength = maxContractLength;
	}

}
