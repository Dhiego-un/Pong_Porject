/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.unidavi.gamepong;

import java.io.InputStream;
import java.util.Scanner;

/**
 *
 * 
 */
public class TrataCliente implements Runnable {
 
   private InputStream cliente;
   private Servidor servidor;
 
   public TrataCliente(InputStream cliente, Servidor servidor) {
     this.cliente = cliente;
     this.servidor = servidor;
   }
 
   public void run() {
     
     Scanner s = new Scanner(this.cliente);
     while (s.hasNextLine()) {
       servidor.distribuiMensagem(s.nextLine());
     }
     s.close();
   }
 }