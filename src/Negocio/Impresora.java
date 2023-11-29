/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Negocio;
import Util.seed.ListaCD;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
public class Impresora {

     public void imprimirPasos(ListaCD<String> mensajes) throws FileNotFoundException, DocumentException {
        if (mensajes.isEmpty()) {
            throw new RuntimeException("Imposible imprimir datos vacíos");
        }
        long num = System.currentTimeMillis();
        //1. Crear el objeto que va a formatear el pdf:
        Document documento = new Document();
        //2. Crear el archivo de almacenamiento--> PDF
        String name = "fichero-" + num + ".pdf";
        FileOutputStream ficheroPdf = new FileOutputStream("src/pdf/" + name);
        //3. Asignar la estructura del pdf al archivo físico:
        PdfWriter.getInstance(documento, ficheroPdf);
        documento.open();
//        //Creando parrafos:
//        Paragraph parrafo = new Paragraph();
//        parrafo.add("");
//        parrafo.add(mensajes.toString());
//        parrafo.add("\n \n");
//
//        documento.add(parrafo);
//
//        PdfPTable table = new PdfPTable(4);
//        PdfPCell celda = new PdfPCell(new Phrase(""));
//        table.addCell(celda);
//        celda = new PdfPCell(new Phrase(""));
//        table.addCell(celda);
//        celda = new PdfPCell(new Phrase(""));
//        table.addCell(celda);
//        celda = new PdfPCell(new Phrase(""));
//        table.addCell(celda);
//        boolean sw=true;
//        for (String datos : mensajes) {
//            String datos2[] = datos.split("-");
//            for (String datos3 : datos2) {
//                celda = new PdfPCell(new Phrase(datos3));
//                if(sw)
//                     celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
//
//                table.addCell(celda);
//            }
//            sw=!sw;
//        }
//
//        documento.add(table);
        //Baja a disco:
        documento.close();
    }
     
}
