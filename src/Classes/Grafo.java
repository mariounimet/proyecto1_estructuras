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
        this.almacenes.buscarAlmacen(size-1).setProductos(productos);
        size++;
    }
    public void cargarRuta(char i, char j, int peso){
        this.rutas[(int)i-65][(int)j-65] = peso;
    }
    
    
    public void nuevoAlm(){
        if(this.size  < this.max){
            this.almacenes.insertar();
            if(size > 0){
                this.nuevaRuta(size-1);
            }
        this.agregarStock(size-1);
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
    
    public void reportePorAnchura(){
        int [] orden = this.recorridoAncho(this.rutas);
        
        String ordenSeguido = "";
        for(int i: orden){
            System.out.println(i);
            JOptionPane.showMessageDialog(null, this.almacenes.buscarAlmacen(i).getProductos().info(), this.almacenes.buscarAlmacen(i).getNombre(), 1);
            ordenSeguido += this.almacenes.buscarAlmacen(i).getNombre() + "\n";
        }        
    }
    
    public void pedido(){
        ListaProd lp = new ListaProd();
        int[] recorrido = recorridoAncho(this.rutas);
        String nombre;
        int cantidad;
        int idAlmacen;
        
        for(int i: recorrido){
            ListaProd aux = this.almacenes.buscarAlmacen(i).getProductos();
            Producto producto = aux.getPfirst();
            while (producto != null){
                lp.insertar(producto.getCantidad(), producto.getNombre());
                producto = aux.proximo(producto);
            }
        }
        
        while (true){           
            nombre = JOptionPane.showInputDialog(null, ("introduzca el nombre del producto deseado:\n\n"+lp.info()));
            if(lp.buscarProducto(nombre)==null){
                JOptionPane.showMessageDialog(null, "Producto no válido");
                continue;
            }
            break;
        }
        
        while(true){
        try{
                cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, ("¿Cuántas unidades desea?\n"+nombre+": "+lp.buscarProducto(nombre).getCantidad())));
                if(cantidad > lp.buscarProducto(nombre).getCantidad()){
                    JOptionPane.showMessageDialog(null, "No existen suficientes unidades en stock");
                    continue;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "valor no válido");
                continue;
            }
            break;
        }
        
        while(true){
            try{
                idAlmacen = Integer.parseInt(JOptionPane.showInputDialog(null, ("escoger almacen desde el que se hará el pedido:\n\n"+this.almacenes.info())));
                if (this.almacenes.buscarAlmacen(idAlmacen)==null){
                    JOptionPane.showMessageDialog(null, "id no válido");
                    continue;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "valor no válido");
                continue;
            }
            break;
        }
            
        Almacen alm = this.almacenes.buscarAlmacen(idAlmacen);
        if(alm.getProductos().buscarProducto(nombre) != null){
            if(alm.getProductos().buscarProducto(nombre).getCantidad() >= cantidad){
                alm.getProductos().buscarProducto(nombre).retirar(cantidad);
                JOptionPane.showMessageDialog(null, "pedido completado");
            }else{
                if(alm.getProductos().buscarProducto(nombre).getCantidad() > 0){
                    cantidad = cantidad - alm.getProductos().buscarProducto(nombre).getCantidad();
                    alm.getProductos().buscarProducto(nombre).setCantidad(0);
                }
                pedirProducto(cantidad, idAlmacen, nombre);
            }
        }else{
            if(alm.getProductos().buscarProducto(nombre) != null){
                pedirProducto(cantidad, idAlmacen, nombre);
            }
        }
    }
    
    public void pedirProducto(int cantidad, int origen, String nombreProd){
        int[] recorrido = recorridoAncho(this.rutas);
        int alm = -1;
        String rutaMinima;
        for(int i: recorrido){
            if(this.almacenes.buscarAlmacen(i).getProductos().buscarProducto(nombreProd) != null){
                if(this.almacenes.buscarAlmacen(i).getProductos().buscarProducto(nombreProd).getCantidad()>0 && alm == -1){
                    alm = i;
                }else if(this.almacenes.buscarAlmacen(i).getProductos().buscarProducto(nombreProd).getCantidad()>0){
                    caminosMinimos cmActual = new caminosMinimos(this, alm);
                    caminosMinimos cmPrueba = new caminosMinimos(this, i);
                    if(cmActual.getD()[origen] > cmPrueba.getD()[origen]){
                        alm = i;
                    }
                }
            }
        caminosMinimos camino = new caminosMinimos(this, alm);
        JOptionPane.showMessageDialog(null, ("Se han pedido unidades al almacen "+(char)(alm+65)+"\nSe ha tomado como ruta:\n"+camino.recuperar(origen, "")));
        }
        
        if(this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).getCantidad() >= cantidad){
                this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).retirar(cantidad);
                JOptionPane.showMessageDialog(null, "pedido completado");
            }else{
                if(this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).getCantidad() > 0){
                    cantidad = cantidad - this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).getCantidad();
                    this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).setCantidad(0);
                }
                pedirProducto(cantidad, origen, nombreProd);
            }   
    }
    
    public int[] recorridoAncho(int[][] rutas){
        if(this.size == 0){
            return null;
        }else{
            int w;
            int[] recorrido = new int[this.size-1];
            for(int i = 0; i<this.size-1; i++){
                recorrido[i] = -1;
            }
            recorrido[0] = 0;
            Cola cola = new Cola();
            
            cola.encolar(0);
            int vacio = 1;
            while(!cola.esVacio()){
                System.out.print(cola.getPfirst().getX()+" ");
                w = cola.desencolar().getX();
                for(int i = 0; i < this.size-1; i++){
                    if(rutas[w][i] != 0 && vacio < size-1 && !estaEnArray(i, recorrido)){
                        recorrido[vacio] = i;
                        vacio++;
                        cola.encolar(i);
                    }
                }
            }           
            return recorrido;
        }        
    }
    
    public boolean estaEnArray(int dato, int[] array){
        for(int i: array){
            if(i==dato){
                return true;
            }
        }
        return false;
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

    public int getSize() {
        return size;
    }

    public ListaAlm getAlmacenes() {
        return almacenes;
    }

    public int getMax() {
        return max;
    }
    
    
}
