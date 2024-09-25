package utils.menus;

import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

import static services.AgenciaService.*;
import static utils.menus.MenuAdm.mostrarOpcoesCrud;

public class MenuCrudAgencias {

    public static void crudAgencias(Scanner input) {
        boolean ativo = true;

        while(ativo) {
            int opcao;
            mostrarOpcoesCrud("Agências");

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 6;
            }

            switch (opcao) {
                case 1 -> adicionar(input);
                case 2 -> editar(input);
                case 3 -> buscar(input);
                case 4 -> remover(input);
                case 5 -> listar(input);
                case 6 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
