package com.usual.random;

import java.util.Random;

public class RandomT extends Thread {
	static int soldier = 0;
	String data = "";
	
	public RandomT() {
		Random r = new Random(20);
		
		for(int i=0; i<10; i++) {
			soldier = r.nextInt(10);
			System.out.print(soldier + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		while(true) {
			RandomT r = new RandomT();
		}
	}
}
