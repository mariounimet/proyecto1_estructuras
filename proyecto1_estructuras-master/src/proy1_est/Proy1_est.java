/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proy1_est;

import Classes.EscritorLector;
import Classes.Grafo;
import Classes.ListaProd;
import Classes.caminosMinimos;
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

        int[][] mat = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                mat[i][j] = 0;
            }
        }
        
        
        for(int i = 0; i < 4; i++){
            for(int j = 0; j<4;j++){
                mat[i][j] = i + j;
                if(i == j){
                    mat[i][j] = 0;
                }
            }
        }
        
        
        
        
        
        /*for(int i: cm.getD()){
            System.out.print(i+"-");
        }
       System.out.println("\n");
        for(boolean i: cm.getF()){
            System.out.print(i+"-");
        }
        System.out.println("\n");
        for(int i: cm.getUltimo()){
            System.out.print(i+"-");
        } */
        
        //cm.recuperar(0);
        
        EscritorLector el = new EscritorLector();
        
        Grafo j = new Grafo(26);
        el.Seleccionador_lectura();
        el.lectura(j);
        
        
        j.pedido();
        
        int[][] a = j.getRutas();
        
        /*for(int i = 0; i < 5; i++){
            for(int k = 0; k < 5; k++){
                System.out.print(a[i][k]+"-");
            }
            System.out.println("");
        }
        */
        caminosMinimos cmm = new caminosMinimos(j, 1);
        cmm.crearCaminos();
        for(int i: cmm.getUltimo()){
            System.out.print(i+"-");
        }
        
    }
}
