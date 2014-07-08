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
		Integer sessionMoney = (Integer) requestService("getSessionMoney");
		
		if (sessionMoney == 0) {
			this.badgeCode = sessionMoney;
			requestService("displayInfo", Messages.BADGE_READ);
			
		} else {
			requestService("displayWarn", Messages.CAN_NOT_READ_BADGE);			
		}
	}

	@Service
	public Integer getBadgeCode() {
		return badgeCode;
	}

}
