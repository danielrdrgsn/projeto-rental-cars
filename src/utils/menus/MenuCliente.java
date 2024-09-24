package utils.menus;

import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MenuCliente {

    public static void mostrarMenuCliente(Scanner input) {
        boolean ativo = true;

        while(ativo) {
            int opcao;
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
                case 1 -> alugarVeiculo(); // TODO
                case 2 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void alugarVeiculo() {
        System.out.println("Bora alugar um veículo?!");
    }

}
