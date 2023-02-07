import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("payload.txt");
        XStream xStream = new XStream(new DomDriver());
        xStream.fromXML(fileInputStream);
    }
}