package br.ufpb.dce.aps.coffeemachine.impl;

import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;
import br.ufpb.dce.aps.coffeemachine.Messages;
import br.ufpb.dce.aps.coffeemachine.PayrollSystem;

public class BadgeReader extends Component {

	private Integer badgeCode;
	private PayrollSystem payrollSystem;

	public BadgeReader() {
		super("Badge reader manager");
	}

	@Service
	public void readBadge(Integer badgeCode) {
		Integer sessionMoney = (Integer) requestService("getSessionMoney");
		
		if (sessionMoney == 0) {
			this.badgeCode = badgeCode;
			requestService("displayInfo", Messages.BADGE_READ);
			
		} else {
			requestService("displayWarn", Messages.CAN_NOT_READ_BADGE);			
		}
	}

	@Service
	public Integer getBadgeCode() {
		return badgeCode;
	}

	@Service
	public void setPayrollSystem(PayrollSystem payrollSystem) {
		this.payrollSystem = payrollSystem;
	}
	
	@Service
	public Boolean debit(int cents, int badgeCode) {
		return payrollSystem.debit(cents, badgeCode);
	}

}
