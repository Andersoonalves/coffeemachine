package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.Coin;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class CashManager extends Component {

	private CashBox cashBox;
	private int[] sessionCoins = new int[Coin.reverse().length];
	private int[] changePlan = new int[Coin.reverse().length];
	
	public CashManager() {
		super("Cash manager");
	}
	
	@Service
    public void setCashBox(CashBox cashBox) {
		this.cashBox = cashBox;
	}

	@Service(name="addSessionMoney")
    public Integer add(Coin coin) {
		Coin[] reverse = Coin.reverse();
		
		for (int i = 0; i < reverse.length; i++) {
			if (coin.equals(reverse[i])) {
				sessionCoins[i]++;
			}
		}
		
		return getSessionMoney();
    }

	@Service
    public void giveBackCoins() {
		changePlan = sessionCoins;
		releaseChange();
	}

	@Service
    public Integer getSessionMoney() {
		int result = 0;
		Coin[] reverse = Coin.reverse();
		
		for (int i = 0; i < sessionCoins.length; i++) {
			int coinNumber = sessionCoins[i];
			result += coinNumber * reverse[i].getValue();
		}
		
		return result;
    }
	
	@Service
    public void resetSessionMoney() {
		for (int i = 0; i < sessionCoins.length; i++) {
			sessionCoins[i] = 0;
			changePlan[i] = 0;
		}
	}

	@Service
    public void releaseChange() {
		Coin[] reverse = Coin.reverse();
				
		for (int i = 0; i < changePlan.length; i++) {
			int coinNumber = changePlan[i];
			for (int j = 0; j < coinNumber; j++) {
				cashBox.release(reverse[i]);
				sessionCoins[i]--;
			}
		}
    }
	
	@Service
	public boolean planChange(int cost) {
		
		int change = getSessionMoney() - cost;
		
		Coin[] reverse = Coin.reverse();
		int[] arrayChange = new int[Coin.values().length];
		for (int i = 0; i < reverse.length; i++) {
			Coin coin = reverse[i];
			int coinValue = coin.getValue();
			if (change >= coinValue) {
				int count = cashBox.count(coin);

				while (count > 0 && change >= coinValue) {
					change -= coinValue;
					arrayChange[i]++;
					count--;
				}
			}
		}
		
		if (change > 0) {
			return false;
		}
		
		changePlan = arrayChange;
		return true;
	}
	
}
