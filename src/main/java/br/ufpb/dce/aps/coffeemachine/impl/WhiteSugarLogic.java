package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class WhiteSugarLogic extends Component {

	public WhiteSugarLogic() {
		super("White with Sugar drink logic");
	}

	@Service
	public String planWhiteSugar() {
		String warn = (String) requestService("planWhite");
		if (warn != null) {
			return warn;
		}

		if (!(Boolean) requestService("dispenserContains",
				MyCoffeeMachine.SUGAR, 0.1)) {
			return Messages.OUT_OF_SUGAR;
		}

		return null;
	}
	
	@Service
	public void mixWhiteSugar() {
		requestService("mixWhite");
		requestService("releaseItem", MyCoffeeMachine.SUGAR, 0.1);
	}

}
