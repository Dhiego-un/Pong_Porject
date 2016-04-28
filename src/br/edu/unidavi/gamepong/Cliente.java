package br.edu.unidavi.gamepong;



import br.edu.unidavi.gamepong.Recebedor;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * 
 */
public class Cliente {
   public static void main(String[] args) 
         throws UnknownHostException, IOException {
     
     // Dispara ip do cliente
     new Cliente("10.3.4.16", 12345).executa();
   }
   
   private String host;
   private int porta;
   
   public Cliente (String host, int porta) {
     this.host = host;
     this.porta = porta;
   }
   
   public void executa() throws UnknownHostException, IOException {
     Socket cliente = new Socket(this.host, this.porta);
     System.out.println("Cliente Conectado!");
 
     // Thread responsavel por recerber mensagens do servidor
     Recebedor r = new Recebedor(cliente.getInputStream());
     new Thread(r).start();
     
     // Lê as mensagens e manda para o servidor.
     Scanner teclado = new Scanner(System.in);
     PrintStream saida = new PrintStream(cliente.getOutputStream());
     while (teclado.hasNextLine()) {
       saida.println(teclado.nextLine());
     }
     
     saida.close();
     teclado.close();
     cliente.close();    
   }
 }
