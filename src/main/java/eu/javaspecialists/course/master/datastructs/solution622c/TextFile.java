package eu.javaspecialists.course.master.datastructs.solution622c;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

/**
 * Solution allowing us to call remove(), but does not support
 * CharSet.
 */

public class TextFile implements Iterable<String> {
    private int modCount = 0;
    private final FileChannel channel;
    private final RandomAccessFile file;

    public TextFile(String filename) throws FileNotFoundException {
        file = new RandomAccessFile(filename, "rw");
        channel = file.getChannel();
    }

    public Iterator<String> iterator() {
        final ByteBuffer bbuf;
        try {
            bbuf = channel.map(FileChannel.MapMode.READ_WRITE, 0, file.length());

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return new Iterator<String>() {
            private int lastStart = -1;
            private int expectedModCount = modCount;

            /**
             * Calling hasNext() repeatedly should not advance the file.
             */
            public boolean hasNext() {
                return bbuf.hasRemaining();
            }

            public String next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                lastStart = bbuf.position();
                StringBuffer sb = new StringBuffer();
                while (bbuf.hasRemaining()) {
                    char c = (char) bbuf.get();
                    if (c == '\n')
                        break;
                    if (c != '\r')
                        sb.append(c);
                }
                return sb.toString();
            }

            public void remove() {
                if (lastStart == -1) {
                    throw new IllegalStateException(
                            "next() method has not been called yet");
                }
                expectedModCount = ++modCount;
                int diff = bbuf.position() - lastStart;
                int start = bbuf.position();
                int end = bbuf.limit();
                for (int pos = start; pos < end; pos++) {
                    bbuf.put(pos - diff, bbuf.get(pos));
                }
                int newLimit = end - diff;
                bbuf.limit(newLimit);
                bbuf.position(lastStart);
                try {
                    channel.truncate(bbuf.limit());
                } catch (IOException ex) {
                    throw new IllegalStateException("Could not truncate file", ex);
                }
            }
        };
    }
}
