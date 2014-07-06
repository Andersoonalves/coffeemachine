package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

import java.util.HashMap;
import java.util.Map;

public class DrinkManagerLogic {

	private final Map<Drink, DrinkLogic> mapDrinks = new HashMap<Drink, DrinkLogic>();
	private final DisplayLogic displayLogic;
	private final DispenserLogic dispenserLogic;
	private final DrinkDispenserLogic drinkDispenserLogic;

	public DrinkManagerLogic() {

		mapDrinks.put(Drink.BLACK, new BlackLogic());
		mapDrinks.put(Drink.BLACK_SUGAR, new BlackSugarLogic());
		mapDrinks.put(Drink.WHITE, new WhiteLogic());
		mapDrinks.put(Drink.WHITE_SUGAR, new WhiteSugarLogic());
		displayLogic = DisplayLogic.getInstance();
		dispenserLogic = DispenserLogic.getInstance();
		drinkDispenserLogic = DrinkDispenserLogic.getInstance();
	}

	public void select(Drink drink) {

		DrinkLogic drinkLogic = mapDrinks.get(drink);

		if (!drinkLogic.checkDispenser()) {
			return;
		}

		if (!CashBoxLogic.getInstance().checkChange(35)) {
			CashBoxLogic.getInstance().abortSession();
			return;
		}
		drinkLogic.mix();
		displayLogic.info(Messages.RELEASING);
		dispenserLogic.releaseItem(TypeDispenser.CUP.getValue(), 1);
		drinkDispenserLogic.releaseDrink(0.1);
		displayLogic.info(Messages.TAKE_DRINK);
		CashBoxLogic.getInstance().finishSession();
	}
}
