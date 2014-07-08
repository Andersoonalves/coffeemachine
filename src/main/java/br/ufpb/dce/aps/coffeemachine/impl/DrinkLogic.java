package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;

public class DrinkLogic {

	private String planService;
	private String mixService;
	private Component component;
	private int waterAmount;
	private int price;
	private String name;

	public DrinkLogic(String name, int price, String planService, String mixService,
			int waterAmount, Component component) {
		this.name = name;
		this.price = price;
		this.planService = planService;
		this.mixService = mixService;
		this.waterAmount = waterAmount;
		this.component = component;
	}

	public int getPrice() {
		return price;
	}

	public boolean plan() {
		String warn = (String) component.requestService(planService,
				waterAmount);
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

	public String getName() {
		return name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
