package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;

public class DrinkDispenserManager extends Component {

	private DrinkDispenser drinkDispenser;

	public DrinkDispenserManager() {
		super("Drink dispenser manager");
	}

	@Service
	public void setDrinkDispenser(DrinkDispenser drinkDispenser) {
		this.drinkDispenser = drinkDispenser;
	}

	@Service
	public void releaseDrink(Double milliliters) {
		drinkDispenser.release(milliliters);
	}
}
