package utils.menus;

import entities.usuario.Cliente;
import entities.usuario.Usuario;
import repositories.UsuarioRepository;
import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

import static services.AluguelService.*;

public abstract class MenuCliente {

    private static final UsuarioRepository usuarioRepository = new UsuarioRepository();

    public static void mostrarMenuCliente(Scanner input) {
        boolean ativo = true;

        while (ativo) {
            System.out.println("Digite seu email de cliente: ");
            String email = input.nextLine();

            Usuario usuario = usuarioRepository.buscar(email);
            if (!(usuario instanceof Cliente)) {
                System.out.println("Cliente não encontrado.");
                return;
            }

            int opcao;
            System.out.println("Cliente - Auto-Atendimento:");
            System.out.println("1. Alugar");
            System.out.println("2. Devolver");
            System.out.println("3. Mostrar extrato de aluguéis");
            System.out.println("4. Voltar");

            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 4;
            }

            switch (opcao) {
                case 1 -> alugarVeiculo(input, usuario);
                case 2 -> devolverVeiculo(input, usuario);
                case 3 -> {
                    mostrarComprovanteDevolucoes(input, ((Cliente) usuario));
                    ativo = false;
                }
                case 4 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

}
