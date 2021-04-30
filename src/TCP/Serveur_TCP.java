package TCP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/*
 * www.codeurjava.com
 */
public class Serveur_TCP {

   public static void main(String[] test) {

     final ServerSocket serveurSocket  ;
     final Socket clientSocket ;
     final BufferedReader in;
     final PrintWriter out;
     final Scanner sc=new Scanner(System.in);

     try {
       serveurSocket = new ServerSocket(5000);
       System.out.println("En attente d'une connexion client");
       clientSocket = serveurSocket.accept();
       System.out.println("un client est connecté");
       out = new PrintWriter(clientSocket.getOutputStream());
       in = new BufferedReader (new InputStreamReader (clientSocket.getInputStream()));
       Thread envoi= new Thread(new Runnable() {
          String msg;
          @Override
          public void run() {
             while(true){
                msg = sc.nextLine();
                out.println("le serveur envoi un message : "+msg);
                out.flush();
             }
          }
       });
       envoi.start();

       Thread recevoir= new Thread(new Runnable() {
          String msg ;
          @Override
          public void run() {
             try {
                msg = in.readLine();
                //tant que le client est connecté
                while(msg!=null){
                   System.out.println(msg);
              	 System.out.println("lecture de la question depuis le clavier:");
                   msg = in.readLine();
                }
                //sortir de la boucle si le client a déconecté
                System.out.println("Client déconecté");
                //fermer le flux et la session socket
                out.close();
                clientSocket.close();
                serveurSocket.close();
             } catch (IOException e) {
                  e.printStackTrace();
             }
         }
      });
      recevoir.start();
      }catch (IOException e) {
         e.printStackTrace();
      }
   }
}