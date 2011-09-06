package eu.javaspecialists.course.master.io.solution313c;

import java.io.*;

public class ComplexClass implements Serializable {
    private static final int BITS_PER_BYTE = 8;
    private static final int INT_SIZE_BYTES = Integer.SIZE / BITS_PER_BYTE;
    private static final int LONG_SIZE_BYTES = Long.SIZE / BITS_PER_BYTE;
    private static final int FLOAT_SIZE_BYTES = INT_SIZE_BYTES;
    private static final int DOUBLE_SIZE_BYTES = LONG_SIZE_BYTES;
    private static final int CHAR_SIZE_BYTES = Character.SIZE / BITS_PER_BYTE;
    private static final int NOT_NULL_FIELDS_SIZE_BYTES = INT_SIZE_BYTES + LONG_SIZE_BYTES + 1; // 4 (i) + 8 (l) + 1 (4-bit boolean flags)
    private static final int MAX_FIELDS_SIZE_BYTES = NOT_NULL_FIELDS_SIZE_BYTES + DOUBLE_SIZE_BYTES + FLOAT_SIZE_BYTES + INT_SIZE_BYTES; // + 8(d) + 4 (f) + 4 (length(s))
    private static final int B_MASK = 1 << 0;
    private static final int S_MASK = 1 << 1;
    private static final int D_MASK = 1 << 2;
    private static final int F_MASK = 1 << 3;

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

    private void writeObject(ObjectOutputStream oos) throws IOException {
        final boolean sNotNull = null != this.s;
        final boolean dNotNull = null != this.d;
        final boolean fNotNull = null != this.f;

        int length = NOT_NULL_FIELDS_SIZE_BYTES;
        if (dNotNull) {
            length += DOUBLE_SIZE_BYTES;
        }
        if (fNotNull) {
            length += FLOAT_SIZE_BYTES;
        }
        if (sNotNull) {
            length += INT_SIZE_BYTES;   // encode the length of the string
            length += CHAR_SIZE_BYTES * this.s.length();
        }
        final byte[] bytes = new byte[length];

        int c = 0;
        c += writeIntBytes(bytes, c, this.i);
        c += writeLongBytes(bytes, c, this.l);
        bytes[c++] = (byte) ((this.b ? B_MASK : 0x00) | (sNotNull ? S_MASK : 0x00) | (dNotNull ? D_MASK : 0x00) | (fNotNull ? F_MASK : 0x00));

        if (dNotNull) {
            c += writeLongBytes(bytes, c, Double.doubleToLongBits(d));
        }
        if (fNotNull) {
            c += writeIntBytes(bytes, c, Float.floatToIntBits(f));
        }
        if (sNotNull) {
            c += writeIntBytes(bytes, c, this.s.length());
            c += writeStringBytes(bytes, c, this.s);
        }

        oos.write(bytes);
    }

    private int writeIntBytes(byte[] bytes, int offset, int value) {
        for (int c = 0; c < INT_SIZE_BYTES; c++) {
            bytes[c + offset] = (byte) ((value >> (c * BITS_PER_BYTE)) & 0xff);
        }

        return INT_SIZE_BYTES;
    }

    private int writeLongBytes(byte[] bytes, int offset, long value) {
        for (int c = 0; c < LONG_SIZE_BYTES; c++) {
            bytes[c + offset] = (byte) ((value >> (c * BITS_PER_BYTE)) & 0xff);
        }

        return LONG_SIZE_BYTES;
    }

    private int writeStringBytes(byte[] bytes, int offset, String value) {
        char[] sChars = value.toCharArray();

        int j = 0;
        for (char c : sChars) {
            bytes[offset + j++] = (byte) (c & 0xff);
            bytes[offset + j++] = (byte) ((c >> BITS_PER_BYTE) & 0xff);
        }

        return j;
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        final byte[] bytes = new byte[MAX_FIELDS_SIZE_BYTES];
        readNBytes(ois, bytes, 0, NOT_NULL_FIELDS_SIZE_BYTES);

        int c = 0;
        this.i = readIntBytes(bytes, c);
        c += INT_SIZE_BYTES;
        this.l = readLongBytes(bytes, c);
        c += LONG_SIZE_BYTES;

        this.b = (bytes[c] & B_MASK) == B_MASK;
        final boolean sNotNull = (bytes[c] & S_MASK) == S_MASK;
        final boolean dNotNull = (bytes[c] & D_MASK) == D_MASK;
        final boolean fNotNull = (bytes[c] & F_MASK) == F_MASK;
        c++;

        int varLength = 0;
        if (dNotNull) {
            varLength += DOUBLE_SIZE_BYTES;
        }
        if (fNotNull) {
            varLength += FLOAT_SIZE_BYTES;
        }
        if (sNotNull) {
            varLength += INT_SIZE_BYTES;
        }
        readNBytes(ois, bytes, c, varLength);

        if (dNotNull) {
            this.d = Double.longBitsToDouble(readLongBytes(bytes, c));
            c += DOUBLE_SIZE_BYTES;
        }
        if (fNotNull) {
            this.f = Float.intBitsToFloat(readIntBytes(bytes, c));
            c += FLOAT_SIZE_BYTES;
        }

        if (sNotNull) {
            final int sLength = readIntBytes(bytes, c);
            final byte[] sBytes = new byte[sLength * CHAR_SIZE_BYTES];
            readNBytes(ois, sBytes, 0, sBytes.length);
            this.s = readStringBytes(sBytes, sLength);
        }
    }

    private void readNBytes(ObjectInputStream ois, byte[] bytes, int offset, int size) throws IOException {
        int r = 0;
        while ((r += ois.read(bytes, r + offset, size - r)) < size) {
        }
    }

    private int readIntBytes(byte[] bytes, int offset) {
        int result = 0;

        for (int c = INT_SIZE_BYTES - 1; c >= 0; c--) {
            result |= ((bytes[offset + c] & 0xff) << c * BITS_PER_BYTE);
        }

        return result;
    }

    private long readLongBytes(byte[] bytes, int offset) {
        long result = 0;

        for (int c = LONG_SIZE_BYTES - 1; c >= 0; c--) {
            result |= ((bytes[offset + c] & 0xffL) << c * BITS_PER_BYTE);
        }

        return result;
    }

    private String readStringBytes(byte[] bytes, int sLength) {
        final char[] chars = new char[sLength];

        for (int j = 0; j < sLength; j++) {
            chars[j] = (char) (bytes[j * CHAR_SIZE_BYTES] & 0xff | (bytes[j * CHAR_SIZE_BYTES + 1] & 0xff) << BITS_PER_BYTE);
        }

        return new String(chars);
    }

    @Override
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

    @Override
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
}
