package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class BouillonLogic extends Component {

	public BouillonLogic() {
		super("Bouillon drink logic");
	}


	@Service
	public String planBouillon(int waterAmount) {
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.CUP, 1)) {
			return Messages.OUT_OF_CUP;						
		}
		
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.WATER, waterAmount)) {
			return Messages.OUT_OF_WATER;			
		}
		
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.BOUILLON, 10)) {
			return Messages.OUT_OF_BOUILLON_POWDER;
		}

		return null;
	}
	
	@Service
	public void mixBouillon(int waterAmount) {
		requestService("releaseItem", MyCoffeeMachine.BOUILLON, 10);
		requestService("releaseItem", MyCoffeeMachine.WATER, waterAmount);
	}

}
