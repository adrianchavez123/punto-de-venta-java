
import java.awt.Desktop;
import java.io.File;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author adrian
 */
public class Imprimir {

    public Imprimir(String path) {
        Desktop desktop = Desktop.getDesktop();

        if (desktop.isSupported(Desktop.Action.PRINT)) {
            try {
                File print = new File(path);
                desktop.print(print);

                //JOptionPane.showMessageDialog(null, "Enviando archivo a imprimir ...");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "No pudo imprimirse el Archivo");
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No pudo imprimirse el Archivo");
        }


    }
    
    
}
