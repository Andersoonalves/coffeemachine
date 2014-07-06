package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class WhiteLogic extends Component {

	public WhiteLogic() {
		super("White drink logic");
	}

	@Service
	public String planWhite(int waterAmount) {
		String warn = (String) requestService("planBlack", waterAmount);
		if (warn != null) {
			return warn;
		}

		if (! (Boolean) requestService("dispenserContains", MyCoffeeMachine.CREAMER, 20)) {
			return Messages.OUT_OF_CREAMER;
		}

		return null;
	}

	@Service
	public void mixWhite(int waterAmount) {
		requestService("mixBlack", waterAmount);
		requestService("releaseItem", MyCoffeeMachine.CREAMER, 20);
	}

}
