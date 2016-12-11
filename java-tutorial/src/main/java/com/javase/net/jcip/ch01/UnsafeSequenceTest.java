package com.javase.net.jcip.ch01;

import com.common.Constant;

public class UnsafeSequenceTest {

	public static void main(String[] args) {
		UnsafeSequence useq = new UnsafeSequence(0);
		
		for(int i=0; i<Constant.M; i++) {
			new Thread(new NextVal("Thread-" + i, useq)).start();
		}
	}

	

}

class NextVal implements Runnable {
	private UnsafeSequence useq;
	private String name;
	public NextVal(String name, UnsafeSequence useq) {
		this.name = name;
		this.useq = useq;
	}
	
	public void run() {
		for(int j=0; j<Constant.N; j++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name + " nextVal : " + useq.getNext() + " : " + System.currentTimeMillis());
		}
	}
}

