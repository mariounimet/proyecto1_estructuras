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
public class Producto {
    private Producto next, prev;
    private int cantidad;
    private String nombre;

    public Producto(int cantidad, String nombre) {
        this.next = null;
        this.prev = null;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }
    
    public void retirar(int unidades){
        this.cantidad -= unidades;
    }
    
    public void agregar(int unidades){
        this.cantidad += unidades;
    }

    public Producto getNext() {
        return next;
    }

    public void setNext(Producto next) {
        this.next = next;
    }

    public Producto getPrev() {
        return prev;
    }

    public void setPrev(Producto prev) {
        this.prev = prev;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
