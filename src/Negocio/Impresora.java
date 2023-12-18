/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import Modelo.Mensaje;
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
import com.itextpdf.text.Element;

public class Impresora {

    private Document documento = new Document();

    public Impresora() throws FileNotFoundException, DocumentException {
        long num = System.currentTimeMillis();

        //2. Crear el archivo de almacenamiento--> PDF
        String name = "fichero-" + num + ".pdf";
        FileOutputStream ficheroPdf = new FileOutputStream("src/pdf/" + name);
        //3. Asignar la estructura del pdf al archivo físico:
        PdfWriter.getInstance(documento, ficheroPdf);
        documento.open();
    }

    public void closeDocument() {
        if (documento != null && documento.isOpen()) {
            documento.close();
        }
    }

    public void imprimirTitulo(String mensaje) throws FileNotFoundException, DocumentException {
        //Creando parrafos:
        Paragraph parrafo = new Paragraph();
        parrafo.setAlignment(Element.ALIGN_CENTER);
        parrafo.add(mensaje);
//        parrafo.add(mensajes.toString());
        parrafo.add("\n \n");
//
        documento.add(parrafo);

    }

    public void imprimirParrafo(String mensaje) throws FileNotFoundException, DocumentException {
        //Creando parrafos:
        Paragraph parrafo = new Paragraph();
        parrafo.add(mensaje);
//        parrafo.add(mensajes.toString());
        parrafo.add("\n \n");
//
        documento.add(parrafo);

    }

    public void imprimirPasos(Mensaje mensajes) throws FileNotFoundException, DocumentException {

        if (mensajes.getMensajes().isEmpty()) {
            throw new RuntimeException("Imposible imprimir datos vacíos");
        }

//
        PdfPTable table = new PdfPTable(3);
        PdfPCell celda = new PdfPCell(new Phrase("OPERACIÓN"));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(celda);
        celda = new PdfPCell(new Phrase("PILA"));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(celda);
        celda = new PdfPCell(new Phrase("SALIDA PARCIAL"));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(celda);
        boolean sw = false;
        int contador = 0;
        for (String datos : mensajes.getMensajes()) {
            celda = new PdfPCell(new Phrase(datos));
            if (sw) {
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            }
            table.addCell(celda);
            contador++;
            if (contador == 3) {
                contador = 0;
                sw = !sw;
            }
        }

        documento.add(table);
    }

    public void imprimirCalculo(Mensaje mensajes) throws FileNotFoundException, DocumentException {
        if (mensajes.getMensajes().isEmpty()) {
            throw new RuntimeException("Imposible imprimir datos vacíos");
        }

//
        PdfPTable table = new PdfPTable(2);
        PdfPCell celda = new PdfPCell(new Phrase("OPERACIÓN"));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(celda);
        celda = new PdfPCell(new Phrase("PILA"));
        celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(celda);
        int contador = 0;
        boolean sw = false;
        for (String datos : mensajes.getMensajes()) {
            celda = new PdfPCell(new Phrase(datos));
            if (sw) {
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
            }
            table.addCell(celda);
            contador++;
            if (contador == 2) {
                contador = 0;
                sw = !sw;
            }

        }

        documento.add(table);

    }

}
