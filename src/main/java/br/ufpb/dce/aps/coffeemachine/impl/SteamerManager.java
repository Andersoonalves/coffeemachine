package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Steamer;

public class SteamerManager extends Component {

	private Steamer steamer;

	public SteamerManager() {
		super("Steamer manager");
	}

	@Service
	public void setSteamer(Steamer steamer) {
		this.steamer = steamer;
	}

	@Service
	public void steam() {
		steamer.steam();
	}
}
