package com.diego.shopeeProfitManager.importacao;

import com.diego.shopeeProfitManager.service.DesempenhoMensalService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.diego.shopeeProfitManager.repository.ProdutoRepository;
import com.diego.shopeeProfitManager.model.Produto;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;


@Service
public class ExcelImportService {

    private final ProdutoRepository produtoRepository;
    private final DesempenhoMensalService desempenhoMensalService;

    public ExcelImportService(ProdutoRepository produtoRepository, DesempenhoMensalService desempenhoMensalService) {
        this.produtoRepository = produtoRepository;
        this.desempenhoMensalService = desempenhoMensalService;
    }

    public void importarExcel(MultipartFile arquivo) {

            Integer[] periodo = extrairPeriodo(arquivo.getOriginalFilename());

            Integer mes = periodo[0];
            Integer ano = periodo[1];

            System.out.println("Período de referência: " + mes + "/" + ano);

        try (InputStream inputStream = arquivo.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            System.out.println("Arquivo Excel carregado com sucesso!");

            // Seleciona a primeira aba
            Sheet sheet = workbook.getSheetAt(0);

            // Formata os valores das células como texto
            DataFormatter dataFormatter = new DataFormatter();

            System.out.println("Nome da aba: " + sheet.getSheetName());

            // Percorre todas as linhas da planilha, ignorando o cabeçalho
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row != null) {

                    String idVariacao = dataFormatter.formatCellValue(row.getCell(3));

                    // Identifica o Produto Pai
                    if (idVariacao.equals("-")) {

                        String idProduto = dataFormatter.formatCellValue(row.getCell(0));
                        String nomeProduto = dataFormatter.formatCellValue(row.getCell(1));

                        Long idProdutoMarketplace = Long.parseLong(idProduto);

                        String vendasTexto = dataFormatter.formatCellValue(row.getCell(9));
                        String unidadesTexto = dataFormatter.formatCellValue(row.getCell(18));

                        BigDecimal vendas = new BigDecimal(
                                vendasTexto.replace(".", "").replace(",", ".")
                        );

                        Integer unidades = Integer.parseInt(unidadesTexto);

                        Produto produto = new Produto();

                        produto.setIdProdutoMarketplace(idProdutoMarketplace);
                        produto.setNomeProduto(nomeProduto);

                        var produtoExistente = produtoRepository.findByIdProdutoMarketplace(
                                produto.getIdProdutoMarketplace()
                        );

                        if (produtoExistente.isPresent()) {

                            Produto produtoBanco = produtoExistente.get();

                            produtoBanco.setNomeProduto(produto.getNomeProduto());

                            produtoRepository.save(produtoBanco);

                            System.out.println("Produto atualizado!");

                        } else {

                            produtoRepository.save(produto);

                            System.out.println("Produto cadastrado!");

                        }

                        desempenhoMensalService.salvarOuAtualizarDesempenhoMensal(
                                idProdutoMarketplace,
                                mes,
                                ano,
                                vendas,
                                unidades
                        );

                        System.out.println("-----------------------------------");
                        System.out.println("ID Produto: " + idProduto);
                        System.out.println("Nome: " + nomeProduto);
                        System.out.println("Vendas Pagas: R$ " + vendas);
                        System.out.println("Unidades Vendidas: " + unidades);

                    }
                }
            }

        } catch (IOException e) {

            throw new RuntimeException("Erro ao ler o arquivo Excel.", e);
        }

    }
    private Integer[] extrairPeriodo(String nomeArquivo) {

        String regex = "(\\d{4})(\\d{2})(\\d{2})_(\\d{4})(\\d{2})(\\d{2})";

        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(nomeArquivo);

        if (matcher.find()) {

            Integer ano = Integer.parseInt(matcher.group(1));
            Integer mes = Integer.parseInt(matcher.group(2));

            return new Integer[]{mes, ano};
        }

        throw new RuntimeException("Não foi possível identificar o período do arquivo: " + nomeArquivo);
    }

}