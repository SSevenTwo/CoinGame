package model;

import java.util.Objects;

import model.interfaces.Coin;
import model.interfaces.CoinPair;

public class CoinPairImpl implements CoinPair {
	
	private CoinImpl coin1;
	private CoinImpl coin2;
	
	//Constructor for CoinPair
	public CoinPairImpl() {
		this.coin1 = new CoinImpl(1);
		this.coin2 = new CoinImpl(2);
	}

	@Override
	//Returns coin 1
	public Coin getCoin1() {
		return this.coin1;
	}

	@Override
	//Returns coin 2
	public Coin getCoin2() {
		return this.coin2;
	}

	@Override
	// Compares the two faces of the coins to check if they are equal
	public boolean equals(CoinPair coinPair) {
		if(this.coin1.equals(coinPair.getCoin1()) && this.coin2.equals(coinPair.getCoin2())){
			return true;
		}
		return false;
	}
	
	@Override
	// Ensures that the object is actually a coinpair first
	public boolean equals(Object obj) {
		if(obj instanceof CoinPair) {
			return this.equals(obj);
		}
		else return false;
	}
	
	@Override
	// Return details of coinpair as string
	public String toString() {
		String coin = String.format("%s, %s", this.coin1,this.coin2); 
		return coin;
	}

	@Override
	// Creates hashcode based on the faces of the two coins
	public int hashCode() {
		return Objects.hash(coin1.getFace(),coin2.getFace());
	}


	
	

}
