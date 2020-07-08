package zq.demo.common;

public class VerifyCodeUtils {

	public static String get() {
		return String.valueOf((int)((Math.random()*9+1)*100000));
	}
	
}
