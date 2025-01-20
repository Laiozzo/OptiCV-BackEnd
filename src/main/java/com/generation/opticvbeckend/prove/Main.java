package com.generation.opticvbeckend.prove;

import com.generation.opticvbeckend.AI.OpenAIClient;
import com.generation.opticvbeckend.model.parse.CVParserIText;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
//        try {
//            // Percorso al PDF
//            String filePath = "C:/Users/nicol/iCloudDrive/com~apple~Pages/CV_EDUCATore.pdf";
//
//            // Carica il PDF
//            PdfReader reader = new PdfReader(filePath);
//            PdfDocument pdfDoc = new PdfDocument(reader);
//
//            // Costruisci un StringBuilder per accumulare il testo
//            StringBuilder fullText = new StringBuilder();
//
//            // Estrai il testo da ogni pagina
//            for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
//                String pageText = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i));
//                fullText.append(pageText).append("\n");
//            }
//
//            // Chiudi il documento PDF
//            pdfDoc.close();
//
//            // Stampa il testo completo estratto
//            System.out.println("Testo estratto dal PDF:");
//            System.out.println(fullText.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        CVParserIText pdf = new CVParserIText();

        String pdfString = pdf.pdfToString("C:/Users/nicol/iCloudDrive/com~apple~Pages/CV_EDUCATore.pdf");

        OpenAIClient aiClient = new OpenAIClient("http://127.0.0.1:1234/v1/chat/completions", "lm-studio");

        aiClient.generatedCV(pdfString);





    }
}




