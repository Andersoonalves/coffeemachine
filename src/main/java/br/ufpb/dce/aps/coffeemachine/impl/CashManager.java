package br.ufpb.dce.aps.coffeemachine.impl;

import br.ufpb.dce.aps.coffeemachine.CashBox;
import br.ufpb.dce.aps.coffeemachine.Coin;
import net.compor.frameworks.jcf.api.Component;
import net.compor.frameworks.jcf.api.Service;

public class CashManager extends Component {

	private CashBox cashBox;
	private int[] sessionCoins = new int[Coin.reverse().length];
	
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
    public int[] getSessionCoins() {
		return sessionCoins;
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
    public void catchSessionMoney() {
		for (int i = 0; i < sessionCoins.length; i++) {
			sessionCoins[i] = 0;
		}
	}

	@Service
    public void releaseChange(int[] arrayChange) {
		Coin[] reverse = Coin.reverse();
		
		for (int i = 0; i < arrayChange.length; i++) {
			int coinNumber = arrayChange[i];
			for (int j = 0; j < coinNumber; j++) {
				cashBox.release(reverse[i]);
				sessionCoins[i]--;
			}
		}
    }
}
