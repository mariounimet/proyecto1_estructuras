/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javax.swing.JOptionPane;

/**
 *
 * @author Mario
 */
public class Almacen {
    private Almacen next, prev;
    private String nombre;
    private int id;
    private ListaProd productos;
    
    public Almacen(int x, String nombre){
        this.next = null;
        this.prev = null;
        this.id = x;
        this.nombre = nombre;
        this.productos = new ListaProd();
    }
    /**
 * @author: Mario Quintero
 * @deprecated: carga productos al almacen
 * @param cantidad numero de unidades a agregar
 * @param nombre nombre de producto a agregar
 */    
    public void cargarProducto(int cantidad, String nombre){
        this.productos.insertar(cantidad, nombre);
    }
 
    public Almacen getNext() {
        return next;
    }

    public void setNext(Almacen next) {
        this.next = next;
    }

    public Almacen getPrev() {
        return prev;
    }

    public void setPrev(Almacen prev) {
        this.prev = prev;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaProd getProductos() {
        return productos;
    }

    public void setProductos(ListaProd productos) {
        this.productos = productos;
    }
    
    
}
