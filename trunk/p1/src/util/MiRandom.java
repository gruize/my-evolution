package util;

public class MiRandom {

	public static boolean boolRandom(){
		double i = Math.random();;
		if(i <= 0.49) 
			return true;
		else 
			return false;
	}
}
