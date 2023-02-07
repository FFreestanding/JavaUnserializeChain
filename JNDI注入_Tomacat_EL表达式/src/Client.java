import javax.naming.NamingException;

public class Client {
    public static void main(String[] args) throws NamingException {
        new javax.naming.InitialContext().lookup("rmi://127.0.0.1:1097/Object");
    }
}