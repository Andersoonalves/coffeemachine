package br.ufpb.dce.aps.coffeemachine.impl;

public class WhiteLogic extends DrinkLogic{

	private static WhiteLogic instance;
	private final DispenserLogic dispenserLogic = DispenserLogic.getInstance();
	
	public static WhiteLogic getInstance(){
		if(instance == null)
			instance = new WhiteLogic();
		return instance;
	}

	@Override
	public Boolean checkDispenser() {

		BlackLogic.getInstance().checkDispenser();

		dispenserLogic.dispenserContains(TypeDispenser.CREAMER.getValue(), 0.1);

		return true;
	}

	@Override
	public void mix() {
		BlackLogic.getInstance().mix();
		dispenserLogic.releaseItem(TypeDispenser.CREAMER.getValue(), 0.1);
	}
}
