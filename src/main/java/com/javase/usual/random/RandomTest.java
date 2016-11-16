package com.usual.random;


public class RandomTest {
	public static void main(String[] args) {
		// Math.random() :  返回带正号的 double 值，该值大于等于 0.0 且小于 1.0
		/**
		 * java.util.Random()
		 * java.util.Random是线程安全的，所以不存在多个线程调用会破坏种子(seed)的风险。
		 */
		
		// http://blog.csdn.net/hengyunabc/article/details/9913143
/*		System.out.println(Math.random());
		System.out.println(Math.round(Math.random() * 10));
		System.out.println(Math.round(Math.random() * 10));
		
		int rand = java.util.concurrent.ThreadLocalRandom.current().nextInt(10);
		
		System.out.println(rand);
		
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		System.out.println(new String(bytes));
		for(int i=0; i<bytes.length; i++) {
			System.out.println(bytes[i]);
		}
		System.out.println();
		
		System.out.println(ThreadLocalRandom.current().nextLong());*/
		
		TestMathRandom();
		System.out.println(System.nanoTime());
	}
	
	public static void TestMathRandom() {
		for(int i=0; i<3; i++) {
			RandomTask t = new RandomTask();
			new Thread(t).start();
		}
		
	}
}
