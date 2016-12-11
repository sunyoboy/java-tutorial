package com.usual.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

public class FtpClient {
	public static boolean uploadFile(String url, int port, String username, 
			String password, String path, String fileName,InputStream ios) {
		boolean success = false;
		FTPClient ftpClient = new FTPClient();
	    FTPClientConfig config = new FTPClientConfig();
	    // config.setXXX(YYY); // change required options
	    config.setRecentDateFormatStr("d MMM HH:mm");
	    config.setDefaultDateFormatStr("d MMM yyy");
	    config.setServerTimeZoneId("Europe/Copenhagen");
	    ftpClient.configure(config);
	    boolean error = false;
	    try {
	      // 192.168.11.240
	      // String server = "192.168.11.240";
	      int reply;
	      ftpClient.connect(url,port);
	      ftpClient.login(username, password);
	      System.out.println("Connected to " + url + ".");
	      System.out.print(ftpClient.getReplyString());

	      // After connection attempt, you should check the reply code to verify
	      // success.
	      reply = ftpClient.getReplyCode();
	      System.out.println("replyCode :" + reply);
	      
	      
	      if(!FTPReply.isPositiveCompletion(reply)) {
	        ftpClient.disconnect();
	        return success;
	        // System.err.println("FTP server refused connection.");
	        // System.exit(1);
	      }
	      
    	  ftpClient.changeWorkingDirectory(path);
    	//伪代码  
    	  
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E");
    	  String start = sdf.format(new Date());
    	  long startTime=System.currentTimeMillis();   //获取开始时间  
    	  System.out.println("开始传输 :" + start);
    	  
    	  ftpClient.storeFile(fileName, ios);
    	  ios.close();
    	  long endTime=System.currentTimeMillis(); //获取结束时间  
    	  System.out.println("程序运行时间： "+(endTime-startTime)+"ms"); 
    	  System.out.println("耗时" + ((endTime-startTime)/1000)/60 +" min");

    	  String end = sdf.format(new Date());  
    	  System.out.println("结束传输:" + end);
    	      	  
	      // ... // transfer files
	      success = true;
	      
	      ftpClient.logout();
	    } catch(IOException e) {
	      error = true;
	      e.printStackTrace();
	    } finally {
	      if(ftpClient.isConnected()) {
	        try {
	          ftpClient.disconnect();
	        } catch(IOException ioe) {
	          // do nothing
	        }
	      }
	      System.exit(error ? 1 : 0);
	    }		
		
		return success;
	}
	
	public static void main(String[] args) {
		
	}
}
