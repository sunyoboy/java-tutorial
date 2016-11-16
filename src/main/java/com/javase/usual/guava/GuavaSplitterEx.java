package com.usual.guava;

import com.google.common.base.Splitter;

public class GuavaSplitterEx {
	public static void main(String[] args) {
		String str = "a,,b,     c,,,d";
		
		Iterable<String> result = Splitter.on(",").split(str);
		System.out.println("--start--");
		for(String s : result) {
			System.out.println(s);
		}
		System.out.println("--end--");
		
		String str1 = "a,,b,     c,,,d";
		Iterable<String> result1 = Splitter.on(",").omitEmptyStrings().split(str1);
		System.out.println("--start--");
		for(String s : result1) {
			System.out.println(s);
		}
		System.out.println("--end--");
	}
}
