package utils.menus;

import services.UsuarioService;
import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.Scanner;

import static utils.menus.MenuAdm.mostrarOpcoesCrud;
import static services.UsuarioService.adicionar;

public abstract class MenuCrudClientes {

    public static void crudClientes(Scanner input) {
        boolean ativo = true;

        while(ativo) {
            int opcao;
            mostrarOpcoesCrud("Clientes");

            try {
                opcao = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ConsoleColors.RED_BOLD + "Opção inválida. Apenas valores numéricos são aceitos." + ConsoleColors.RESET);
                ativo = false;
                opcao = 6;
            }

            switch (opcao) {
                case 1 -> adicionar(input);
                case 2 -> UsuarioService.editar();
                case 3 -> UsuarioService.buscar();
                case 4 -> UsuarioService.remover();
                case 5 -> UsuarioService.listar();
                case 6 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


}
