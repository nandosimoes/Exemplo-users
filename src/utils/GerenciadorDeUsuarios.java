package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class GerenciadorDeUsuarios {
    private static final String NOME_ARQUIVO = "usuarios.txt";
    
    public void verificaECria(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);

        if(arquivo.exists()) {
            System.out.println("------------------------------------");
            System.out.println("Banco de usuarios funcionando!");
        } else {
            try {
                arquivo.createNewFile();
                System.out.println("------------------------------------");
                System.out.println("Arquivo criado com sucesso!");
                System.out.println("------------------------------------");
            } catch (IOException e) {
                System.out.println("------------------------------------");
                System.out.println("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
                System.out.println("------------------------------------");
            }
        }
    }
    
    public void adicionarUsuario(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO, true))) {
            bw.write(usuario.toString()); 
            bw.newLine(); 
            System.out.println("------------------------------------");
            System.out.println("Usuario adicionado com sucesso!");
            System.out.println("------------------------------------");
        }   catch (IOException e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao escrever no arquivo: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }
    
    public List<Usuario> lerUsuarios() {
        List<Usuario> usuarios = new ArrayList<Usuario>();
    
        try (BufferedReader br = new BufferedReader(new FileReader(NOME_ARQUIVO))){
            
            String linha; 
            while((linha = br.readLine()) != null) {
                String[] partes = linha.split(";"); 
                usuarios.add(new Usuario(Integer.parseInt(partes[0]), partes[1], partes[2]));
            }
        } catch (IOException e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao ler o arquivo: " + e.getMessage());
            System.out.println("------------------------------------");
        }
        return usuarios;
    }
 
    
    public void deletarUsuario(int id) {
        if (id <= 0 || id != (int) id) { // Check if id is a positive number
            System.out.println("------------------------------------");
            System.out.println("ID inválido. O ID deve ser um número positivo.");
            System.out.println("------------------------------------");
            return;
        }

        try {
            List<Usuario> usuarios = lerUsuarios();
            boolean encontrado = usuarios.removeIf(usuario -> usuario.getId() == id);

            if (encontrado) {
                reescreverArquivo(usuarios);
                System.out.println("------------------------------------");
                System.out.println("Usuário deletado com sucesso!");
                System.out.println("------------------------------------");
            } else {
                System.out.println("------------------------------------");
                System.out.println("Usuário não encontrado.");
                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao deletar o usuário: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }

    public void editarUsuario(int id, String novoNome, String novaSenha) {
        if (id <= 0 || id != (int) id) { // Check if id is a positive number
            System.out.println("------------------------------------");
            System.out.println("ID inválido. O ID deve ser um número positivo.");
            System.out.println("------------------------------------");
            return;
        }

        try {
            List<Usuario> usuarios = lerUsuarios();
            boolean encontrado = false;

            for (Usuario usuario : usuarios) {
                if (usuario.getId() == id) {
                    usuario.setNome(novoNome);
                    usuario.setSenha(novaSenha);
                    encontrado = true;
                    break;
                }
            }

            if (encontrado) {
                reescreverArquivo(usuarios);
                System.out.println("------------------------------------");
              System.out.println("Usuário editado com sucesso!");
                System.out.println("------------------------------------");
            } else {
                System.out.println("------------------------------------");
                System.out.println("Usuário não encontrado.");
                System.out.println("------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao editar o usuário: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }


    
    public void reescreverArquivo(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for(Usuario usuario : usuarios) {
                bw.write(usuario.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao reescrever o arquivo: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }

    

    public void listarUsuarios() {
        try {
            List<Usuario> usuarios = lerUsuarios();
            if (!usuarios.isEmpty()) {
                System.out.println("------------------------------------");
                System.out.println("Lista de usuários:");
                for (Usuario usuario : usuarios) {
                    
                    System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Senha: " + usuario.getSenha());
                    System.out.println("------------------------------------");
                }
            } else {
                System.out.println("Nenhum usuário cadastrado");
            }
        } catch (Exception e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao listar os usuários: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }



    public void locUsuario(int id) {
        try {
            List<Usuario> usuarios = lerUsuarios();
            for (Usuario usuario : usuarios) {
            if(usuarios.isEmpty()) {
                System.out.println("------------------------------------");
                System.out.println("Nenhum usuário cadastrado");
                System.out.println("------------------------------------");
            }else if(usuario.getId() == id) {
                System.out.println("------------------------------------");
                System.out.println("Lista de usuários");
                    System.out.println("ID: " + usuario.getId() + ", Nome: " + "" + usuario.getNome() + ", Senha: " + usuario.getSenha());
                    System.out.println("------------------------------------");    
            }
            }
        } catch (Exception e) {
            System.out.println("------------------------------------");
            System.out.println("Ocorreu um erro ao localizar o usuário: " + e.getMessage());
            System.out.println("------------------------------------");
        }
    }
}
