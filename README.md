# Lamport-s-Bakery-Algorithm

This project implements Lamport's Bakery Algorithm and gives a simple demostration. It is implemented on a client-server using Java RMI. 
To start the project please make sure to change the ip address in the "LamportClient.java" as per your local IPV4 address. The changes must be made on lines 8th, 19th, 38th, 56th and 75th line of "LamportClient.java".

First Start by running LamportServer file and ensure Lamport Server is running. Then run the client side code.
P.S. (Comment/Uncomment client.lock() and client.unlock() functions fom lines 91 to 102 to see the effect of Lamport's Algorithm.
