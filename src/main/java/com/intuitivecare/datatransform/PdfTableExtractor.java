package com.intuitivecare.datatransform;

import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.Page;
import technology.tabula.PageIterator;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;
import technology.tabula.ObjectExtractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfTableExtractor {
    /**
     * Extrai todas as tabelas de todas as páginas de um PDF.
     *
     * @param pdfPath Caminho do arquivo PDF.
     * @return Lista de tabelas extraídas.
     */
    public static List<Table> extractTables(String pdfPath) {
        List<Table> todasTabelas = new ArrayList<>();

        try {
            // Carrega o PDF com PDFBox
            PDDocument document = PDDocument.load(new File(pdfPath));

            // Usa Tabula para extrair página por página
            ObjectExtractor extractor = new ObjectExtractor(document);
            SpreadsheetExtractionAlgorithm algoritmo = new SpreadsheetExtractionAlgorithm();

            PageIterator iterador = extractor.extract();

            while (iterador.hasNext()) {
                Page page = iterador.next();
                List<Table> tabelasPagina = algoritmo.extract(page);
                todasTabelas.addAll(tabelasPagina);
            }

            document.close();

        } catch (IOException e) {
            System.err.println("Erro ao ler o PDF: " + e.getMessage());
        }

        return todasTabelas;
    }
}
