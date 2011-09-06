package eu.javaspecialists.course.master.threads.exercise233;

public class HouseDrawing extends StupidFramework {
    private final String colour;

    public HouseDrawing(String title, String colour) {
        super(title);
        this.colour = colour;
    }

    public void draw() {
        System.out.println("Drawing house with colour " + colour);
    }
}
