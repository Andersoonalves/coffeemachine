package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class BlackLogic extends Component {

	public BlackLogic() {
		super("Black drink logic");
	}


	@Service
	public boolean planBlack() {
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.CUP, 1)) {
			requestService("displayWarn", Messages.OUT_OF_CUP);
			return false;						
		}
		
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.WATER, 0.1)) {
			requestService("displayWarn", Messages.OUT_OF_WATER);
			return false;			
		}
		
		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.COFFEE_POWDER, 0.1)) {
			requestService("displayWarn", Messages.OUT_OF_COFFEE_POWDER);
			return false;
		}

		return true;
	}
	
	@Service
	public void mixBlack() {
		requestService("releaseItem", MyCoffeeMachine.COFFEE_POWDER, 0.1);
		requestService("releaseItem", MyCoffeeMachine.WATER, 0.1);
	}

}
