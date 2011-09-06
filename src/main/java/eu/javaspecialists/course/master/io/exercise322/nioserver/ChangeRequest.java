package eu.javaspecialists.course.master.io.exercise322.nioserver;

import java.nio.channels.*;

public class ChangeRequest {
    public static final int REGISTER = 1;
    public static final int CHANGEOPS = 2;

    public final SocketChannel socket;
    public final int type;
    public final int ops;

    public ChangeRequest(SocketChannel socket, int type, int ops) {
        this.socket = socket;
        this.type = type;
        this.ops = ops;
    }
}