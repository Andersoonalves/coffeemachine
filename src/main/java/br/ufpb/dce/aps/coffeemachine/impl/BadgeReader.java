package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class BadgeReader extends Component {

	public BadgeReader() {
		super("Badge reader manager");
	}

	@Service
	public void readBadge(int badgeCode) {
		requestService("displayInfo", Messages.BADGE_READ);
	}

}
