/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KombatServerExec;

/**
 *
 * @author Ksa
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
public class KombatServerExec implements Runnable  {       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Starting...");
        players = new Player[2];
        KombatServerExec kse = new KombatServerExec();
        Thread th = new Thread(kse);
        th.start();
        kse.waitForPlayer();        
    }
   
    
    public void waitForPlayer(){
        try {
            ServerSocket ss = new ServerSocket(8880);
            for(int i = 0; i < 2; i++){
            Socket s = ss.accept();
            Player  p1 = new Player(s);
            players[i] = p1;
            PlayerThread p2 = new PlayerThread(p1,s);
            Thread th = new Thread(p2);
            th.start();            
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static final int SPEED = 8;
    public static Player[] players;
    PrintWriter out;   

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(SPEED);
            } catch (InterruptedException ex) {
                Logger.getLogger(KombatServerExec.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                        
            
            for(int i = 0; i < players.length; i++){
                if (players[i] != null) {
                String posicao = players[i].id + "_" +  players[i].x + "_" + players[i].y + "_" + players[i].w + "_" + players[i].h + "_" + players[i].lado + "_" + players[i].pontos;
                players[i].out.println(posicao);
                System.out.println(posicao);
                           
                    for(int h = 0; h < players.length; h++){
                        if (h != i && players[h] != null){    
                        players[h].out.println(posicao);
                        System.out.println(posicao);
                        double x = 0, y = 0;
                        
                        if (players[i].punch == 1){
                            players[i].punch = 0;
                            
                            
                            if (players[i].x > players[h].x){
                            x=players[i].x-players[h].x;
                                                      
                            }else{
                            x=players[h].x-players[i].x;
                            }
                            
                            if (players[i].y > players[h].y){
                            y=players[i].y-players[h].y;
                                                      
                            }else{
                            y=players[h].y-players[i].y;
                            }
                                
                                
                            if (x < 50 && y <50){
                                players[i].pontos = players[i].pontos + 1;
                             } 
                        }
                    
                        
                        
                        
                        if (players[h].punch == 1){
                             players[h].punch = 0;
                             
                             if (players[i].x > players[h].x){
                            x=players[i].x-players[h].x;
                                                      
                            }else{
                            x=players[h].x-players[i].x;
                            }
                            
                            if (players[i].y > players[h].y){
                            y=players[i].y-players[h].y;
                                                      
                            }else{
                            y=players[h].y-players[i].y;
                            }
                            
                            
                             
                            if (x < 50 && y <50){
                                players[h].pontos = players[h].pontos + 1;
                                
                            } 
                        }
                        
                        }
                        
                    }
                
                
                }
            }
                
        }
    }
}

