package com.javase.corejavaii.internet;

import com.sun.xml.internal.rngom.parse.host.Base;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by root on 12/29/16.
 */
public class URLTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.baidu.com");
            URLConnection urlConn = url.openConnection();
            urlConn.connect();
            // getURLResource(urlConn);
            getStandardFields(urlConn);
            // URLUsage();
            URLParse();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void URLParse() throws URISyntaxException {
        URI base = new URI("http://www.ifeng.com/api/");
        URI relative = new URI("java/lang/String.html");
        URI combined = base.resolve(relative);
        System.out.println(combined);
        System.out.println(base.relativize(new URI("http://www.ifeng.com/api/java/lang/String.html")));
    }


    public static void URLUsage() throws IOException {
        URL url = new URL("http://www.baidu.com");
        InputStream inputStream = url.openStream();
        Scanner in = new Scanner(inputStream);
        while(in.hasNext()) {
            System.out.println(in.next());
        }
    }

    /**
     * 通过FTP访问一个有密码保护的文件时,URL如下:
     * ftp://username:password@ftp.yourserver.com/pub/file.txt
     * @param urlConn
     */
    public static void setRequestParameters(URLConnection urlConn) {
        urlConn.setDoInput(true);
        urlConn.setDoOutput(true);
        urlConn.setIfModifiedSince((new Date()).getTime());
        urlConn.setUseCaches(true);
        urlConn.setAllowUserInteraction(true);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String username = "root";
        String password = "root";
        String input = username + ":" + password;
        String encoding = base64Encoder.encode(input.getBytes());
        // 访问有秘密包含的Web页
        urlConn.setRequestProperty("Authorization", "Basic " + encoding);

        // urlConn.setRequestProperty();
        urlConn.setConnectTimeout(3000); //ms
        urlConn.setReadTimeout(30); // ms
    }

    public static void getURLResource(URLConnection urlConn) throws IOException {
        InputStream inputStream = urlConn.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    public static void getStandardFields(URLConnection urlConn) {
        System.out.println("urlConn.getContentType(): " + urlConn.getContentType());
        System.out.println("urlConn.getContentLength(): " + urlConn.getContentLength());
        System.out.println("urlConn.getContentEncoding(): " + urlConn.getContentEncoding());
        System.out.println("urlConn.getDate(): " + urlConn.getDate());
        System.out.println("urlConn.getExpiration(): " + urlConn.getExpiration());
        System.out.println("urlConn.getLastModified(): " + urlConn.getLastModified());
    }
}
