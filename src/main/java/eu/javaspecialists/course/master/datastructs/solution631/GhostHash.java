package eu.javaspecialists.course.master.datastructs.solution631;

public class GhostHash implements Comparable<GhostHash> {
    private int id;
    private final String name;

    public GhostHash(int id, String name) {
        this.id = id;
        this.name = name;
        if (this.name == null) {
            throw new IllegalArgumentException("name == null");
        }
    }

    public void modifyId() {
        id *= 31;
    }

    public int compareTo(GhostHash o) {
        if (o.id > id)
            return -1;
        if (o.id < id)
            return 1;
        return name.compareTo(o.name);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GhostHash ghostHash = (GhostHash) o;

        if (id != ghostHash.id)
            return false;
        if (name != null ? !name.equals(ghostHash.name) : ghostHash.name != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
