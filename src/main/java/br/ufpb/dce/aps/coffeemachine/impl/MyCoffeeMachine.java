package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CoffeeMachine;
import br.ufpb.dce.aps.coffeemachine.Coin;
import br.ufpb.dce.aps.coffeemachine.ComponentsFactory;
import net.compor.frameworks.jcf.api.ComporFacade;

public class MyCoffeeMachine extends ComporFacade implements CoffeeMachine {

	public MyCoffeeMachine(ComponentsFactory factory) {
		requestService("setDisplay", factory.getDisplay());
		requestService("initSession");
	}
	
	@Override
    protected void addComponents() {
        add(new DisplayManager());
        add(new SessionManager());
        add(new CashManager());
    }

	public void insertCoin(Coin coin) {
		requestService("insertCoin", coin);
	}
}
