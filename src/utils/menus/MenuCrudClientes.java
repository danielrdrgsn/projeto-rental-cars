package utils.menus;

import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuCrudClientes {

    public static void crudClientes(Scanner input) {
        boolean ativo = true;

        while (ativo) {
            int opcao;

            // Menu estilo ASCII
            System.out.println(ConsoleColors.GREEN_BOLD + "+-------------------------------------+");
            System.out.println("|           MENU DE CLIENTES          |");
            System.out.println("+-------------------------------------+" + ConsoleColors.RESET);
            System.out.println("| 1 - Adicionar Cliente               |");
            System.out.println("| 2 - Editar Cliente                  |");
            System.out.println("| 3 - Buscar Cliente                  |");
            System.out.println("| 4 - Remover Cliente                 |");
            System.out.println("| 5 - Listar Clientes                 |");
            System.out.println("| 6 - Voltar ao Menu Anterior         |");
            System.out.println("+-------------------------------------+");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                input.nextLine();
                continue;
            }

            switch (opcao) {
                case 1 -> ClienteService.adicionar(input);
                case 2 -> ClienteService.editar(input);
                case 3 -> {
                    System.out.println("Digite o código do cliente:");
                    Integer codigo = input.nextInt();
                    input.nextLine();
                    ClienteService.buscarCliente(codigo);
                }
                case 4 -> ClienteService.remover(input);
                case 5 -> ClienteService.listarClientes();
                case 6 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
