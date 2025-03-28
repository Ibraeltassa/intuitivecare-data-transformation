package com.intuitivecare.datatransform;

import technology.tabula.Table;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class DataTransformer {
    public static void main(String[] args) {
        // Caminho para o PDF e CSV na pasta resources/files
        String caminhoPDF = Paths.get("src", "main", "resources", "files", "Anexo_I_Rol.pdf").toString();
        String caminhoCSV = Paths.get("src", "main", "resources", "files", "TabelaExtraida.csv").toString();

        // Extrai as tabelas
        List<Table> tabelas = PdfTableExtractor.extractTables(caminhoPDF);

        // Exporta para CSV
        CsvExporter.exportarParaCsv(tabelas, caminhoCSV);

        System.out.println("Transformação concluída com sucesso!");
    }
}
