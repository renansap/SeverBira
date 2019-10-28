package KombatServerExec;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author ubiratan.dias
 */
public class Player {
    public int x=70, y=130, w= 90, h = 127, id, punch = 0,pontos = 0;
    public char lado = 'R';
    
    public Socket conexao;
    public PrintWriter out;
    
    public Player(Socket socket) {
        
        
        Random r = new Random();
        this.id = r.nextInt();
        
        this.conexao = socket;
        
        try {
            out = new PrintWriter(this.conexao.getOutputStream(),true);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

}
}