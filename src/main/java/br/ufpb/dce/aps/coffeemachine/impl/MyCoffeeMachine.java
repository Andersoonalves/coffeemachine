package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.Recipe;
import net.compor.frameworks.jcf.api.ComporFacade;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	static final String CUP = "cup";

	@Override
    protected void addComponents() {
        add(new DisplayManager());
        add(new SessionManager());
        add(new CashManager());
        add(new DrinkManager());
        add(new DispenserManager());
        add(new DrinkDispenserManager());
        add(new BadgeReader());
        add(new ButtonManager());
    }

	public void setFactory(ComponentsFactory factory) {
		requestService("setDisplay", factory.getDisplay());
		requestService("setCashBox", factory.getCashBox());
		requestService("setDispenser", Recipe.COFFEE_POWDER, factory.getCoffeePowderDispenser());
		requestService("setDispenser", CUP, factory.getCupDispenser());
		requestService("setDispenser", Recipe.WATER, factory.getWaterDispenser());
		requestService("setDrinkDispenser", factory.getDrinkDispenser());
		requestService("setDispenser", Recipe.SUGAR, factory.getSugarDispenser());
		requestService("setDispenser", Recipe.CREAMER, factory.getCreamerDispenser());
		requestService("setDispenser", Recipe.BOUILLON, factory.getBouillonDispenser());
		requestService("setPayrollSystem", factory.getPayrollSystem());
		requestService("setButtonDisplay", factory.getButtonDisplay());

		requestService("loadDefaultButtonConfiguration");
		requestService("initSession");
	}

	public void insertCoin(Coin coin) {
		requestService("insertCoin", coin);
	}

	public void cancel() {
		requestService("cancel");		
	}

	public void select(Button drink) {
		requestService("select", drink);
	}

	public void readBadge(int badgeCode) {
		requestService("readBadge", badgeCode);
	}

	public void configuteDrink(Button drink, Recipe recipe) {
		requestService("configuteDrink", drink, recipe);
	}

}
