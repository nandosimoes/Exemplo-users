package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Produto;

public class GerenciadorDeProdutos {
    private static final String NOME_ARQUIVO = "produtos.txt";

    public void verificaECria(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        try {
            if (arquivo.exists()) {
                System.out.println("Banco de produtos funcionando!");
                System.out.println("------------------------------------");
            } else {
                if (nomeArquivo.isEmpty()) {
                    throw new IllegalArgumentException("Nome do arquivo não pode ser vazio.");
                }
                arquivo.createNewFile();
                System.out.println("------------------------------------");
                System.out.println("Arquivo de produtos criado com sucesso!");
                System.out.println("------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao criar ou verificar o arquivo de produtos: " + e.getMessage());
            System.out.println("------------------------------------");
        } catch (IllegalArgumentException e) {
            System.err.println("------------------------------------");
            System.err.println("Nome do arquivo inválido: " + e.getMessage());
            System.err.println("------------------------------------");
        }
    }


    public void adicionarProduto(Produto produto) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            bw.write(produto.toString());
            bw.newLine();
            System.out.println("------------------------------------");
            System.out.println("Produto adicionado com sucesso!");
            System.out.println("------------------------------------");
        } catch (IOException e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao adicionar o produto: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }

    public List<Produto> lerProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                produtos.add(new Produto(Integer.parseInt(partes[0]), partes[1], Double.parseDouble(partes[2])));
            }
        } catch (IOException e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao ler o arquivo de produtos: " + e.getMessage());
            System.out.println("------------------------------------");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("------------------------------------");
            System.out.println("Formato de arquivo inválido: " + e.getMessage());
            System.out.println("------------------------------------");
        }
        return produtos;
    }

    public void deletarProduto(int id) {
        List<Produto> produtos = lerProdutos();
        if (id <= 0) {
            System.err.println("------------------------------------");
            System.err.println("ID inválido. O ID deve ser um número positivo.");
            System.err.println("------------------------------------");
        } else {
            if (produtos.removeIf(produto -> produto.getId() == id)) {
                reescreverArquivo(produtos);
                System.out.println("------------------------------------");
                System.out.println("Produto deletado com sucesso");
                System.out.println("------------------------------------");
            } else {
                System.out.println("------------------------------------");
                System.out.println("Produto não encontrado");
                System.out.println("------------------------------------");
            }
        }
    }

    public void editarProduto(int id, String novoNome, double novoPreco) {
        List<Produto> produtos = lerProdutos();
        if (id <= 0) {
            System.err.println("------------------------------------");
            System.err.println("ID inválido. O ID deve ser um número positivo.");
            System.err.println("------------------------------------");
        } else {
            boolean isNumericId = true;
            try {
                Integer.parseInt(String.valueOf(id));
            } catch (NumberFormatException e) {
                isNumericId = false;
            }

            if (isNumericId) {
                boolean encontrado = false;
                for (Produto produto : produtos) {
                    if (produto.getId() == id) {
                        produto.setNome(novoNome);
                        produto.setPreco(novoPreco);
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado) {
                    reescreverArquivo(produtos);
                    System.out.println("------------------------------------");
                    System.out.println("Produto editado com sucesso!");
                    System.out.println("------------------------------------");
                } else {
                    System.out.println("------------------------------------");
                    System.out.println("Produto não encontrado");
                    System.out.println("------------------------------------");
                }
            } else {
                System.err.println("------------------------------------");
                System.err.println("ID inválido. O ID deve ser um número.");
                System.err.println("------------------------------------");
            }
        }
    }


    public void listarProdutos() {
        List<Produto> produtos = lerProdutos();
        if (produtos.isEmpty()) {
            System.out.println("------------------------------------");
            System.out.println("Lista de produtos vazia");
            System.out.println("------------------------------------");
        } else {
            System.out.println("------------------------------------");
            System.out.println("Lista de produtos:");
            for (Produto produto : produtos) {
                System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Preço: " + produto.getPreco());
                System.out.println("------------------------------------");
            }
        }
    }

    private void reescreverArquivo(List<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Produto produto : produtos) {
                bw.write(produto.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao reescrever o arquivo de produtos: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }

    public double somarPrecosProdutos() {
        double totalPrecos = 0;
        List<Produto> produtos = lerProdutos();
        for (Produto produto : produtos) {
            totalPrecos += produto.getPreco();
        }
        return totalPrecos;
    }

    public int contarProdutos() {
        List<Produto> produtos = lerProdutos();
        return produtos.size();
    }

}
