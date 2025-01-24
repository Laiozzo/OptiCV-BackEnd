package com.generation.opticvbeckend.model.parse;

import com.generation.opticvbeckend.model.entities.CV;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class CVParserIText
{
    PdfReader reader;

    public String pdfToString(String filePath) {
        StringBuilder fullText = new StringBuilder();
        try {
            PdfReader reader = new PdfReader(filePath);
            PdfDocument pdfDoc = new PdfDocument(reader);

            // Costruisci un StringBuilder per accumulare il testo

            // Estrai il testo da ogni pagina
            for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                String pageText = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i));
                fullText.append(pageText).append("\n");
            }

            // Chiudi il documento PDF
            pdfDoc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullText.toString();
    }

}
