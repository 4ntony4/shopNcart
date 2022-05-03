package business;

import java.util.Random;

import util.ClientSession;

/**
 * @author Diogo Antão
 *
 * 04-2022
 */

public class ThreadLocalTest extends Thread {
	
	@SuppressWarnings("nls")
	@Override
	public void run() {
		
		ClientSession clientSession = ClientSession.getSession();
		System.out.println(getThreadName() + clientSession);
		sleep(100, 50);
		
		ClientSession clientSession2 = ClientSession.getSession();
		System.out.println(getThreadName() + clientSession2);
		
		boolean equal = clientSession == clientSession2;
		String message = equal ? "Both are equal" : "Not equal";
		System.out.println(getThreadName() + message);
	}
	
	private static void sleep(int max, int min) {
		try {
			int time = new Random().nextInt(max - min + 1) + min;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("nls")
	private static String getThreadName() {
		return "[" + Thread.currentThread().getName() + "] - ";
	}

}
