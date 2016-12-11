package com.usual.programing;

public class MoveStepTest {

		public static void main(String[] args) {
			long seed = 1L;
			System.out.println(seed << 48);
			
			long mask = (1L << 48) - 1;
			long addend = 0xBL;
			long multiplier = 0x5DEECE66DL;
			System.out.println(Integer.MAX_VALUE + " " + Integer.MIN_VALUE + " " + Math.pow(2,31));
			
		}
}
