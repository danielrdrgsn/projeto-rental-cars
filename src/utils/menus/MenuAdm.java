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

        while(ativo) {
            int opcao;
            System.out.println("Administrador - Escolha uma opção:");
            System.out.println("1. CRUD - Clientes");
            System.out.println("2. CRUD - Agências");
            System.out.println("3. CRUD - Veículos");
            System.out.println("4. Voltar ao menu principal");

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 4;
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

    public static void mostrarOpcoesCrud(String nomeCrud) {
        System.out.println("CRUD - " + nomeCrud);
        System.out.println("1. Adicionar");
        System.out.println("2. Editar");
        System.out.println("3. Buscar");
        System.out.println("4. Remover");
        System.out.println("5. Listar");
        System.out.println("6. Voltar ao menu anterior");
    }
}
