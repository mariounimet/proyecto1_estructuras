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
     /**
 * @author: Mario Quintero
 * @deprecated: conocer si la lista de productos está vacía
 * @return: Booleano
 */     
    public boolean esVacio(){
        return pfirst == null;
    }
    /**
 * @author: Mario Quintero
 * @deprecated: encontrar proximo nodo producto de la lista
 * @param: nodo nodo del que se va a buscar su siguiente
 * @return: Nodo Producto
 */     
    public Producto proximo(Producto nodo){
        return nodo.getNext();
    }
      /**
 * @author: Mario Quintero
 * @deprecated: encontrar anterior nodo producto de la lista
 * @param: nodo nodo del que se va a buscar su anterior
 * @return: Nodo Producto
 */   
    public Producto previo(Producto nodo){
        return nodo.getPrev();
    } 
     /**
 * @author: Mario Quintero
 * @deprecated: buscar un nodo específico
 * @param: nombre nombre de producto que se va a buscar
 * @return: Nodo Producto
 */    
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
     /**
 * @author: Mario Quintero
 * @deprecated: agregar producto a la lista
 * @param: cantidad numero de unidades que se agregaran
 * @param: nombre nombre del producto nuevo
 */    
    public void insertar(int cantidad, String nombre){
        size++;
        Producto newprod = new Producto(cantidad, nombre);
        if(this.esVacio()){
            this.pfirst = this.plast = newprod;
        }else if(this.buscarProducto(nombre) == null){
            Producto temp = this.plast;
            this.plast = newprod;
            this.plast.setPrev(temp);
            temp.setNext(newprod);
        }else{
            this.buscarProducto(nombre).agregar(cantidad);
        }
    }
    /**
 * @author: Mario Quintero
 * @deprecated: conocer los productor de la lista y sus cantidades
 * @return: String informacion de la lista
 */     
    public String info(){
        String info = "";
        Producto aux = this.pfirst;
        
        while(aux != null){
            info += aux.getNombre()+": "+aux.getCantidad()+"\n";
            aux = this.proximo(aux);
        }
        if (info.equals("")){
            return "Almacen vacío";
        }else{
            return info;
        }
    }

    public Producto getPfirst() {
        return pfirst;
    }

    public Producto getPlast() {
        return plast;
    }
    
}
