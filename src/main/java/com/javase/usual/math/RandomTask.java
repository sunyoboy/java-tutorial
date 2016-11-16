package com.usual.math;

public class RandomTask {
	public static void main(String[] args) {
		for(int i=0; i<10; i++) {
			RandomT  r = new RandomT();
			new Thread(r).start();
			/*double rand = Math.random();
			System.out.println(rand);*/
		}
		
	}
	

}

class RandomT implements Runnable {
	
	public void run() {
		System.out.println(Math.random() + 
				" : " + System.currentTimeMillis()); 
		
	}
}