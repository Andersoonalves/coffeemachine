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

		releaseChange();

		display.info(Messages.INSERT_COINS_MESSAGE);
	}

	/**
	 * This method do release of change.
	 */
	public void releaseChange() {
		final Coin[] reverse = Coin.reverse();

		reverseArrayOfCoins();

		for (int i = 0; i < coins.length; i++) {
			final int coinNumber = coins[i];

			for (int j = 0; j < coinNumber; j++) {
				final Coin coin = reverse[i];
				cashBox.release(coin);
				current -= coin.getValue();
			}
		}
	}

	/**
	 * This method reverse the array coins.
	 */
	private void reverseArrayOfCoins() {
		for(int i = 0; i < coins.length / 2; i++){
		    int temp = coins[i];
		    coins[i] = coins[coins.length - i - 1];
		    coins[coins.length - i - 1] = temp;
		}
	}
}
