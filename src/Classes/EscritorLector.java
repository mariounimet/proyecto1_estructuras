/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author Radenixlol
 */
public class EscritorLector {

    JFileChooser jfc = new JFileChooser();
    File archivo;
    String texto = "";

    public void Seleccionador_lectura() {

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        jfc.setFileFilter(filtro);
        int selec = jfc.showOpenDialog(null);

        if (selec == JFileChooser.APPROVE_OPTION) {
            this.archivo = jfc.getSelectedFile();
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                JOptionPane.showMessageDialog(null, "Carga exitosa.");
                // Lectura del fichero
                String linea;
                while ((linea = br.readLine()) != null) {
                    texto += linea + "\n";
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la carga de datos.");
            }
        }
    }

    public void lectura() {
        if (!"".equals(texto)) {
            String[] datos = texto.split("\n");
            int fin_alm = final_list(datos);
            if (fin_alm != 0) {
                String almacenes = "";
                for (int j = 1; j < fin_alm; j++) {
                    almacenes += datos[j] + " ";
                }

                for (int k = fin_alm + 1; k < datos.length; k++) {

                    String rutas = String.valueOf(datos[k]);
                    String[] ruta = rutas.split(",");
                    //datos de las rutas separados, falta hacer la carga
                }
                System.out.println("rutas listas");
            } else {
                JOptionPane.showMessageDialog(null, "No hay almacenes en el archivo");
            }
        }
        
    

    

    public void clasificacion() {
        ListaProd lista = new ListaProd();
        String[] productos = (almacenes.replace(";", ":")).split(":");
        for (int i = 1; i < productos.length; i += 2) {
            String product = String.valueOf(productos[i]);
            String[] producto = (product.replace(",", " ")).split(" ");
            for (int j = 1; j < producto.length; j += 2) {
                lista.insertar(Integer.parseInt(producto[j + 1]), producto[j]);

                //carga de productos
//                            System.out.println(producto[j]+" linea");
//                            System.out.println(producto[j+1]+" linea");
            }
        }
        System.out.println("almacen listo");

    }

    public int final_list(String[] datos) {
        int fin_alm;
        for (int i = 0; i < datos.length; i++) {
            if (datos[i].equals("Rutas;")) {
                fin_alm = i;
                return fin_alm;
            }
        }
        return 0;

    }
}
