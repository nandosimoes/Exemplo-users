package sistema;

import java.util.InputMismatchException;
import java.util.Scanner;

import service.HandleMenu;

public class Sistema {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        HandleMenu hm = new HandleMenu();
        int opcao = 0;
        do {
            try {
                System.out.print("\nMENU USUARIO\n\n1 - Criar Usuário\n2 - Editar Usuário\n3 - Deletar Usuário\n"
                        + "4 - Listar Usuários\n---------------------\n\nMENU PRODUTO\n\n5 - Criar Produto\n6 - Editar Produto\n"
                        + "7 - Deletar Produto\n8 - Listar Produtos\n---------------------\n"
                        + "\nEXTRAS\n\n10 - Somar Preços dos Produtos\n11 - Contar Produtos\n12 - Trocar Senha do Usuário\n---------------------\n9 - Sair\n---------------------\n");

                opcao = sc.nextInt();

                switch (opcao) {
                    case 1: {
                        hm.criarUsuario();
                        break;
                    }
                    case 2: {
                        hm.editarUsuario();
                        break;
                    }
                    case 3: {
                        hm.deletarUsuario();
                        break;
                    }
                    case 4: {
                        hm.listarUsuarios();
                        break;
                    }
                    case 5: {
                        hm.criarProduto();
                        break;
                    }
                    case 6: {
                        hm.editarProduto();
                        break;
                    }
                    case 7: {
                        hm.deletarProduto();
                        break;
                    }
                    case 8: {
                        hm.listarProdutos();
                        break;
                    }
                    case 10: {
                        double totalPrecos = hm.somarPrecosProdutos();
                        System.out.println("Total de preços dos produtos: " + totalPrecos);
                        break;
                    }
                    case 11: {
                        int totalProdutos = hm.contarProdutos();
                        System.out.println("Total de produtos cadastrados: " + totalProdutos);
                        break;
                    }
                    case 12: {
                        hm.trocarSenhaUsuario();
                        break;
                    }
                    case 9: {
                        System.out.println("Saindo do sistema...");
                        break;
                    }
                    default:
                        System.err.println("----------------");
                        System.err.println("Opção Inválida");
                        System.err.println("----------------");
                        break;
                }
            } catch (InputMismatchException e) {
                System.err.println("------------------------------------------------------------");
                System.err.println("Por favor, digite um número correspondente à opção desejada.");
                System.err.println("------------------------------------------------------------");
                sc.nextLine();
            }

        } while (opcao != 9);
        sc.close();

    }
}
