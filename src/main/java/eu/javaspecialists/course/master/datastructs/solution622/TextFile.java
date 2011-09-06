package eu.javaspecialists.course.master.datastructs.solution622;

import java.io.*;
import java.util.*;

/**
 * Instead of first building up a list and then iterating through
 * it, we iterate through the file. Be careful that hasNext()
 * does not unnecessarily advance the reading of the file.
 *
 * @author Heinz Kabutz
 */
public class TextFile implements Iterable<String> {
    private final String filename;

    public TextFile(String filename) {
        this.filename = filename;
    }

    public Iterator<String> iterator() {
        final BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        return new Iterator<String>() {
            private String nextLine;
            private boolean lineReadFromFile = false;

            /**
             * Calling hasNext() repeatedly should not advance the file.
             */
            public boolean hasNext() {
                if (!lineReadFromFile) {
                    try {
                        nextLine = in.readLine();
                    } catch (IOException e) {
                        return false;
                    }
                    lineReadFromFile = true;
                }
                return nextLine != null;
            }

            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lineReadFromFile = false;
                return nextLine;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
