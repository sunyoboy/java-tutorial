package org.lieve.nio.nbio;

import org.lieve.nio.common.Constant;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/11/17
 * Time: 10:43 AM
 */
public class HttpHandler implements Runnable {

    private int bufferSize = Constant.BUFFER_BYTE_SIZE;

    private String localCharset = Constant.CHARSET;

    private SelectionKey key;

    public HttpHandler(SelectionKey key) {
        this.key = key;
    }

    public HttpHandler(String localCharset) {
        this(-1, localCharset);
    }

    public HttpHandler(int bufferSize, String localCharset) {
        if (bufferSize > 0) {
            this.bufferSize = bufferSize;
        }

        if (localCharset != null) {
            this.localCharset = localCharset;
        }
    }

    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ,
                ByteBuffer.allocate(Constant.BUFFER_BYTE_SIZE));
    }

    public void handleRead(SelectionKey key) throws IOException {
        // get channel
        SocketChannel sc = (SocketChannel) key.channel();

        // get buffer and reset buffer
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();

        // if nothing to read then close it
        if (sc.read(buffer) == -1) {
            sc.close();
        } else {
            // 将buffer转换为读状态
            buffer.flip();

            // 将接收到的值按localCharset格式编码后保存到receivedString
            String receivedStr = Charset.forName(localCharset).newDecoder()
                    .decode(buffer).toString();

            String[] requestMessage = receivedStr.split("\r\n");
            for(String s : requestMessage) {
                System.out.println(s);
                if(s.isEmpty())
                    break;
            }
            // System.out.println("received from client: " + receivedStr);

            // 返回数据给客户端
            // String sendStr = "received data : " + receivedStr;

            // 控制台打印首行信息
            String [] firstLine = requestMessage[0].split(" ");
            System.out.println();
            System.out.println("Method:\t" + firstLine[0]);
            System.out.println("URL:\t" + firstLine[1]);
            System.out.println("HTTP Version: \t" + firstLine[2]);
            System.out.println();

            // 返回客户端
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP/1.1 200 OK\r\n");
            sb.append("Content-Type:text/html;charset=" + localCharset + "\r\n");
            sb.append("\r\n");
            sb.append("<html><head><title>显示报文</title></head><body>");
            sb.append("接收到请求报文是: <br/>");
            for(String s: requestMessage) {
                sb.append(s + "<br/>");
            }
            sb.append("</body></html>");
            buffer = ByteBuffer.wrap(sb.toString().getBytes(localCharset));
            sc.write(buffer);

            // close socket
            sc.close();
        }
    }

    @Override
    public void run() {

            try {
                // receive request
                if (key.isAcceptable()) {
                    handleAccept(key);
                }

                if (key.isReadable()) {
                    handleRead(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
