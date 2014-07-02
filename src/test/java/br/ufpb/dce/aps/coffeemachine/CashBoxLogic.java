package br.ufpb.dce.aps.coffeemachine;

public class CashBoxLogic {
	private final Display display;
	private final int amountCoins = Coin.values().length;
	private final int[] coins = new int[amountCoins];
	private int current;

	public CashBoxLogic(final Display display) {
		this.display = display;
	}

	/**
	 * This method is responsible for insert coins in cashBox.
	 * @param coin
	 */
	public void insertCoin(Coin coin) {

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
}
