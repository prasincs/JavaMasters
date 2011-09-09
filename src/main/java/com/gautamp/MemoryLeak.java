package com.gautamp;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/7/11
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class MemoryLeak {
    private final static Map<String, byte[]> data =
            new ConcurrentHashMap<String, byte[]>();
    public static void doStuff(){
        while (true){
            if (Math.random() < .00005){
                data.put(new Date().toString(), new byte[10000]);
                System.out.println("Little leak");
            }else {
                byte[] junk = new byte[100];
            }
        }
    }

    public static void main(String[] args) {
       doStuff();
    }
}
