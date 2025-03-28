# Transformação de Dados - IntuitiveCare

Este repositório contém a solução para o desafio de **Transformação de Dados** do processo seletivo da **IntuitiveCare**.

## 📝 Descrição

O sistema realiza as seguintes etapas automaticamente:

1. Lê o arquivo PDF `Anexo_I_Rol.pdf` contendo a tabela de procedimentos.
2. Extrai **todas as tabelas** de **todas as páginas** do documento.
3. Substitui as abreviações:
    - `OD` → `Odontologia`
    - `AMB` → `Ambulatorial`
4. Salva os dados estruturados em um arquivo CSV: `TabelaExtraida.csv`.
5. Compacta o CSV em um arquivo ZIP chamado `Teste_Ibrahim_El_Tassa.zip`.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Maven
- Biblioteca [Tabula](https://github.com/tabulapdf/tabula-java) para extração de tabelas de PDFs
- Biblioteca nativa `java.util.zip` para compactação

---

## ▶️ Como Executar

```bash
# Clone o repositório
git clone https://github.com/IbraelTassa/intuitivecare-data-transformation.git

# Navegue até o projeto
cd intuitivecare-data-transformation

# Compile e execute com Maven ou pela sua IDE preferida
