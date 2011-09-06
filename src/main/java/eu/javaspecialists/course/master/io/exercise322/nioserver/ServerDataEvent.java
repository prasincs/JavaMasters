package eu.javaspecialists.course.master.io.exercise322.nioserver;

import java.nio.channels.*;

class ServerDataEvent {
    public final NioServer server;
    public final SocketChannel socket;
    public final byte[] data;

    public ServerDataEvent(NioServer server, SocketChannel socket, byte[] data) {
        this.server = server;
        this.socket = socket;
        this.data = data;
    }
}