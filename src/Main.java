import entities.locadora.Locadora;
import utils.persistencia.LocadoraUtils;
import utils.ConsoleColors;
import utils.menus.MenuPrincipal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Locadora locadora = new Locadora("* RENT-A-CAR *");
        LocadoraUtils.carregarDadosLocadora();

        System.out.println(ConsoleColors.BLUE_BOLD + locadora.getNome() + ConsoleColors.RESET);

        MenuPrincipal.mostrarMenuPrincipal();
    }
}