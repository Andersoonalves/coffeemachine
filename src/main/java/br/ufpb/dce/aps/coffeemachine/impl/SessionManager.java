package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;

public class SessionManager extends Component {

	public SessionManager() {
		super("Session manager");
	}

	@Service
    public void initSession() {
		requestService("displayInfo", "Insert coins and select a drink!");
	}

	@Service(name="insertCoin")
    public void insert(Coin coin) {
		if (coin == null) {
			throw new CoffeeMachineException("Invalid null coin");
		}

		int total = (Integer) requestService("addSessionMoney", coin.getValue());
		
		int number = total / 100;
		int decimals = total % 100;
		requestService("displayInfo", "Total: US$ " + number + "." + decimals);
	}
	
	@Service
    public void cancel() {
		int change = (Integer) requestService("getSessionMoney");
		
		if (change == 0) {
			throw new CoffeeMachineException("There are not inserted coins!");
		}
	}

}
