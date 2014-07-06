package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class WhiteSugarLogic extends Component {

	public WhiteSugarLogic() {
		super("White with Sugar drink logic");
	}

	@Service
	public boolean planWhiteSugar() {
		if (!(Boolean) requestService("planWhite")) {
			return false;
		}

		if (!(Boolean) requestService("dispenserContains",
				MyCoffeeMachine.SUGAR, 0.1)) {
			requestService("displayWarn", Messages.OUT_OF_SUGAR);
			return false;
		}

		return true;
	}
	
	@Service
	public void mixWhiteSugar() {
		requestService("mixWhite");
		requestService("releaseItem", MyCoffeeMachine.SUGAR, 0.1);
	}

}
