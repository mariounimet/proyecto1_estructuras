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

    /**
     * @author: Mario Quintero
     * @deprecated: cargar almacen de base de datos
     * @param: productos lista de productos perteneciente al almacen que se está
     * cargando
     */
    public void cargarAlm(ListaProd productos) {
        this.almacenes.insertar();
        this.almacenes.buscarAlmacen(size - 1).setProductos(productos);
        size++;
    }

    /**
     * @author: Mario Quintero
     * @deprecated: cargar ruta de un almacen a otro desde base de datos
     * @param: i almacen de salida
     * @param: j almacen de llegada
     * @param: peso peso de la arista
     */
    public void cargarRuta(char i, char j, int peso) {
        this.rutas[(int) i - 65][(int) j - 65] = peso;
    }

    /**
     * @author: Mario Quintero
     * @deprecated: crear nuevo almacen en el grafo
     */
    public void nuevoAlm() {
        if (this.size < this.max) {
            this.almacenes.insertar();
            if (size > 0) {
                this.nuevaRuta(size - 1);
            }
            this.agregarStock(size - 1);
            size++;
        }
    }

    /**
     * @author: Mario Quintero
     * @deprecated: agregar productos a un almacen
     * @param: id almacen al que se le agregarán productos
     */
    public void agregarStock(int id) {
        while (true) {
            try {
                int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar productos al Almacen " + (char) (id + 65) + "?", "añadir productos", 0);
                if (opcion == 1) {
                    break;
                }
                String nombre = JOptionPane.showInputDialog(null, "Ingrese nombre de producto \n\nProductos en stock:\n" + this.almacenes.buscarAlmacen(id).getProductos().info());

                int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Cuántas unidades desea ingresar"));
                if (cantidad < 1) {
                    JOptionPane.showMessageDialog(null, "Valor ingresado no válido");
                    continue;
                }
                if (this.almacenes.buscarAlmacen(id).getProductos().buscarProducto(nombre) != null) {
                    this.almacenes.buscarAlmacen(id).getProductos().buscarProducto(nombre).agregar(cantidad);
                } else {
                    this.almacenes.buscarAlmacen(id).getProductos().insertar(cantidad, nombre);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor ingresado no válido");
            }
        }
    }

    /**
     * @author: Mario Quintero
     * @deprecated: crear nueva arista
     * @param: idNuevo posicion de nuevo almacen agregado
     */
    public void nuevaRuta(int idNuevo) {
        int entradasAgregadas = 0;
        int cantEntradas = 0;
        int salidasAgregadas = 0;
        int cantSalidas = 0;

        while (true) {
            try {
                cantEntradas = Integer.parseInt(JOptionPane.showInputDialog(null, ("¿Cuantas entradas al Almacen " + (char) (idNuevo + 65)) + " desea registrar?\n\nCantidad de almacenes existentes: " + (idNuevo)));
                if (cantEntradas < 1 || cantEntradas > idNuevo) {
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Valor no válido");

            }
        }
        while (true) {
            try {

                int entrada = Integer.parseInt(JOptionPane.showInputDialog(null, (this.almacenes.info() + "\n\ningrese id de nueva entrada"), ("Nueva entrada a " + this.almacenes.buscarAlmacen(idNuevo).getNombre()), -1));
                if (entrada < 0 || entrada >= idNuevo || this.rutas[entrada][idNuevo] != 0) {
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                int peso = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese distancia entre almacenes"));
                if (peso < 1) {
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }

                this.rutas[entrada][idNuevo] = peso;
                entradasAgregadas++;

                if (entradasAgregadas == cantEntradas) {
                    break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "valor no válido");
            }
        }
        while (true) {
            try {
                cantSalidas = Integer.parseInt(JOptionPane.showInputDialog(null, ("¿Cuantas salidas desde el Almacen " + (char) (idNuevo + 65)) + " desea registrar?"));
                if (cantSalidas < 1 || cantSalidas > idNuevo) {
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                break;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "valor no válido");
            }
        }
        while (true) {
            try {
                int salida = Integer.parseInt(JOptionPane.showInputDialog(null, (this.almacenes.info() + "\n\ningrese id de nueva salida"), ("Nueva salida desde " + this.almacenes.buscarAlmacen(idNuevo).getNombre()), -1));
                if (salida < 0 || salida >= idNuevo || this.rutas[idNuevo][salida] != 0) {
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }
                int peso = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese distancia entre almacenes"));
                if (peso < 1) {
                    JOptionPane.showMessageDialog(null, "Valor no válido");
                    continue;
                }

                this.rutas[idNuevo][salida] = peso;
                salidasAgregadas++;

                if (salidasAgregadas == cantSalidas) {
                    break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "valor no válido");
            }
        }

    }

    /**
     * @author: Mario Quintero
     * @deprecated: conocer stock de cada almacen por BFS
     */
    public void reportePorAnchura() {
        int[] orden = this.recorridoAncho(this.rutas);

        for (int i : orden) {
            System.out.println(i);
            JOptionPane.showMessageDialog(null, this.almacenes.buscarAlmacen(i).getProductos().info(), this.almacenes.buscarAlmacen(i).getNombre(), 1);
        }
    }

    /**
     * @author: Santiago Urdaneta
     * @deprecated: conocer stock de cada almacen por DFS
     */
    public void reportePorProfundidad() {
        int[] orden = this.recorridoProfundidad(this.rutas);

        for (int i : orden) {
            System.out.println(i);
            JOptionPane.showMessageDialog(null, this.almacenes.buscarAlmacen(i).getProductos().info(), this.almacenes.buscarAlmacen(i).getNombre(), 1);
        }
    }

    /**
     * @author: Mario Quintero
     * @deprecated: realizar pedido por anchura
     */
    public void pedido() {
        ListaProd lp = new ListaProd();
        int[] recorrido = recorridoAncho(this.rutas);
        String nombre;
        int cantidad;
        int idAlmacen;

        for (int i : recorrido) {
            ListaProd aux = this.almacenes.buscarAlmacen(i).getProductos();
            Producto producto = aux.getPfirst();
            while (producto != null) {
                lp.insertar(producto.getCantidad(), producto.getNombre());
                producto = aux.proximo(producto);
            }
        }

        while (true) {
            nombre = JOptionPane.showInputDialog(null, ("introduzca el nombre del producto deseado:\n\n" + lp.info()));
            if (lp.buscarProducto(nombre) == null) {
                JOptionPane.showMessageDialog(null, "Producto no válido");
                continue;
            }
            break;
        }

        while (true) {
            try {
                cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, ("¿Cuántas unidades desea?\n" + nombre + ": " + lp.buscarProducto(nombre).getCantidad())));
                if (cantidad < 0 || cantidad > lp.buscarProducto(nombre).getCantidad()) {
                    JOptionPane.showMessageDialog(null, "No existen suficientes unidades en stock");
                    continue;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "valor no válido");
                continue;
            }
            break;
        }

        while (true) {
            try {
                idAlmacen = Integer.parseInt(JOptionPane.showInputDialog(null, ("escoger almacen desde el que se hará el pedido:\n\n" + this.almacenes.info())));
                if (this.almacenes.buscarAlmacen(idAlmacen) == null) {
                    JOptionPane.showMessageDialog(null, "id no válido");
                    continue;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "valor no válido");
                continue;
            }
            break;
        }

        Almacen alm = this.almacenes.buscarAlmacen(idAlmacen);
        if (alm.getProductos().buscarProducto(nombre) != null) {
            if (alm.getProductos().buscarProducto(nombre).getCantidad() >= cantidad) {
                alm.getProductos().buscarProducto(nombre).retirar(cantidad);
                JOptionPane.showMessageDialog(null, "pedido completado");
            } else {
                if (alm.getProductos().buscarProducto(nombre).getCantidad() > 0) {
                    cantidad = cantidad - alm.getProductos().buscarProducto(nombre).getCantidad();
                    alm.getProductos().buscarProducto(nombre).setCantidad(0);
                }
                pedirProductoAncho(cantidad, idAlmacen, nombre);
            }
        } else {
            pedirProductoAncho(cantidad, idAlmacen, nombre);
        }
    }

    /**
     * @author: Mario Quintero
     * @deprecated: pedir producto a otro almacen por anchura
     */
    public void pedirProductoAncho(int cantidad, int origen, String nombreProd) {
        int[] recorrido = recorridoAncho(this.rutas);
        int alm = -1;
        String rutaMinima;
        caminosMinimos camino;
        for (int i : recorrido) {
            if (this.almacenes.buscarAlmacen(i).getProductos().buscarProducto(nombreProd) != null) {
                if (this.almacenes.buscarAlmacen(i).getProductos().buscarProducto(nombreProd).getCantidad() > 0 && alm == -1) {
                    alm = i;
                } else if (this.almacenes.buscarAlmacen(i).getProductos().buscarProducto(nombreProd).getCantidad() > 0) {
                    caminosMinimos cmActual = new caminosMinimos(this, alm);
                    cmActual.crearCaminos();
                    caminosMinimos cmPrueba = new caminosMinimos(this, i);
                    cmPrueba.crearCaminos();
                    if (cmActual.getD()[origen] > cmPrueba.getD()[origen]) {
                        alm = i;
                    }
                }
            }
        }
        camino = new caminosMinimos(this, alm);
        camino.crearCaminos();
        JOptionPane.showMessageDialog(null, ("Se han pedido unidades al almacen " + (char) (alm + 65) + "\nSe ha tomado como ruta:\n" + camino.recuperar(origen, "")));
        if (this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd) != null) {
            if (this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).getCantidad() >= cantidad) {
                this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).retirar(cantidad);
                JOptionPane.showMessageDialog(null, "pedido completado");

            } else {
                if (this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).getCantidad() > 0) {
                    cantidad = cantidad - this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).getCantidad();
                    this.almacenes.buscarAlmacen(alm).getProductos().buscarProducto(nombreProd).setCantidad(0);
                }

                pedirProductoAncho(cantidad, origen, nombreProd);
            }
        } else {
            pedirProductoAncho(cantidad, origen, nombreProd);
        }
    }
    
    /**
     * @author: Mario Quintero
     * @deprecated: recorrer grafo por anchura
     * @return: arreglo de números que representan el orden en el que se recorre
     * el grafo
     */
    public int[] recorridoAncho(int[][] rutas) {
        if (this.size == 0) {
            return null;
        } else {
            int w;
            int[] recorrido = new int[this.size - 1];
            for (int i = 0; i < this.size - 1; i++) {
                recorrido[i] = -1;
            }
            recorrido[0] = 0;
            Cola cola = new Cola();

            cola.encolar(0);
            int vacio = 1;
            while (!cola.esVacio()) {
                System.out.print(cola.getPfirst().getX() + " ");
                w = cola.desencolar().getX();
                for (int i = 0; i < this.size - 1; i++) {
                    if (rutas[w][i] != 0 && vacio < size - 1 && !estaEnArray(i, recorrido)) {
                        recorrido[vacio] = i;
                        vacio++;
                        cola.encolar(i);
                    }
                }
            }
            return recorrido;
        }
    }

    /**
     * @author: Santiago Urdaneta
     * @deprecated: recorrer grafo por profundidad
     * @return: arreglo de números que representan el orden en el que se recorre
     * el grafo
     */
    public int[] recorridoProfundidad(int[][] rutas) {
        if (this.size == 0) {
            return null;
        } else {
            int w;
            int[] recorrido = new int[this.size - 1];
            for (int i = 0; i < this.size - 1; i++) {
                recorrido[i] = -1;
            }
            recorrido[0] = 0;
            Pila pila = new Pila();

            pila.apilar(0);
            int vacio = 1;
            while (!pila.esVacio()) {
                System.out.print(pila.getInicio().getValor() + " ");
                w = pila.cima();
                pila.desapilar();
                for (int i = 0; i < this.size - 1; i++) {
                    if (rutas[w][i] != 0 && vacio < size - 1 && !estaEnArray(i, recorrido)) {
                        recorrido[vacio] = i;
                        vacio++;
                        pila.apilar(i);
                    }
                }
            }
            return recorrido;
        }
    }

    /**
     * @author: Mario Quintero
     * @deprecated: busca elemento en arreglo
     * @param: dato elemento a buscar
     * @param array arreglo en el que se buscará
     * @return booleano (encontrado o no)
     */
    public boolean estaEnArray(int dato, int[] array) {
        for (int i : array) {
            if (i == dato) {
                return true;
            }
        }
        return false;
    }

    /**
     * @author: Mario Quintero
     * @deprecated: inicializar matriz de adyacencia
     * @param: max numero maximo de vertices
     * @return: matriz de adyacencia inicial
     */
    public int[][] inicializar(int max) {
        int[][] array = new int[max][max];

        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
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
