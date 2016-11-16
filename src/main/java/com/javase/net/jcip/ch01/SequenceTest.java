package net.jcip.ch01;

import com.common.Constant;

public class SequenceTest {

	public static void main(String[] args) {
		Sequence seq = new Sequence(0);
		
		for(int i=0; i<Constant.N; i++) {
			new Thread(new NextValue("Thread-" + i, seq)).start();
		}
	}

	

}

class NextValue implements Runnable {
	private Sequence seq;
	private String name;
	public NextValue(String name, Sequence seq) {
		this.name = name;
		this.seq = seq;
	}
	
	public void run() {
		for(int j=0; j<Constant.N; j++) {
			System.out.println(name + " nextVal : " + seq.getNext() + " : " + System.currentTimeMillis());
		}
	}
}

