package eu.javaspecialists.course.master.io.exercise321;

import java.io.*;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class EndianTestWrite {
    public static void main(String... args) throws IOException {
        long l1 = 0x87654321ABCDEF00L;     //8
        short s1 = 0x0033;                 //2
        int i1 = 0x12345678;               //4
        long l2 = 0x0000111122223333L;     //8
        char c1 = 0xFABC;                  //2
        int length = 24;
        // Get the FileChannel from a RandomAccessFile
        RandomAccessFile randomAccessFile = new RandomAccessFile("file.out", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        // Get a mapped byte buffer from the channel
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);
        // Set the buffer to use Little Endian order
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        // write the various values and close the channel
        buffer.putLong(l1);
        buffer.putShort(s1);
        buffer.putInt(i1);
        buffer.putLong(l2);
        buffer.putChar(c1);
        buffer.force();
        channel.close();
        randomAccessFile.close();
        System.out.println("Done");
    }
}
