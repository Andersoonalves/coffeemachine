package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;

public class WhiteSugarLogic extends DrinkLogic{

	private final DispenserLogic dispenserLogic = DispenserLogic.getInstance();

	@Override
	public Boolean checkDispenser() {

		WhiteLogic.getInstance().checkDispenser();

		if (!dispenserLogic.dispenserContains(TypeDispenser.SUGAR.getValue(), 0.1)){
			DisplayLogic.getInstance().warn(Messages.OUT_OF_SUGAR);
			CashBoxLogic.getInstance().abortSession();
			return false;
		}
		return true;
	}

	@Override
	public void mix() {
		WhiteLogic.getInstance().mix();
		dispenserLogic.releaseItem(TypeDispenser.SUGAR.getValue(), 0.1);
	}
}
