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
public class Cola {
    private NodoCola pfirst, plast;
    private int size;

    public Cola() {
        this.pfirst = null;
        this.plast = null;
        this.size = 0;
    }
    
    public boolean esVacio(){
        return pfirst == null;
    }
    
    public void encolar(int x){
        NodoCola nodo = new NodoCola(x);
        if(this.esVacio()){
            this.pfirst = this.plast =nodo;
        }else{
            NodoCola temp = this.plast;
            this.plast = nodo;
            temp.setNext(nodo);
        }
        size++;
    }
    
    public NodoCola desencolar(){
        if(!this.esVacio()){
            NodoCola n = this.pfirst;
            this.pfirst = this.pfirst.getNext();
            size--;
            if(size == 0){
                pfirst = null;
                plast = null;
            }
            return n;
        }        
        return null;
    }

    public NodoCola getPfirst() {
        return pfirst;
    }

    public void setPfirst(NodoCola pfirst) {
        this.pfirst = pfirst;
    }

    public NodoCola getPlast() {
        return plast;
    }

    public void setPlast(NodoCola plast) {
        this.plast = plast;
    }
    
    
    
}
