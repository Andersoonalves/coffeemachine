package br.ufpb.dce.aps.coffeemachine;

public class CoffeeMachineImplementation implements CoffeeMachine {
	private final Display display;

	public CoffeeMachineImplementation(ComponentsFactory factory) {
		this.display = factory.getDisplay();
		this.display.info("Insert coins and select a drink!");
	}
}
