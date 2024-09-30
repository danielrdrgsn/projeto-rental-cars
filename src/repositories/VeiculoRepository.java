package repositories;

import entities.veiculo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static utils.menus.MenuPrincipal.input;

public class VeiculoRepository implements Repositorio<Veiculo, String> {

    private List<Veiculo> veiculos = new ArrayList<>();

    @Override
    public void adicionar(Veiculo veiculo) {


        Scanner input = new Scanner(System.in);

        System.out.println("Digite a placa do veiculo:");
        String placaVeiculo = input.nextLine();

        System.out.println("Digite o modelo do veiculo:");
        String modeloVeiculo = input.nextLine();

        System.out.println("Digite o ano do veiculo:");
        int anoFabricacao = input.nextInt();
        input.nextLine(); // Consumir a nova linha

        System.out.println("Digite a cor do veiculo:");
        String corVeiculo = input.nextLine();

        System.out.println("O veículo está disponível? (true/false):");
        boolean disponivel = input.nextBoolean();
        input.nextLine(); // Consumir a nova linha

        System.out.println("Digite o numero da agencia do veiculo:");
        int agenciaVeiculo = input.nextInt();
        input.nextLine(); // Consumir a nova linha

        System.out.println("Qual tipo de Veiculo você gostaria:");
        for (int i = 0; i < TipoVeiculo.values().length; i++) {
            System.out.println((i + 1) + ". " + TipoVeiculo.values()[i].getDescricao());
        }

        int escolhaVeiculo = input.nextInt();
        input.nextLine(); // Consumir a nova linha

        Veiculo novoVeiculo = null;

        switch (escolhaVeiculo) {
            case 1:
                novoVeiculo = new Carro(placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, disponivel, agenciaVeiculo);
                System.out.println("Carro cadastrado com sucesso!");
                break;
            case 2:
                novoVeiculo = new Moto(placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, disponivel, agenciaVeiculo);
                System.out.println("Moto cadastrada com sucesso!");
                break;
            case 3:
                novoVeiculo = new Caminhao(placaVeiculo, modeloVeiculo, anoFabricacao, corVeiculo, disponivel, agenciaVeiculo);
                System.out.println("Caminhão cadastrado com sucesso!");
                break;
            default:
                System.out.println("Escolha inválida.");
                break;
        }

        if (novoVeiculo != null) {
            veiculos.add(novoVeiculo);
        }

    }

    @Override
    public void editar(Veiculo veiculo, String placa) {

        Scanner input = new Scanner(System.in);

        System.out.println("Qual o placa do veiculo que voce quer atualizar?");
        String placaVeiculo = input.nextLine();

        for (Veiculo v : veiculos) {
            if (placaVeiculo.equals(v.getPlaca())) {
                System.out.println("Qual a placa do veiculo?");
                v.setPlaca(placaVeiculo);
                System.out.println("Qual a modelo do veiculo?");
                v.setModelo(veiculo.getModelo());
                System.out.println("Qual a ano de fabricação do veiculo?");
                v.setAnoFabricacao(veiculo.getAnoFabricacao());
                System.out.println("Qual a cor do veiculo?");
                v.setCor(veiculo.getCor());
                System.out.println("Qual o modelo do veiculo?");
                v.setModelo(veiculo.getModelo());
            }
        }
    }

    public void remover(Veiculo veiculo) {

        Scanner input = new Scanner(System.in);
        System.out.println("Qual o veiculo que voce quer remover? digite a placa");

        String placaVeiculo = input.nextLine();

        if (placaVeiculo.equals(veiculo.getPlaca())) {
            veiculos.remove(veiculo);
        }
    }


    @Override
    public Veiculo buscar(String placa) {

        Scanner input = new Scanner(System.in);

        System.out.println("Qual veiculo que voce quer buscar? digite a placa");
        String placaVeiculo = input.nextLine();

        for (Veiculo v : veiculos) {
            if (placaVeiculo.equals(v.getPlaca())) {
                return v;
            }
        }
        return null;
    }


    public void listarVeiculos() {
        for (Veiculo v : veiculos) {
            System.out.println(v);
        }
    }

    public List<Veiculo> listar() {
        return List.of();
    }
}
