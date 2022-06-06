/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Santiago
 */
public class Pila {

    private NodoPila inicio;
    private int tamaño;

    /**
     * @author: Santiago Urdaneta
     * @deprecated: constructor de la pila
     */
    public void Pila() {
        inicio = null;
        tamaño = 0;
    }

    /**
     * @author: Santiago Urdaneta
     * @deprecated: conocer si la pila está vacía
     * @return: booleano
     */
    public boolean esVacio() {
        return inicio == null;
    }

    /**
     * @author: Santiago Urdaneta
     * @deprecated: determina el tamaño de la pila
     * @return: int
     */
    public int getTamaño() {
        return tamaño;
    }

    /**
     * @author: Santiago Urdaneta
     * @deprecated: apilar nodo en la pila
     * @param: valor dato que almacena nodo a apilar
     */
    public void apilar(int valor) {
        NodoPila nuevo = new NodoPila();
        nuevo.setValor(valor);
        if (esVacio()) {
            inicio = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        tamaño++;
    }

    /**
     * @author: Santiago Urdaneta
     * @deprecated: desapilar nodo en la pila
     */
    public void desapilar() {
        if (!esVacio()) {
            inicio = inicio.getSiguiente();
            tamaño--;
        }
    }

    /**
     * @author: Santiago Urdaneta
     * @deprecated: indica nodo que esta en el tope de la pila
     * @return: int
     */
    public int cima() {
        if (!esVacio()) {
            return inicio.getValor();
        } else {
            return -1;
        }
    }

    public NodoPila getInicio() {
        return inicio;
    }

    public void setInicio(NodoPila inicio) {
        this.inicio = inicio;
    }
}
