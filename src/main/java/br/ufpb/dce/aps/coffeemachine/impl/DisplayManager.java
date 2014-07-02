package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Display;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class DisplayManager extends Component {

	private Display display;

	public DisplayManager() {
		super("Display manager");
	}

	@Service(name="setDisplay")
    public void setDisplay(Display display) {
		this.display = display;
	}

	@Service(name="displayInfo")
    public void info(String msg) {
		display.info(msg);
    }
	
	@Service(name="displayWarn")
    public void warn(String msg) {
		display.warn(msg);
    }

}
