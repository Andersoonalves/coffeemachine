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

		logics.put(Drink.BLACK, new DrinkLogic("planBlack", "mixBlack", this));
		logics.put(Drink.BLACK_SUGAR, new DrinkLogic("planBlackSugar",
				"mixBlackSugar", this));
		logics.put(Drink.WHITE, new DrinkLogic("planWhite", "mixWhite", this));
		logics.put(Drink.WHITE_SUGAR, new DrinkLogic("planWhiteSugar",
				"mixWhiteSugar", this));
	}

	@Service
	public void select(Drink drink) {
		int drinkValue = 35;
		
		if(! (Boolean) requestService("checkMoney", drinkValue)) {
			requestService("abortSession", Messages.NO_ENOUGHT_MONEY);
			return;
		}
		
		DrinkLogic drinkLogic = logics.get(drink);

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
