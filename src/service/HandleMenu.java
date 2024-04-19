package service;

import java.util.List;
import java.util.Scanner;

import models.Usuario;
import models.Produto;
import utils.GerenciadorDeUsuarios;
import utils.GerenciadorDeProdutos;

public class HandleMenu {
    Scanner sc = new Scanner(System.in);
    GerenciadorDeUsuarios gs = new GerenciadorDeUsuarios();
    GerenciadorDeProdutos gp = new GerenciadorDeProdutos();

    public HandleMenu() {
        gs.verificaECria("usuarios.txt");
        gp.verificaECria("produtos.txt");
    }

    public void criarUsuario() {
        System.out.println("Digite o nome do usuário:");
        String nome = sc.next();
        System.out.println("Digite a senha do usuário:");
        String senha = sc.next();

        int id = getNextUserId();

        Usuario usuario = new Usuario(id, nome, senha);
        gs.adicionarUsuario(usuario);
    }

    public void editarUsuario() {
        System.out.println("Digite o ID do usuário:");
        int id = sc.nextInt();
        System.out.println("Digite o novo nome do usuário:");
        String novoNome = sc.next();
        System.out.println("Digite a nova senha do usuário:");
        String novaSenha = sc.next();

        gs.editarUsuario(id, novoNome, novaSenha);
    }

    public void deletarUsuario() {
        System.out.println("Digite o ID do usuário a ser deletado:");
        int id = sc.nextInt();
        gs.deletarUsuario(id);
    }

    public void listarUsuarios() {
        gs.listarUsuarios();
    }

    public void criarProduto() {
        System.out.println("Digite o nome do produto:");
        String nome = sc.next();
        System.out.println("Digite o preço do produto:");
        double preco = sc.nextDouble();

        int id = getNextProductId();

        Produto produto = new Produto(id, nome, preco);
        gp.adicionarProduto(produto);
    }

    public void editarProduto() {
        System.out.println("Digite o ID do produto:");
        int id = sc.nextInt();
        System.out.println("Digite o novo nome do produto:");
        String novoNome = sc.next();
        System.out.println("Digite o novo preço do produto:");
        double novoPreco = sc.nextDouble();

        gp.editarProduto(id, novoNome, novoPreco);
    }

    public void deletarProduto() {
        System.out.println("Digite o ID do produto a ser deletado:");
        int id = sc.nextInt();
        gp.deletarProduto(id);
    }

    public void listarProdutos() {
        gp.listarProdutos();
    }

    private int getNextUserId() {
        List<Usuario> usuarios = gs.lerUsuarios();
        int maxId = 0;
        for (Usuario usuario : usuarios) {
            int id = usuario.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }

    private int getNextProductId() {
        List<Produto> produtos = gp.lerProdutos();
        int maxId = 0;
        for (Produto produto : produtos) {
            int id = produto.getId();
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }
    
    public double somarPrecosProdutos() {
        return gp.somarPrecosProdutos();
    }

   
    public int contarProdutos() {
        return gp.contarProdutos();
    }

    
    public void trocarSenhaUsuario() {
        sc = new Scanner(System.in);
        System.out.println("Digite o ID do usuário:");
        int idUsuario = sc.nextInt();
        System.out.println("Digite a senha antiga:");
        String senhaAntiga = sc.next();
        System.out.println("Digite a nova senha:");
        String novaSenha = sc.next();
        gs.trocarSenhaUsuario(idUsuario, senhaAntiga, novaSenha);
    }
    
}
