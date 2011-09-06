package eu.javaspecialists.course.master.io.solution313b;

import java.io.*;

public class HackAttack {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ComplexClass cc = new ComplexClass(1, 2,
                "hello world", true, 3.3, 3.2f);

        System.out.println(cc);

        hackit(cc);

        System.out.println(cc);

    }

    private static void hackit(ComplexClass cc) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(cc);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(baos.toByteArray())
        ) {
            private boolean val = false;

            public float readFloat() throws IOException {
                return 3.6f;
            }

            public boolean readBoolean() throws IOException {
                return (val = !val);
            }

            public String readUTF() throws IOException {
                return "you've been hacked";
            }

            public int readInt() {
                return 1;
            }

            public long readLong() {
                return 2000000000000L;
            }
        };

        cc.readExternal(ois);
    }
}
