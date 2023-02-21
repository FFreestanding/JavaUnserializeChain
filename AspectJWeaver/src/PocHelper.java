import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Base64;

public class PocHelper {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public static void OutBase64Encode(Object o) throws IOException {
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(o);
        byte[] bs = barr.toByteArray();
        System.out.println(Base64.getEncoder().encodeToString(bs).length());
    }
    public static void writeSer(Object obj, String filename) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
        objectOutputStream.writeObject(obj);
        objectOutputStream.close();
    }
    public static void readSer(String filename) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        objectInputStream.readObject();
        objectInputStream.close();
    }
}
