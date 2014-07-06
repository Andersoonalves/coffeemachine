package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;

public class BlackLogic extends DrinkLogic {

	private static BlackLogic instance;
	private final DisplayLogic displayLogic = DisplayLogic.getInstance();
	private final DispenserLogic dispenserLogic = DispenserLogic.getInstance();

	public static BlackLogic getInstance(){
		if(instance == null)
			instance = new BlackLogic();
		return instance;
	}
	
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
		return true;
	}

	@Override
	public void mix() {
		displayLogic.info(Messages.MIXING);
		dispenserLogic.releaseItem(TypeDispenser.COFFEE_POWDER.getValue(), 0.1);
		dispenserLogic.releaseItem(TypeDispenser.WATER.getValue(), 0.1);
	}
}
