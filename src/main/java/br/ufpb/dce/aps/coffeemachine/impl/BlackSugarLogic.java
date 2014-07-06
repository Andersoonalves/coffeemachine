package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class BlackSugarLogic extends Component {

	public BlackSugarLogic() {
		super("Black with Sugar drink logic");
	}

	@Service
	public String planBlackSugar(int waterAmount) {
		String warn = (String) requestService("planBlack", waterAmount);
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
	public void mixBlackSugar(int waterAmount) {
		requestService("mixBlack", waterAmount);
		requestService("releaseItem", MyCoffeeMachine.SUGAR, 5);
	}

}
