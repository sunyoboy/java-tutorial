package net.jcip.ch01;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Sequence {
	
	@GuardedBy("this")
	private int value;
	
	public Sequence() {
		this.value = 0;
	}
	
	public Sequence(int value) {
		this.value = value;
	}
	
	public synchronized int getNext() {
		return value++;
	}
}
