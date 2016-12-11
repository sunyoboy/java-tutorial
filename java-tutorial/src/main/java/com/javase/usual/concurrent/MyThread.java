package com.usual.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThread {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
	}
}
