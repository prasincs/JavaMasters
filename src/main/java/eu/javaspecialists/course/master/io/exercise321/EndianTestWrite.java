package eu.javaspecialists.course.master.io.exercise321;

import java.io.*;

public class EndianTestWrite {
    public static void main(String... args) throws IOException {
        long l1 = 0x87654321ABCDEF00L;
        short s1 = 0x0033;
        int i1 = 0x12345678;
        long l2 = 0x0000111122223333L;
        char c1 = 0xFABC;

        // Get the FileChannel from a RandomAccessFile

        // Get a mapped byte buffer from the channel

        // Set the buffer to use Little Endian order

        // write the various values and close the channel
    }
}
