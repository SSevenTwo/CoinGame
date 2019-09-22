package model.util;

import java.util.Random;

import com.sun.xml.internal.ws.util.StringUtils;

import model.enumeration.CoinFace;

public class Utilities {
	private static Random random = new Random();
	
	// Returns a random enum
	 public static <T extends Enum<?>> T randomEnum(Class<T> coinFace){
	        int number = random.nextInt(coinFace.getEnumConstants().length);
	        return coinFace.getEnumConstants()[number];
	    }
	 
	// Converts coin faces to title case
	 public static String titleConvert(CoinFace coinFace) {
			String title = "" + coinFace;
			return StringUtils.capitalize(title.toLowerCase());
		}
}
