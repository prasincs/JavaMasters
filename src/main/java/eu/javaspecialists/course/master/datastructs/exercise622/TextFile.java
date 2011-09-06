package eu.javaspecialists.course.master.datastructs.exercise622;

import java.io.*;
import java.util.*;

/**
 * Instead of first building up a list and then iterating through
 * it, we iterate through the file. Be careful that hasNext()
 * does not unnecessarily advance the reading of the file.
 *
 * @author Heinz Kabutz
 * @see TextFileTest
 */
public class TextFile implements Iterable<String> {
    private final String filename;

    public TextFile(String filename) throws FileNotFoundException {
        this.filename = filename;
    }

    public Iterator<String> iterator() {
        final BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
        // Create an anonymous inner class for the iterator
        return new Iterator<String>() {
            // todo: create a field for the next line

            // todo: use a boolean to remember if you read file

            /**
             * Calling hasNext() repeatedly should not advance the file.
             */
            public boolean hasNext() {
                // todo: implement method to read line from file
                // todo: null means you have reached the end
                throw new UnsupportedOperationException("todo");
            }

            public String next() {
                // todo: return the next line if there is one
                throw new UnsupportedOperationException("todo");
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}