package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class WhiteLogic extends Component {

	public WhiteLogic() {
		super("White drink logic");
	}

	@Service
	public String planWhite() {
		String warn = (String) requestService("planBlack");
		if (warn != null) {
			return warn;
		}

		requestService("dispenserContains", MyCoffeeMachine.CREAMER, 0.1);

		return null;
	}

	@Service
	public void mixWhite() {
		requestService("mixBlack");
		requestService("releaseItem", MyCoffeeMachine.CREAMER, 0.1);
	}

}
