package utils.menus;

import services.UsuarioService;
import utils.ConsoleColors;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static services.UsuarioService.*;
import static utils.menus.MenuAdm.mostrarOpcoesCrud;

public abstract class MenuCrudClientes {

    public static void crudClientes(Scanner input) {
        boolean ativo = true;

        while(ativo) {
            int opcao;
            mostrarOpcoesCrud("Clientes");

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
                case 2 -> UsuarioService.editar();
                case 3 -> buscar(input);
                case 4 -> remover(input);
                case 5 -> listar();
                case 6 -> ativo = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
