package utils.menus;

import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.menus.MenuCrudAgencias.crudAgencias;
import static utils.menus.MenuCrudClientes.crudClientes;
import static utils.menus.MenuCrudVeiculos.crudVeiculos;

public abstract class MenuAdm {

    public static void mostrarMenuAdministrador(Scanner input) {
        boolean ativo = true;

        while (ativo) {
            exibirMenuAdministrador();

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
                case 1 -> crudClientes(input);
                case 2 -> crudAgencias(input);
                case 3 -> crudVeiculos(input);
                case 4 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void exibirMenuAdministrador() {
        System.out.println("==============================================");
        System.out.println("|          Administrador - Menu de Opções    |");
        System.out.println("==============================================");
        System.out.println("| 01 - CRUD - Clientes                       |");
        System.out.println("| 02 - CRUD - Agências                       |");
        System.out.println("| 03 - CRUD - Veículos                       |");
        System.out.println("| 04 - Voltar ao menu principal              |");
        System.out.println("==============================================");
        System.out.print("Opção escolhida: ");
    }

    public static void mostrarOpcoesCrud(String nomeCrud) {
        System.out.println("==============================================");
        System.out.println("|                CRUD - " + nomeCrud + "                |");
        System.out.println("==============================================");
        System.out.println("| 01 - Adicionar                            |");
        System.out.println("| 02 - Editar                               |");
        System.out.println("| 03 - Buscar                               |");
        System.out.println("| 04 - Buscar por parte do nome             |");
        System.out.println("| 05 - Remover                              |");
        System.out.println("| 06 - Listar                               |");
        System.out.println("| 07 - Voltar ao menu anterior              |");
        System.out.println("==============================================");
        System.out.print("Opção escolhida: ");
    }
}