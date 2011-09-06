package eu.javaspecialists.course.master.datastructs.solution622d;

import java.io.*;

public class TextFileTest2 {
    public static void main(String[] args) throws IOException {
        TextFile tf = new TextFile("src/eu/javaspecialists/course/"
                + "master/datastructs/solution622d/TextFileTest2.java");
        for (String s : tf) {
            System.out.println(">>> " + s);
        }
    }
}