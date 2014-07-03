package br.ufpb.dce.aps.coffeemachine;

public class CashBoxLogic {
	private final Display display;
	private final CashBox cashBox;
	private final int amountCoins = Coin.values().length;
	private final int[] coins = new int[amountCoins];
	private int current;

	public CashBoxLogic(final Display display, final CashBox cashBox) {
		this.display = display;
		this.cashBox = cashBox;
	}

	/**
	 * This method is responsible for insert coins in cashBox.
	 * @param coin
	 */
	public void insertCoin(Coin coin) {

		if (coin == null) {
			throw new CoffeeMachineException("Please insert a valid coin");
		}

		current += coin.getValue();
		int i = 0;
		for (Coin c : Coin.values()) {
			if (c.equals(coin)) {
				coins[i]++;
			}
			i++;
		}

		display.info("Total: US$ " + current / 100 + "." + current % 100);
	}

	/**
	 * This method cancels operations.
	 */
	public void cancel (){
		if (current == 0){
			throw new CoffeeMachineException("Cancel Operation");
		}
		
		display.warn(Messages.CANCEL_MESSAGE);

		releaseChange(planChange(current));

		display.info(Messages.INSERT_COINS_MESSAGE);
	}
	
	public int[] planChange(int change) {
		final Coin[] reverse = Coin.reverse();
		final int[] arrayChange = new int[Coin.values().length];

		for (int i = 0; i < reverse.length; i++) {
			final Coin coin = reverse[i];

			while (change >= coin.getValue()) {
				change -= coin.getValue();
				arrayChange[i]++;
			}
		}
		return arrayChange;
	}

	public void releaseChange(final int[] arrayChange) {
		final Coin[] reverse = Coin.reverse();
		for (int i = 0; i < arrayChange.length; i++) {
			final int coinNumber = arrayChange[i];

			for (int j = 0; j < coinNumber; j++) {
				final Coin coin = reverse[i];
				cashBox.release(coin);
				current -= coin.getValue();
			}
		}
	}
}
