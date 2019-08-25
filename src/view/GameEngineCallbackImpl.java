package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.Utilities;
import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton implementation of GameEngineCallback showing Java logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

	//Constructor of GameEngineCallback
	public GameEngineCallbackImpl() {
		logger.setLevel(Level.FINE);
	}

	// Updates the state of the players coin
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		// intermediate results logged at Level.FINE
		logger.log(Level.FINE, String.format("%s coin %d flipped to %s", player.getPlayerName(),coin.getNumber()
				,Utilities.titleConvert(coin.getFace())));
	}

	// Logs the final result of the player after spinning
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		// final results logged at Level.INFO
		logger.log(Level.INFO, String.format("%s , final result: %s" ,player.getPlayerName(),coinPair));
	}

	@Override
	// Updates the state of the spinners coin
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		logger.log(Level.INFO, String.format("Spinner coin %d flipped to %s",coin.getNumber(),
				Utilities.titleConvert(coin.getFace())));
	}

	@Override
	// Logs the final result of the spinner and updates the final results of the players bets
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		logger.log(Level.INFO, String.format("Spinner, final result: %s", coinPair));
		String message = "";
		for(Player player: engine.getAllPlayers()) {
			message += player + "\n";
		}
		logger.log(Level.INFO, String.format("Final Player Results\n%s",message));
	}
	
}
