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
	
	// Constructor of simple player
	public SimplePlayer(String playerId, String playerName, int initialPoints) {
		this.id = playerId;
		this.name = playerName;
		this.points = initialPoints;
		this.bet = 0;
		this.betType = null;
	}

	@Override
	// Return player name
	public String getPlayerName() {
		return this.name;
	}

	@Override
	// Set player name
	public void setPlayerName(String playerName) {
		this.name = playerName;
		
	}

	@Override
	// Returns the players current amount of points
	public int getPoints() {
		return this.points;
	}

	@Override
	// Sets the players current amount of points
	public void setPoints(int points) {
		this.points = points;
		
	}

	@Override
	// Returns the id of the player
	public String getPlayerId() {
		return this.id;
	}

	@Override
	// Ensures the bet is valid and sets it
	public boolean setBet(int bet) {
		if(bet>0 && this.points >= bet) {
			this.bet = bet;
			return true;
		}
		else return false;
	}

	@Override
	// Returns the bet of the player
	public int getBet() {
		return this.bet;
	}

	@Override
	// Sets the players bet type
	public void setBetType(BetType betType) {
		this.betType = betType;
		
	}

	@Override
	// Returns the players bet type
	public BetType getBetType() {
		return this.betType;
	}

	@Override
	// Sets the players bet at 0 and bet type to no bet
	public void resetBet() {
		this.bet = 0;
		this.betType = BetType.NO_BET;
	}

	@Override
	// Returns the results of the player
	public CoinPair getResult() {
		return coin;
	}

	@Override
	// Sets the result of the player
	public void setResult(CoinPair coinPair) {
		this.coin = coinPair;
	}
	
	// Returns string detailing player stats and results
	@Override
	public String toString() {
		return String.format("Player: id= %s, name= %s, bet= %d, betType= %s, points= %d, RESULT .. %s"
				,this.id,this.name,this.bet,this.betType,this.points,this.coin);
	}

}
