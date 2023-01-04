import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Lamports extends Remote {
    public void lock(int processId) throws RemoteException;
    public void unlock(int processId) throws RemoteException;
    public int withdraw(int amount)throws RemoteException;
    public int deposit(int amount)throws RemoteException;
}
