package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.DrinkDispenser;

public class DrinkDispenserLogic {

	private DrinkDispenser drinkDispenser;
	
	private static DrinkDispenserLogic instance;

	public DrinkDispenserLogic() {
	}

	public static DrinkDispenserLogic getInstance(){
		if (instance == null)
			instance = new DrinkDispenserLogic();
		return instance;
	}

	public void setDrinkDispenser(DrinkDispenser drinkDispenser) {
		this.drinkDispenser = drinkDispenser;
	}

	public void releaseDrink(double milliliters) {
		drinkDispenser.release(milliliters);
	}
}
