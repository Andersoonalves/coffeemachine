package br.ufpb.dce.aps.coffeemachine;

public class CoffeeMachineImplementation implements CoffeeMachine {
	private final Display display;
	private final CashBoxLogic cashBoxLogic;

	public CoffeeMachineImplementation(ComponentsFactory factory) {
		this.display = factory.getDisplay();
		this.display.info("Insert coins and select a drink!");
		this.cashBoxLogic = new CashBoxLogic(display);
	}

	public void insertCoin(Coin coin) {
		cashBoxLogic.insertCoin(coin);
	}
}
