package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.Recipe;

public class DrinkLogic {

	private Component component;
	private Recipe recipe;
	private String[] planSequence;
	private String[] mixSequence;

	public DrinkLogic(Recipe recipe, Component component) {
		this.recipe = recipe;
		this.component = component;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setPlanSequence(String... planSequence) {
		this.planSequence = planSequence;
	}

	public void setMixSequence(String... mixSequence) {
		this.mixSequence = mixSequence;
	}

	public boolean plan() {
		if (!(Boolean) component.requestService("dispenserContains",
				MyCoffeeMachine.CUP, 1)) {
			component.requestService("abortSession", Messages.OUT_OF_CUP);
			return false;
		}

		for (String ingredient : planSequence) {
			Double quantity = recipe.getIngredientQuantity(ingredient);
			
			if (!(Boolean) component.requestService("dispenserContains", ingredient, quantity)) {
				component.requestService("abortSession", "Out of " + ingredient);
				return false;
			}
		}

		return true;
	}

	public void mix() {
		component.requestService("displayInfo", Messages.MIXING);

		for (String ingredient : mixSequence) {
			Double quantity = recipe.getIngredientQuantity(ingredient);
			component.requestService("releaseItem", ingredient, quantity);
		}
	}

	public String getName() {
		return recipe.getName();
	}

	public void setPrice(int price) {
		this.recipe.setPriceCents(price);
	}

}
