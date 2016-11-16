package com.usual.http;

import java.util.HashMap;
import java.util.Map;

public class Call {

    public static void main(String[] args) throws Exception {
        
        /* Post Request */
        Map dataMap = new HashMap();
        dataMap.put("String", "BON%2BhRcoG526STstTZW%2FcAfgmjI%2BvODcl4jIFqmQF8R%2FHsMeDyjzsMEV1kPFmYc1bP2PH9%2BYC7CJbaVrVsDQ%2F2AGf51oYp96e7kLy%2BHObMFpRzrNeI91MWOBEDWrnBJWBp05kabBdu%2BDVIE8y08ZokFBC2ois10ObUUjGlqbQIFd9YEq8MaEoHyaWTZJjYqUWFd89EhmK7TgjyrYwLZamH2n0xV4ID3%2F%2BRXxPaJeGHj0vlP5IW5HZpL2h5vORYRk2NU%2FGK4%3D");
        dataMap.put("to", "null");
        String url = "http://192.168.11.54:8088/activate";
        System.out.println(new HttpRequestor().doPost(url, dataMap));
        
        /* Get Request */
        // System.out.println(new HttpRequestor().doGet("http://localhost:8080/OneHttpServer/"));
    }
}

