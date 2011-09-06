package eu.javaspecialists.course.master.threads.solution233;

public class HouseDrawing extends StupidFramework {
    private final String colour;

    private static final ThreadLocal<String> colourInit = new ThreadLocal<String>();

    public HouseDrawing(String title, String colour) {
        super(setThreadLocals(colour, title));
        this.colour = colour;
        colourInit.remove();
    }

    private static String setThreadLocals(String colour, String title) {
        colourInit.set(colour);
        return title;
    }

    private String getColour() {
        if (colour == null) {
            return colourInit.get();
        }
        return colour;
    }

    public void draw() {
        System.out.println("Drawing house with colour " + getColour());
    }

    public static void main(String... args) {
        new HouseDrawing("HomeSweetHome", "beige");
    }
}
