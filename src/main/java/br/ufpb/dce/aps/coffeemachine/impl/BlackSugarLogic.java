package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class BlackSugarLogic extends Component {

	public BlackSugarLogic() {
		super("Black with Sugar drink logic");
	}

	@Service
	public String planBlackSugar() {
		String warn = (String) requestService("planBlack");
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
	public void mixBlackSugar() {
		requestService("mixBlack");
		requestService("releaseItem", MyCoffeeMachine.SUGAR, 0.1);
	}

}
