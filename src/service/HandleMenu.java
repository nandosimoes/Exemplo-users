package service;

import java.util.List;
import java.util.Scanner;

import models.Usuario;
import utils.GerenciadorDeUsuarios;

public class HandleMenu {
	
	Scanner sc = new Scanner(System.in);
	
	//Gerenciador
	GerenciadorDeUsuarios gs = new GerenciadorDeUsuarios();
	
	// Construto vazio
	public HandleMenu() {
		//Toda vez que a classe menu, for instanciada, o nosso arquivo sera verificado
		gs.verificaECria("usuarios.txt");
	}
	public void criar() {
		System.out.println("Digite o nome:");
		String nome = sc.next();
		System.out.println("Digite sua Senha:");
		String senha = sc.next();
		
		int id = getNextId();
		
		Usuario u = new Usuario(1, nome, senha);
		gs.adicionarUsuario(u);
		
	}
	
	public void editar() {
		System.out.println("Digite oID do usuario:");
		int id = sc.nextInt();
		System.out.println("Digite o novo nome:");
		String nome = sc.next();
		System.out.println("Digite a nova semha:");
		String senha = sc.next();
		
		gs.editarUsuario(id, nome, senha);
	}
	
	public void deletar () {
		System.out.println("Digite o ID do usuario a ser deletado:");
		int id = sc.nextInt();
		gs.deletarUsuario(id);
	}
	public void listar() {
		gs.listarUsuarios();
	}

	private int getNextId() {
		List<Usuario> usuarios = gs.lerUsuarios();
		int maxId = 0;
		for(Usuario usuario : usuarios) {
			int id = usuario.getId();
			
			if(id > maxId) {
				maxId = id;	
			}
		}
		return maxId + 1;
	}

    public void listUnico() {
	System.out.println("Digite o ID do usuário");
	int id = sc.nextInt();
	gs.locUsuario(id);
}
}
