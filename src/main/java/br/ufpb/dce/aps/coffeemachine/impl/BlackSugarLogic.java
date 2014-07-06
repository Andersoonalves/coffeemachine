package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;

public class BlackSugarLogic extends DrinkLogic{

	private final DisplayLogic displayLogic = DisplayLogic.getInstance();
	private final DispenserLogic dispenserLogic = DispenserLogic.getInstance();

	@Override
	public Boolean checkDispenser() {

		if (!dispenserLogic.dispenserContains(TypeDispenser.CUP.getValue(), 1)){
			displayLogic.warn(Messages.OUT_OF_CUP);
			CashBoxLogic.getInstance().abortSession();
			return false;
		}
		
		
		if (!dispenserLogic.dispenserContains(TypeDispenser.WATER.getValue(), 0.1)){
			displayLogic.warn(Messages.OUT_OF_WATER);
			CashBoxLogic.getInstance().abortSession();
			return false;
		}
		
		
		if (!dispenserLogic.dispenserContains(TypeDispenser.COFFEE_POWDER.getValue(), 0.1)){
			displayLogic.warn(Messages.OUT_OF_COFFEE_POWDER);
			CashBoxLogic.getInstance().abortSession();
			return false;
		}
		
		if (!dispenserLogic.dispenserContains(TypeDispenser.SUGAR.getValue(), 0.1)){
			displayLogic.warn(Messages.OUT_OF_SUGAR);
			CashBoxLogic.getInstance().abortSession();
			return false;
		}

		return true;
	}

	@Override
	public void mix() {
		BlackLogic.getInstance().mix();
		dispenserLogic.releaseItem(TypeDispenser.SUGAR.getValue(), 0.1);
	}
}
