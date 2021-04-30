package TCP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client_TCP {

   public static void main(String[] args) {

      final Socket clientSocket;
      final BufferedReader in;
      final PrintWriter out;
      final Scanner sc = new Scanner(System.in);//pour lire � partir du clavier

      try {
         /*
         * les informations du serveur ( port et adresse IP ou nom d'hote
         * 127.0.0.1 ou localhost est l'adresse local de la machine
         */
         clientSocket = new Socket("localhost",5000);
         System.out.println("le serveur a accepter");
         System.out.println("lecture de la question depuis le clavier:");
         //flux pour envoyer
         out = new PrintWriter(clientSocket.getOutputStream());
         //flux pour recevoir
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         Thread envoyer = new Thread(new Runnable() {
             String msg;
              @Override
              public void run() {
                while(true){
                  msg = sc.nextLine();
                  out.println("le client envoi un message: "+msg);
                  out.flush();
                }
             }
         });
         envoyer.start();

        Thread recevoir = new Thread(new Runnable() {
            String msg;
            @Override
            public void run() {
               try {
                 msg = in.readLine();
                 while(msg!=null){
                    System.out.println(msg);
               	 System.out.println("lecture de la question depuis le clavier:");
                    msg = in.readLine();
                 }
                 System.out.println("Serveur d�conect�");
                 out.close();
                 clientSocket.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
            }
        });
        recevoir.start();

      } catch (IOException e) {
           e.printStackTrace();
      }
  }
}