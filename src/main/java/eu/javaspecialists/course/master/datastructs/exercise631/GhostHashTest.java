package eu.javaspecialists.course.master.datastructs.exercise631;

import java.util.*;
import java.util.concurrent.*;

public class GhostHashTest {
    private static void test(Map<GhostHash, String> ghost) {
        GhostHash heinz = new GhostHash(2, "Heinz");
        GhostHash peter = new GhostHash(2, "Peter");
        GhostHash fred = new GhostHash(4, "Fred");
        GhostHash oscar = new GhostHash(6, "Oscar");

        ghost.put(heinz, "Heinz Kabutz");
        ghost.put(peter, "Peter Kemper");
        ghost.put(fred, "Fred Wilde");
        ghost.put(oscar, "Oscar Smith");

        System.out.println("Testing " + ghost.getClass().getSimpleName());
        // should work
        System.out.println(ghost.get(heinz));
        // should work
        System.out.println(ghost.get(new GhostHash(2, "Heinz")));
        heinz.modifyId();
        // might work
        System.out.println(ghost.get(heinz));

        ghost.put(new GhostHash(2 * 31, "Heinz"), "Heinz Double Agent");

        System.out.println("All values:");
        for (String s : ghost.values()) {
            System.out.println("\t" + s);
        }
    }

    public static void main(String... args) {
        test(new HashMap<GhostHash, String>());
        test(new TreeMap<GhostHash, String>());
        test(new ConcurrentHashMap<GhostHash, String>());
        test(new ConcurrentSkipListMap<GhostHash, String>());
    }
}
