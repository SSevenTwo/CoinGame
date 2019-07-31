package model.enumeration;

import java.util.Random;

public class RandomEnumGenerator {
	private static Random random = new Random();
	
	 public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
	        int x = random.nextInt(clazz.getEnumConstants().length);
	        return clazz.getEnumConstants()[x];
	    }
}
