import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LamportServer {
public static void main(String[] args) throws Exception {
int n = 4; // TOTAL PROCESSES
System.setProperty("java.rmi.server.hostname","192.168.0.142");

Registry reg = null;

int port = 10900;
LamportServerImpl server = new LamportServerImpl(n);
reg = LocateRegistry.createRegistry(port);
reg.rebind("LamportServer", server);
System.out.println("\nLamport server ready");
}
}