package com.usual.file;

import java.io.File;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
		String fileName = "G:/Ñ¸À×ÏÂÔØ/video";
		File f = new File(fileName);
		if(f.isDirectory()) {
			File[] file = f.listFiles();
			for(File f1 : file) {
				if(f1.isFile()) {
					f1.createNewFile();
					
				}
			}
		}
	}
}
