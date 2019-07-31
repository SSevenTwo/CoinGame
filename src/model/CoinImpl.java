package model;

import java.util.Random;

import model.enumeration.RandomEnumGenerator;
import model.enumeration.CoinFace;
import model.interfaces.Coin;

public class CoinImpl implements Coin {
	
	private int number;
	private CoinFace face;
	
	public CoinImpl(int number) {
		this.number = number;
		this.face = RandomEnumGenerator.randomEnum(CoinFace.class);
		
	}

	@Override
	public int getNumber() {
		return this.number;
	}

	@Override
	public CoinFace getFace() {
		return this.face;
	}

	@Override
	public void flip() {
		if(this.face.equals(CoinFace.HEADS)) {
			this.face = CoinFace.TAILS;
		}
		else this.face = CoinFace.HEADS;
		
	}
	
	@Override
	public boolean equals(Coin coin) {
		if(this.face.equals(coin.getFace())) {
			return true;
		}
		else return false;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((face == null) ? 0 : face.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object coin) {
		if (this == coin)
			return true;
		if (coin == null)
			return false;
		if (getClass() != coin.getClass())
			return false;
		CoinImpl other = (CoinImpl) coin;
		if (face != other.face)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Coin " + this.number + ": " + this.face;
	}


}
