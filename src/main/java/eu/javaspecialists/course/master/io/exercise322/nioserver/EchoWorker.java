package eu.javaspecialists.course.master.io.exercise322.nioserver;

import java.nio.channels.*;
import java.util.concurrent.*;

public class EchoWorker extends Thread {
    private final BlockingQueue<ServerDataEvent> queue = new LinkedBlockingQueue<ServerDataEvent>();

    public void processData(NioServer server, SocketChannel socket, byte[] data,
                            int count) {
        byte[] copy = new byte[count];
        System.arraycopy(data, 0, copy, 0, count);
        queue.add(new ServerDataEvent(server, socket, copy));
    }

    public void run() {
        while (true) {
            try {
                ServerDataEvent event = queue.take();
                event.server.send(event.socket, event.data);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
