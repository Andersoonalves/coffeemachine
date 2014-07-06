package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Display;

public class DisplayLogic {

	private Display display;
	private static DisplayLogic instance;

	public DisplayLogic() {
	}

	public static DisplayLogic getInstance() {
		if (instance == null)
			instance = new DisplayLogic();
		return instance;
	}

	public void setDisplay(Display display) {
		this.display = display;
	}

	public void info(String msg) {
		display.info(msg);
	}

	public void warn(String msg) {
		display.warn(msg);
	}
}
