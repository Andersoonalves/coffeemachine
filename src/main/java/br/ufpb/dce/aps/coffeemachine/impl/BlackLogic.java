package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class BlackLogic extends Component {

	public BlackLogic() {
		super("Black drink logic");
	}


	@Service
	public String planBlack(int waterAmount) {
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.CUP, 1)) {
			return Messages.OUT_OF_CUP;						
		}
		
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.WATER, waterAmount)) {
			return Messages.OUT_OF_WATER;			
		}
		
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.COFFEE_POWDER, 15)) {
			return Messages.OUT_OF_COFFEE_POWDER;
		}

		return null;
	}
	
	@Service
	public void mixBlack(int waterAmount) {
		requestService("releaseItem", MyCoffeeMachine.COFFEE_POWDER, 15);
		requestService("releaseItem", MyCoffeeMachine.WATER, waterAmount);
	}

}
