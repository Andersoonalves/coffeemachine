package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.Messages;

public class DrinkManager extends Component {

	public DrinkManager() {
		super("Drink manager");
	}
	
	@Service
	public void loadDefaultButtonConfiguration() {
		requestService("configureButton", Button.BUTTON_1, new DrinkLogic("Black", 35, "planBlack", "mixBlack", 100, this));
		requestService("configureButton", Button.BUTTON_3, new DrinkLogic("Black with sugar", 35, "planBlackSugar", 
				"mixBlackSugar", 100, this));
		requestService("configureButton", Button.BUTTON_2, new DrinkLogic("White", 35, "planWhite", "mixWhite", 80, this));
		requestService("configureButton", Button.BUTTON_4, new DrinkLogic("White with sugar", 35, "planWhiteSugar", 
				"mixWhiteSugar", 80, this));
		requestService("configureButton", Button.BUTTON_5, new DrinkLogic("Bouillon", 25, "planBouillon", "mixBouillon", 100, this));
		
		requestService("showButtons");
	}

	@Service
	public void select(Button drink) {
		DrinkLogic drinkLogic = (DrinkLogic) requestService("getButtonConfiguration", drink);
		int drinkValue = drinkLogic.getPrice();

		Integer badgeCode = (Integer) requestService("getBadgeCode");

		if ( badgeCode == null ) {

			if(! (Boolean) requestService("checkMoney", drinkValue)) {
				requestService("abortSession", Messages.NO_ENOUGHT_MONEY);
				return;
			}
			
			planChangeMixRelease(drinkLogic, drinkValue);				

		} else {
			planDebitMixRelease(drinkLogic, drinkValue, badgeCode);				
		}
	}
	
	@Service
	public void setPrice(Button drink, int priceCents) {
		DrinkLogic drinkLogic = (DrinkLogic) requestService("getButtonConfiguration", drink);
		drinkLogic.setPrice(priceCents);
		requestService("showButtons");
	}


	private void planDebitMixRelease(DrinkLogic drinkLogic, int drinkValue, int badgeCode) {
		if (!drinkLogic.plan()) {
			return;
		}
		
		if ( ! (Boolean) requestService("debit", drinkValue, badgeCode)) {
			requestService("displayWarn", Messages.UNKNOWN_BADGE_CODE);
			requestService("initSession");
			return;
		}
		
		drinkLogic.mix();
		release();
	}

	private void planChangeMixRelease(DrinkLogic drinkLogic, int drinkValue) {
		if (!drinkLogic.plan()) {
			return;
		}
		
		if ( ! (Boolean) requestService("planChange", drinkValue)) {
			requestService("abortSession", Messages.NO_ENOUGHT_CHANGE);
			return;
		}
		
		drinkLogic.mix();
		release();
	}

	private void release() {
		requestService("displayInfo", Messages.RELEASING);
		requestService("releaseItem", MyCoffeeMachine.CUP, 1);
		requestService("releaseDrink", 100);
		requestService("displayInfo", Messages.TAKE_DRINK);
		requestService("finishSession");
	}

}
