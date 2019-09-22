package model;

import java.util.ArrayList;
import java.util.Collection;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineImpl implements GameEngine {

	private Collection<Player> players;
	private Collection<GameEngineCallback> gameEngines;
	private boolean coin1FinishedSpinning;
	private boolean coin2FinishedSpinning;

	// Constructor of GameEngine
	public GameEngineImpl() {
		this.players = new ArrayList<Player>();
		this.gameEngines = new ArrayList<GameEngineCallback>();
	}

	@Override
	// Spins the Player's coin
	public void spinPlayer(Player player, int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {

		this.spinPlayerOrSpinner(player, initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2,
				delayIncrement2);

	}

	@Override
	// Spins the spinners coin
	public void spinSpinner(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2, int finalDelay2,
			int delayIncrement2) throws IllegalArgumentException {

		this.spinPlayerOrSpinner(null, initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2,
				delayIncrement2);

	}

	@Override
	// Apples the wins or losses to each player in the game based on their bet type
	public void applyBetResults(CoinPair spinnerResult) {
		for (Player player : this.players) {
			player.getBetType().applyWinLoss(player, spinnerResult);
		}
	}

	@Override
	// Adds player to the collection
	public void addPlayer(Player player) {
		// Removes any players with the same unique player ID from the collection first.
		this.removePlayer(player);
		players.add(player);
	}

	@Override
	// Returns the player that matches the input id
	public Player getPlayer(String id) {
		for (Player player : this.players) {
			if (player.getPlayerId().equals(id)) {
				return player;
			}
		}
		return null;
	}

	@Override
	// Removes the matching player
	public boolean removePlayer(Player player) {
		for (Player tempPlayer : this.players) {
			if (player.getPlayerId().equals(tempPlayer.getPlayerId())) {
				this.players.remove(tempPlayer);
				return true;
			}
		}
		return false;
	}

	@Override
	// Adds a game engine callback object to the collection
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngines.add(gameEngineCallback);
	}

	@Override
	// Removes a matching game engine callback object
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
	// Returns the players collection
	public Collection<Player> getAllPlayers() {

		return new ArrayList<Player>(this.players);
	}

	@Override
	// Places the bet for a specific player
	public boolean placeBet(Player player, int bet, BetType betType) {
		if (player.setBet(bet)) {
			player.setBetType(betType);
			return true;
		} else {
			player.resetBet();
			return false;
		}
	}

	// Spins the player or spinner based on which method calls this method
	private void spinPlayerOrSpinner(Player player, int initialDelay1, int finalDelay1, int delayIncrement1,
			int initialDelay2, int finalDelay2, int delayIncrement2) {

		// Checks that the delay are valid
		this.checkValidityOfDelay(initialDelay1, finalDelay1, delayIncrement1, initialDelay2, finalDelay2,
				delayIncrement2);
		
		resetCoinFinishedSpinningChecks();

		// Creates a coin pair and spins it in different threads
		CoinPairImpl coins = new CoinPairImpl();

		new Thread() {
			public void run() {
				spinCoin1(coins, player, initialDelay1, finalDelay1, delayIncrement1);
			}
		}.start();
		new Thread() {
			public void run() {
				spinCoin2(coins, player, initialDelay2, finalDelay2, delayIncrement2);
			}
		}.start();
		
	}

	private void spinCoin1(CoinPair coins, Player player, int initialDelay1, int finalDelay1, int delayIncrement1) {
		for (int i = initialDelay1; i < finalDelay1; i += delayIncrement1) {
			coins.getCoin1().flip();
			for (GameEngineCallback game : gameEngines) {
				if (isSpinner(player)) {
					game.spinnerCoinUpdate(coins.getCoin1(), this);
				} else {
					game.playerCoinUpdate(player, coins.getCoin1(), this);
				}
				try {
					Thread.sleep(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		coin1FinishedSpinning = true;
		checkIfFinishedSpinning(coins, player);
	}

	private void spinCoin2(CoinPair coins, Player player, int initialDelay2, int finalDelay2, int delayIncrement2) {
		for (int i = initialDelay2; i < finalDelay2; i += delayIncrement2) {
			coins.getCoin2().flip();
			for (GameEngineCallback game : gameEngines) {
				if (isSpinner(player)) {
					game.spinnerCoinUpdate(coins.getCoin2(), this);
				} else {
					game.playerCoinUpdate(player, coins.getCoin2(), this);
				}
				try {
					Thread.sleep(i);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		coin2FinishedSpinning = true;
		checkIfFinishedSpinning(coins, player);
	}

	// Checks that the delay are valid. If not throw exception.
	private void checkValidityOfDelay(int initialDelay1, int finalDelay1, int delayIncrement1, int initialDelay2,
			int finalDelay2, int delayIncrement2) throws IllegalArgumentException {

		if (initialDelay1 < 0 || finalDelay1 < 0 || delayIncrement1 < 0 || initialDelay2 < 0 || finalDelay2 < 0
				|| delayIncrement2 < 0) {
			throw new IllegalArgumentException("Error: Delay must be more than 0.");
		}
		if (finalDelay1 < initialDelay1 || finalDelay2 < initialDelay2) {
			throw new IllegalArgumentException("Error: Initial delay must be larger than final delay.");
		}
		if (delayIncrement1 > (finalDelay1 - initialDelay1) || delayIncrement2 > (finalDelay2 - initialDelay2)) {
			throw new IllegalArgumentException(
					"Error: Delay increment must not be larger than the difference of " + "final and initial delays.");
		}
	}

	// Applies the player results or the bet results dependent on the caller of the
	// method
	private void logResults(CoinPair coins, Player player) {
		for (GameEngineCallback game : gameEngines) {
			if (isSpinner(player)) {
				this.applyBetResults(coins);
				game.spinnerResult(coins, this);
			} else {
				game.playerResult(player, coins, this);
				player.setResult(coins);

			}
		}
	}

	private void checkIfFinishedSpinning(CoinPair coins, Player player) {
		if(coinsFinishedSpinning()) {
			logResults(coins, player);
		}
	}

	private boolean isSpinner(Player player) {
		if (player != null) {
			return false;
		} else
			return true;
	}
	
	private void resetCoinFinishedSpinningChecks() {
		coin1FinishedSpinning = false;
		coin2FinishedSpinning = false;
	}
	
	private boolean coinsFinishedSpinning() {
		if(coin1FinishedSpinning && coin2FinishedSpinning) {
			return true;
		}
		else return false;
	}
}
