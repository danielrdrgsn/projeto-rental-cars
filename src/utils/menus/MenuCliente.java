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
            System.out.print("Digite seu email de cliente: ");
            String email = input.nextLine();

            Usuario usuario = usuarioRepository.buscar(email);
            if (!(usuario instanceof Cliente)) {
                System.out.println("Cliente não encontrado.");
                return;
            }

            exibirMenuCliente();

            int opcao;
            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                input.nextLine();
                continue;
            }

            switch (opcao) {
                case 1 -> alugarVeiculo(input, usuario);
                case 2 -> devolverVeiculo(input, usuario);
                case 3 -> {
                    mostrarComprovanteDevolucoes(input, (Cliente) usuario);
                    ativo = false;
                }
                case 4 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuCliente() {
        System.out.println("==============================================");
        System.out.println("|         Cliente - Auto-Atendimento         |");
        System.out.println("==============================================");
        System.out.println("| 01 - Alugar                                |");
        System.out.println("| 02 - Devolver                              |");
        System.out.println("| 03 - Mostrar extrato de aluguéis           |");
        System.out.println("| 04 - Voltar                                |");
        System.out.println("==============================================");
        System.out.print("Opção escolhida: ");
    }
}
