package com.intuitivecare.datatransform;

import technology.tabula.Table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DataTransformer {
    public static void main(String[] args) {
        // Caminho para o PDF e CSV na pasta resources/files
        String caminhoPDF = Paths.get("src", "main", "resources", "files", "Anexo_I_Rol.pdf").toString();
        String caminhoCSV = Paths.get("src", "main", "resources", "files", "TabelaExtraida.csv").toString();

        // Extrai as tabelas
        List<Table> tabelas = PdfTableExtractor.extractTables(caminhoPDF);

        // Substitui "OD" e "AMB" por descrições completas
        substituirAbreviacoes(tabelas);

        // Exporta para CSV
        CsvExporter.exportarParaCsv(tabelas, caminhoCSV);

        // Compacta em ZIP
        compactarCSV(caminhoCSV);

        System.out.println("Transformação concluída com sucesso!");
    }

    /**
     * Compacta o CSV gerado em um arquivo ZIP no mesmo diretório.
     * Nome do ZIP: Teste_Ibrahim_El_Tassa.zip
     *
     * @param csvPath Caminho do arquivo CSV gerado
     */
    private static void compactarCSV(String csvPath) {
        // Caminho de saída do arquivo ZIP
        String zipPath = "src/main/resources/files/Teste_Ibrahim_El_Tassa.zip";

        try (
                // Cria um fluxo para escrever no arquivo ZIP
                FileOutputStream fos = new FileOutputStream(zipPath);
                ZipOutputStream zos = new ZipOutputStream(fos);
                FileInputStream fis = new FileInputStream(csvPath)
        ) {
            // Cria uma entrada no ZIP com o nome do CSV dentro do arquivo ZIP
            ZipEntry zipEntry = new ZipEntry("TabelaExtraida.csv"); // nome dentro do zip
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            // Lê e escreve no ZIP até o fim do arquivo
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }
            // Fecha a entrada atual no ZIP
            zos.closeEntry();
            System.out.println("CSV compactado com sucesso em: " + zipPath);
        } catch (Exception e) {
            System.err.println("Erro ao compactar o CSV: " + e.getMessage());
        }
    }

    /**
     * Substitui "OD" e "AMB" por suas descrições completas no momento da exportação.
     * OBS: A biblioteca Tabula não permite sobrescrever diretamente o conteúdo das células (não possui método setText).
     * Portanto, as substituições são feitas apenas no momento da leitura e escrita no CSV, sem alterar os objetos originais.
     */
    private static void substituirAbreviacoes(List<Table> tabelas) {
        for (Table tabela : tabelas) {
            for (int i = 0; i < tabela.getRowCount(); i++) {
                for (int j = 0; j < tabela.getColCount(); j++) {
                    // Apenas para debug: exibe o texto original e o modificado (opcional)
                    String textoOriginal = tabela.getCell(i, j).getText();
                    String textoSubstituido = textoOriginal
                            .replace("OD", "Odontologia")
                            .replace("AMB", "Ambulatorial");

                    if (!textoOriginal.equals(textoSubstituido)) {
                        System.out.println("Substituição: [" + textoOriginal + "] → [" + textoSubstituido + "]");
                    }

                    // Não é possível sobrescrever o texto da célula,
                    // por isso a substituição é feita apenas na hora da exportação para CSV.
                }
            }
        }
    }




}
