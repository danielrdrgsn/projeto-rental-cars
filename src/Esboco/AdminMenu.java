import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

package Esboco;

public class AdminMenu {
    private static List<Agencia> agencias = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Aluguel> alugueis = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        while (executando) {
            System.out.println("\nMENU - ADMINISTRADOR");
            System.out.println("\n1. Agências"); // ELOISE
            System.out.println("2. Veículos"); // THIAGO
            System.out.println("3. Aluguéis"); // VINICIUS
            System.out.println("4. Clientes"); // SAMUEL
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarAgencia(sc);
                    break;
                case 2:
                    cadastrarCliente(sc);
                    break;
                case 3:
                    cadastrarVeiculo(sc);
                    break;
                case 4:
                    alugarVeiculo(sc);
                    break;
                case 5:
                    devolverVeiculo(sc);
                    break;
                case 6:
                    listarVeiculosDisponiveis();
                    break;
                case 7:
                    listarClientes();
                    break;
                case 8:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
        System.out.println("Saindo...");
    }
}