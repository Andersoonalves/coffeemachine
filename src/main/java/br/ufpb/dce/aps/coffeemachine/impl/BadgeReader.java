package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.Messages;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class BadgeReader extends Component {

	private Integer badgeCode;

	public BadgeReader() {
		super("Badge reader manager");
	}

	@Service
	public void readBadge(Integer badgeCode) {
		this.badgeCode = badgeCode;
		requestService("displayInfo", Messages.BADGE_READ);
	}

	@Service
	public Integer getBadgeCode() {
		return badgeCode;
	}

}
