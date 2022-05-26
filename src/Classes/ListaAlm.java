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
public class ListaAlm {
    private Almacen pfirst, plast;
    private int size;

    public ListaAlm(Almacen pfirst, Almacen plast, int size) {
        this.pfirst = pfirst;
        this.plast = plast;
        this.size = size;
    }

    public ListaAlm() {
        this.pfirst = null;
        this.plast = null;
        this.size = 0;
    }
    
    public boolean esVacio(){
        return pfirst == null;
    }
    
    public Almacen proximo(Almacen nodo){
        return nodo.getNext();
    }
    public Almacen previo(Almacen nodo){
        return nodo.getPrev();
    } 
    
    public Almacen buscarAlmacen(int posicion){
        if(!this.esVacio()){
            Almacen aux = pfirst;            
            while(aux.getId() < posicion){
                aux = proximo(aux);
            }
            return aux;
        }
        return null;
    }
    
    public void insertar(){
        
        char letra = (char)(size+65);
        Almacen newAlm = new Almacen(size, "Almacen "+letra); //asigna id dependiendo de la posicion y nombre al almacen usando ASCII 
        if(this.esVacio()){
            this.pfirst = this.plast = newAlm;
        }else{
            Almacen temp = this.plast;
            this.plast = newAlm;
            this.plast.setPrev(temp);
            temp.setNext(newAlm);
        }
        size++;
    }
    
    public String info(){
        String info = "";
        
        Almacen aux = this.pfirst;
        
        while(aux != null){
            info += aux.getNombre()+" id: "+aux.getId()+"\n";
            aux = this.proximo(aux);
        }       
        return info;
    }

    public Almacen getPfirst() {
        return pfirst;
    }

    public void setPfirst(Almacen pfirst) {
        this.pfirst = pfirst;
    }

    public Almacen getPlast() {
        return plast;
    }

    public void setPlast(Almacen plast) {
        this.plast = plast;
    }

    public int getSize() {
        return size;
    }
    
    
}
