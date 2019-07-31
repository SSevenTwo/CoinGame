package model;

import java.util.Collection;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player getPlayer(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		// TODO Auto-generated method stub
		return false;
	}

}
