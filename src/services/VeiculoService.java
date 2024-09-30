package services;

import entities.locadora.Locadora;
import entities.veiculo.*;
import repositories.VeiculoRepository;

import java.util.Scanner;

public class VeiculoService {

    private static final VeiculoRepository veiculoRepository = new VeiculoRepository();

    public static void adicionar(Scanner input) {
        System.out.println("Digite a placa do veiculo: ");
        String placaVeiculo = input.nextLine();

        System.out.println("Digite o modelo do veiculo: ");
        String modeloVeiculo = input.nextLine();

        System.out.println("Digite o ano do veiculo: ");
        int anoFabricacao = input.nextInt();
        input.nextLine();

        System.out.println("Digite a cor do veiculo: ");
        String corVeiculo = input.nextLine();

        System.out.println("Qual tipo de Veiculo você gostaria:");
        for (int i = 0; i < TipoVeiculo.values().length; i++) {
            System.out.println((i + 1) + ". " + TipoVeiculo.values()[i].getDescricao());
        }
        int escolhaVeiculo = input.nextInt();
        input.nextLine();

        Integer idVeiculo = obterUltimoIdVeiculo() + 1;

        switch (escolhaVeiculo) {
            case 1 -> {
                veiculoRepository.adicionar(new Carro(idVeiculo, placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, true););
                System.out.println("Carro cadastrado com sucesso!");
            }
            case 2 -> {
                new Moto(idVeiculo, placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, true);
                System.out.println("Moto cadastrada com sucesso!");
            }
            case 3 -> {
                new Caminhao(idVeiculo, placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, true);
                System.out.println("Caminhão cadastrado com sucesso!");
            }
            default -> System.out.println("Tipo de veículo desconhecido.");
        };
    }


    public static void editar(Scanner input) {
        //TODO
    }

    public static void buscar(Scanner input) {
        System.out.println("Qual veiculo que voce quer buscar? digite a placa");
        String placaVeiculo = input.nextLine();
        Veiculo veiculo = veiculoRepository.buscar(placaVeiculo);
        if (veiculo != null) {
            veiculo.mostrarVeiculo();
        }
    }

    public static void remover(Scanner input) {
        //TODO
    }

    public static void listar(Scanner input) {
        //TODO
    }

    public static Veiculo buscarVeiculo(String placa) {
        return veiculoRepository.buscar(placa);
    }

    public static Veiculo buscarVeiculo(Integer codigo) {
        return veiculoRepository.buscarPorId(codigo);
    }

    private static Integer obterUltimoIdVeiculo() {
        Integer ultimoIdVeiculo = -1;
        for (Veiculo v : Locadora.getVeiculos()) {
            if (v.getId() > ultimoIdVeiculo) {
                ultimoIdVeiculo = v.getId();
            }
        }
        return ultimoIdVeiculo;
    }
}
