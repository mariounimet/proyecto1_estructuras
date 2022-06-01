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

    public void Seleccionador() {

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
                String texto = "";
                while ((linea = br.readLine()) != null) {
                    texto += linea + "\n";
                }
                System.out.print(texto);
                if (!"".equals(texto)){
                    String[] datos = texto.split("\n");
                    int fin_alm = 0;
                    for (int i = 0; i<datos.length; i++){
                        if (datos[i].equals("Rutas;")){
                            fin_alm = i;
                            break;
                        }
                    }
                    for (int j = 1; j<fin_alm; j++){
                        String[] almacenes = datos[j].split(":");
                        System.out.println(almacenes[0]);
                    }
                    for (int k = fin_alm+1; k<datos.length; k++){
                        System.out.println(datos[k]);
                    }
                    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la carga de datos.");
            }
        }
    }

    public void lector() {

    }
}
