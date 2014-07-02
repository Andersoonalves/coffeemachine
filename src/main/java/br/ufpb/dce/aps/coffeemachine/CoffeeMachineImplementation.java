package br.ufpb.dce.aps.coffeemachine;

public class CoffeeMachineImplementation implements CoffeeMachine {
	private final Display display;
	private final CashBox cashBox;
	private final CashBoxLogic cashBoxLogic;

	public CoffeeMachineImplementation(ComponentsFactory factory) {
		this.display = factory.getDisplay();
		this.display.info("Insert coins and select a drink!");
		this.cashBox = factory.getCashBox();
		this.cashBoxLogic = new CashBoxLogic(display, cashBox);
	}

	public void insertCoin(Coin coin) {
		cashBoxLogic.insertCoin(coin);
	}

	public void cancel() {
		cashBoxLogic.cancel();
	}
}
