package client;

import model.CoinImpl;
import model.CoinPairImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import validate.Validator;
import view.GameEngineCallbackImpl;

public class test {
	
	private static final Logger logger = Logger.getLogger(SimpleTestClient.class.getName());

	public static void main(String[] args) {
		
		//TESTING COIN 
//		CoinImpl coin1 = new CoinImpl(1);
//		CoinImpl coin2 = new CoinImpl(2);
//		CoinImpl coin3 = new CoinImpl(3);
		//String coin4 = "Hello";
//		
//		System.out.println(coin1.getFace() + " " + coin2.getFace() + " " + coin3.getFace());
//		System.out.println(coin1.equals(coin2));
//		System.out.println(coin1.hashCode() + "\n" + coin2.hashCode() + "\n" + coin3.hashCode());
//		
//		System.out.println(coin3);
		
		//TESTING COINPAIR
//		CoinPairImpl coins1 = new CoinPairImpl();
//		CoinPairImpl coins2 = new CoinPairImpl();
//		CoinPairImpl coins3 = new CoinPairImpl();
//		
//		System.out.println(coins1);
//		System.out.println(coins2);
//		System.out.println(coins3);
//		System.out.println(coins1.equals(coins2));
//		System.out.println(coins1.equals(coin4));
//		System.out.println(coins1.hashCode());
//		System.out.println(coins2.hashCode());
//		System.out.println(coins3.hashCode());
		
		//TESTING SIMPLETESTCLIENT
		   try {
			    FileInputStream fis =  new FileInputStream("p.properties");
			    LogManager.getLogManager().readConfiguration(fis);
			    logger.setLevel(Level.FINE);
			    logger.addHandler(new java.util.logging.ConsoleHandler());
			    logger.setUseParentHandlers(false);
			    fis.close();
			    } 
			    catch(IOException e) {
			    e.printStackTrace();
			    }
		   
		 final GameEngine gameEngine = new GameEngineImpl();

	      // call method in Validator.jar to test *structural* correctness
	      // just passing this does not mean it actually works .. you need to test yourself!
	      // pass false if you want to show minimal logging (pass/fail) .. (i.e. ONLY once it passes)
	      Validator.validate(true);

	      // create some test players
	      Player[] players = new Player[] { new SimplePlayer("1", "The Coin Master", 1000),
	         new SimplePlayer("2", "The Loser", 750), new SimplePlayer("3", "The Dabbler", 500) };
	      
	      
	      // add logging callback
	      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

	      // main loop to add players and place a bet
	      int enumOrdinal = 0;
	      for (Player player : players)
	      {
	         gameEngine.addPlayer(player);
	         
	         // mod with BetType length so we always stay in range even if num players increases
	         // NOTE: we are passing a different BetType each time!
	         gameEngine.placeBet(player, 100, BetType.values()[enumOrdinal++ % BetType
	            .values().length]);
	         //gameEngine.spinPlayer(player, 100, 1000, 100, 50, 500, 50);
	      }
	      
	      //Testing unmodifiable collection of players
	      Collection<Player> poo = gameEngine.getAllPlayers();
	      poo.add(new SimplePlayer("7", "The Blade Master", 10000));
	      for(Player poop: poo) {
	    	  System.out.println(poop);
	      }
	      
	      System.out.println("AROOOOOOOOOOOOOO:");
	      for(Player poopy: gameEngine.getAllPlayers()) {
	    	  System.out.println(poopy);
	      }
         
	      
	      logger.log(Level.INFO, "SPINNING ...");
	      // OutputTrace.pdf was generated with these parameter values (using only first 3 params as per spec)
	      gameEngine.spinSpinner(100, 1000, 200, 50, 500, 25);

	      // TODO reset bets for next round if you were playing again
	      for(Player player:players) {
	    	  player.resetBet();
	      }

	}

}
