package br.ufpb.dce.aps.coffeemachine.impl;

import java.util.HashMap;
import java.util.Map;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Dispenser;

public class DispenserManager extends Component {

	private Map<String,Dispenser> dispensers = new HashMap<String, Dispenser>();
	
	
	public DispenserManager() {
		super("Dispenser manager");
	}

	@Service
    public void setDispenser(String type, Dispenser dispenser) {
		dispensers.put(type, dispenser);
	}

	@Service
    public boolean dispenserContains(String type, Object amount) {
		return dispensers.get(type).contains(amount);
	}

	@Service
    public void releaseItem(String type, Object amount) {
		dispensers.get(type).release(amount);
	}
}
