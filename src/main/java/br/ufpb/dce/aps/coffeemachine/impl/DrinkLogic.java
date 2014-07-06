package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;

public class DrinkLogic {

	private String planService;
	private String mixService;
	private Component component;
	private int waterAmount;
	
	

	public DrinkLogic(String planService, String mixService, int waterAmount, Component component) {
		this.planService = planService;
		this.mixService = mixService;
		this.waterAmount = waterAmount;
		this.component = component;
	}

	public boolean plan() {
		String warn = (String) component.requestService(planService, waterAmount);
		if (warn != null) {
			component.requestService("abortSession", warn);
			return false;						
		}
		
		return true;
	}
	
	public void mix() {
		component.requestService("displayInfo", Messages.MIXING);
		component.requestService(mixService, waterAmount);
	}

}
