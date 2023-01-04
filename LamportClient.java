import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class LamportClient {
	public static void main(String[] args) throws Exception {
	String ip = "192.168.0.142"; // IP of host server
	int port = 10900;
	Registry reg = LocateRegistry.getRegistry(ip, port); // Locate registry at ip and port
	Lamports client = (Lamports)reg.lookup("LamportServer"); // lookup() "lamportserver implementation"

	//INITIAL BALANCE OF BANK AT SERVER SIDE IS 100
	//INITIATING FOUR THREADS IMITATING PROCESSES
	Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
			try {
				String ip = "192.168.0.142";
				int port = 10900;
				Registry registry = LocateRegistry.getRegistry(ip, port);
				Lamports bank = (Lamports) registry.lookup("LamportServer"); //"LamportServer" contains bank classes for demonstration purposes
				int balance = bank.deposit(100);
				System.out.println("Thread 1: " + ": New balance: " + balance);
	
	
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
        }
    });
	int id1 = 0;

	Thread t2 = new Thread(new Runnable() {
        @Override
        public void run() {
			try {
				String ip = "192.168.0.142";
				int port = 10900;
				Registry registry = LocateRegistry.getRegistry(ip, port);
				Lamports bank = (Lamports) registry.lookup("LamportServer");
				int balance = bank.withdraw(200);
				System.out.println("Thread 2: " + ": New balance: " + balance);
	
	
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
        }
    });
	int id2 = 1;
	Thread t3 = new Thread(new Runnable() {
        @Override
        public void run() {
			try {
				String ip = "192.168.0.142";
				int port = 10900;
				Registry registry = LocateRegistry.getRegistry(ip, port);
				Lamports bank = (Lamports) registry.lookup("LamportServer");
				int balance = bank.deposit(200);
				System.out.println("Thread 3: " + ": New balance: " + balance);
	
	
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
        }
    });
	int id3 = 2;

	Thread t4 = new Thread(new Runnable() {
        @Override
        public void run() {
			try {
				String ip = "192.168.0.142";
				int port = 10900;
				Registry registry = LocateRegistry.getRegistry(ip, port);
				Lamports bank = (Lamports) registry.lookup("LamportServer");
				int balance = bank.withdraw(100);
				System.out.println("Thread 4: " + ": New balance: " + balance);
	
	
			} catch (RemoteException | NotBoundException e) {
				e.printStackTrace();
			}
        }
    });
	int id4 = 3;
	

	client.lock(id1); //LOCK PROCESS 1
	t1.start(); //RUN THREAD 1
	client.unlock(id1); //UNLOCK PROCESS 1
	client.lock(id2); //LOCK PROCESS 2
	t2.start(); //RUN THREAD 2
	client.unlock(id2); //UNLOCK PROCESS 2
	client.lock(id3); //LOCK PROCESS 3
	t3.start(); //RUN THREAD 3
	client.unlock(id3); //UNLOCK PROCESS 3
	client.lock(id4); //LOCK PROCESS 4
	t4.start(); //RUN THREAD 4
	client.unlock(id4); //UNLOCK PROCESS 4

	


	}
	}
