package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class WhiteSugarLogic extends Component {

	public WhiteSugarLogic() {
		super("White with Sugar drink logic");
	}

	@Service
	public String planWhiteSugar(int waterAmount) {
		String warn = (String) requestService("planWhite", waterAmount);
		if (warn != null) {
			return warn;
		}

		if (!(Boolean) requestService("dispenserContains",
				MyCoffeeMachine.SUGAR, 5)) {
			return Messages.OUT_OF_SUGAR;
		}

		return null;
	}
	
	@Service
	public void mixWhiteSugar(int waterAmount) {
		requestService("mixWhite", waterAmount);
		requestService("releaseItem", MyCoffeeMachine.SUGAR, 5);
	}

}
