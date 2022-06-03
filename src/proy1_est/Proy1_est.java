/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proy1_est;

import Classes.Grafo;
import javax.swing.JOptionPane;
import Classes.EscritorLector;
/**
 *
 * @author Mario
 */
public class Proy1_est {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EscritorLector el = new EscritorLector();
        Grafo g = new Grafo(26);
        el.Seleccionador_lectura();
        el.lectura(g);
        el.escritura(g);
        
//        EscritorLector test = new EscritorLector();
//        test.Seleccionador_lectura();
        
        
        
        //g.nuevoStock(3);

    }
    
}
