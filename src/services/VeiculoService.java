package services;

import entities.veiculo.Veiculo;
import entities.veiculo.TipoVeiculo;
import repositories.VeiculoRepository;
import repositories.VeiculoRepositoryImpl;
import java.util.Scanner;

public class VeiculoService {

    private static final VeiculoRepository veiculoRepository = new VeiculoRepositoryImpl();

    public static void adicionar(Scanner input) {
        System.out.println("Digite a placa do veículo:");
        String placa = input.nextLine();
        System.out.println("Digite o modelo do veículo:");
        String modelo = input.nextLine();
        System.out.println("Digite o ano de fabricação:");
        int anoFabricacao = input.nextInt();
        input.nextLine();
        System.out.println("Digite a cor do veículo:");
        String cor = input.nextLine();
        System.out.println("Digite o tipo do veículo (CARRO, MOTO, CAMINHAO):");
        TipoVeiculo tipo = TipoVeiculo.valueOf(input.nextLine().toUpperCase());

        System.out.println("Digite o número da agência:");
        Integer numeroAgencia = input.nextInt();
        input.nextLine();

        boolean disponivel = true;
        Veiculo veiculo = new Veiculo(placa, modelo, anoFabricacao, disponivel);
        veiculo.setNumeroAgencia(numeroAgencia);
        veiculoRepository.adicionar(veiculo);
        System.out.println("Veículo adicionado: " + veiculo);
    }

    public static void editar(Scanner input) {
        System.out.println("Digite a placa do veículo a ser editado:");
        String placa = input.nextLine();
        Veiculo veiculo = veiculoRepository.buscar(placa);

        if (veiculo != null) {
            System.out.println("Digite o novo modelo do veículo:");
            String novoModelo = input.nextLine();
            veiculo.setModelo(novoModelo);
            System.out.println("Digite o novo ano de fabricação:");
            int novoAno = input.nextInt();
            input.nextLine();
            veiculo.setAnoFabricacao(novoAno);
            veiculoRepository.editar(veiculo);
            System.out.println("Veículo editado: " + veiculo);
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void buscar(Scanner input) {
        System.out.println("Digite a placa do veículo:");
        String placa = input.nextLine();
        Veiculo veiculo = veiculoRepository.buscar(placa);

        if (veiculo != null) {
            System.out.println("Veículo encontrado: ");
            System.out.println(veiculo.mostrarVeiculo());
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void remover(Scanner input) {
        System.out.println("Digite a placa do veículo que deseja remover:");
        String placa = input.nextLine();
        Veiculo veiculoRemovido = veiculoRepository.remover(placa);

        if (veiculoRemovido != null) {
            System.out.println("Veículo removido com sucesso.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void listar() {
        var veiculos = veiculoRepository.listar();
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
        } else {
            System.out.println("Lista de veículos:");
            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo.mostrarVeiculo());
            }
        }
    }
}
