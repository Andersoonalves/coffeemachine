package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Button;
import br.ufpb.dce.aps.coffeemachine.ButtonDisplay;

public class ButtonManager extends Component {

	private ButtonDisplay buttonDisplay;
	private DrinkLogic[] configurations = new DrinkLogic[7];

	public ButtonManager() {
		super("Button manager");
	}

	@Service
	public void setButtonDisplay(ButtonDisplay buttonDisplay) {
		this.buttonDisplay = buttonDisplay;
	}

	@Service
	public void configureButton(Button button, DrinkLogic drinkLogic) {
		configurations[button.getPosition()-1] = drinkLogic;
	}

	@Service
	public DrinkLogic getButtonConfiguration(Button button) {
		return configurations[button.getPosition()-1];
	}

	@Service
	public void showButtons() {
		buttonDisplay.show(s(0), s(1), s(2), s(3), s(4), s(5), s(6));
	}

	private String s(int i) {
		DrinkLogic drinkLogic = configurations[i];
		
		if (drinkLogic == null) {
			return null;
		}
		
		return drinkLogic.getName() + ": $0." + drinkLogic.getRecipe().getPriceCents();
	}
}
