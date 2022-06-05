/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Mario
 */
public class caminosMinimos {
    private int[][] pesos;
    private int[] ultimo;
    private int[] d;
    private boolean[] f;
    private int origen, n;
    
    public caminosMinimos(Grafo g, int origen){
        this.pesos = g.getRutas();
        this.n = g.getSize()-1;
        this.ultimo = new int[n];
        this.origen = origen;
        this.d = new int[n];
        this.f = new boolean[n];
    }
    
    public void crearCaminos(){
        this.inicializar();
        for(int i = 0; i < this.n; i++){
            int v = minimo();
            if(v == -1){
                break;
            }
            this.f[v] = true;
            for(int j = 0; j < n; j++){
                if(!f[j]){
                    if((d[v] + pesos[v][j]) < d[j]){
                        d[j] = d[v] + pesos[v][j];
                        ultimo[j] = v;
                    }
                }
            }
        }
    }
    
    public void inicializar(){
        for(int i = 0; i < n; i++){
            this.f[i] = false;
            this.d[i] = pesos[this.origen][i];
            if(d[i] == 0){
                d[i] = 2000000000;
            }
            this.ultimo[i] = origen;
        }
        this.f[origen] = true;
        this.d[origen] = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(this.pesos[i][j] == 0){
                    this.pesos[i][j] = 2000000000;
                }
            }
        }
    }
    
    public int minimo(){
        long mx = 2000000000;
        int v = 0;
        for(int j = 1; j < this.n; j++){
            if(!f[j] && d[j] < mx){
                mx = d[j];
                v = j;
            }
        }
        return v;
    }
    
    public String recuperar(int v, String camino){
        int anterior = this.ultimo[v];
        if (v != this.origen){
            //camino += recuperar(anterior, camino);
            //return camino += (" -> Almacen" + (char)(v+65));
        }else{
            return ("Almacen" + (char)(this.origen + 65));
        }
        return camino;
    }

    public int[][] getPesos() {
        return pesos;
    }

    public int[] getUltimo() {
        return ultimo;
    }

    public int[] getD() {
        return d;
    }

    public boolean[] getF() {
        return f;
    }

    public int getOrigen() {
        return origen;
    }

    public int getN() {
        return n;
    }
    
}
