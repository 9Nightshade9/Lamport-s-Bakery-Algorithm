import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class LamportServerImpl extends UnicastRemoteObject implements Lamports {
    // Each element in the array represents a process.
    // The value of the element represents the ticket number of the process.
    private AtomicIntegerArray tickets;

    public int balance = 100; //INITIAL BALANCE AT BANK FOR DEMO

    // An array to keep track of which process is entering its critical section.
    // This is needed to avoid the second condition of the algorithm.
    private boolean[] entering;

    public LamportServerImpl(int numProcesses) throws RemoteException {
        tickets = new AtomicIntegerArray(numProcesses);
        entering = new boolean[numProcesses]; //REQUEST ARRAY
        for(int i=0; i<4 ; i++)
        {
            entering[i] = false;
        }
    }

    public void lock(int processId) {
        entering[processId] = true;
        //1
        System.out.print("For Process: "+(processId+1)+"\n");
        System.out.print("Request array (before Critical Section): [");
        for(int i=0; i<4 ; i++)
        {
            System.out.print(entering[i]+",");
        }
        System.out.print("]");
        // Find the maximum ticket number of all processes.
        int maxTicket = 0;
        for (int i = 0; i < tickets.length(); i++) {
            maxTicket = Math.max(maxTicket, tickets.get(i));
        }

        // Assign the next available ticket number to this process.
        tickets.set(processId, maxTicket + 1);
        System.out.println("\nTicket array (before unlock): "+tickets+"\n");
        entering[processId] = false;
        System.out.print("Request array (after Critical Section): [");
        for(int i=0; i<4 ; i++)
        {
            System.out.print(entering[i]+",");
        }
        System.out.print("]");


        // Wait until it is this process's turn.
        for (int i = 0; i < tickets.length(); i++) {
            // Wait until the process with the ticket number in front of this process finishes.
            while (entering[i]) {
                Thread.yield();
            }

            // Wait until either it is this process's turn, or the process in front of this process has a lower ticket number.
            while (tickets.get(i) != 0 && (tickets.get(i) < tickets.get(processId) ||
                    (tickets.get(i) == tickets.get(processId) && i < processId))) {
                Thread.yield();
            }
        }
    }

    public void unlock(int processId) {
        // Release the ticket.
        tickets.set(processId, 0);
        System.out.println("\nTicket array (after unlock): "+tickets+"\n");
    }

    public int withdraw(int amount) {
        balance -= amount;
        return balance;
    }
    
    public int deposit(int amount) {
        balance += amount;
        return balance;
    }

    
}
