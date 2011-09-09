package com.gautamp;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;
import com.sun.tools.javac.code.Source;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/8/11
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Bits {
    static int i1 = 1;
    static int i2 = 2;
    static int i3 = 4;
    static int i4 = 8;
    static int i5 = 16;
    static int i6 = 32;
    static int i7 = 64;
    static int i8 = 128;
    static byte store = 0;
    public static void main(String[] args) {
//        System.out.println(1<<31 == Integer.MIN_VALUE);
//        System.out.println(1>>30);

        store |= i1;
        store |= i2;

        System.out.println((store & i1) == 1);

    }
}
