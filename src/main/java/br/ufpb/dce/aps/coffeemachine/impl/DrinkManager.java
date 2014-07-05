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
	}

	@Service
	public void select(Drink drink) {
		DrinkLogic drinkLogic = logics.get(drink);

		if (drinkLogic.run()) {

			// Release
			requestService("displayInfo", Messages.RELEASING);
			requestService("releaseItem", MyCoffeeMachine.CUP, 1);
			requestService("releaseDrink", 0.1);
			requestService("displayInfo", Messages.TAKE_DRINK);
			requestService("finishSession");
		}
	}

}
