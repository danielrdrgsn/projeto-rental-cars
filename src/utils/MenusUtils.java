package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenusUtils {

    static Scanner input = new Scanner(System.in);

    public static void mostrarMenuPrincipal() {
        boolean ativo = true;

        while(ativo) {
            System.out.println("Bem vind@! Identifique-se: ");
            System.out.println("1. Administrador/Operador do Sistema");
            System.out.println("2. Cliente");
            System.out.println("0. Sair");

            int opcao;

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 0;
            }

            switch (opcao) {
                case 1 -> mostrarMenuAdministrador();
                case 2 -> mostrarMenuCliente();
                case 0 -> {
                    System.out.println("Saindo...");
                    ativo = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public static void mostrarMenuAdministrador() {
        boolean ativo = true;
        int opcao;

        while(ativo) {
            System.out.println("Administrador - Escolha uma opção:");
            System.out.println("1. Clientes");
            System.out.println("2. Agências");
            System.out.println("3. Veículos");
            System.out.println("4. Voltar");

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 4;
            }

            switch (opcao) {
                case 1 -> mostrarCrudCliente();
                case 2 -> mostrarCrudAgencias();
                case 3 -> mostrarCrudVeiculos();
                case 4 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void mostrarCrudVeiculos() {
        System.out.println("CRUD Veículos");
    }

    private static void mostrarCrudAgencias() {
        System.out.println("CRUD Agencias");
    }

    public static void mostrarCrudCliente() {
        System.out.println("CRUD Clientes");
    }

    private static void mostrarMenuCliente() {
        boolean ativo = true;
        int opcao;

        while(ativo) {
            System.out.println("Cliente - Auto-Atendimento:");
            System.out.println("1. Alugar");
            System.out.println("2. Voltar");

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 0;
            }

            switch (opcao) {
                case 1 -> alugarVeiculo();
                case 2 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void alugarVeiculo() {
        System.out.println("Bora alugar um carro?!");

    }
}
