package model;

import java.util.ArrayList;
import java.util.Collection;

import model.enumeration.BetType;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackImpl;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

	private ArrayList<Player> players;
	private ArrayList<GameEngineCallback> gameEngines;

	public GameEngineImpl() {
		this.players = new ArrayList<Player>();
		this.gameEngines = new ArrayList<GameEngineCallback>();
	}

	@Override
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {
		
		//this.spin(player, initialDelay1, finalDelay1, delayIncrement1);
		CoinPairImpl coins = new CoinPairImpl();

		for (int i = initialDelay1; i < finalDelay1; i += delayIncrement1) {
			for (GameEngineCallback game : gameEngines) {
				game.playerCoinUpdate(player, coins.getCoin1(), this);
				game.playerCoinUpdate(player, coins.getCoin2(), this);
			}
			coins.getCoin1().flip();
			coins.getCoin2().flip();
		}
		
		//Possibly move the below methods to the above method. Depending on usage of game engines array.
		for (GameEngineCallback game : gameEngines) {
			game.playerResult(player, coins, this);
			player.setResult(coins);
		}

	}

	@Override
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {
		
		CoinPairImpl coins = new CoinPairImpl();

		for (int i = initialDelay1; i < finalDelay1; i += delayIncrement1) {
			for (GameEngineCallback game : gameEngines) {
				game.spinnerCoinUpdate(coins.getCoin1(), this);
				game.spinnerCoinUpdate(coins.getCoin2(), this);
			}
			coins.getCoin1().flip();
			coins.getCoin2().flip();
		}

		for (GameEngineCallback game : gameEngines) {
			this.applyBetResults(coins);
			game.spinnerResult(coins, this);
		}

	}

	@Override
	public void applyBetResults(CoinPair spinnerResult) {
		for(Player player:this.players) {
			player.getBetType().applyWinLoss(player, spinnerResult);
		}

	}

	@Override
	public void addPlayer(Player player) {
		players.add(player);

	}

	@Override
	public Player getPlayer(String id) {
		for (Player player : this.players) {
			if (player.getPlayerId() == id) {
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		for (Player tempPlayer : this.players) {
			if (player.getPlayerId() == tempPlayer.getPlayerId()) {
				this.players.remove(tempPlayer);
				return true;
			}
		}
		return false;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngines.add(gameEngineCallback);

	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		for (GameEngineCallback game : this.gameEngines) {
			if (game.equals(gameEngineCallback)) {
				this.gameEngines.remove(game);
				return true;
			}
		}
		return false;
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return this.players;
	}

	@Override
	public boolean placeBet(Player player, int bet, BetType betType) {
		if(player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		}
		else {
			player.resetBet();
			return false;
		}
		
	}

	/*
	public void spin(Object obj, int initialDelay1, int finalDelay1, int delayIncrement1) {
		CoinPairImpl coins = new CoinPairImpl();

		for (int i = initialDelay1; i < finalDelay1; i += delayIncrement1) {
			for (GameEngineCallback game : gameEngines) {
				if (obj instanceof SimplePlayer) {
					game.playerCoinUpdate((Player) obj, coins.getCoin1(), this);
					game.playerCoinUpdate((Player) obj, coins.getCoin2(), this);
				} else {
					game.spinnerCoinUpdate(coins.getCoin1(), this);
					game.spinnerCoinUpdate(coins.getCoin2(), this);
				}
				coins.getCoin1().flip();
				coins.getCoin2().flip();
			}

			for (GameEngineCallback game : gameEngines) {
				if (obj instanceof SimplePlayer) {
					game.playerResult((Player) obj, coins, this);
				} else {
					game.spinnerResult(coins, this);
				}
			}

		}

	}*/
}
