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
public class Grafo {
    private ListaAlm almacenes;
    private int[][] rutas;
    private int max, size;

    public Grafo(int max) {
        this.almacenes = new ListaAlm();
        this.rutas = inicializar(max);
        this.max = max;
        this.size = 0;
    }
    
    public void cargarAlm(ListaProd productos){
        this.almacenes.insertar();
        this.almacenes.buscarAlmacen(size).setProductos(productos);
    }
    public void cargarRuta(char i, char j, int peso){
        this.rutas[(int)i-65][(int)j-65] = peso;
    }
    
    
    public void nuevoAlm(){
        if(this.size  < this.max){
            this.almacenes.insertar();
            if(size > 0){
                this.nuevaRuta(size);
            }
        this.agregarStock(size);
        size++;
        }
    }
    
    public void agregarStock(int id){
        while(true){
            try{
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar productos al Almacen "+(char)(id+65)+"?", "añadir productos", 0);
                if(opcion == 1){
                    break;
                }
                String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre de producto \n\nProductos en stock:\n"+this.almacenes.buscarAlmacen(id).getProductos().info());
                
                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Cuántas unidades desea ingresar"));
                if(cantidad<1){
                    JOptionPane.showMessageDialog(null, "Valor ingresado no válido");
                    continue;
                }
                if(this.almacenes.buscarAlmacen(id).getProductos().buscarProducto(nombre) != null){
                    this.almacenes.buscarAlmacen(id).getProductos().buscarProducto(nombre).agregar(cantidad);
                }else{
                    this.almacenes.buscarAlmacen(id).getProductos().insertar(cantidad, nombre);
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Valor ingresado no válido");
            }
        }
    }
    
    public void nuevaRuta(int idNuevo){
        int entradasAgregadas = 0;
        int cantEntradas = 0;
        int salidasAgregadas = 0;
        int cantSalidas = 0;
        
        while(true){
            try{
                cantEntradas = Integer.parseInt(JOptionPane.showInputDialog(null, ("¿Cuantas entradas al Almacen "+(char)(idNuevo+65))+" desea registrar?\n\nCantidad de almacenes existentes: "+(idNuevo)));
                if(cantEntradas < 1 || cantEntradas>idNuevo){
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                break;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Valor no válido");
                
            }            
        }
        while (true){
            try{
                
                int entrada = Integer.parseInt(JOptionPane.showInputDialog(null, (this.almacenes.info()+"\n\ningrese id de nueva entrada"), ("Nueva entrada a "+this.almacenes.buscarAlmacen(idNuevo).getNombre()), -1));              
                if(entrada<0 || entrada>=idNuevo || this.rutas[entrada][idNuevo] != 0){
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                int peso = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese distancia entre almacenes"));
                if(peso < 1){
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                
                this.rutas[entrada][idNuevo] = peso;               
                entradasAgregadas++;
            
                if(entradasAgregadas == cantEntradas){
                    break;
                }                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "valor no válido");
            }        
        }
        while(true){
            try{
                cantSalidas = Integer.parseInt(JOptionPane.showInputDialog(null, ("¿Cuantas salidas desde el Almacen "+(char)(idNuevo+65))+" desea registrar?"));
                    if(cantSalidas < 1 || cantSalidas > idNuevo){
                        JOptionPane.showMessageDialog(null, "Valor no válido");
                        continue;
                    }
                    break;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "valor no válido");
            }            
        }
        while (true){
            try{                
                int salida = Integer.parseInt(JOptionPane.showInputDialog(null, (this.almacenes.info()+"\n\ningrese id de nueva salida"), ("Nueva salida desde "+this.almacenes.buscarAlmacen(idNuevo).getNombre()), -1));              
                if(salida<0 || salida>=idNuevo || this.rutas[idNuevo][salida] != 0){
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                int peso = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese distancia entre almacenes"));
                if(peso < 1){
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                
                this.rutas[idNuevo][salida] = peso;               
                salidasAgregadas++;
            
                if(salidasAgregadas == cantSalidas){
                    break;
                }                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "valor no válido");
            }        
        }
        
    }
    
    public int[][] inicializar(int max){
        int[][] array= new int[max][max];
        
        for(int i = 0; i < max; i++){
            for(int j = 0; j < max; j++){
                array[i][j] = 0;
            }
        }       
        return array;
    }

    public int[][] getRutas() {
        return rutas;
    }
    
    
}
