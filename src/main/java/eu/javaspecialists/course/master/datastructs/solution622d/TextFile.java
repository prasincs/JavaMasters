package eu.javaspecialists.course.master.datastructs.solution622d;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

/**
 * Solution allowing smaller buffer sizes, necessary when we want
 * to read very large files.
 *
 * @author Neil Petkus, Heinz Kabutz
 */

public class TextFile implements Iterable<String> {
    private final FileChannel fc;
    private final long bufferSize;

    public TextFile(String filename) throws IOException {
        this(filename, 1024);
    }

    public TextFile(String filename, long bufferSize) throws IOException {
        RandomAccessFile file = new RandomAccessFile(filename, "r");
        fc = file.getChannel();
        this.bufferSize = bufferSize;
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private MappedByteBuffer mbb = nextBuffer();

            private MappedByteBuffer nextBuffer() {
                try {
                    long size = fc.size();
                    long position = fc.position();
                    long buffer_length = Math.min(size - position, bufferSize);
                    fc.position(position + buffer_length);
                    return fc.map(FileChannel.MapMode.READ_ONLY, position, buffer_length);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

            public boolean hasNext() {
                return mbb.hasRemaining();
            }

            public String next() {
                try {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    StringBuffer sb = new StringBuffer();
                    while (mbb.hasRemaining()) {
                        try {
                            char c = (char) mbb.get();
                            if (c == '\n')
                                break;
                            if (c != '\r')
                                sb.append(c);
                        } finally {
                            if (!mbb.hasRemaining() && fc.position() != fc.size()) {
                                mbb = nextBuffer();
                            }
                        }
                    }
                    return sb.toString();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException(
                        "Not Implemented for this iterator");
            }
        };
    }
}