package br.ufpb.dce.aps.coffeemachine;

public class MyCoffeeMachineTest extends CoffeeMachineTest {

	@Override
	protected CoffeeMachine createFacade(ComponentsFactory factory) {
		return new CoffeeMachineImplementation(factory);
	}

}
