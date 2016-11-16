package tomcat.socket.http;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.Constant;

public class Request {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private InputStream input;
	private String uri;
	
	public Request(InputStream input) {
		this.input = input;
	}
	
	public void parse() {
		// Read a set of characters from the socket
		logger.debug(this.getClass() + " parse() ");
		StringBuffer request = new StringBuffer(Constant.BUFFER_SIZE);
		int i;
		byte[] buffer = new byte[Constant.BUFFER_SIZE];
		try {
			i = input.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
			i = -1;
		}
		
		for(int j=0; j<i; j++) {
			request.append((char)buffer[j]);
		}
		
		System.out.print(request.toString());
		uri = parseUri(request.toString());
	}
	
	private String parseUri(String requestString) {
		int index1, index2;
		index1 = requestString.indexOf(' ');
		if(index1 != -1) {
			index2 = requestString.indexOf(' ', index1 + 1);
			if(index2 > index1) {
				return requestString.substring(index1 + 1, index2);
			}
		}
		return null;
	}
	
	public String getUri() {
		return uri;
	}
}
