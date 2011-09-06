package eu.javaspecialists.course.master.io.exercise322;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class BlockingEchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(9080);
        ExecutorService pool = Executors.newCachedThreadPool();
        while (true) {
            final Socket socket = ss.accept();
            pool.submit(new Callable<Void>() {
                public Void call() throws Exception {
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream();
                    int i;
                    while ((i = in.read()) != -1) {
                        out.write(i);
                    }
                    out.close();
                    in.close();
                    return null;
                }
            });
        }
    }
}
