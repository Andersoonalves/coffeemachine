package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class DrinkManager extends Component {

	private Map<Drink, DrinkLogic> logics = new HashMap<Drink, DrinkLogic>();

	public DrinkManager() {
		super("Drink manager");

		logics.put(Drink.BLACK, new DrinkLogic(35, "planBlack", "mixBlack", 100, this));
		logics.put(Drink.BLACK_SUGAR, new DrinkLogic(35, "planBlackSugar", 
				"mixBlackSugar", 100, this));
		logics.put(Drink.WHITE, new DrinkLogic(35, "planWhite", "mixWhite", 80, this));
		logics.put(Drink.WHITE_SUGAR, new DrinkLogic(35, "planWhiteSugar", 
				"mixWhiteSugar", 80, this));
		logics.put(Drink.BOUILLON, new DrinkLogic(25, "planBouillon", "mixBouillon", 100, this));
	}

	@Service
	public void select(Drink drink) {
		DrinkLogic drinkLogic = logics.get(drink);
		int drinkValue = drinkLogic.getPrice();
		
		if(! (Boolean) requestService("checkMoney", drinkValue)) {
			requestService("abortSession", Messages.NO_ENOUGHT_MONEY);
			return;
		}

		if (!drinkLogic.plan()) {
			return;
		}
		
		if ( ! (Boolean) requestService("planChange", drinkValue)) {
			requestService("abortSession", Messages.NO_ENOUGHT_CHANGE);
			return;
		}
		
		drinkLogic.mix();
		
		// Release
		requestService("displayInfo", Messages.RELEASING);
		requestService("releaseItem", MyCoffeeMachine.CUP, 1);
		requestService("releaseDrink", 100);
		requestService("displayInfo", Messages.TAKE_DRINK);
		requestService("finishSession");				
	}

}
