import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileStream {
    public static void main(String[] args) throws Exception {
        readSer("CC5.ser");
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
