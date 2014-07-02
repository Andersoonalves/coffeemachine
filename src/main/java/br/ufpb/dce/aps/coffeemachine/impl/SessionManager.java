package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class SessionManager extends Component {

	public SessionManager() {
		super("Session manager");
	}

	@Service
    public void initSession() {
		requestService("displayInfo", "Insert coins and select a drink!");
	}

}
