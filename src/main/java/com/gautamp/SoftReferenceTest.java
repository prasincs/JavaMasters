package com.gautamp;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/7/11
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        String name = new String("test");
        Reference<String> refStr = new SoftReference<String>(name);
        name = null;
        System.out.println(refStr.get());
        System.gc();
        System.out.println(refStr.get());
        try{
            byte[] b = new byte[1024*1024*1024];
        }catch (OutOfMemoryError e){
            System.out.println(refStr.get());
        }
    }
}
