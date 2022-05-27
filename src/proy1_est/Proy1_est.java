/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proy1_est;

import Classes.Grafo;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class Proy1_est {
    private static Grafo g;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Grafo g = new Grafo(26);
        
        g.nuevoAlm();
        g.nuevoAlm();
        g.nuevoAlm();
        
        int[][] r = g.getRutas();
        
        for(int i=0; i<3; i++){
            for(int j = 0; j<3; j++){
                System.out.print(r[i][j]+"--");
                
            }
            System.out.print("\n");
        }

    }
    
}
