package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class DrinkManager extends Component {

	public DrinkManager() {
		super("Drink manager");
	}
	
	@Service
	public void loadDefaultButtonConfiguration() {
		requestService("configureButton", Button.BUTTON_1, defaultBlackRecipe());
		requestService("configureButton", Button.BUTTON_3, defaultBlackSugarRecipe());
		requestService("configureButton", Button.BUTTON_2, defaultWhiteRecipe());
		requestService("configureButton", Button.BUTTON_4, defaultWhiteSugarRecipe());
		requestService("configureButton", Button.BUTTON_5, defaultBouillonRecipe());
		
		requestService("showButtons");
	}

	private DrinkLogic defaultBlackRecipe() {
		Recipe black = new Recipe();
		black.setName("Black");
		black.setPriceCents(35);
		black.setItem(Recipe.WATER, 100.0);
		black.setItem(Recipe.COFFEE_POWDER, 15.0);
		
		DrinkLogic logic = new DrinkLogic(black, this);
		logic.setPlanSequence(Recipe.WATER, Recipe.COFFEE_POWDER);
		logic.setMixSequence(Recipe.COFFEE_POWDER, Recipe.WATER);

		return logic;
	}

	private DrinkLogic defaultBlackSugarRecipe() {
		Recipe blackSugar = new Recipe();
		blackSugar.setName("Black with sugar");
		blackSugar.setPriceCents(35);
		blackSugar.setItem(Recipe.WATER, 100.0);
		blackSugar.setItem(Recipe.COFFEE_POWDER, 15.0);
		blackSugar.setItem(Recipe.SUGAR, 5.0);

		DrinkLogic logic = new DrinkLogic(blackSugar, this);
		logic.setPlanSequence(Recipe.WATER, Recipe.COFFEE_POWDER, Recipe.SUGAR);
		logic.setMixSequence(Recipe.COFFEE_POWDER, Recipe.WATER, Recipe.SUGAR);
		
		return logic;
	}

	private DrinkLogic defaultWhiteRecipe() {
		Recipe white = new Recipe();
		white.setName("White");
		white.setPriceCents(35);
		white.setItem(Recipe.WATER, 80.0);
		white.setItem(Recipe.COFFEE_POWDER, 15.0);
		white.setItem(Recipe.CREAMER, 20.0);

		DrinkLogic logic = new DrinkLogic(white, this);
		logic.setPlanSequence(Recipe.WATER, Recipe.COFFEE_POWDER, Recipe.CREAMER);
		logic.setMixSequence(Recipe.COFFEE_POWDER, Recipe.WATER, Recipe.CREAMER);
		
		return logic;
	}

	private DrinkLogic defaultWhiteSugarRecipe() {
		Recipe whiteSugar = new Recipe();
		whiteSugar.setName("White with sugar");
		whiteSugar.setPriceCents(35);
		whiteSugar.setItem(Recipe.WATER, 80.0);
		whiteSugar.setItem(Recipe.COFFEE_POWDER, 15.0);
		whiteSugar.setItem(Recipe.CREAMER, 20.0);
		whiteSugar.setItem(Recipe.SUGAR, 5.0);

		DrinkLogic logic = new DrinkLogic(whiteSugar, this);
		logic.setPlanSequence(Recipe.WATER, Recipe.COFFEE_POWDER, Recipe.CREAMER, Recipe.SUGAR);
		logic.setMixSequence(Recipe.COFFEE_POWDER, Recipe.WATER, Recipe.CREAMER, Recipe.SUGAR);
		
		return logic;
	}

	private DrinkLogic defaultBouillonRecipe() {
		Recipe bouillon = new Recipe();
		bouillon.setName("Bouillon");
		bouillon.setPriceCents(25);
		bouillon.setItem(Recipe.WATER, 100.0);
		bouillon.setItem(Recipe.BOUILLON, 10.0);
		
		DrinkLogic logic = new DrinkLogic(bouillon, this);
		logic.setPlanSequence(Recipe.WATER, Recipe.BOUILLON);
		logic.setMixSequence(Recipe.BOUILLON, Recipe.WATER);

		return logic;
	}

	@Service
	public void select(Button drink) {
		DrinkLogic drinkLogic = (DrinkLogic) requestService("getButtonConfiguration", drink);
		int drinkValue = drinkLogic.getRecipe().getPriceCents();

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
	public void configuteDrink(Button drink, Recipe recipe) {
		DrinkLogic drinkLogic = (DrinkLogic) requestService("getButtonConfiguration", drink);
		drinkLogic.setRecipe(recipe);
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
		requestService("releaseDrink", 100.0);
		requestService("displayInfo", Messages.TAKE_DRINK);
		requestService("finishSession");
	}

}
