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

    public String parseCv() throws IOException{
        try {
            reader = new PdfReader(filePath);
            PdfDocument pdfDoc = new PdfDocument(reader);
            StringBuilder fullText = new StringBuilder();

            for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                fullText.append(PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i))).append("\n");
            }
            pdfDoc.close();

            // Analisi del testo estratto
            CV cv = parseCV(fullText.toString());
            return cv.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CV parseCV(String text) {
        CV cv = new CV();

        // Estrarre dati comuni (esempio con pattern generici)
        cv.setName(extractWithFallback(text, new String[]{"Name:", "Full Name:", "Nome:"}));
        cv.setContactDetails(extractWithFallback(text, new String[]{"Contact:", "Contact Details:", "Contatti:"}));
        cv.setEducation(extractWithFallback(text, new String[]{"Education:", "Studies:", "Istruzione:"}));
        cv.setWorkExperience(extractWithFallback(text, new String[]{"Experience:", "Work History:", "Esperienza:"}));
        cv.setSkills(extractWithFallback(text, new String[]{"Skills:", "Competencies:", "Competenze:"}));

        return cv;
    }

    private static String extractWithFallback(String text, String[] patterns) {
        for (String pattern : patterns) {
            int startIndex = text.indexOf(pattern);
            if (startIndex != -1) {
                int endIndex = text.indexOf("\n", startIndex);
                if (endIndex == -1) endIndex = text.length();
                return text.substring(startIndex + pattern.length(), endIndex).trim();
            }
        }
        return "Not Found";
    }
}


