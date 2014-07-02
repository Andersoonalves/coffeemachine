package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Drink;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class DrinkManager extends Component {

	public DrinkManager() {
		super("Drink manager");
	}

	@Service
    public void select(Drink drink) {
		
		//Plan
		requestService("dispenserContains", MyCoffeeMachine.CUP, 1);
		requestService("dispenserContains", MyCoffeeMachine.WATER, 0.1);
		requestService("dispenserContains", MyCoffeeMachine.COFFEE_POWDER, 0.1);
		
		//Mix
		requestService("displayInfo", Messages.MIXING);
		requestService("releaseItem", MyCoffeeMachine.COFFEE_POWDER, 0.1);
		requestService("releaseItem", MyCoffeeMachine.WATER, 0.1);
		
		//Release
		requestService("displayInfo", Messages.RELEASING);
		requestService("releaseItem", MyCoffeeMachine.CUP, 1);
		requestService("releaseDrink", 0.1);
		requestService("displayInfo", Messages.TAKE_DRINK);
		requestService("initSession");
	}

}
