package com.usual.ftp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FtpDemo {
	public static void main(String[] args) throws FileNotFoundException, 
			IOException {
		
		FileInputStream fis = new FileInputStream("E:/guoanvsboli.ts");
		boolean flag = FtpClient.uploadFile("192.168.11.240", 21, "tqz", "tqz", 
				"E:/", "444.mp4",fis);
		System.out.println(flag);
	}
}
