package br.ufpb.dce.aps.coffeemachine;

import br.ufpb.dce.aps.coffeemachine.impl.CoffeeMachineImpl;

public class MyCoffeeMachineTest extends CoffeeMachineTest {

	@Override
	protected CoffeeMachine createFacade(ComponentsFactory factory) {
		return new CoffeeMachineImpl(factory);
	}

}
