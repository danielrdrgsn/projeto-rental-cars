package utils.menus;

import entities.agencia.Agencia;
import services.AgenciaService;
import utils.ConsoleColors;
import java.util.InputMismatchException;
import java.util.Scanner;

import static services.AgenciaService.*;

public class MenuCrudAgencias {

    public static void crudAgencias(Scanner input) {
        boolean ativo = true;

        while (ativo) {
            exibirMenu();

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
                case 1 -> adicionarAgencia(input);
                case 2 -> editarAgencia(input);
                case 3 -> buscarAgencia(input);
                case 4 -> removerAgencia(input);
                case 5 -> listarAgencias();
                case 6 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("==============================================");
        System.out.println("|                Menu de Opções              |");
        System.out.println("==============================================");
        System.out.println("| 01 - Cadastrar Agência                     |");
        System.out.println("| 02 - Editar Agência                        |");
        System.out.println("| 03 - Buscar Agência                        |");
        System.out.println("| 04 - Remover Agência                       |");
        System.out.println("| 05 - Listar Agências                       |");
        System.out.println("| 06 - Voltar ao Menu Anterior               |");
        System.out.println("==============================================");
        System.out.print("Opção escolhida: ");
    }
}
