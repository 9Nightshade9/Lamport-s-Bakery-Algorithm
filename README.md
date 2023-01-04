# Lamport-s-Bakery-Algorithm

This project implements Lamport's Bakery Algorithm and gives a simple demostration using a simple example. It is implemented on a client-server using Java RMI. 
To start the project please make sure to change the ip address in the "LamportClient.java" as per your local IPV4 address. The changes must be made on lines 8, 19, 38, 56 and 75 line of "LamportClient.java".

First Start by running LamportServer.java and ensure Lamport Server is running. Then run the LamportClient.java code.
P.S. (Comment/Uncomment client.lock() and client.unlock() functions calls fom lines 91 to 102 (LamportClient.java) to see the effect of Lamport's Algorithm.
