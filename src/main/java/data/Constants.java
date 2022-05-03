package data;

/**
 * @author Diogo Ant�o
 *
 * 04-2022
 */

public final class Constants {
	
	public static final int MAX_LENGTH = 250;
	
	public static final boolean verifyLength(String str) {
		if (str != null && str.length() <= MAX_LENGTH) {
			return true;
		}
		return false;
	}
}
