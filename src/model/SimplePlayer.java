package model;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

public class SimplePlayer implements Player {
	
	private String name;
	private String id;
	private int points;
	private int bet;
	private BetType betType;
	private CoinPair coin;
	
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.id = playerId;
		this.name = playerName;
		this.points = initialPoints;
		this.bet = 0;
		this.betType = null;
	}

	@Override
	public String getPlayerName() {
		return this.name;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.name = playerName;
		
	}

	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
		
	}

	@Override
	public String getPlayerId() {
		return this.id;
	}

	@Override
	public boolean setBet(int bet) {
		if(bet>0 && this.points >= bet) {
			this.bet = bet;
			return true;
		}
		else return false;
	}

	@Override
	public int getBet() {
		return this.bet;
	}

	@Override
	public void setBetType(BetType betType) {
		this.betType = betType;
		
	}

	@Override
	public BetType getBetType() {
		return this.betType;
	}

	@Override
	public void resetBet() {
		this.bet = 0;
		this.betType = betType.NO_BET;
	}

	@Override
	public CoinPair getResult() {
		return coin;
	}

	@Override
	public void setResult(CoinPair coinPair) {
		this.coin = coinPair;
	}
	
	@Override
	public String toString() {
		return "Player: id=" + this.id + ", name="+ this.name + ", bet=" + this.bet +
				", betType=" + this.betType +", points=" + this.points + ", RESULT .." + this.coin;
	}

}
