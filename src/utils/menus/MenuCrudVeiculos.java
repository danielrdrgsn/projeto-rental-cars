package utils.menus;

import services.VeiculoService;
import utils.ConsoleColors;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuCrudVeiculos {

    public static void crudVeiculos(Scanner input) {
        boolean ativo = true;

        while (ativo) {
            int opcao;

            // Menu estilo ASCII para Veículos
            System.out.println(ConsoleColors.BLUE_BOLD + "+-------------------------------------+");
            System.out.println("|          MENU DE VEÍCULOS           |");
            System.out.println("+-------------------------------------+" + ConsoleColors.RESET);
            System.out.println("| 1 - Adicionar Veículo               |");
            System.out.println("| 2 - Editar Veículo                  |");
            System.out.println("| 3 - Buscar Veículo                  |");
            System.out.println("| 4 - Remover Veículo                 |");
            System.out.println("| 5 - Listar Veículos                 |");
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
                case 1 -> VeiculoService.adicionar(input);
                case 2 -> VeiculoService.editar(input);
                case 3 -> {
                    System.out.println("Digite o código do veículo:");
                    Integer codigo = input.nextInt();
                    input.nextLine();
                    VeiculoService.buscar(input);
                }
                case 4 -> VeiculoService.remover(input);
                case 5 -> VeiculoService.listar();
                case 6 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
