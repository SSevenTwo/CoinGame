package model;

import model.interfaces.Coin;
import model.interfaces.CoinPair;

public class CoinPairImpl implements CoinPair {
	
	private CoinImpl coin1;
	private CoinImpl coin2;

	@Override
	public Coin getCoin1() {
		return this.coin1;
	}

	@Override
	public Coin getCoin2() {
		return this.coin2;
	}

	@Override
	public boolean equals(CoinPair coinPair) {
		if(this.coin1.equals(coinPair.getCoin1()) && this.coin2.equals(coinPair.getCoin2())){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.coin1 + ", " + this.coin2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coin1 == null) ? 0 : coin1.hashCode());
		result = prime * result + ((coin2 == null) ? 0 : coin2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoinPairImpl other = (CoinPairImpl) obj;
		if (coin1 == null) {
			if (other.coin1 != null)
				return false;
		} else if (!coin1.equals(other.coin1))
			return false;
		if (coin2 == null) {
			if (other.coin2 != null)
				return false;
		} else if (!coin2.equals(other.coin2))
			return false;
		return true;
	}
	
	

}
