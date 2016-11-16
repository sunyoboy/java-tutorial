package tomcat.socket.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.common.Constant;

/**
 * HTTP Response = Status-Line
 * *((general-header | response-header | entity-header) CRLF)
 * CRLF
 * [message-body]
 * Status-Line = HTTP-Version SP Status-Code SP Reason-Phrase CRLF
 * @author sunlj
 *
 */
public class Response {
	Request request;
	OutputStream output;
	
	public Response(OutputStream output) {
		this.output = output;
	}
	
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public void sendSaticResource() throws IOException {
		byte[] bytes = new byte[Constant.BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if(file.exists()) {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes, 0, Constant.BUFFER_SIZE);
				while(ch != -1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, Constant.BUFFER_SIZE);
				}
			} else {
				// file not found
				String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
						"Content-Type: text/html\r\n" + 
						"Content-Length:23 \r\n" +
						"\r\n" +
						"<h1>File Not Found</h1>";
				output.write(errorMessage.getBytes());
 			}
		} catch (Exception e) {
			// throw if cannot instantiate a File object
			System.out.println(e.toString());
		} finally {
			if(fis != null) {
				fis.close();
			}
		}
	}
}
