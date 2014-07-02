package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
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
		int total = (Integer) requestService("addSessionMoney", coin.getValue());
		
		int number = total / 100;
		int decimals = total % 100;
		requestService("displayInfo", "Total: US$ " + number + "." + decimals);
	}
}
