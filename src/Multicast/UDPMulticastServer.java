package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPMulticastServer {

   public static void sendUDPMessage(String message,String ipAddress, int port) throws IOException {
      DatagramSocket socket = new DatagramSocket();
      InetAddress group = InetAddress.getByName(ipAddress);
      byte[] msg = message.getBytes();
      DatagramPacket packet = new DatagramPacket(msg, msg.length,group, port);
      socket.send(packet);
      socket.close();
   }

   public static void main(String[] args) throws IOException {
	  while (true) {
		  System.out.println("entrer un msg");
		   Scanner sc=new  Scanner(System.in);
		   String msg=sc.nextLine();
	      sendUDPMessage(msg, "230.0.0.0",4321);
	}

   }
}