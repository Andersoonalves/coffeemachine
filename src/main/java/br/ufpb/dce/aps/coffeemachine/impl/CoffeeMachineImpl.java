package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class CoffeeMachineImpl implements CoffeeMachine {
	private CashBoxLogic cashBoxLogic;
	private DisplayLogic displayLogic;
	private DrinkManagerLogic drinkManager;
	private DispenserLogic dispenserLogic;
	private DrinkDispenserLogic drinkDispenserLogic;

	public CoffeeMachineImpl(ComponentsFactory factory) {
		startingLogics();
		this.displayLogic.setDisplay(factory.getDisplay());
		this.cashBoxLogic.setCashBox(factory.getCashBox());
		this.displayLogic.info(Messages.INSERT_COINS);
		this.dispenserLogic.setDispenser(TypeDispenser.COFFEE_POWDER.getValue(), factory.getCoffeePowderDispenser());
		this.dispenserLogic.setDispenser(TypeDispenser.CREAMER.getValue(), factory.getCreamerDispenser());
		this.dispenserLogic.setDispenser(TypeDispenser.CUP.getValue(), factory.getCupDispenser());
		this.dispenserLogic.setDispenser(TypeDispenser.SUGAR.getValue(), factory.getSugarDispenser());
		this.dispenserLogic.setDispenser(TypeDispenser.WATER.getValue(), factory.getWaterDispenser());
		this.drinkDispenserLogic.setDrinkDispenser(factory.getDrinkDispenser());
	}

	public void insertCoin(Coin coin) {
		cashBoxLogic.insertCoin(coin);
	}

	public void cancel() {
		cashBoxLogic.cancel();
	}

	public void select(Drink drink) {
		drinkManager.select(drink);
	}
	
	private void startingLogics(){
		this.cashBoxLogic = new CashBoxLogic();
		this.displayLogic = DisplayLogic.getInstance();
		this.drinkManager = new DrinkManagerLogic();
		this.dispenserLogic = DispenserLogic.getInstance();
		this.drinkDispenserLogic = DrinkDispenserLogic.getInstance();
	}
}
