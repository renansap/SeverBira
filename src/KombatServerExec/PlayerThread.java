package KombatServerExec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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

public class PlayerThread implements Runnable {
    
    public Socket socket;
    public Player player;
    public static final int SPEED = 8;
    
    public PlayerThread(Player player,Socket socket){
       this.player = player;
       this.socket = socket;      
    
        
    }

    @Override
    public void run() {
BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    PrintWriter out = new PrintWriter(this.socket.getOutputStream(),true);
    
           String command = "";            
            
            while(true){
                if (!(command = in.readLine()).equals("exit")){
                    if(command.equals("PR_R")){
                        player.x += SPEED;
                        player.lado = 'R';
                    }
                     if(command.equals("PR_L") && player.x >= 0){
                        player.x -= SPEED;
                        player.lado = 'L';
                    }
                      if(command.equals("PR_U")&& player.y >= 0){
                        player.y -= SPEED;
                    }
                       if(command.equals("PR_D")){
                        player.y += SPEED;
                    }
                       if(command.equals("PUNCH")){
                        player.punch = 1;
                    }
                       
                       
                }   
            
            }   
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
    }  
    
}