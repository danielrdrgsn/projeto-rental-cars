package services;

import repositories.UsuarioRepository;

import java.util.Scanner;

public class UsuarioService {

    private UsuarioRepository usuarioRepository = new UsuarioRepository();


    public static void adicionar(Scanner input) {

        System.out.println("Digite o tipo de cliente que deseja adicionar: ");
        System.out.println("1. Pessoa Física (PF)");
        System.out.println("2. Pessoa Jurídica (PJ)");
        System.out.println("3. Operador/Administrador");
        System.out.println("4. Sair");

        int opcao = input.nextInt();







    }

    public static void editar() {
        // TODO
    }

    public static void buscar() {
        // TODO
    }

    public static void remover() {
        // TODO
    }

    public static void listar() {
        // TODO
    }
}
