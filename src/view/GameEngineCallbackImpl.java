package view;

import java.util.logging.Level;
import java.util.logging.Logger;

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
public class GameEngineCallbackImpl implements GameEngineCallback
{
   private static final Logger logger = Logger.getLogger(GameEngineCallback.class.getName());

   public GameEngineCallbackImpl()
   {
      // NOTE need to also set the console to FINE in %JRE_HOME%\lib\logging.properties
      logger.setLevel(Level.FINE);
   }

   public void playerCoinUpdate(Player player, Coin coin, GameEngine engine)
   {
      // intermediate results logged at Level.FINE
      logger.log(Level.FINE, "Intermediate data to log .. String.format() is good here!");
      // TODO: complete this method to log intermediate results
   }

   public void playerResult(Player player, CoinPair coinPair, GameEngine engine)
   {
      // final results logged at Level.INFO
      logger.log(Level.INFO, "Result data to log .. String.format() is good here!");
      // TODO: complete this method to log results
   }

   // TODO: implement rest of interface
}
