package utils.menus;

import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

import static services.VeiculoService.*;
import static utils.menus.MenuAdm.mostrarOpcoesCrud;

public class MenuCrudVeiculos {

    public static void crudVeiculos(Scanner input) {
        boolean ativo = true;

        while(ativo) {
            int opcao;
            mostrarOpcoesCrud("Veículos");

            try {
                opcao = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 6;
            }

            switch (opcao) {
                case 1 -> adicionar(input);
                case 2 -> editar(input);
                case 3 -> buscar(input);
                case 4 -> buscarVeiculosPorModelo(input);
                case 5 -> remover(input);
                case 6 -> listar(input);
                case 7 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
