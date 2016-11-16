package com.javase.net.jcip.ch01;

import com.javase.net.jcip.annotations.GuardedBy;
import com.javase.net.jcip.annotations.ThreadSafe;

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
