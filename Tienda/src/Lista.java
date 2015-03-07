
import java.io.BufferedWriter;
import java.io.FileWriter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrian
 */
public class Lista {
    
    

    Lista(String[] nombreAr, String[] precioAr, int[] canti,String total,String cajero) {
       
        try
        {
        BufferedWriter writer= new BufferedWriter(new FileWriter("operacion.txt"));
           writer.write("Nombre empresa\n");
           writer.write("Direccion empresa\n");
           writer.write("Producto\t\tcantidad\t\tprecio\n");
           for(int i = 0; i<nombreAr.length;i++)
           {
                writer.write(nombreAr[i]+"  \t\t"+canti[i]+"   \t\t"+precioAr[i]+"\n");
                
           }
           writer.write("--------------------\n");
           writer.write("total\t\t"+total+"\n");
           writer.write("\nle atendio   "+cajero);
           writer.close();
           
          
        }
        catch(Exception e)
        {
        }
    }

    
}
