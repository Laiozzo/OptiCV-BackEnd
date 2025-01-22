package com.generation.opticvbeckend.model.parse;

import com.generation.opticvbeckend.model.entities.CV;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.IOException;

public class CVParserIText
{
    String filePath = "path/to/file.pdf";
    PdfReader reader;

    public String pdfToString(String filePath) {
        try {
            PdfReader reader = new PdfReader(filePath);
            PdfDocument pdfDoc = new PdfDocument(reader);

            // Costruisci un StringBuilder per accumulare il testo
            StringBuilder fullText = new StringBuilder();

            // Estrai il testo da ogni pagina
            for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                String pageText = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i));
                fullText.append(pageText).append("\n");
            }

            // Chiudi il documento PDF
            pdfDoc.close();

            // Stampa il testo completo estratto
            System.out.println("Testo estratto dal PDF:");
            System.out.println(fullText.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath.toString();
    }


}


