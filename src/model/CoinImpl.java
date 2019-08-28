package model;

import java.util.Objects;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.util.Utilities;

public class CoinImpl implements Coin {
	
	private int number;
	private CoinFace face;
	
	//Constructor of coins. Generates a random face for the coin.
	public CoinImpl(int number) {
		this.number = number;
		this.face = Utilities.randomEnum(CoinFace.class);
		
	}

	@Override
	//Returns coin number
	public int getNumber() {
		return this.number;
	}

	@Override
	//Returns face of coin
	public CoinFace getFace() {
		return this.face;
	}

	@Override
	// Flips the coin face to the opposite face
	public void flip() {
		if(this.face.equals(CoinFace.HEADS)) {
			this.face = CoinFace.TAILS;
		}
		else this.face = CoinFace.HEADS;
		
	}
	
	@Override
	//Compares two coins by face and returns true if they match
	public boolean equals(Coin coin) {
		if(this.face.equals(coin.getFace())) {
			return true;
		}
		else return false;
	}

	@Override
	// Checks whether the object is a coin then compares it
	public boolean equals(Object coin) {
		if(coin instanceof CoinImpl) {
			this.equals(coin);
		}
		return false;
	}
	
	@Override
	// Creates hashcode based on the coin face
	public int hashCode() {
		return Objects.hash(this.getFace());
	}
	
	@Override
	// Returns details of coin as a string
	public String toString() {
		String coin = String.format("Coin %d: %s", this.getNumber(), Utilities.titleConvert(this.getFace())); 
		return coin;
	}

}
