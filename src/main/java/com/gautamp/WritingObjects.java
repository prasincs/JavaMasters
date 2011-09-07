package com.gautamp;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: gautamp
 * Date: 9/7/11
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class WritingObjects {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream
                (new BufferedOutputStream(new FileOutputStream(new File("test.out"))));
        objectOutputStream.writeInt(10);
        objectOutputStream.close();
    }
}
