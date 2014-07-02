package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class CashManager extends Component {

	private int sessionCents = 0;
	
	public CashManager() {
		super("Cash manager");
	}

	@Service(name="addSessionMoney")
    public Integer add(Integer cents) {
		sessionCents += cents;
		return sessionCents;
    }

}
