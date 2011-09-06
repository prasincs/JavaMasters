package eu.javaspecialists.course.master.io.exercise322;

import eu.javaspecialists.course.master.io.exercise322.nioserver.*;

import java.io.*;
import java.net.*;

public class TestClient {
    public static void main(String[] args) throws IOException {
        int threads = getThreads();

        NioServer.main(args);
        Socket[] sockets = new Socket[100];
        for (int i = 0; i < sockets.length; i++) {
            sockets[i] = new Socket("localhost", 9090);
        }

        threads = getThreads() - threads;
        System.out.println("threads = " + threads);
    }

    private static int getThreads() {
        return Thread.currentThread().getThreadGroup().activeCount();
    }
}
