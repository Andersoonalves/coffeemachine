package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Drink;
import net.compor.frameworks.jcf.api.ComporFacade;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	static final String COFFEE_POWDER = "coffeePowder";
	static final String CUP = "cup";
	static final String WATER = "water";

	public MyCoffeeMachine(ComponentsFactory factory) {
		requestService("setDisplay", factory.getDisplay());
		requestService("setCashBox", factory.getCashBox());
		requestService("setDispenser", COFFEE_POWDER, factory.getCoffeePowderDispenser());
		requestService("setDispenser", CUP, factory.getCupDispenser());
		requestService("setDispenser", WATER, factory.getWaterDispenser());
		requestService("setDrinkDispenser", factory.getDrinkDispenser());
		requestService("initSession");
	}
	
	@Override
    protected void addComponents() {
        add(new DisplayManager());
        add(new SessionManager());
        add(new CashManager());
        add(new DrinkManager());
        add(new DispenserManager());
        add(new DrinkDispenserManager());
    }

	public void insertCoin(Coin coin) {
		requestService("insertCoin", coin);
	}

	public void cancel() {
		requestService("cancel");		
	}

	public void select(Drink drink) {
		requestService("select", drink);
	}
}
