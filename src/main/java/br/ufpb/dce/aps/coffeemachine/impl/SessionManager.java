package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.CoffeeMachineException;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class SessionManager extends Component {

	public SessionManager() {
		super("Session manager");
	}

	@Service
    public void initSession() {
		requestService("displayInfo", Messages.INSERT_COINS);
	}

	@Service
    public void finishSession() {
		requestService("releaseChange");
		requestService("resetSessionMoney");
		initSession();
	}

	@Service(name="insertCoin")
    public void insert(Coin coin) {
		if (coin == null) {
			throw new CoffeeMachineException("Invalid null coin");
		}

		int total = (Integer) requestService("addSessionMoney", coin);
		
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
		
		requestService("displayWarn", Messages.CANCEL);
		requestService("giveBackCoins");
		requestService("resetSessionMoney");
		requestService("initSession");
	}

	@Service
    public void abortSession(String warn) {
		requestService("displayWarn", warn);
		requestService("giveBackCoins");
		requestService("resetSessionMoney");
		requestService("initSession");
	}

}
