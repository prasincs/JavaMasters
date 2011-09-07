package eu.javaspecialists.course.master.io.exercise313;

import java.io.*;

public class ComplexClass implements Serializable {
    private int i;
    private long l;
    private String s;
    private boolean b;
    private Double d;
    private Float f;

    public ComplexClass(int i, long l, String s, boolean b, Double d, float f) {
        this.i = i;
        this.l = l;
        this.s = s;
        this.b = b;
        this.d = d;
        this.f = f;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComplexClass that = (ComplexClass) o;

        if (b != that.b)
            return false;
        if (i != that.i)
            return false;
        if (l != that.l)
            return false;
        if (d != null ? !d.equals(that.d) : that.d != null)
            return false;
        if (f != null ? !f.equals(that.f) : that.f != null)
            return false;
        if (s != null ? !s.equals(that.s) : that.s != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = i;
        result = 31 * result + (int) (l ^ (l >>> 32));
        result = 31 * result + (s != null ? s.hashCode() : 0);
        result = 31 * result + (b ? 1 : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (f != null ? f.hashCode() : 0);
        return result;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(i);
        out.writeLong(l);
        if (s == null){
            out.writeUTF("{null}");
        }else {
            out.writeUTF(s);
        }
        out.writeBoolean(b);
        if (d == null){
            out.writeDouble(Double.POSITIVE_INFINITY);
        }else {
            out.writeDouble(d);
        }
        if (f == null){
            out.writeFloat(Float.POSITIVE_INFINITY);
        }else {
            out.writeFloat(f);
        }

    }


    private void readObject(ObjectInputStream in) throws IOException {
       this.i = in.readInt();
       this.l = in.readLong();
       this.s = in.readUTF();
       if (s.equals("{null}"))
           this.s = null;


       this.b = in.readBoolean();
       this.d = in.readDouble();
       if (d.equals(Double.POSITIVE_INFINITY))
           this.d = null;
       this.f = in.readFloat();
       if (f.equals(Float.POSITIVE_INFINITY))
           this.f  = null;
    }

}
