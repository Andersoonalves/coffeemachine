package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Dispenser;

import java.util.HashMap;
import java.util.Map;

public class DispenserLogic {

	private final Map<String,Dispenser> dispensers = new HashMap<String, Dispenser>();
	private static DispenserLogic instance;

	public DispenserLogic() {
	}

	public static DispenserLogic getInstance() {
		if (instance == null)
			instance = new DispenserLogic();
		return instance;
	}

    public void setDispenser(String type, Dispenser dispenser) {
		dispensers.put(type, dispenser);
	}

    public boolean dispenserContains(String type, Object amount) {
		return dispensers.get(type).contains(amount);
	}

    public void releaseItem(String type, Object amount) {
		dispensers.get(type).release(amount);
	}
}
