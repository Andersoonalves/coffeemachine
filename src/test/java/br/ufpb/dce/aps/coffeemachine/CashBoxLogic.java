package br.ufpb.dce.aps.coffeemachine;

import static org.mockito.Matchers.anyDouble;

import java.util.Arrays;


public class CashBoxLogic {
	private final Display display;
	private final CashBox cashBox;
	private final Dispenser coffePowderDispenser;
	private final Dispenser waterDispenser;
	private final Dispenser cupDispenser;
	private final DrinkDispenser drinkDispenser;
	private final Dispenser sugarDispenser;
	private final int amountCoins = Coin.values().length;
	private final int[] coins = new int[amountCoins];
	private int current;

	public CashBoxLogic(ComponentsFactory factory) {
		this.display = factory.getDisplay();
		this.cashBox = factory.getCashBox();
		this.coffePowderDispenser = factory.getCoffeePowderDispenser();
		this.waterDispenser = factory.getWaterDispenser();
		this.cupDispenser = factory.getCupDispenser();
		this.drinkDispenser = factory.getDrinkDispenser();
		this.sugarDispenser = factory.getSugarDispenser();
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

		display.warn(Messages.CANCEL);

		releaseChange();

		display.info(Messages.INSERT_COINS);

	}
	/**
	 * This method do release of change.
	 */
	private void releaseChange() {
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

	/**
	 * This method select a Drink.
	 */
	public void select(Drink drink) {

		checkingEnoughCoins();

		cupDispenser.contains(1);
		waterDispenser.contains(anyDouble());
		coffePowderDispenser.contains(anyDouble());

		if(drink.equals(Drink.BLACK_SUGAR)){
			sugarDispenser.contains(anyDouble());
		}

		display.info(Messages.MIXING);
		coffePowderDispenser.release(anyDouble());
		waterDispenser.release(anyDouble());

		if(drink.equals(Drink.BLACK_SUGAR)){
			sugarDispenser.release(anyDouble());
		}

		display.info(Messages.RELEASING);
		cupDispenser.release(1);
		drinkDispenser.release(anyDouble());
		display.info(Messages.TAKE_DRINK);
		display.info(Messages.INSERT_COINS);

		resetVariables();

	}

	private void resetVariables() {
		current = 0;
		Arrays.fill(coins, 0);
	}

	private void checkingEnoughCoins() {
		if (current < 35) {
			throw new RuntimeException(Messages.CANCEL);
		}
	}


}
