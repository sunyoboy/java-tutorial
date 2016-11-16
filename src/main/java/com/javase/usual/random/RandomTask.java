package com.usual.random;

public class RandomTask implements Runnable {
	
	public void run() {
		System.out.println(Math.random() + " : " + System.currentTimeMillis() + " : ");
	}

}
