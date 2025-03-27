package com.intuitivecare.datatransform;

import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class CsvExporter {
    /**
     * Exporta uma lista de tabelas para um arquivo CSV.
     *
     * @param tabelas Lista de tabelas extraídas.
     * @param csvPath Caminho do arquivo CSV a ser gerado.
     */
    public static void exportarParaCsv(List<Table> tabelas, String csvPath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvPath))) {

            // Percorre todas as tabelas extraídas
            for (Table tabela : tabelas) {

                // Percorre todas as linhas da tabela
                for (int i = 0; i < tabela.getRowCount(); i++) {
                    List<RectangularTextContainer> linha = tabela.getRows().get(i);

                    // Constrói uma linha CSV separando por vírgula
                    StringBuilder linhaCsv = new StringBuilder();
                    for (int j = 0; j < linha.size(); j++) {
                        linhaCsv.append(linha.get(j).getText());

                        // Adiciona vírgula entre as colunas (exceto na última)
                        if (j < linha.size() - 1) {
                            linhaCsv.append(",");
                        }
                    }

                    // Escreve a linha no arquivo
                    writer.println(linhaCsv);
                }

                // Adiciona uma linha em branco entre tabelas
                writer.println();
            }

            System.out.println("CSV gerado com sucesso em: " + csvPath);

        } catch (IOException e) {
            System.err.println("Erro ao escrever o CSV: " + e.getMessage());
        }
    }
}
