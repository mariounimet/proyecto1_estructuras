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
    
    public void cargarProducto(int cantidad, String nombre){
        this.productos.insertar(cantidad, nombre);
    }
    
    public void gestionarProductos(){
        if(this.productos.esVacio()){
            JOptionPane.showMessageDialog(null, "El "+this.nombre+" está vacío");
        }else{
            String nombreProd = JOptionPane.showInputDialog(null, this.productos.info());
            if(this.productos.buscarProducto(nombreProd) != null){
                Producto prod = this.productos.buscarProducto(nombreProd);
                nombreProd = prod.getNombre();
                
                try{    
                    int num = Integer.parseInt(JOptionPane.showInputDialog(null, nombreProd+"\nCantidad: "+prod.getCantidad(), "número de unidades a agregar"));
                    prod.agregar(num);
                    JOptionPane.showMessageDialog(null, num+ " Unidades agregadas");
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Debe escribir un número entero");
                }               
            }
        }
    }
    
    public String info(){
        String info = "";
        
        
        return info;
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
