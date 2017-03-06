package wr.com.utils;

import java.util.Random;

public class MathRandom {
	private static Random rnd = new Random();  
	  
	public static String getRandomNumber(int digCount) {  
	    StringBuilder sb = new StringBuilder(digCount);  
	    for(int i=0; i < digCount; i++)  
	        sb.append((char)('0' + rnd.nextInt(10)));  
	    return sb.toString();  
}
	}
