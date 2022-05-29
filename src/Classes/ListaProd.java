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
public class ListaProd {
    private Producto pfirst, plast;
    private int size;

    public ListaProd(Producto pfirst, Producto plast, int size){
        this.pfirst = pfirst;
        this.plast = plast;
        this.size = size;
    }

    public ListaProd(){
        this.pfirst = null;
        this.plast = null;
        this.size = 0;
    }
    
    public boolean esVacio(){
        return pfirst == null;
    }
    
    public Producto proximo(Producto nodo){
        return nodo.getNext();
    }
    public Producto previo(Producto nodo){
        return nodo.getPrev();
    } 
    
    public Producto buscarProducto(String nombre){
        if(!this.esVacio()){
            Producto aux = pfirst;            
            while(!aux.getNombre().toLowerCase().equals(nombre.toLowerCase())){
                aux = proximo(aux);
                if(aux == null){
                    return null;
                }
            }
            return aux;
        }
        return null;
    }
    
    public void insertar(int cantidad, String nombre){
        size++;
        Producto newprod = new Producto(cantidad, nombre);
        if(this.esVacio()){
            this.pfirst = this.plast = newprod;
        }else{
            Producto temp = this.plast;
            this.plast = newprod;
            this.plast.setPrev(temp);
            temp.setNext(newprod);
        }
    }
    
    public String info(){
        String info = "";
        Producto aux = this.pfirst;
        
        while(aux != null){
            info += aux.getNombre()+": "+aux.getCantidad();
            aux = this.proximo(aux);
        }        
        return info;
    }
}
