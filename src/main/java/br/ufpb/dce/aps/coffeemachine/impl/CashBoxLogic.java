package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.Messages;

import java.util.Arrays;

public class CashBoxLogic {
	private CashBox cashBox;
	private final DisplayLogic displayLogic;
	private int[] currentCash = new int[Coin.values().length];
	private int[] changePlan = new int[Coin.values().length];
	private boolean cancelFlag = false;

	private static CashBoxLogic instance;

	public CashBoxLogic() {
		this.displayLogic = DisplayLogic.getInstance();
		instance = this;
	}

	public static CashBoxLogic getInstance() {
		if (instance == null){
			instance = new CashBoxLogic();
		}
		return instance;
	}
	
	public void setCashBox(final CashBox cashBox){
		this.cashBox = cashBox;
	}

	/**
	 * This method is responsible for insert coins in cashBox.
	 * 
	 * @param coin
	 */
	public void insertCoin(final Coin coin) {

		if (coin == null) {
			throw new CoffeeMachineException("Please insert a valid coin");
		}

		Coin[] reverse = Coin.reverse();

		for (int i = 0; i < reverse.length; i++) {
			if (coin.equals(reverse[i])) {
				currentCash[i]++;
			}
		}

		displayLogic
				.info("Total: US$ " + getCurrentCash() / 100 + "." + getCurrentCash() % 100);
	}

	/**
	 * This method cancels operations.
	 */
	public void cancel() {
		
		int change = getCurrentCash();
		
		if (change == 0) {
			throw new CoffeeMachineException("You have not entered Coins!");
		}

		displayLogic.warn(Messages.CANCEL);
		
		abortSession();
		cancelFlag = true;

	}

	public void initMessage() {
		displayLogic.info(Messages.INSERT_COINS);
	}

	public void giveBackCoins() {
		changePlan = currentCash;
		releaseChange();
	}

	public Integer getCurrentCash() {
		int result = 0;
		Coin[] reverse = Coin.reverse();

		for (int i = 0; i < currentCash.length; i++) {
			int coinNumber = currentCash[i];
			result += coinNumber * reverse[i].getValue();
		}
		return result;
    }

	public void resetCurrentCash() {
		Arrays.fill(currentCash, 0);
		Arrays.fill(changePlan, 0);
	}

	public void abortSession() {
		giveBackCoins();
		resetCurrentCash();
		initMessage();
	}

	public void finishSession() {
		releaseChange();
		resetCurrentCash();
		initMessage();
	}

	/**
	 * This method do release of change.
	 */
	private void releaseChange() {

		if (cancelFlag)Arrays.fill(changePlan, 0);
		
		final Coin[] reverse = Coin.reverse();	
		for (int i = 0; i < changePlan.length; i++) {
			final int coinNumber = changePlan[i];
			for (int j = 0; j < coinNumber; j++) {
				final Coin coin = reverse[i];
				cashBox.release(coin);
				currentCash[i]--;
			}
		}
	}

	public boolean checkChange(int cost) {
		int change = getCurrentCash() - cost;
		
		final Coin[] reverse = Coin.reverse();
		final int[] arrayChange = new int[Coin.values().length];

		for (int i = 0; i < reverse.length; i++) {
			final Coin coin = reverse[i];
			int coinValue = coin.getValue();
			if (change >= coinValue) {
				int count = cashBox.count(coin);
				while (change >= coinValue && count > 0) {
					change -= coinValue;
					arrayChange[i]++;
					count--;
				}
			}
		}
		if (change > 0) {
			return false;
		}

		currentCash = arrayChange;
		return true;
	}
}
