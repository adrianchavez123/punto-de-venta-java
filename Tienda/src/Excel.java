
import java.io.FileOutputStream;
import java.util.Vector;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author adrian
 */
public class Excel {

    String datos[][];
    String columnas[];
    int d;

    Excel(String datos[][], String columnas[], int d) {

        this.datos = datos;
        this.columnas = columnas;
        this.d = d;


    }

    public void crearExcel(String namesheet, String filename)
            throws Exception {
        try {
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet(namesheet);

            Row row = sheet.createRow((short) 1);
            for (int i = 0; i < columnas.length; i++) {

                CellStyle style = wb.createCellStyle();
                style.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                Font font = wb.createFont();
                // font.setFontHeightInPoints((short)10);
                font.setFontName("Courier New");
                // font.setItalic(true);
                // font.setStrikeout(true);
                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
                font.setColor(IndexedColors.WHITE.getIndex());
                style.setFont(font);
                createCell(row, i, columnas[i], style);
            }

            for (int i = 0; i < d; i++) {
                 Row r = sheet.createRow((short) i+2);
           
                for (int j = 0; j < columnas.length; j++) {
                    System.out.print(datos[i][j] + "  ");
                    createCell(r, j, datos[i][j], null);
                }
                System.out.println();
            }

            // Escribir el fichero.
            FileOutputStream fileOut = new FileOutputStream(filename);
            wb.write(fileOut);
            fileOut.close();
            
            JOptionPane.showMessageDialog(null, "Archivo guardado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createCell(Row row, int i, String value, CellStyle style) {
        Cell cell = row.createCell(i);
        value = value + " ";
        cell.setCellValue(value);
        // si no hay estilo, no se aplica
        if (style != null) {
            cell.setCellStyle(style);
        }
    }

   
}
