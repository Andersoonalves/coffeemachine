package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;

public class DrinkLogic {

	private String planService;
	private String mixService;
	private Component component;
	
	

	public DrinkLogic(String planService, String mixService, Component component) {
		this.planService = planService;
		this.mixService = mixService;
		this.component = component;
	}

	public boolean plan() {
		if (! (Boolean) component.requestService(planService)) {
			component.requestService("abortSession");
			return false;						
		}
		
		return true;
	}
	
	public void mix() {
		component.requestService("displayInfo", Messages.MIXING);
		component.requestService(mixService);
	}

}
