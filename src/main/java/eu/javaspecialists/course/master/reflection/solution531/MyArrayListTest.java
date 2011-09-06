package eu.javaspecialists.course.master.reflection.solution531;

public class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<String> message = new MyArrayList<String>(String.class);
        message.add("Hello");
        message.add("world");
        String[] messageArray = message.toArray();
        System.out.println("messageArray:");
        for (String s : messageArray) {
            System.out.println(s);
        }

        MyArrayList<Number> ages = new MyArrayList<Number>(Number.class);
        ages.add(37);
        ages.add(37);
        ages.add(10);
        ages.add(7);
        ages.add(2);
        Number[] agesArray = ages.toArray();
        System.out.println("agesArray:");
        for (Number number : agesArray) {
            System.out.println(number);
        }
    }
}