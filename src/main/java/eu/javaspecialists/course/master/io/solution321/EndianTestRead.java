package eu.javaspecialists.course.master.io.solution321;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class EndianTestRead {
    public static void main(String... args) throws IOException {
        RandomAccessFile file = new RandomAccessFile(
                "data.out", "r");
        FileChannel channel = file.getChannel();
        MappedByteBuffer buffer = channel.map(
                FileChannel.MapMode.READ_ONLY, 0, channel.size());
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        while (buffer.hasRemaining()) {
            System.out.printf("0x%02x ", buffer.get());
        }

        buffer.rewind();
        System.out.println();
        System.out.printf("0x%x%n", buffer.getLong());
        System.out.printf("0x%x%n", buffer.getShort());
        System.out.printf("0x%x%n", buffer.getInt());
        System.out.printf("0x%x%n", buffer.getLong());
        System.out.printf("0x%x%n", (int) buffer.getChar());
    }
}
