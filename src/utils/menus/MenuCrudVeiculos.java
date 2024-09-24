package utils.menus;

import services.VeiculoService;
import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.menus.MenuAdm.mostrarOpcoesCrud;

public class MenuCrudVeiculos {

    public static void crudVeiculos(Scanner input) {
        boolean ativo = true;

        while(ativo) {
            int opcao;
            mostrarOpcoesCrud("Veículos");

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 6;
            }

            switch (opcao) {
                case 1 -> VeiculoService.adicionar();
                case 2 -> VeiculoService.editar();
                case 3 -> VeiculoService.buscar();
                case 4 -> VeiculoService.remover();
                case 5 -> VeiculoService.listar();
                case 6 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
