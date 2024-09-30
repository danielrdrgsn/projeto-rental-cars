import entities.locadora.Locadora;
import utils.persistencia.LocadoraUtils;
import utils.ConsoleColors;
import utils.menus.MenuPrincipal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Locadora locadora = new Locadora("* RENT-A-CAR *");

        try {
            LocadoraUtils.carregarDadosLocadora();
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }

        System.out.println(ConsoleColors.BLUE_BOLD + locadora.getNome() + ConsoleColors.RESET);

        MenuPrincipal.mostrarMenuPrincipal();

        try {
            LocadoraUtils.salvarDadosLocadora();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }
}
