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
    
    /**
 * @author: Santiago Urdaneta
 * @deprecated: constructor de la cola
 */        
    public Cola() {
        this.pfirst = null;
        this.plast = null;
        this.size = 0;
    }
    /**
 * @author: Mario Quintero
 * @deprecated: conocer si la cola está vacía
 * @return: booleano
 */        
    public boolean esVacio(){
        return pfirst == null;
    }
     /**
 * @author: Mario Quintero
 * @deprecated: encolar nodo en la cola
 * @param: x dato que almacena nodo a encolar
 */        
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
     /**
 * @author: Mario Quintero
 * @deprecated: desencola nodo
 * @return: NodoCola desencolado
 */         
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
