package eu.javaspecialists.course.master.io.exercise321;

import java.io.*;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class EndianTestRead {
    public static void main(String... args) throws IOException {
        int length = 24;
        // Open a FileChannel from a RandomAccessFile
        RandomAccessFile file = new RandomAccessFile("file.out", "r");
        // Again set the order to Little Endian
        FileChannel channel = file.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        // Read the buffer one byte at a time and print it out
        for (int i = 0; i < length; i++)
            System.out.println((int)buffer.get(i));
        // Rewind the buffer
        buffer.rewind();
        // Now read in the values in the same type and order that they were
        // written
        System.out.printf("0x%x", buffer.getLong());
        System.out.printf("0x%x", buffer.getShort());
        System.out.printf("0x%x", buffer.getInt());
        System.out.printf("0x%x", buffer.getLong());
        System.out.printf("0x%x", (int)buffer.getChar());
        channel.close();
        file.close();

    }
}
