package utils.menus;

import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.menus.MenuAdm.mostrarMenuAdministrador;
import static utils.menus.MenuCliente.mostrarMenuCliente;

public abstract class MenuPrincipal {

    static Scanner input = new Scanner(System.in);

    public static void mostrarMenuPrincipal() {
        boolean ativo = true;

        while (ativo) {
            System.out.println("Bem vind@! Identifique-se: ");
            System.out.println("1. Administrador/Operador do Sistema");
            System.out.println("2. Cliente");
            System.out.println("0. Sair");

            int opcao;

            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 0;
            }

            switch (opcao) {
                case 1 -> mostrarMenuAdministrador(input);
                case 2 -> mostrarMenuCliente(input);
                case 0 -> {
                    System.out.println(ConsoleColors.CYAN_BOLD + "Saindo..." + ConsoleColors.RESET);
                    ativo = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public static void fecharScanner() {
        input.close();
    }
}
