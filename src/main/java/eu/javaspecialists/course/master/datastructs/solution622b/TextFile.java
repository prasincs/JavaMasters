package eu.javaspecialists.course.master.datastructs.solution622b;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.util.*;

public class TextFile implements Iterable<String> {
    private final Charset charset;
    private final FileChannel channel;
    private final RandomAccessFile file;

    public TextFile(String filename, Charset charset)
            throws FileNotFoundException {
        file = new RandomAccessFile(filename, "rw");
        channel = file.getChannel();
        this.charset = charset;
    }

    public TextFile(String filename) throws FileNotFoundException {
        this(filename, Charset.defaultCharset());
    }

    public Iterator<String> iterator() {
        final CharBuffer cbuf;
        try {
            MappedByteBuffer mappedByteBuffer = channel.map(
                    FileChannel.MapMode.READ_ONLY, 0, file.length());
            cbuf = charset.decode(mappedByteBuffer);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return new Iterator<String>() {
            public boolean hasNext() {
                return cbuf.hasRemaining();
            }

            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("End of file reached");
                }
                StringBuilder sb = new StringBuilder(80);
                while (cbuf.hasRemaining()) {
                    char c = cbuf.get();
                    if (c == '\n')
                        break;
                    if (c != '\r')
                        sb.append(c);
                }
                return sb.toString();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}